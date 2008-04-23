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
     *  �ж�SESSION�Ƿ�ʱ
     * @author: Eddy
     *
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        HttpSession session = request.getSession(true);

        ServletContext context = this.servlet.getServletContext();

        // �ж��Ƿ�ʱ����ʱ��session��գ�ȡ����userName
        String actionUrl = request.getRequestURI();
        String userName = (String) session.getAttribute(Constants.USERNAME);

        // �Ƿ��½��ǳ���ACTION
        boolean login = ((actionUrl.indexOf("Login.do") == -1) &&
            (actionUrl.indexOf("loginAction.do") == -1));
        //���ȡ�����û������Ҳ��ǵ�¼��ǳ�Action����Ϊ��ʱ
        if ((userName == null) && login) {
            return mapping.findForward("timeout_page");
        }

        // �ж�Ȩ�� --------
        // �������ļ��л�ȡ�Ƿ���ACTIONȨ��
        if (isCheckPrivilege(session)) {
            String method = (String) request.getParameter("action");

            if (method == null) {
                method = "list";
            }

            //ȡ�ø�ACTION�������ļ��е�ǰ׺
            String preFix = mapping.getModuleConfig().getPrefix();

            if ("".equals(preFix)) {
                preFix = "config/";
            } else {
                preFix = "config" + preFix;
            }

            String actionPath = preFix + mapping.getPath();

            //ȡ�õ�ǰACTION����Դ��������
            PermissionMap perMap = PermissionMapACL.getResOpratByActionPath(context,
                    actionPath, method);

            if (perMap == null) {
                return mapping.findForward("access_deny");
            }

            //�жϵ�ǰ�û��Ƿ�Ե�ǰACTION��Ȩ�޲���
            boolean right = PermissionUtil.checkPermissionByCode(session,
                    perMap.getResOpCode(), perMap.getResCode());

            if (!right && login) {
                return mapping.findForward("access_deny");
            }
        }

        return super.execute(mapping, form, request, response);
    }

    /*
     * ȡ�������ļ����Ƿ��ж�ACTIONȨ�޵�ֵ
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
