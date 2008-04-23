package com.gever.struts.action;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gever.exception.DefaultException;
import com.gever.struts.Constant;
import com.gever.struts.form.BaseForm;
import com.gever.struts.pager.PageControl;
import com.gever.struts.pager.PageHelper;
import com.gever.util.DateTimeUtils;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.util.log.Log;
import com.gever.vo.VOInterface;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title: goa所有Action的父类</p>
 * <p>Description: 包括新增、修改、删除修改、分页、页面跳转</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BaseAction extends ActionBase {

    public final static String ADD_ACTION = "add";
    public final static String INSERT_ACTION = "insert";
    public final static String MODIFY_ACTION = "modify";
    public final static String UPDATE_ACTION = "update";
    public final static String DELETE_ACTION = "delete";
    public final static String REMOVE_ACTION = "remove";
    protected final static String QUERY_ACTION = "query";
    protected final static String PAGE_ACTION = "doPage";
    protected final static String COPY_ACTION = "copy";
    protected final static String LIST_ACTION = "toList";
    protected final static String EDIT_ACTION = "toEdit";
    protected final static String VIEW_ACTION = "toView";

    protected final static String GOURL_ACTION = "goUrl"; //页面跳转

    protected final static String FORWORD_LIST_PAGE = "index";
    protected final static String FORWORD_EDIT_PAGE = "edit";
    protected final static String FORWORD_VIEW_PAGE = "view";

    protected final static String QUERY_PAGE = "query";
    protected static final String TREE_PAGE = "TREE_DATA";
    protected Log log = Log.getInstance(BaseAction.class);
    private String module = "";
    protected String dbData = Constant.DATA_SOURCE;
    private boolean voSql = true;


    public BaseAction() {
    }

    /**
     * 动作处理中心，由父类ActionBase的execute()调用
     * 
     * @param actionmapping ActionMapping
     * @param actionform ActionForm
     * @param httpRequest HttpServletRequest
     * @param httpResponse HttpServletResponse
     * @param action String 什么动作
     * @return ActionForward
     * 
     * @throws DefaultException
     * @throws IOException
     * @throws ServletException
     * @throws java.lang.Exception
     */
    public final ActionForward doActionCenter(ActionMapping actionmapping,
                                        ActionForm actionform,
                                        HttpServletRequest httpRequest,
                                        HttpServletResponse httpResponse,
                                        String action) throws DefaultException,
        IOException, ServletException, Exception {

        ActionForward forward = null;
        String strForward = "";

        PageHelper pHelper = new PageHelper();
        GoaActionConfig config = new GoaActionConfig();
        HttpSession session = httpRequest.getSession();
        config.setBaseForm( (BaseForm) actionform);
        config.setMapping(actionmapping);
        config.setRequest(httpRequest);
        config.setResponse(httpResponse);
        config.setPageHelper(pHelper);
        config.setSession(session);

        int pos = -1;
        String userId = "0";

        userId = (String) session.getAttribute(Constant.USER_ID);
        if (StringUtils.isNull(userId)){
            userId = "0";
        }

        //初始化相关数据
        initData(config);
        BaseForm bForm = (BaseForm) actionform;
        log.console(this.getClass(),"Attr:"+Constant.NAME,(String)session.getAttribute(Constant.NAME));
        bForm.setUserId(userId);
        if (!StringUtils.isNull((String)session.getAttribute(Constant.USER_FILTER)))
            bForm.setUserFilter((String) session.getAttribute(Constant.USER_FILTER));  //筛选层级
        if (!StringUtils.isNull((String)session.getAttribute(Constant.NAME)))
            bForm.setUserName((String) session.getAttribute(Constant.NAME));           //用户名称
        if (!StringUtils.isNull((String)session.getAttribute(Constant.USER_NAME)))
            bForm.setLogonName((String) session.getAttribute(Constant.USER_NAME));     //登入名称
        if (!StringUtils.isNull((String)session.getAttribute(Constant.DEPT_CODES)))
            bForm.setCurDeptCodes((String) session.getAttribute(Constant.DEPT_CODES));     //登入名称
        if (!StringUtils.isNull((String)session.getAttribute(Constant.DEPT_NAMES)))
            bForm.setCurDeptNames((String) session.getAttribute(Constant.DEPT_NAMES));     //登入名称

        if (config.getBaseDao() != null)
            config.getBaseDao().setUserID(userId);

        log.console(this.getClass(),"doActionCenter()->action=",action);
        //动作类型中转
        if (INSERT_ACTION.equals(action)) { //新增记录动作,继续返回到本页面新增
            strForward = this.doInsert(config, false);
        } else if (ADD_ACTION.equals(action)) { //新增记录动作
            strForward = this.doInsert(config, true);
        } else if (MODIFY_ACTION.equals(action)) { //修改记录动作
            strForward = this.doUpdate(config, true);
        } else if (UPDATE_ACTION.equals(action)) { //修改记录动作
            strForward = this.doUpdate(config, false);
        } else if (VIEW_ACTION.equals(action)) { //修改记录动作
            strForward = this.toView(config);
        } else if (DELETE_ACTION.equals(action)) { //删除记录动作
            strForward = this.doDelete(config);
        } else if (QUERY_ACTION.equals(action)) { //查询记录动作
            strForward = this.doInsert(config, false);
        } else if (LIST_ACTION.equals(action)) { //到清单页面
            strForward = this.toList(config);
        } else if (EDIT_ACTION.equals(action)) { //到修改页面
            strForward = this.toEdit(config);
        } else if (PAGE_ACTION.equals(action)) { //翻页处理
            strForward = this.doPage(config, "");
        } else if (COPY_ACTION.equals(action)) { //复制记录
            strForward = this.doCopy(config);
        } else if ("doTreeData".equals(action)) { //复制记录
            strForward = this.doTreeData(config);
        } else if ( (pos = action.indexOf(this.GOURL_ACTION)) >= 0) {
            String subPage = action.substring(pos + 1);
            pos = (subPage.indexOf("("));
            subPage = subPage.substring(pos + 1, subPage.length() - 1);
            log.console(this.getClass(),"doActionCenter()->Action=goUrl->subPage:",subPage);            
            strForward = this.goUrl(config, subPage);
        } else {
        	//由ActionBase执行指定的方法，限制是参数只能有一个且必须是GoaConfig
            strForward = super.performMethod(config, action);
        }

        //如果不是默认的
        if (strForward.indexOf(".jsp") > 0 || strForward.indexOf(".do") > 0) {
            forward = new ActionForward();
            forward.setPath(strForward);
            forward.setRedirect(true);
            return forward;
        }
        forward = actionmapping.findForward(strForward);
        log.console(this.getClass(),"doActionCenter forward",forward);
        return forward;
    }

    /**
     * 新增动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();

        cfg.getBaseDao().insert(vo);
        bForm.setVo(cfg.getBaseDao().getInstanceVO(vo));
        if (isBack == true)
            return this.FORWORD_LIST_PAGE;
        else
            return this.FORWORD_EDIT_PAGE;
    }

    /**
     * 修改动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();

        cfg.getBaseDao().update(vo);

        if (isBack == true)
            return this.FORWORD_LIST_PAGE;
        else
            return this.FORWORD_EDIT_PAGE;

    }

    /**
     * 删除动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doDelete(GoaActionConfig cfg) throws DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        HttpServletRequest request = cfg.getRequest();

        int iRet = 0;
        String[] keyId = request.getParameterValues("fid");

        if (keyId == null) //容错处理
            keyId = new String[0];
        if (keyId.length > 0)
            iRet = cfg.getBaseDao().delBySelect(keyId, vo);
        doPage(cfg, "");

        //toList(vo);
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * 复制动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doCopy(GoaActionConfig cfg) throws DefaultException,
        Exception {
        int iRet = -1;
        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();

        String[] keyId = request.getParameterValues("fid");
        
        log.console(this.getClass(),"BaseAction.doCopy()->fid: ",keyId[0]);
        if (keyId == null) //容错处理
            keyId = new String[0];
        if (keyId.length > 0)
            iRet = cfg.getBaseDao().copy(keyId, vo);
        doPage(cfg, "");

        return this.FORWORD_LIST_PAGE;
    }

    /**
     * 跳转(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param url 当前url地址
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String goUrl(GoaActionConfig cfg, String url) throws
        DefaultException,
        Exception {
        log.console(this.getClass(),"goUrl()->URL:",url);    	
        return url;
    }

    /**
     * 翻页动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param pageType 页面类型
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doPage(GoaActionConfig cfg, String pageType) throws
        DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();
        PageHelper pHelper = cfg.getPageHelper();
        cfg.getBaseDao().setOrderColumn(cfg.getBaseForm().getOrderFld());
        cfg.getBaseDao().setOrderType(cfg.getBaseForm().getOrderType());
        cfg.getBaseDao().setSqlWhere(cfg.getBaseForm().getSqlWhere());
        log.console(this.getClass(),"doPage:sqlWhere",cfg.getBaseForm().getSqlWhere());    	

        long lngCnt = cfg.getBaseDao().queryByCount(vo); //统计
        setPageNumber(cfg);
        String strPage = pHelper.getPageStr(cfg, lngCnt, pageType); //得到当前页
        bForm.setCurPage(NumberUtil.stringToLong(strPage, 1l)); //备份
        PageControl pc = pHelper.pagination(bForm.getCurPage(), lngCnt,
                                            pageType); //设置分页属性

        //得到分页数据
        pc.setData(cfg.getBaseDao().queryByPage(vo, pHelper.getStart(),
                                                pHelper.getCount()));

        cfg.getBaseForm().setPageControl(pc);
        bForm.setPageControl(pc);


        return this.FORWORD_LIST_PAGE;
    }


    /**
     * 到清单页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {

    	log.console(this.getClass(),"toList:sqlWhere",cfg.getBaseForm().getSqlWhere());    	
    	
        if (this.ADD_ACTION.equals(cfg.getBaseForm().getActionType())) {
            doPage(cfg, Constant.LAST_PAGE); // 得到第一页的资料
        } else {
            doPage(cfg, ""); // 得到第一页的资料
        }
    	log.console(this.getClass(),"toList:sqlWhere-1",cfg.getBaseForm().getSqlWhere());    	
       // cfg.getBaseForm().setOrderFld("");
       // cfg.getBaseForm().setOrderType("asc");
        cfg.getBaseForm().setActionType(""); // 恢复默认动作类型((add,modify)
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * 到修改页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
        Exception {

        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();

        String actionType = SumUtils.nullToString(request.getParameter(
            "actionType"));

        log.console(this.getClass(),"toEdit:actionType",actionType);    	
        
        String curUpdateId = SumUtils.nullToString(request.getParameter("fid"));
        VOInterface curVO = null;
        log.console(this.getClass(),"toEdit:","actionType==" + actionType + "  fid=" + curUpdateId);    	
        
        //修改动作
        if (this.MODIFY_ACTION.equals(actionType)) {
            //得到当前的主键名字
            StringTokenizer stk = new StringTokenizer(curUpdateId, "::");
            StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(), ",");
            if (stk.countTokens() != stk.countTokens()) {
                log.showLog("toEdit()方法 主键个数与值个数不一致");
                //   throw new DefaultException("主键个数与值个数不一致");
            }

            while (stk.hasMoreTokens()) {
                vo.setValue(stkPk.nextToken(), stk.nextToken());
            }
            if (this.voSql == true){
                curVO = (VOInterface) cfg.getBaseDao().queryByPk(vo);
            } else {
                curVO = (VOInterface) cfg.getBaseDao().queryByPk2(vo);
            }
            bForm.setActionType(actionType);
        } else { //新增动作
            curVO = cfg.getBaseDao().getInstanceVO(vo);
            bForm.setActionType(this.ADD_ACTION);
        }

        bForm.setVo(curVO);
        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * 到检视页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String toView(GoaActionConfig cfg) throws DefaultException,
        Exception {

        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();

        String curUpdateId = StringUtils.nullToString(request.getParameter(
            "fid"));
        StringTokenizer stk = new StringTokenizer(curUpdateId, "::");
        StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(), ",");
        if (stkPk.countTokens() != stk.countTokens()) {
            log.showLog("toView()方法 主键个数与值个数不一致");
            //   throw new DefaultException("主键个数与值个数不一致");
        }

        while (stk.hasMoreTokens()) {
            vo.setValue(stkPk.nextToken(), stk.nextToken());
        }
        //得到当前的主键名字
        if (this.voSql == true){
            vo = (VOInterface) cfg.getBaseDao().queryByPk(vo);
        } else {
            vo = (VOInterface) cfg.getBaseDao().queryByPk2(vo);
        }

        bForm.setVo(vo);
        return this.FORWORD_VIEW_PAGE;
    }

    /**
     * 初始化页面数据，子类必须覆盖
     * 
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     */

    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {
        return;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    /**
     * 到树页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String toTree(GoaActionConfig cfg) throws DefaultException,
        Exception {
        return FORWORD_LIST_PAGE;
    }


    /**
     * 到树页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String doOrderBy(GoaActionConfig cfg) throws DefaultException,
        Exception {

        doPage(cfg, ""); // 得到第一页的资料
        return FORWORD_LIST_PAGE;
    }

    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
        Exception{

        return TREE_PAGE;
    }

    /**
     * 设置分页数量
     * @param cfg 分页数据
     */
    public void setPageNumber(GoaActionConfig cfg) {
        cfg.getPageHelper().setPageCount(-1);

    }
    public void setVoSql(boolean voSql) {
        this.voSql = voSql;
    }

    public String toDate(String strDate) {
        return   DateTimeUtils.toDate(strDate);

    }
    public String toDateTime(String strDateTime) {
        return DateTimeUtils.toDateTime(strDateTime);
    }

    public boolean isOracle() {
        ActiveUsers au = ActiveUsers.getInstance();

        return au.isOracle();

    }

    public String getIsNull(String fldValue){
        String strRet = "=''";
        if (isOracle()) {
            strRet = fldValue+" is null ";
        }else{
          strRet=fldValue+strRet;
        }
        return strRet;
    }

    public String getIsNotNull(String fldValue) {
        String strRet = "<>''";
        if (isOracle()) {
            strRet =fldValue+ " is not null ";
          }else{
            strRet=fldValue+strRet;
          }
        return strRet;
    }

}
