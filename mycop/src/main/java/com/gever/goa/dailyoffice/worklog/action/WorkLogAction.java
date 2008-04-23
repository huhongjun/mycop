package com.gever.goa.dailyoffice.worklog.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogAdviceDAO;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogDao;
import com.gever.goa.dailyoffice.worklog.dao.WorkLogFactory;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogVO;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.goa.util.ConstantSet;
import com.gever.goa.web.util.TemplateUtils;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.pager.PageHelper;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.organization.model.impl.DefaultUser;
import com.gever.util.Codes;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.util.log.Log;

/**
 * <p>Title: 工作日志控制器</p>
 * <p>Description: 工作日志控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorkLogAction extends BaseAction {
    WorkLogDao workLogDao = null; //定义接口
    Log log = Log.getInstance(WorkLogAction.class);

    public WorkLogAction() {
    }

    /**
     * 初始化页面数据
     * @param cfg 当前的vo对象
     * @throws DefaultException
     * @throws Exception
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //得到form变量
        workLogDao = WorkLogFactory.getInstance().createWorkLog(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) workLogDao); //cfg--Goa Action获取类--设置超类中的Dao
        cfg.getBaseDao().setIsIdMng(false);
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null) {
            myForm.setVo(new WorkLogVO()); //设置VO到Form中
        }
    }

    /**
     * 到清单页面--这里是重载BaseAction中的toList方法
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
         Exception {
         HttpServletRequest request = cfg.getRequest();
         HttpSession session = cfg.getSession();
         WorkLogForm workLogForm = (WorkLogForm) cfg.getBaseForm();
         String curUser = cfg.getBaseForm().getUserId(); //取得当前用户ID
         String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
         boolean isInsertTodayWorkLog = workLogDao.findCurPsnCurDayWorkLogIsExist(curUser, curDate);
         if (isInsertTodayWorkLog == true) {
             workLogForm.setIsInsertFlag("1"); //设置已经写过当天日志-1-已写过-0-未写过
         } else {
             workLogForm.setIsInsertFlag("0");
         }
         int numOfPage = workLogDao.findLogNumOfEveryPage();
         String queryFlag = request.getParameter("queryFlag");
         StringBuffer sbSqlWhere = new StringBuffer();
         sbSqlWhere.append(" where 1=1 ");
         if ("true".equals(queryFlag)) { //该处可能还需要考虑一下日期直接比较是否可行
             String searchBeginTime = ( (WorkLogForm) cfg.getBaseForm()).getSearchBeginTime();
             String searchEndTime = ( (WorkLogForm) cfg.getBaseForm()).getSearchEndTime();
             if (!StringUtils.isNull(searchEndTime)) { //时间不为空时才进行查询
                 sbSqlWhere.append(" and FILL_DATE <= " + this.toDate(searchEndTime) + " ");
             }
             if (!StringUtils.isNull(searchBeginTime)) {
                 sbSqlWhere.append(" and FILL_DATE >= " + toDate(searchBeginTime) + " ");
             }
         }else{
             String eDate = DateTimeUtils.getCurrentDate();
             String bDate = DateTimeUtils.getDateSum(eDate, -7);
             sbSqlWhere.append(" and fill_date between ");
             sbSqlWhere.append(this.toDate(bDate));
             sbSqlWhere.append(" and ");
             sbSqlWhere.append(this.toDate(eDate));
             sbSqlWhere.append(" ");
         }
         if(this.isOracle()){
             sbSqlWhere.append(" and USER_CODE = " + Integer.parseInt(curUser) +
                               " ");
             sbSqlWhere.append(" and  rownum<= " + numOfPage);
             sbSqlWhere.append(" order by  FILL_DATE DESC "); //按填写日期倒叙排列
         }
         else{
             sbSqlWhere.append(" and USER_CODE = " + Integer.parseInt(curUser) +
                               " ");
             sbSqlWhere.append(" order by  FILL_DATE DESC "); //按填写日期倒叙排列
         }

         WorkLogVO vo = (WorkLogVO) workLogForm.getVo();
         cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
        // cfg.getBaseDao().setSqlWhere(sbSqlWhere.toString());
         super.toList(cfg);
//       workLogForm.setListLog(workLogDao.queryAll(vo));
         return this.FORWORD_LIST_PAGE;
     }


    /**
     * 到列表页面处理排序功能--这里是重载了超类里面的doOrderBy方法
     * 这个方法是为了处理排序sql语句中不支持fetch First 语句的情况
     * 通过页面屏蔽掉了对该方法的调用，该方法暂时不生效
     * 如果设计人员要求一定要加入排序功能时，还是需要加入该方法的
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String doOrderBy(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String pageType = "";
        //VOInterface vo = cfg.getBaseForm().getVo();
        //BaseForm bForm = cfg.getBaseForm();
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm();
        WorkLogVO vo = (WorkLogVO) myForm.getVo();
        PageHelper pHelper = cfg.getPageHelper();
        cfg.getBaseDao().setOrderColumn(cfg.getBaseForm().getOrderFld());
        cfg.getBaseDao().setOrderType(cfg.getBaseForm().getOrderType());
        cfg.getBaseDao().setSqlWhere(cfg.getBaseForm().getSqlWhere());
        long lngCnt = cfg.getBaseDao().queryByCount(vo); //统计
        setPageNumber(cfg);

        String strPage = pHelper.getPageStr(cfg, lngCnt, pageType); //得到当前页
        myForm.setCurPage(NumberUtil.stringToLong(strPage, 1l)); //备份
        return FORWORD_LIST_PAGE;
    }

    /**
     * 到修改页面--这里是重载BaseAction中的toEdit方法
     * 以便初始化新增页面上的一些常量数据（不用输入，只需读取）
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
        Exception {
        super.toEdit(cfg);
        HttpServletRequest request = cfg.getRequest();
        HttpSession session = cfg.getSession();
        User user = new DefaultUser();
        UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //得到form变量
        if (!myForm.getActionType().equals(this.MODIFY_ACTION)) { //如果报表模板还没有设置
            IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance().createIsStandardModel(super.dbData);
            IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.getTemplate(ConstantSet.WorkLogTemplate);
            myForm.setReportTemplateName(ismVO.getFile_path());
        }
        else{
          WorkLogVO vo = (WorkLogVO) myForm.getVo();
          byte[] bcell = vo.getLog_content();
          String strPath = super.getServlet().getServletContext().getRealPath("/");
          //华表文件名
          String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
          myForm.setCellname(strFilename);
        }
        WorkLogVO vo = (WorkLogVO) myForm.getVo();
        String curUser = cfg.getBaseForm().getUserId(); //取得当前用户--对应创建者-user_code
        String curDeptCodes = cfg.getBaseForm().getCurDeptCodes();
        String curDeptNames = cfg.getBaseForm().getCurDeptNames();
        String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
        //String curTime = DateTimeUtils.getCurrentTime();
        String curTime = DateTimeUtils.getCurrentDateTime();
        String initUserCode = vo.getUser_code();
        if ("".equals(initUserCode) || initUserCode == null) {
            //如果初始用户为空(新增的情况)则设置当前用户为创建人
            vo.setUser_code(curUser);
        }

        user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
        String curName = user.getName();
        vo.setCreate_time(curTime); 	//创建时间
        vo.setFill_date(curDate); 		//填写日期
        vo.setDept_code(curDeptCodes); 	//设置当前用户对应的部门
        vo.setUser_code(curUser);
        myForm.setUsername(curName); 	//设置页面显示的用户中文名
        myForm.setDeptnames(curDeptNames);

        //在将部门代码进行转化的过程中还需要同样的获取部门名称用逗号隔开的字符串
        cfg.getBaseForm().setVo(vo);
        String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
            "actionType"));
        if ("modify".equals(actionType)) { //如果是修改操作
            makeCellField(cfg);
        }
        if (!this.MODIFY_ACTION.equals(actionType)) {
            vo.setSerial_num(IdMng.getModuleID(myForm.getUserId()));
            cfg.getBaseForm().setActionType(super.ADD_ACTION);
        }
        WorkLogAdviceDAO adviceDao = WorkLogFactory.getInstance().
            createWorkLogAdvice(this.dbData);
        List adviceList = adviceDao.getAdviceListByLogId(vo.getSerial_num());
        myForm.setAdviceList(adviceList);

        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * 新增动作--这里重载BaseAction里面的方法，实现添加worklog模板的功能
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        WorkLogForm workLogForm = (WorkLogForm) cfg.getBaseForm();
        WorkLogVO vo = (WorkLogVO) workLogForm.getVo();
        workLogForm.setIsInsertFlag("1"); //设置已经写过当天日志
        String logTotalStr = workLogForm.getLogTotalStr();
        String logGrjb     = workLogForm.getLogGrjb();
        String curUserID = cfg.getBaseForm().getUserId();
        String logID = vo.getSerial_num();
        vo.setLog_content(Codes.decode(workLogForm.getCellcontent().toCharArray()));
        	log.console(this.getClass(),"doInsert:Log_content.length",String.valueOf(vo.getLog_content().length) );
        String strRet = super.doInsert(cfg, isBack);
        workLogDao.insertLogsIntoBakTable(logTotalStr, logGrjb, logID, curUserID);
        if(!isBack){
          cfg.getBaseForm().setVo(vo);
          cfg.getBaseForm().setActionType(this.MODIFY_ACTION);
          this.makeCellField(cfg);
        }
        return strRet;
    }

    /**
     * 修改动作--这里重载BaseAction里面的方法，实现修改模板的功能
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        WorkLogForm workLogForm = (WorkLogForm) cfg.getBaseForm();
        WorkLogVO vo = (WorkLogVO) workLogForm.getVo();
        String logTotalStr = workLogForm.getLogTotalStr();
        String logGrjb     = workLogForm.getLogGrjb();
        //this.writeXMLDoc(logTotalStr);
        String logID = vo.getSerial_num();
        vo.setLog_content(Codes.decode(workLogForm.getCellcontent().toCharArray()));
        String ret = super.doUpdate(cfg, isBack);
        if(!this.isOracle()){
          workLogDao.updateLogContent(logID, logTotalStr, logGrjb);
        }
        else{
          workLogDao.insertLogsIntoBakTable(logTotalStr, logGrjb, logID, cfg.getBaseForm().getUserId());
        }
        if(!isBack){
          toEdit(cfg);
        }
        return ret;
    }

    /**
     * 到检视页面
     * @param cfg 当前Action相关配置类
     * @return forward 地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String toView(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String forwardpath = super.toView(cfg);
        String curUser = cfg.getBaseForm().getUserId();
        String curDate = DateTimeUtils.getCurrentDate();
        User user = new DefaultUser();
        UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //得到form变量
        WorkLogVO vo = (WorkLogVO) myForm.getVo();
        String usercode = vo.getUser_code(); //获取创建人ID
        if (!"".equals(usercode) && usercode != null) {
            int iUsercode = Integer.parseInt(usercode);
            user = userDao.findUserByID(iUsercode);
            String userName = user.getName();
            myForm.setUsername(userName); //设置创建人中文名
            //String curDeptNames = cfg.getBaseForm().getCurDeptNames();
            OrganizationUtil systemMngUtil = new OrganizationUtil();
            String curUserDeptNames = systemMngUtil.getDepartmentNamesByUser(iUsercode);
            if (StringUtils.isNull(curUserDeptNames) == false) { //不为空的话
                myForm.setDeptnames(curUserDeptNames); //创建人所对应的部门
            } else {
                myForm.setDeptnames("未分配");
            }
            WorkLogAdviceDAO adviceDao = WorkLogFactory.getInstance().createWorkLogAdvice(this.dbData);
            if(curUser.equals(usercode) == false)  {
                String advice = adviceDao.getAdviceWordsByLogIdAndAdviser(vo.getSerial_num(),curUser);
                myForm.setAdviceWords(advice);
                myForm.setEditAdviceFlag("1");
            } else {
                List adviceList = adviceDao.getAdviceListByLogId(vo.
                    getSerial_num());
                myForm.setAdviceList(adviceList);
                myForm.setEditAdviceFlag("0");
            }
        }
        String filldate = vo.getFill_date();
        String isCurUserTodayWorkLogFlag = workLogDao.isCurUserTodayWorkLog(vo.getSerial_num(), usercode, filldate, curDate);
        if ("1".equals(isCurUserTodayWorkLogFlag)) { //如果是当前人当天的日志则设置页面标志为1,允许修改
            myForm.setIsTodayWorkLogFlag("1");
        } else {
            myForm.setIsTodayWorkLogFlag("0");
        }
        makeCellField(cfg);
        return forwardpath;
    }

    /**
     * 保存日志意见方法
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String saveAdvice(GoaActionConfig cfg) throws DefaultException,
        Exception {
        log.showLog("=====> saveAdvice ");
        String forward = "index";
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //得到form变量
        WorkLogVO vo = (WorkLogVO) myForm.getVo();
        String adviser = cfg.getBaseForm().getUserId();
        String advisername = cfg.getBaseForm().getUserName();
        HttpServletRequest request = cfg.getRequest();
        if("".equals(request.getParameter("words")) == false) {
            String words = myForm.getAdviceWords();
            String actionType = request.getParameter("type");
            log.showLog("=======> words: " + words);
            log.showLog("=======> actionType: " + actionType);
            WorkLogAdviceDAO adviceDao = WorkLogFactory.getInstance().createWorkLogAdvice(this.dbData);
            adviceDao.saveAdvice(vo.getSerial_num(), adviser, words, actionType);
        }
        return forward;
    }

    public String toTemplate(GoaActionConfig cfg) throws DefaultException,
        Exception {
        return "template";
    }

    //创建临时文件以便查询
    private void makeCellField(GoaActionConfig cfg) throws DefaultException,
        Exception {
        TemplateUtils templateUtils = new TemplateUtils();
        WorkLogForm workLogForm = (WorkLogForm) cfg.getBaseForm();
        WorkLogVO vo = (WorkLogVO) workLogForm.getVo();
        String strPath = super.getServlet().getServletContext().getRealPath("/");
        String strFilename = "/cell/report/" +
            templateUtils.makeCellName(strPath + "/cell/report") + ".cll";
        workLogForm.setCellname(strFilename);
        byte[] bcell = vo.getLog_content();
        File file = new File(strPath + strFilename);
        OutputStream fos = new FileOutputStream(file);
        OutputStream os = new BufferedOutputStream(fos);
        os.write(bcell);
        os.close();
    }

    /**
     * 将数据写入XML中Document对象当中
     * @param logTotalStr 填写的日志字符串，用逗号隔开
     * @return XML中Document对象(菜单)
     */
    private Document writeXMLDoc(String logTotalStr) {
        StringTokenizer stkElementName = new StringTokenizer(logTotalStr, ",");
        int i = 0;
        String[] logs = new String[7];
        while (stkElementName.hasMoreElements()) {
            i++;
            logs[i] = stkElementName.nextToken();
        }

        //为解析XML作准备，创建DocumentBuilderFactory实例,指定DocumentBuilder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            System.err.println(pce);
            return null;
        }
        Document doc = null;
        doc = db.newDocument();
        //下面是建立XML文档内容的过程，先建立根元素"根元素"
        Element root = doc.createElement("root");
        //根元素添加上文档
        doc.appendChild(root);
        //加入空菜单项元素
        Element log = doc.createElement("log");
        log.setAttribute("id", "cylogmenu"); //设置menu的属性id的值为cylogmenu-CY的日志菜单
        root.appendChild(log); //menu作为root的下级文档
        Element firstlog = doc.createElement("firstlog"); //再创建一个叫做第一条日志的元素
        firstlog.setAttribute("text", "(空)"); //将firstlog的属性text的值设置为(空)字符

        log.appendChild(firstlog); //将firstlog作为menu的下级文档
        Element seconglog = doc.createElement("secondlog");
        seconglog.setAttribute("text", "(空)");
        log.appendChild(seconglog);
        Element thirdlog = doc.createElement("thirdlog");
        thirdlog.setAttribute("text", "(空)");
        log.appendChild(thirdlog);
        Element fourthlog = doc.createElement("fourthlog");
        fourthlog.setAttribute("text", "(空)");
        log.appendChild(fourthlog);
        Element fivthlog = doc.createElement("fivthlog");
        fivthlog.setAttribute("text", "(空)");
        log.appendChild(fivthlog);

        //加入菜单自定义属性
        //Element customUrl = doc.createElement("customattribute");
        //customUrl.setAttribute("name", "url");
        //customUrl.setAttribute("value", "");
        //menuItem.appendChild(customUrl);
        /*
                for (int idx = 0; idx < aryList.size(); idx++) {
                    MenuView view = new MenuView();
                    //添加menu
                    view = (MenuView) aryList.get(idx);
                    if ("1".equals(view.getIsFolder())) {
                        setXMLElement(view, doc, root);

                    }
                }
         */
        this.printDOMTree(doc);
        try {
            File dirName = new File("c:\\1.xml");
            if (dirName.exists()) {
                dirName.delete();
            }
            java.io.FileOutputStream fos = new FileOutputStream("c:\\1.xml".toString());
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            //System.out.println("----保存文件  失败xml----");
        }
        return doc;

    }

    private StringBuffer sb = new StringBuffer();
    public void printDOMTree(Node node) { //生成XML树
        int type = node.getNodeType();
        switch (type) {
            // print the document element
            case Node.DOCUMENT_NODE: {
                sb.append("<?xml version=\"1.0\" ?>");
                printDOMTree( ( (Document) node).getDocumentElement());
                break;
            }
            // print element with attributes
            case Node.ELEMENT_NODE: {
                sb.append("<");
                sb.append(node.getNodeName());
                NamedNodeMap attrs = node.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Node attr = attrs.item(i);
                    sb.append(" " + attr.getNodeName() +
                              "=\"" + attr.getNodeValue() +
                              "\"");
                }
                sb.append(">");
                NodeList children = node.getChildNodes();
                if (children != null) {
                    int len = children.getLength();
                    for (int i = 0; i < len; i++) {
                        printDOMTree(children.item(i));
                    }
                }

                break;
            }
            // handle entity reference nodes
            case Node.ENTITY_REFERENCE_NODE: {
                sb.append("&");
                sb.append(node.getNodeName());
                sb.append(";");
                break;
            }
            // print cdata sections
            case Node.CDATA_SECTION_NODE: {
                sb.append("<![CDATA[");
                sb.append(node.getNodeValue());
                sb.append("]]>");
                break;
            }
            // print text
            case Node.TEXT_NODE: {
                sb.append(node.getNodeValue());
                break;
            }
            // print processing instruction
            case Node.PROCESSING_INSTRUCTION_NODE: {
                sb.append("<?");
                sb.append(node.getNodeName());
                String data = node.getNodeValue();
                {
                    sb.append(" ");
                    sb.append(data);
                }
                sb.append("?>");
                break;
            }
        }

        if (type == Node.ELEMENT_NODE) {
            sb.append("\n");
            sb.append("</");
            sb.append(node.getNodeName());
            sb.append('>');
        }
    }

}
