package com.gever.struts.action;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.FactoryNotFoundException;
import org.apache.struts.tiles.TilesUtil;
import org.apache.struts.util.MessageResources;

import com.gever.exception.DefaultException;
import com.gever.struts.Constant;
import com.gever.util.StringUtils;
import com.gever.util.log.Log;

/**
 * <p>Title: goas1.0�汾֮��,����Action�ĸ���</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class ActionBase extends Action implements Serializable {
    public ActionBase() {
    }

    private static MessageResources messages = MessageResources.
        getMessageResources("org.apache.struts.actions.LocalStrings");
    private HashMap methods = new HashMap();
    private final static String LIST_ACTION = "toList";
    private final static String EDIT_ACTION = "toEdit";
    private final static int MAX_SIZE = 6;
    private final static String[] NO_DEL_FORMS = {
        "com.gever.goa.menselect.action.MenSelectAction",
        "com.gever.goa.archive.action.ArcBorrowApplyArchivesAction",
        "com.gever.goa.archive.action.ArchivedFileAction",
        "com.gever.goa.archive.action.ArcBorrowRegArchivesAction",
    };
    private Log log = Log.getInstance(ActionBase.class);

    /* 
     * ����Struts action��ͬ���������Զ�����������ActionBase����������doActionCenter����
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final ActionForward execute(ActionMapping actionmapping,
                                       ActionForm actionform,
                                       HttpServletRequest httpRequest,
                                       HttpServletResponse httpResponse) throws
        javax.servlet.ServletException, java.io.IOException, Exception {

        ActionForward forward = null;
        String name = actionmapping.getName();

        log.console(this.getClass(),"ActionBase.execute()->actionMapping.name",name);
        HttpSession session = httpRequest.getSession(true);
        String currentPath = (String) session.getAttribute("currentaction");
        log.console(this.getClass(),"ActionBase.execute()->Attr:currentaction",currentPath);
        //�������ͣ���Ӧ *.do?action=toList
        String action = StringUtils.nullToString(httpRequest.getParameter("action"));
        String path = actionmapping.getPath();
        try {

        	//
            if (currentPath != null) {

                //ͨ��path���õ�ActionMapping
                ActionMapping currentMap = this.getServlet().findMapping(currentPath);
                log.console(this.getClass(),"ActionBase.execute()->��ǰ:ActionMapping.type",currentMap.getType());
                log.console(this.getClass(),"ActionBase.execute()->�ϴ�:ActionMapping.type",actionmapping.getType());

                //�������Action��type��������ͬ,��ͬһ��Action�������˶��path
                if (!currentMap.getType().equals(actionmapping.getType())) {
                  String manSelect =
                        "com.gever.goa.menselect.action.MenSelectAction";

                    if (! (manSelect.equals(currentMap.getType()) ||
                           manSelect.equals(actionmapping.getType()) ||
                           "request".equals(actionmapping.getScope())
                           )) {
                        release(actionmapping, httpRequest);
                    }

                }
            }//end of if (currentPath != null)

            if (isHasPermission(actionmapping, httpRequest)) {
                String methodName = findMethod(actionmapping,
                                               actionform,
                                               httpRequest,
                                               httpResponse);
                actionform = getFormBean(actionform,httpRequest,path);
                //���û��ָ��action������action��paremeterָ���ķ���ΪĬ�Ϸ���
                if ("".equals(action)) action = methodName;

                //ִ������ķ�����
                forward = doActionCenter(actionmapping, actionform,
                                         httpRequest, httpResponse, action);
            } else {
                forward = actionmapping.findForward("NO_PERMISSION");
            }//end of if (isHasPermission(actionmapping, httpRequest))

            //����currentaction��ֵ
            session.removeAttribute("currentaction");
            session.setAttribute("currentaction", actionmapping.getPath());

        } catch (DefaultException e) {
            e.printStackTrace(System.out);
            throw e;

        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new DefaultException(e);
        }

        if (forward == null) {

            throw new ServletException("forward == null");
            //forward = actionmapping.findForward("NULL_FORWARD");
        }

        log.console(this.getClass(),"execute()->forward",forward);
//        boolean isUseTemplate = isUseTemplate(forward,httpRequest,this.getServlet().getServletContext());
//        if(isUseTemplate==false){
//            log.showLog("��û����ģ��template,�����ر������");
//            throw new IOException("��û����ģ��template,�����ر������");
//        }


        return forward;
    }

    //�ж��Ƿ��õ�ģ�壬���û��ģ�壬return false
    //�˷������ڿ���ʱʹ�ã�������Ϻ�Ӧȥ���˷�����
    public boolean isUseTemplate(ActionForward forward,
                                 HttpServletRequest request,
                                 ServletContext context) {
        if (forward == null || forward.getRedirect())
            return true;
        String path = forward.getPath();
        try {
            ComponentDefinition definition = TilesUtil.getDefinition(path,
                request, context);
            if (definition == null)
                return false;
        } catch (FactoryNotFoundException fe) {
            fe.printStackTrace();
            new DefaultException("DefinitionFactoryû���֡�����,������Ĵ����޹أ�");
        } catch (DefinitionsFactoryException de) {
            de.printStackTrace();
            new DefaultException("DefinitionFactory���⡣����,������Ĵ����޹أ�");
        }
        return true;
    }

    /**
     * ���ܣ������������ģ�Ӧ���������б����ǡ�
     *      * 
     * @param actionmapping ActionMapping		Դ��execute()
     * @param actionform 	ActionForm			Դ��execute()
     * @param httpRequest 	HttpServletRequest	Դ��execute()
     * @param httpResponse 	HttpServletResponse	Դ��execute()
     * @param action		String				�Զ��壬���Ĳ�����ָ��Ҫִ�еĶ���
     * @return 				ActionForward
     * 
     * @throws DefaultException
     */

    public ActionForward doActionCenter(ActionMapping actionmapping,
                                        ActionForm actionform,
                                        HttpServletRequest httpRequest,
                                        HttpServletResponse httpResponse,
                                        String action) throws DefaultException,
        IOException, ServletException, Exception {
        return null;
    }

    /**
     * ���ܣ��ж��Ƿ�Actionĳ��ActionMapping��Ȩ��,Ŀǰֻ����True���Ժ�����趨��
     * ���ܵķ�������xml�ļ��趨ĳ��ɫ��ActionMapping�Ĳ���Ȩ�ޡ�
     * 
     * @param 	anActionMapping ActionMapping
     * @param 	aHttpServletRequest HttpServletRequest
     * @return 	boolean
     */
    protected boolean isHasPermission(ActionMapping anActionMapping,
                                      HttpServletRequest aHttpServletRequest) {
        return true;
    }

    /**
     * ���session��currentMapping����Ӧ��ActionForm
     * @param currentMapping ActionMapping
     * @param request HttpServletRequest
     */
    protected void release(ActionMapping currentMapping,
                           HttpServletRequest request) {

        String curFormName = "";
        boolean bFind = false;
        ActionMapping mapping = currentMapping;
        String form = mapping.getAttribute();
        List listForm = new ArrayList();
        HttpSession session = request.getSession();
        if (session == null)
            return;

        Object obj = session.getAttribute(Constant.LIST_FORM);
        if (obj != null) {
            listForm = (ArrayList) obj;
        }

        if (form != null && session.getAttribute(form) != null) {
            log.showLog("------delete form-------size---" + listForm.size());

            //������б����������form,��Ҫ���ö�ջ�㷨ʵ��
            for (int idx = 0; idx < listForm.size(); idx++) {
                curFormName = (String) listForm.get(idx);
                log.showLog("-----equals----" + form  + " = " + curFormName);
                if (form.equals(curFormName)) {
                    bFind = true;

                    listForm.remove(idx);
                    listForm.add(form);
                    session.setAttribute(Constant.LIST_FORM, listForm);
                    return;
                }
            }
            listForm.add(form); //����ƨ�ɺ���
            if (listForm.size() > this.MAX_SIZE) {
                curFormName = (String) listForm.get(0);

                session.removeAttribute(curFormName);
                try{
                    session.setAttribute(curFormName,this.createFormBean(request, curFormName));
                } catch(Exception e){
                    e.printStackTrace();
                }
                listForm.remove(0); //ɾ���׸�
                log.showLog("-----equals----" + form  + " = " + curFormName);
                //session.removeAttribute(form);
                log.showLog("-*****%%%%%--* ---remove form-------size---" +
                            listForm.size());
            }
        }
        session.setAttribute(Constant.LIST_FORM, listForm);

    }

    /**
     * �õ���������Ӧ�þ���Action��Parameter������
     * ���û�����û�Ϊ"auto"��Ĭ����executeIt
     * 
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * 
     * @return String ������
     * @throws DefaultException
     */

    private String findMethod(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
        ServletException, DefaultException, Exception {
        String parameter = mapping.getParameter();
        if (parameter == null || "auto".equalsIgnoreCase(parameter))
            parameter = "executeIt";
        return parameter;
    }

    /**
     * ͨ��Method��ִ��ָ���ķ�����
     * Ҫ�󷽷�ֻ����һ������������GoaActionConfig������ֵ��String���ͣ�������
     * 
     * @param cfg 				GoaActionConfig
     * @param methodName 		String	��Ҫִ�еķ�����
     * @return ActionForward 	@see ActionForward
     * 
     * @throws DefaultException
     */
    protected String performMethod(GoaActionConfig cfg, String methodName) throws
        IOException,
        ServletException, DefaultException, Exception {

        Method method = null;
        try {
            log.console(this.getClass(), "performMethod()->methodNam", methodName);
        	method = getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.console(this.getClass(), "performMethod()->methodNam", "������"+methodName+"û�ҵ���");
            throw new DefaultException(e.getMessage());
        }

        String forward = null;
        try {
            Object args[] = {cfg};
            // ִ��ָ���ķ���
            forward = (String) method.invoke(this, args);

        } catch (ClassCastException e) {
            e.printStackTrace();
            String message = messages.getMessage("dispatch.return",
                                                 cfg.getMapping().getPath(),
                                                 methodName);
            throw new DefaultException(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            String message = messages.getMessage("dispatch.error",
                                                 cfg.getMapping().getPath(),
                                                 methodName);
            throw new DefaultException(message);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DefaultException(e.getTargetException().getMessage());
        }
        return forward;
    }

    /**
     * �õ�������Method��һ��ʵ������������Ϊname������ΪGoaActionConfig��
     * @param name ������
     * @return Method ������
     * @throws NoSuchMethodException û�з��ִ˷���
     */

    private Method getMethod(String name) throws NoSuchMethodException {

        Class types[] = {GoaActionConfig.class};

        synchronized (methods) {
            Method method = (Method) methods.get(name);
            if (method == null) {
                method = this.getClass().getMethod(name, types);
                methods.put(name, method);
            }
            return (method);
        }

    }


    /**
     * �ȴ�Action���õ�name���Ի��Form���ƣ�����ActionFormʵ�����󲢷��أ�
     * 
     * @param form		ActionForm
     * @param request	HttpServletRequest
     * @param path		String		Action���õ�path����
     * @return			ActionForm	�������ɵ�Formʵ������
     * 
     * @throws DefaultException
     */
    private ActionForm getFormBean(ActionForm form,
                                   HttpServletRequest request,
                                   String path) throws DefaultException {

        if (form != null) return form;

        ServletContext application = request.getSession().getServletContext();
        String key = org.apache.struts.Globals.ACTION_SERVLET_KEY;
        Object obj = application.getAttribute(key);
        ActionServlet servlet = (ActionServlet) obj;
        
        ActionMapping mapping = servlet.findMapping(path);
        String formName = mapping.getName();
        	log.console(this.getClass(), "getFormBean()->form.name", formName);
        ActionFormBean formBean = servlet.findFormBean(formName);
        String className = formBean.getType();
        	log.console(this.getClass(), "getFormBean()->form.className", className);
        ActionForm instance = null;
        HttpSession session =  request.getSession();
        try {
            Class clazz = Class.forName(className);
            clazz.newInstance();
            instance = (ActionForm) clazz.newInstance();
            session.setAttribute(formName, instance);

        } catch (Exception e) {
            throw new DefaultException("sys_other_001");

        }
        return instance;
    }

    /**
     * ����ActionForm�����ƣ�����ActionFormʵ�����󲢷��أ�
     * 
     * @param request	HttpServletRequest
     * @param formName	String
     * @return	ActionForm
     * @throws DefaultException
     */
    private ActionForm createFormBean(HttpServletRequest request,
                                      String formName) throws DefaultException {

        ServletContext application = request.getSession().getServletContext();
        String key = org.apache.struts.Globals.ACTION_SERVLET_KEY;
        Object obj = application.getAttribute(key);
        ActionServlet servlet = (ActionServlet) obj;
        ActionFormBean formBean = servlet.findFormBean(formName);
        String className = formBean.getType();

        	log.console(this.getClass(), "createFormBean()->form.className", className);
        ActionForm instance = null;
        HttpSession session = request.getSession();
        try {
            Class clazz = Class.forName(className);
            clazz.newInstance();
            instance = (ActionForm) clazz.newInstance();
            session.setAttribute(formName, instance);

        } catch (Exception e) {
            throw new DefaultException("sys_other_001");

        }
        return instance;
    }
}
