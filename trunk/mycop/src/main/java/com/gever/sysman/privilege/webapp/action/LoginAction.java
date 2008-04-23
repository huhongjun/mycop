package com.gever.sysman.privilege.webapp.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gever.config.Environment;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.struts.Constant;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.Department;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PermissionDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.util.Constants;
import com.gever.sysman.util.BaseAction;
import com.gever.util.DateTimeUtils;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.util.ActiveUsers;

/**
 *登录相关Action
 */
public class LoginAction extends BaseAction {
    public static final String NORMAL_PORT = "normal.port"; // 正常端口
    public static final String SSL_PORT = "ssl.port"; // ssl 端口
    public static final String FORWARD_URL = "FORWARD_URL"; // 校验后转发的URL

    public ActionForward login(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        String userName = (String) f.get("name");
        String password = (String) f.get("password");

        try {
            if ((userName == null) || "".equals(userName)) {
                request.setAttribute("isLogon", Boolean.toString(false));
                throw new DefaultException("LOGIN_003", DefaultException.ERROR);
            }

            UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();

            User user = null;
            boolean isLogin = userDAO.checkLogin(userName, password);

            if (isLogin) {
                try {
                    user = (User) userDAO.findUserByUserName(userName);
                    request.getSession(true).setAttribute(Constants.USERNAME,
                        user.getName());
                    request.getSession(true).setAttribute(Constants.LOGINNAME,
                        userName);

                    request.setAttribute("isLogon", Boolean.toString(true));
                } catch (DefaultException e) {
                    request.setAttribute("isLogon", Boolean.toString(false));
                    throw e;
                } catch (Exception e) {
                    request.setAttribute("isLogon", Boolean.toString(false));
                    throw new DefaultException("LOGIN_004",
                        DefaultException.ERROR);
                }

                //====================================================================================

                /*
                                ********************************************************
                                * 用户登录系统时，实时更新权限   杨帆 2004-11-26修改        *
                                ********************************************************
                */
                String strUserId = String.valueOf(user.getId());

                // 级联更新菜单定制模块
                PrivilegeFactory factory = PrivilegeFactory.getInstance();

                OperationDAO optDAO = factory.getOperationDAO();
                PermissionDAO pmDAO = factory.getPermissionDAO();

                Collection user_res_opt_collect = null;
                Collection user_role1_perm_collect = null;

                //根据用户id获得所有的资源操作集合（即用户权限）
                user_res_opt_collect = optDAO.getOptByUserid(Long.parseLong( strUserId));

                //根据用户id获得用户所有角色权限集合
                user_role1_perm_collect = optDAO.getUserRolePerm(strUserId);

                //根据角色id查出所有的用户，并且将权限都给所查的用户
                //将用户本身的权限与用户角色权限合并，得到最终的用户权限
                user_res_opt_collect = pmDAO.getUserAndRolePerm(Long.parseLong(
                            strUserId), user_res_opt_collect,
                        user_role1_perm_collect);

                if (user_res_opt_collect != null) {
                    // 获取用户拥有的资源
                    user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect);
                    // 获取所有资源（包括父资源）
                    user_res_opt_collect = pmDAO.getAllResources(user_res_opt_collect);
                }

                UserMenuDao userMenuDao = UserMenuDAOFactory.getInstance()
                                                            .getUserMenuDao();
                userMenuDao.resetUserMenus(strUserId, user_res_opt_collect);

                //========================================================================================
                request = this.getPermission(user, request);

                // 设置返回端口类型
                return makeReturnType(request, NORMAL_PORT, "success", mapping);

            } else {
                request.setAttribute("isLogon", Boolean.toString(false));
                throw new DefaultException("LOGIN_004", DefaultException.ERROR);
            }
        } catch (DAOException ex) {
            request.setAttribute("isLogon", Boolean.toString(false));
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        }
    }

    /*
     * create by eddy on 20041217
     * 生成新的转发位置，可改变原有的访问协议
     * @param request
     * @param portType  端口类型，即本类定义的两个端口常量：normal_port/ssl_port
     * @param forward  要转发的标记
     * @return
     * @example makeReturnType(request, NORMAL_PORT, "success", mapping);
     */
    private ActionForward makeReturnType(HttpServletRequest request,
        String portType, String forward, ActionMapping mapping) {
        StringBuffer sbForward = new StringBuffer();

        if (NORMAL_PORT.equals(portType)) {
            sbForward.append("http://");
        } else {
            sbForward.append("https://");
        }

        sbForward.append(getRequestAddress(request.getRequestURL().toString()));
        sbForward.append(":");
        sbForward.append(Environment.getProperty(portType));
        sbForward.append(request.getContextPath());
        sbForward.append(mapping.findForward(forward).getPath());

        request.setAttribute(FORWARD_URL, sbForward.toString());

        //return new RedirectingActionForward(sbForward.toString());
        return mapping.findForward("ssl_forward");
    }

    /**
     * create by eddy on 20041220
     * 通过解析访问URL获得查询主地址
     * @param url 访问的URL，通过request获得
     * @return 访问主机
     */
    public static String getRequestAddress(String url) {
        String host = null;

        if ((url != null) && !"".equals(url.trim())) {
            int begin = url.indexOf("//") + 2;
            int end = url.indexOf("/", begin);
            host = url.substring(begin, end);

            if (host.indexOf(":") > -1) {
                host = host.substring(0, host.indexOf(":"));
            }
        }

        return host;
    }

    public ActionForward welcome(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            // 读取配置文件中的服务器的正常端口和SSL端口值
            String normalPort = Environment.getProperty(NORMAL_PORT);
            String sslPort = Environment.getProperty(SSL_PORT);

            request.setAttribute(NORMAL_PORT, normalPort);
            request.setAttribute(SSL_PORT, sslPort);

            checkLicense();
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("license check false", ex);
        }

        return mapping.findForward("login");
    }

    public ActionForward logout(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        request.getSession(true).removeAttribute(Constants.USERID);
        request.getSession(true).removeAttribute(Constants.USER_PERMISSION);
        request.getSession(true).invalidate();

        return mapping.findForward("login");
    }

    private void checkLicense() throws Exception {

    }

    private HttpServletRequest getPermission(User user,
        HttpServletRequest request) throws DefaultException {
        request.getSession(true).setAttribute(Constants.USERID,
            Long.toString(user.getId()));

        // 判断帐号是否被激活
        if (!user.getStatus().equals("1")) {
            throw new DefaultException("LOGIN_006", DefaultException.ERROR);
        }

        // 
        if (!user.getStatus().equals("1") && !user.getUserType().equals("0")) {
            throw new DefaultException("LOGIN_005", DefaultException.ERROR);
        }

        // 判断帐号是否过期
        String date = user.getValidDate();
        if ((DateTimeUtils.getDateSubtract(date, DateTimeUtils.getCurrentDate()) < 0) &&
                !user.getUserType().equals("0")) {
            throw new DefaultException("LOGIN_005", DefaultException.ERROR);
        }

        // 操作，用户-资源-操作，用户-角色-权限
        Collection opt_collect = null;
        Collection user_res_opt_collect = null;
        Collection user_role_perm_collect = null;

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO optDAO = factory.getOperationDAO();
        PermissionDAO pmDAO = factory.getPermissionDAO();

        //获得所有的操作集合
        opt_collect = optDAO.getOperations();
        HttpSession session = request.getSession(true);
        //根据用户id获得所有的资源操作集合（即用户权限）
        user_res_opt_collect = optDAO.getOptByUserid(user.getId());

        //根据用户id获得用户所有角色权限集合
        user_role_perm_collect = optDAO.getUserRolePerm(String.valueOf(
                    user.getId()));
         Collection cDepts = new ArrayList();
        //将用户本身的权限与用户角色权限合并，得到最终的用户权限
        user_res_opt_collect = pmDAO.getUserAndRolePerm(user.getId(),
                user_res_opt_collect, user_role_perm_collect);
        UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
        cDepts =  userDAO.getDepartments(user);

        session.setAttribute(Constant.USER_PERMISSION,  user_res_opt_collect);
        session.setAttribute(Constant.USER_FILTER, user.getLevel());
        session.setAttribute(Constant.USER_NAME, user.getUserName());
        session.setAttribute(Constant.USER_ID, Long.toString(user.getId()));
        session.setAttribute(Constant.NAME, user.getName());
        session.setAttribute(com.gever.sysman.privilege.util.Constants.USERNAME, user.getName());
        session.setAttribute(Constant.DEPT_CODES, this.getDeptCodes(cDepts,0));
        session.setAttribute(Constant.DEPT_NAMES, this.getDeptCodes(cDepts,1));
        session.setAttribute(Constant.LIST_DEPTS, cDepts);
        long lngMaxTime = 3600;
        ActiveUsers atUsers = ActiveUsers.getInstance(lngMaxTime);
        session.setAttribute("isLogon", "true");

        String strAdmin = ("1".equals(user.getUserType())?"true":"false");
        session.setAttribute(Constant.IS_ADMIN, strAdmin);
        return request;
    }

    /**
     * 从？得到部门id号字串(间隔符为,或空格)
     * @param clDepts 当前部门列表
     * @param type 类型：0 - 部门ID；1 - 部门名称
     * @return 部门字串
     */
    private String getDeptCodes(Collection clDepts ,int type){
        List ary = (ArrayList) clDepts;
        StringBuffer sb = new StringBuffer(30);

        for (int idx = 0; idx < ary.size(); idx++) {
            Department dept = OrganizationFactory.getInstance().
                createDepartment();
            dept = (Department)ary.get(idx);
            sb.append( (type == 0) ? String.valueOf(dept.getId()) :
                      dept.getName());
            sb.append(" ");

        }

        return  sb.toString();
    }
    
    //获取随机数
    private String getRandom() {
        int i = 0;
        int t = 0;
        double j = 1;
        String rand = new String("");
        String tmp;

        for (i = 0; i < 4; i++) {
            j = Math.random();
            tmp = new String((new Double(j)).toString());
            t = tmp.indexOf(".", 0);
            tmp = tmp.substring(t + 1, tmp.length());
            rand += tmp;
        }

        while (rand.length() < 64) {
            rand += "0";
        }

        if (rand.length() > 64) {
            rand = rand.substring(0, 64);
        }

        return rand;
    }
}
