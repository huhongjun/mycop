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
 * <p>Title: ������־������</p>
 * <p>Description: ������־������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorkLogAction extends BaseAction {
    WorkLogDao workLogDao = null; //����ӿ�
    Log log = Log.getInstance(WorkLogAction.class);

    public WorkLogAction() {
    }

    /**
     * ��ʼ��ҳ������
     * @param cfg ��ǰ��vo����
     * @throws DefaultException
     * @throws Exception
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //�õ�form����
        workLogDao = WorkLogFactory.getInstance().createWorkLog(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) workLogDao); //cfg--Goa Action��ȡ��--���ó����е�Dao
        cfg.getBaseDao().setIsIdMng(false);
        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null) {
            myForm.setVo(new WorkLogVO()); //����VO��Form��
        }
    }

    /**
     * ���嵥ҳ��--����������BaseAction�е�toList����
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
         Exception {
         HttpServletRequest request = cfg.getRequest();
         HttpSession session = cfg.getSession();
         WorkLogForm workLogForm = (WorkLogForm) cfg.getBaseForm();
         String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
         String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
         boolean isInsertTodayWorkLog = workLogDao.findCurPsnCurDayWorkLogIsExist(curUser, curDate);
         if (isInsertTodayWorkLog == true) {
             workLogForm.setIsInsertFlag("1"); //�����Ѿ�д��������־-1-��д��-0-δд��
         } else {
             workLogForm.setIsInsertFlag("0");
         }
         int numOfPage = workLogDao.findLogNumOfEveryPage();
         String queryFlag = request.getParameter("queryFlag");
         StringBuffer sbSqlWhere = new StringBuffer();
         sbSqlWhere.append(" where 1=1 ");
         if ("true".equals(queryFlag)) { //�ô����ܻ���Ҫ����һ������ֱ�ӱȽ��Ƿ����
             String searchBeginTime = ( (WorkLogForm) cfg.getBaseForm()).getSearchBeginTime();
             String searchEndTime = ( (WorkLogForm) cfg.getBaseForm()).getSearchEndTime();
             if (!StringUtils.isNull(searchEndTime)) { //ʱ�䲻Ϊ��ʱ�Ž��в�ѯ
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
             sbSqlWhere.append(" order by  FILL_DATE DESC "); //����д���ڵ�������
         }
         else{
             sbSqlWhere.append(" and USER_CODE = " + Integer.parseInt(curUser) +
                               " ");
             sbSqlWhere.append(" order by  FILL_DATE DESC "); //����д���ڵ�������
         }

         WorkLogVO vo = (WorkLogVO) workLogForm.getVo();
         cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
        // cfg.getBaseDao().setSqlWhere(sbSqlWhere.toString());
         super.toList(cfg);
//       workLogForm.setListLog(workLogDao.queryAll(vo));
         return this.FORWORD_LIST_PAGE;
     }


    /**
     * ���б�ҳ�洦��������--�����������˳��������doOrderBy����
     * ���������Ϊ�˴�������sql����в�֧��fetch First �������
     * ͨ��ҳ�����ε��˶Ը÷����ĵ��ã��÷�����ʱ����Ч
     * ��������ԱҪ��һ��Ҫ����������ʱ��������Ҫ����÷�����
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
        long lngCnt = cfg.getBaseDao().queryByCount(vo); //ͳ��
        setPageNumber(cfg);

        String strPage = pHelper.getPageStr(cfg, lngCnt, pageType); //�õ���ǰҳ
        myForm.setCurPage(NumberUtil.stringToLong(strPage, 1l)); //����
        return FORWORD_LIST_PAGE;
    }

    /**
     * ���޸�ҳ��--����������BaseAction�е�toEdit����
     * �Ա��ʼ������ҳ���ϵ�һЩ�������ݣ��������룬ֻ���ȡ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //�õ�form����
        if (!myForm.getActionType().equals(this.MODIFY_ACTION)) { //�������ģ�廹û������
            IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance().createIsStandardModel(super.dbData);
            IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.getTemplate(ConstantSet.WorkLogTemplate);
            myForm.setReportTemplateName(ismVO.getFile_path());
        }
        else{
          WorkLogVO vo = (WorkLogVO) myForm.getVo();
          byte[] bcell = vo.getLog_content();
          String strPath = super.getServlet().getServletContext().getRealPath("/");
          //�����ļ���
          String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
          myForm.setCellname(strFilename);
        }
        WorkLogVO vo = (WorkLogVO) myForm.getVo();
        String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�--��Ӧ������-user_code
        String curDeptCodes = cfg.getBaseForm().getCurDeptCodes();
        String curDeptNames = cfg.getBaseForm().getCurDeptNames();
        String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
        //String curTime = DateTimeUtils.getCurrentTime();
        String curTime = DateTimeUtils.getCurrentDateTime();
        String initUserCode = vo.getUser_code();
        if ("".equals(initUserCode) || initUserCode == null) {
            //�����ʼ�û�Ϊ��(���������)�����õ�ǰ�û�Ϊ������
            vo.setUser_code(curUser);
        }

        user = userDao.findUserByID(Integer.parseInt(vo.getUser_code()));
        String curName = user.getName();
        vo.setCreate_time(curTime); 	//����ʱ��
        vo.setFill_date(curDate); 		//��д����
        vo.setDept_code(curDeptCodes); 	//���õ�ǰ�û���Ӧ�Ĳ���
        vo.setUser_code(curUser);
        myForm.setUsername(curName); 	//����ҳ����ʾ���û�������
        myForm.setDeptnames(curDeptNames);

        //�ڽ����Ŵ������ת���Ĺ����л���Ҫͬ���Ļ�ȡ���������ö��Ÿ������ַ���
        cfg.getBaseForm().setVo(vo);
        String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
            "actionType"));
        if ("modify".equals(actionType)) { //������޸Ĳ���
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
     * ��������--��������BaseAction����ķ�����ʵ�����worklogģ��Ĺ���
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        WorkLogForm workLogForm = (WorkLogForm) cfg.getBaseForm();
        WorkLogVO vo = (WorkLogVO) workLogForm.getVo();
        workLogForm.setIsInsertFlag("1"); //�����Ѿ�д��������־
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
     * �޸Ķ���--��������BaseAction����ķ�����ʵ���޸�ģ��Ĺ���
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
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
     * ������ҳ��
     * @param cfg ��ǰAction���������
     * @return forward ��ַ
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
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //�õ�form����
        WorkLogVO vo = (WorkLogVO) myForm.getVo();
        String usercode = vo.getUser_code(); //��ȡ������ID
        if (!"".equals(usercode) && usercode != null) {
            int iUsercode = Integer.parseInt(usercode);
            user = userDao.findUserByID(iUsercode);
            String userName = user.getName();
            myForm.setUsername(userName); //���ô�����������
            //String curDeptNames = cfg.getBaseForm().getCurDeptNames();
            OrganizationUtil systemMngUtil = new OrganizationUtil();
            String curUserDeptNames = systemMngUtil.getDepartmentNamesByUser(iUsercode);
            if (StringUtils.isNull(curUserDeptNames) == false) { //��Ϊ�յĻ�
                myForm.setDeptnames(curUserDeptNames); //����������Ӧ�Ĳ���
            } else {
                myForm.setDeptnames("δ����");
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
        if ("1".equals(isCurUserTodayWorkLogFlag)) { //����ǵ�ǰ�˵������־������ҳ���־Ϊ1,�����޸�
            myForm.setIsTodayWorkLogFlag("1");
        } else {
            myForm.setIsTodayWorkLogFlag("0");
        }
        makeCellField(cfg);
        return forwardpath;
    }

    /**
     * ������־�������
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String saveAdvice(GoaActionConfig cfg) throws DefaultException,
        Exception {
        log.showLog("=====> saveAdvice ");
        String forward = "index";
        WorkLogForm myForm = (WorkLogForm) cfg.getBaseForm(); //�õ�form����
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

    //������ʱ�ļ��Ա��ѯ
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
     * ������д��XML��Document������
     * @param logTotalStr ��д����־�ַ������ö��Ÿ���
     * @return XML��Document����(�˵�)
     */
    private Document writeXMLDoc(String logTotalStr) {
        StringTokenizer stkElementName = new StringTokenizer(logTotalStr, ",");
        int i = 0;
        String[] logs = new String[7];
        while (stkElementName.hasMoreElements()) {
            i++;
            logs[i] = stkElementName.nextToken();
        }

        //Ϊ����XML��׼��������DocumentBuilderFactoryʵ��,ָ��DocumentBuilder
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
        //�����ǽ���XML�ĵ����ݵĹ��̣��Ƚ�����Ԫ��"��Ԫ��"
        Element root = doc.createElement("root");
        //��Ԫ��������ĵ�
        doc.appendChild(root);
        //����ղ˵���Ԫ��
        Element log = doc.createElement("log");
        log.setAttribute("id", "cylogmenu"); //����menu������id��ֵΪcylogmenu-CY����־�˵�
        root.appendChild(log); //menu��Ϊroot���¼��ĵ�
        Element firstlog = doc.createElement("firstlog"); //�ٴ���һ��������һ����־��Ԫ��
        firstlog.setAttribute("text", "(��)"); //��firstlog������text��ֵ����Ϊ(��)�ַ�

        log.appendChild(firstlog); //��firstlog��Ϊmenu���¼��ĵ�
        Element seconglog = doc.createElement("secondlog");
        seconglog.setAttribute("text", "(��)");
        log.appendChild(seconglog);
        Element thirdlog = doc.createElement("thirdlog");
        thirdlog.setAttribute("text", "(��)");
        log.appendChild(thirdlog);
        Element fourthlog = doc.createElement("fourthlog");
        fourthlog.setAttribute("text", "(��)");
        log.appendChild(fourthlog);
        Element fivthlog = doc.createElement("fivthlog");
        fivthlog.setAttribute("text", "(��)");
        log.appendChild(fivthlog);

        //����˵��Զ�������
        //Element customUrl = doc.createElement("customattribute");
        //customUrl.setAttribute("name", "url");
        //customUrl.setAttribute("value", "");
        //menuItem.appendChild(customUrl);
        /*
                for (int idx = 0; idx < aryList.size(); idx++) {
                    MenuView view = new MenuView();
                    //���menu
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
            //System.out.println("----�����ļ�  ʧ��xml----");
        }
        return doc;

    }

    private StringBuffer sb = new StringBuffer();
    public void printDOMTree(Node node) { //����XML��
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
