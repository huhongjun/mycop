package com.gever.sysman.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.gever.config.Environment;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.sysman.log.dao.LogDAOFactory;
import com.gever.sysman.log.dao.LogDao;
import com.gever.sysman.privilege.model.PermissionMap;
import com.gever.sysman.privilege.util.Constants;
import com.gever.sysman.privilege.util.PermissionMapACL;
import com.gever.sysman.privilege.util.PermissionUtil;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author
 * @version 0.5
 */
public class BaseAction extends DispatchAction {
    private String dbData = "gdp";

    public BaseAction() {
    }

    public void setDbData(String dbData) {
        this.dbData = dbData;
    }

    public int addLog(HttpServletRequest request, String module, String action,
        String memo) throws DAOException {
        LogDao dao = LogDAOFactory.getInstance().getLogDao();

        HttpSession session = request.getSession();
        String ipAddress = request.getRemoteAddr();
        String userName = (String) session.getAttribute(Constants.USERNAME);

        try {
            return dao.addLog(userName, module, action, ipAddress, memo, 0, 0);
        } catch (DefaultException e) {
            throw new DAOException("ORG_Log_001", e);
        }
    }

    /*
     *  判断SESSION是否超时
     * @author: Eddy
     *
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        HttpSession session = request.getSession(true);

        ServletContext context = this.servlet.getServletContext();

        // 判断是否超时，超时后session清空，取不到userName
        String actionUrl = request.getRequestURI();
        String userName = (String) session.getAttribute(Constants.USERNAME);

        // 是否登陆或登出的ACTION
        boolean login = ((actionUrl.indexOf("Login.do") == -1) &&
            (actionUrl.indexOf("loginAction.do") == -1));
        //如果取不到用户名并且不是登录或登出Action，则为超时
        if ((userName == null) && login) {
            return mapping.findForward("timeout_page");
        }

        // 判断权限 --------
        // 从配置文件中获取是否检测ACTION权限
        if (isCheckPrivilege(session)) {
            String method = (String) request.getParameter("action");

            if (method == null) {
                method = "list";
            }

            //取得该ACTION在配置文件中的前缀
            String preFix = mapping.getModuleConfig().getPrefix();

            if ("".equals(preFix)) {
                preFix = "config/";
            } else {
                preFix = "config" + preFix;
            }

            String actionPath = preFix + mapping.getPath();

            //取得当前ACTION的资源操作代码
            PermissionMap perMap = PermissionMapACL.getResOpratByActionPath(context,
                    actionPath, method);

            if (perMap == null) {
                return mapping.findForward("access_deny");
            }

            //判断当前用户是否对当前ACTION有权限操作
            boolean right = PermissionUtil.checkPermissionByCode(session,
                    perMap.getResOpCode(), perMap.getResCode());

            if (!right && login) {
                return mapping.findForward("access_deny");
            }
        }

        return super.execute(mapping, form, request, response);
    }

    /*
     * 取得配置文件中是否判断ACTION权限的值
     * @param session
     * @return
     */
    private boolean isCheckPrivilege(HttpSession session) {
        String isCheck = (String) session.getAttribute("IS_CHECK_PRIVIEGE");

        if ((isCheck == null) || "".equals(isCheck)) {
            isCheck = Environment.getProperty("privilege");
        }

        if ((isCheck != null) && !"false".equals(isCheck)) {
            return true;
        }

        return false;
    }
}
