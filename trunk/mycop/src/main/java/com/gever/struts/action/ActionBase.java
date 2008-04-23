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
 * <p>Title: goas1.0版本之后,所有Action的父类</p>
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
     * 重载Struts action的同名方法，自动触发，调用ActionBase的命令中心doActionCenter函数
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
        //动作类型，对应 *.do?action=toList
        String action = StringUtils.nullToString(httpRequest.getParameter("action"));
        String path = actionmapping.getPath();
        try {

        	//
            if (currentPath != null) {

                //通过path来得到ActionMapping
                ActionMapping currentMap = this.getServlet().findMapping(currentPath);
                log.console(this.getClass(),"ActionBase.execute()->当前:ActionMapping.type",currentMap.getType());
                log.console(this.getClass(),"ActionBase.execute()->上次:ActionMapping.type",actionmapping.getType());

                //如果两个Action的type即类名相同,即同一个Action类配置了多个path
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
                //如果没有指定action，就以action的paremeter指定的方法为默认方法
                if ("".equals(action)) action = methodName;

                //执行子类的方法，
                forward = doActionCenter(actionmapping, actionform,
                                         httpRequest, httpResponse, action);
            } else {
                forward = actionmapping.findForward("NO_PERMISSION");
            }//end of if (isHasPermission(actionmapping, httpRequest))

            //更新currentaction的值
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
//            log.showLog("你没有用模板template,请遵守编码规则");
//            throw new IOException("你没有用模板template,请遵守编码规则");
//        }


        return forward;
    }

    //判断是否用到模板，如果没用模板，return false
    //此方法是在开发时使用，开发完毕后，应去掉此方法。
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
            new DefaultException("DefinitionFactory没发现。不过,这与你的代码无关！");
        } catch (DefinitionsFactoryException de) {
            de.printStackTrace();
            new DefaultException("DefinitionFactory例外。不过,这与你的代码无关！");
        }
        return true;
    }

    /**
     * 功能：动作处理中心，应该在子类中被覆盖。
     *      * 
     * @param actionmapping ActionMapping		源自execute()
     * @param actionform 	ActionForm			源自execute()
     * @param httpRequest 	HttpServletRequest	源自execute()
     * @param httpResponse 	HttpServletResponse	源自execute()
     * @param action		String				自定义，核心参数，指定要执行的动作
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
     * 功能：判断是否本Action某个ActionMapping的权限,目前只返回True。以后可以设定。
     * 可能的方案－用xml文件设定某角色对ActionMapping的操作权限。
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
     * 清除session中currentMapping所对应的ActionForm
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

            //如果在列表当中则不用清除form,主要采用堆栈算法实现
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
            listForm.add(form); //加上屁股后面
            if (listForm.size() > this.MAX_SIZE) {
                curFormName = (String) listForm.get(0);

                session.removeAttribute(curFormName);
                try{
                    session.setAttribute(curFormName,this.createFormBean(request, curFormName));
                } catch(Exception e){
                    e.printStackTrace();
                }
                listForm.remove(0); //删除首个
                log.showLog("-----equals----" + form  + " = " + curFormName);
                //session.removeAttribute(form);
                log.showLog("-*****%%%%%--* ---remove form-------size---" +
                            listForm.size());
            }
        }
        session.setAttribute(Constant.LIST_FORM, listForm);

    }

    /**
     * 得到方法名，应该就是Action的Parameter参数。
     * 如果没有设置或为"auto"，默认是executeIt
     * 
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * 
     * @return String 方法名
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
     * 通过Method类执行指定的方法。
     * 要求方法只能有一个参数，且是GoaActionConfig，返回值是String类型，代表？。
     * 
     * @param cfg 				GoaActionConfig
     * @param methodName 		String	将要执行的方法名
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
            log.console(this.getClass(), "performMethod()->methodNam", "方法："+methodName+"没找到！");
            throw new DefaultException(e.getMessage());
        }

        String forward = null;
        try {
            Object args[] = {cfg};
            // 执行指定的方法
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
     * 得到方法体Method的一个实例，方法名称为name，参数为GoaActionConfig。
     * @param name 方法名
     * @return Method 方法类
     * @throws NoSuchMethodException 没有发现此方法
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
     * 先从Action配置的name属性获得Form名称，生成ActionForm实例对象并返回；
     * 
     * @param form		ActionForm
     * @param request	HttpServletRequest
     * @param path		String		Action配置的path属性
     * @return			ActionForm	返回生成的Form实例对象
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
     * 传入ActionForm的名称，生成ActionForm实例对象并返回；
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
