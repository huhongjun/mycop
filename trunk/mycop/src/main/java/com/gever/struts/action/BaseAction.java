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
 * <p>Title: goa����Action�ĸ���</p>
 * <p>Description: �����������޸ġ�ɾ���޸ġ���ҳ��ҳ����ת</p>
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

    protected final static String GOURL_ACTION = "goUrl"; //ҳ����ת

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
     * �����������ģ��ɸ���ActionBase��execute()����
     * 
     * @param actionmapping ActionMapping
     * @param actionform ActionForm
     * @param httpRequest HttpServletRequest
     * @param httpResponse HttpServletResponse
     * @param action String ʲô����
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

        //��ʼ���������
        initData(config);
        BaseForm bForm = (BaseForm) actionform;
        log.console(this.getClass(),"Attr:"+Constant.NAME,(String)session.getAttribute(Constant.NAME));
        bForm.setUserId(userId);
        if (!StringUtils.isNull((String)session.getAttribute(Constant.USER_FILTER)))
            bForm.setUserFilter((String) session.getAttribute(Constant.USER_FILTER));  //ɸѡ�㼶
        if (!StringUtils.isNull((String)session.getAttribute(Constant.NAME)))
            bForm.setUserName((String) session.getAttribute(Constant.NAME));           //�û�����
        if (!StringUtils.isNull((String)session.getAttribute(Constant.USER_NAME)))
            bForm.setLogonName((String) session.getAttribute(Constant.USER_NAME));     //��������
        if (!StringUtils.isNull((String)session.getAttribute(Constant.DEPT_CODES)))
            bForm.setCurDeptCodes((String) session.getAttribute(Constant.DEPT_CODES));     //��������
        if (!StringUtils.isNull((String)session.getAttribute(Constant.DEPT_NAMES)))
            bForm.setCurDeptNames((String) session.getAttribute(Constant.DEPT_NAMES));     //��������

        if (config.getBaseDao() != null)
            config.getBaseDao().setUserID(userId);

        log.console(this.getClass(),"doActionCenter()->action=",action);
        //����������ת
        if (INSERT_ACTION.equals(action)) { //������¼����,�������ص���ҳ������
            strForward = this.doInsert(config, false);
        } else if (ADD_ACTION.equals(action)) { //������¼����
            strForward = this.doInsert(config, true);
        } else if (MODIFY_ACTION.equals(action)) { //�޸ļ�¼����
            strForward = this.doUpdate(config, true);
        } else if (UPDATE_ACTION.equals(action)) { //�޸ļ�¼����
            strForward = this.doUpdate(config, false);
        } else if (VIEW_ACTION.equals(action)) { //�޸ļ�¼����
            strForward = this.toView(config);
        } else if (DELETE_ACTION.equals(action)) { //ɾ����¼����
            strForward = this.doDelete(config);
        } else if (QUERY_ACTION.equals(action)) { //��ѯ��¼����
            strForward = this.doInsert(config, false);
        } else if (LIST_ACTION.equals(action)) { //���嵥ҳ��
            strForward = this.toList(config);
        } else if (EDIT_ACTION.equals(action)) { //���޸�ҳ��
            strForward = this.toEdit(config);
        } else if (PAGE_ACTION.equals(action)) { //��ҳ����
            strForward = this.doPage(config, "");
        } else if (COPY_ACTION.equals(action)) { //���Ƽ�¼
            strForward = this.doCopy(config);
        } else if ("doTreeData".equals(action)) { //���Ƽ�¼
            strForward = this.doTreeData(config);
        } else if ( (pos = action.indexOf(this.GOURL_ACTION)) >= 0) {
            String subPage = action.substring(pos + 1);
            pos = (subPage.indexOf("("));
            subPage = subPage.substring(pos + 1, subPage.length() - 1);
            log.console(this.getClass(),"doActionCenter()->Action=goUrl->subPage:",subPage);            
            strForward = this.goUrl(config, subPage);
        } else {
        	//��ActionBaseִ��ָ���ķ����������ǲ���ֻ����һ���ұ�����GoaConfig
            strForward = super.performMethod(config, action);
        }

        //�������Ĭ�ϵ�
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
     * ��������(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
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
     * �޸Ķ���(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
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
     * ɾ������(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */
    protected String doDelete(GoaActionConfig cfg) throws DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        HttpServletRequest request = cfg.getRequest();

        int iRet = 0;
        String[] keyId = request.getParameterValues("fid");

        if (keyId == null) //�ݴ���
            keyId = new String[0];
        if (keyId.length > 0)
            iRet = cfg.getBaseDao().delBySelect(keyId, vo);
        doPage(cfg, "");

        //toList(vo);
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * ���ƶ���(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
        if (keyId == null) //�ݴ���
            keyId = new String[0];
        if (keyId.length > 0)
            iRet = cfg.getBaseDao().copy(keyId, vo);
        doPage(cfg, "");

        return this.FORWORD_LIST_PAGE;
    }

    /**
     * ��ת(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @param url ��ǰurl��ַ
     * @return forward��ַ
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
     * ��ҳ����(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @param pageType ҳ������
     * @return forward��ַ
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

        long lngCnt = cfg.getBaseDao().queryByCount(vo); //ͳ��
        setPageNumber(cfg);
        String strPage = pHelper.getPageStr(cfg, lngCnt, pageType); //�õ���ǰҳ
        bForm.setCurPage(NumberUtil.stringToLong(strPage, 1l)); //����
        PageControl pc = pHelper.pagination(bForm.getCurPage(), lngCnt,
                                            pageType); //���÷�ҳ����

        //�õ���ҳ����
        pc.setData(cfg.getBaseDao().queryByPage(vo, pHelper.getStart(),
                                                pHelper.getCount()));

        cfg.getBaseForm().setPageControl(pc);
        bForm.setPageControl(pc);


        return this.FORWORD_LIST_PAGE;
    }


    /**
     * ���嵥ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {

    	log.console(this.getClass(),"toList:sqlWhere",cfg.getBaseForm().getSqlWhere());    	
    	
        if (this.ADD_ACTION.equals(cfg.getBaseForm().getActionType())) {
            doPage(cfg, Constant.LAST_PAGE); // �õ���һҳ������
        } else {
            doPage(cfg, ""); // �õ���һҳ������
        }
    	log.console(this.getClass(),"toList:sqlWhere-1",cfg.getBaseForm().getSqlWhere());    	
       // cfg.getBaseForm().setOrderFld("");
       // cfg.getBaseForm().setOrderType("asc");
        cfg.getBaseForm().setActionType(""); // �ָ�Ĭ�϶�������((add,modify)
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * ���޸�ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
        
        //�޸Ķ���
        if (this.MODIFY_ACTION.equals(actionType)) {
            //�õ���ǰ����������
            StringTokenizer stk = new StringTokenizer(curUpdateId, "::");
            StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(), ",");
            if (stk.countTokens() != stk.countTokens()) {
                log.showLog("toEdit()���� ����������ֵ������һ��");
                //   throw new DefaultException("����������ֵ������һ��");
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
        } else { //��������
            curVO = cfg.getBaseDao().getInstanceVO(vo);
            bForm.setActionType(this.ADD_ACTION);
        }

        bForm.setVo(curVO);
        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * ������ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
            log.showLog("toView()���� ����������ֵ������һ��");
            //   throw new DefaultException("����������ֵ������һ��");
        }

        while (stk.hasMoreTokens()) {
            vo.setValue(stkPk.nextToken(), stk.nextToken());
        }
        //�õ���ǰ����������
        if (this.voSql == true){
            vo = (VOInterface) cfg.getBaseDao().queryByPk(vo);
        } else {
            vo = (VOInterface) cfg.getBaseDao().queryByPk2(vo);
        }

        bForm.setVo(vo);
        return this.FORWORD_VIEW_PAGE;
    }

    /**
     * ��ʼ��ҳ�����ݣ�������븲��
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
     * ����ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String toTree(GoaActionConfig cfg) throws DefaultException,
        Exception {
        return FORWORD_LIST_PAGE;
    }


    /**
     * ����ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String doOrderBy(GoaActionConfig cfg) throws DefaultException,
        Exception {

        doPage(cfg, ""); // �õ���һҳ������
        return FORWORD_LIST_PAGE;
    }

    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
        Exception{

        return TREE_PAGE;
    }

    /**
     * ���÷�ҳ����
     * @param cfg ��ҳ����
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
