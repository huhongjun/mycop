package com.gever.web.menusetup.action;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;



import com.gever.sysman.privilege.util.Constants;
import com.gever.sysman.util.*;

import org.apache.commons.lang.*;

import org.apache.struts.action.*;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.dao.PermissionDAO;
import com.gever.util.log.*;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.homepage.vo.*;
import com.gever.web.menusetup.dao.MenuSetupDao;
import com.gever.web.menusetup.dao.MenuSetupFactory;
import com.gever.web.menusetup.form.MenuSetupForm;


/**
 * <p>Title: 菜单定制控制器</p>
 * <p>Description: 包括隐蔽,新增菜单,调整菜单顺序等功能</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */
public class MenuSetupAction extends BaseAction {
    private final static String DIR_PATH = "dir"; //目录页面
    private final static String HIDE_PATH = "hide"; //隐藏页面
    private final static String LEFT_PATH = "left"; //隐藏页面
    private final static String DEL_PATH = "del"; //隐藏页面
    private final static String HIDE_DATA_PATH = "hidedata"; //隐藏页面
    private final static String XTREE_PATH = "xtree"; //隐藏页面
    private final static String EDIT_PATH = "edit"; //新增页面
    private final static String DEFAULT_PATH = "index"; //默认页面
    private final static String MENUSETUP_PATH = "menusetup"; //默认页面
    private final static String ROOT_NODE = "-1"; //根节点
    private static final String PATH_SPLIT = "-->"; //层次目录分隔
    private String dbData = "gdp"; //数据源信息
    private int MAX_LEVEL = 5;
    private int MAX_MENUS = 10;
    private String module = "自定义菜单";
    private Log log = Log.getInstance(MenuSetupAction.class);
    private int level = 0;

    public MenuSetupAction() {
    }

    //得到当前用户ID
    public String getUserID(MenuSetupForm form, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(Constants.USERID);

        if ((userId == null) || "".equals(userId)) {
            userId = "0";
        }

        if ((form.getUserID() == null) || (!userId.equals(form.getUserID()))) {
            form.setUserID(userId);
        }

        return userId;
    }

    //菜单定制主页面
    public ActionForward toHome(ActionMapping mapping, ActionForm actionform,
        HttpServletRequest request, HttpServletResponse response)
        throws DAOException, DefaultException, ServletException, IOException {
        ActionForward forward;

        try {
            String strForward = DEFAULT_PATH;
            forward = mapping.findForward(DEFAULT_PATH);

            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            HttpSession session = request.getSession();
            MenuSetupForm form = (MenuSetupForm) actionform;

            String strUserId = getUserID(form, request);
            form.setIsRoot("true");

            String action = request.getParameter("actionFlag");

            //如果是恢复为默认值
            if ("default".equals(action)) {
                dao.resetDefaultMenus(form.getUserID());

                form.getVo().setParentid("0");
                super.addLog(request, this.module, "save", "执行恢复默认菜单动作");
                request.setAttribute("isLoad", "true");
            } else if ("addNew".equals(action)) {
                return this.toEditMenu(mapping, actionform, request, response);
            }

//====================================================================================
/*
            ****************************************************************
            * 用户定制菜单时，实时更新权限   杨帆 2004-11-26修改                *
            ****************************************************************
*/
          // 级联更新菜单定制模块
          PrivilegeFactory factory = PrivilegeFactory.getInstance();
          //RoleDAO roleDAO = factory.getRoleDAO();
          //UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
          OperationDAO optDAO = factory.getOperationDAO();
          ResourceDAO resDAO = factory.getResourceDAO();
          PermissionDAO pmDAO = factory.getPermissionDAO();

          Collection opt_collect = null;
          Collection res_collect = null;
          Collection user_res_opt_collect = null;
          Collection user_role_perm_collect = null;
          Collection user_role1_perm_collect=null;
          //user_role_perm_collect=optDAO.getOptByRoleid(Long.parseLong(rolesId));
          //根据用户id获得所有的资源操作集合（即用户权限）
          user_res_opt_collect = optDAO.getOptByUserid(Long.parseLong(strUserId));
          //根据用户id获得用户所有角色权限集合
          user_role1_perm_collect = optDAO.getUserRolePerm(strUserId);
          //根据角色id查出所有的用户，并且将权限都给所查的用户

          //将用户本身的权限与用户角色权限合并，得到最终的用户权限
          user_res_opt_collect = pmDAO.getUserAndRolePerm(Long.parseLong(strUserId),
              user_res_opt_collect, user_role1_perm_collect);
          if (user_res_opt_collect != null) {
              // 获取用户拥有的资源
              user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect);
              // 获取所有资源（包括父资源）
              user_res_opt_collect = pmDAO.getAllResources(
                  user_res_opt_collect);
          }

          //Collection opt_isOpt_collect = pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
          //Collection ress = pmDAO.returnRess(res_collect, opt_isOpt_collect);
          UserMenuDao userMenuDao = UserMenuDAOFactory.getInstance().getUserMenuDao();

          userMenuDao.resetUserMenus(strUserId,
                             user_res_opt_collect);
//========================================================================================
            //查找资料
            List allMenus = (ArrayList) dao.queryAll(form.getUserID());
            form.setHomeData((ArrayList) dao.queryFirstMenus(allMenus));

            //求出所有子项菜单的总和
            int sum = 0;
            int hides = 0;
            int levels = 0;

            for (int idx = 0; idx < form.getHomeData().size(); idx++) {
                UserMenuVO vo = new UserMenuVO();
                vo = (UserMenuVO) form.getHomeData().get(idx);
                sum += NumberUtils.stringToInt(vo.getNextnodenum());
                hides += NumberUtils.stringToInt(vo.getHidenum());
                levels += NumberUtils.stringToInt(vo.getSumnum());
            }

            if (form.getHomeData().size() < this.MAX_MENUS) {
                form.setHasInsert("true");
            } else {
                form.setHasInsert("false");
            }

            form.setSum(sum);
            form.setHides(hides);
            form.setLevelnums(levels);
            request.setAttribute("MenuSetupForm", form);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_MenuSetup_007",
                DefaultException.ERROR, ex);
        }

        return forward;
    }

    // 到菜单设置页面
    public ActionForward toMenuSetup(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        ActionForward forward;

        try {
            MenuSetupForm form = (MenuSetupForm) actionform;
            String strForward = DEFAULT_PATH;
            forward = null;

            String curNodeid = request.getParameter("nodeid");
            String curNodeName = request.getParameter("nodename");

            if (((curNodeid == null) || "".equals(curNodeid)) ||
                    this.ROOT_NODE.equals(curNodeid)) {
                curNodeid = "0";
                curNodeName = "根菜单";
            }

            //curNodeName乱码，changed by zxczf
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute(Constants.USERID);

            if ((userId == null) || "".equals(userId)) {
                userId = "0";
            }

            UserMenuVO menu = new UserMenuVO();
            menu.setEmpid(userId);
            menu.setNodeid(curNodeid);
            menu = MenuSetupFactory.getInstance().getMenuSetupDao().queryByPk(menu);

            if (menu != null) {
                curNodeName = menu.getNodename();
            }

            String isFolder = request.getParameter("isFolder");
            form.setNodeid(curNodeid);
            form.setNodename(curNodeName);
            form.getVo().setNodename(curNodeName);
            form.setIsFolder(isFolder);

            String strUserId = getUserID(form, request);

            setMenuSetupData(form, curNodeid);

            request.setAttribute("MenuSetupForm", form);

            forward = mapping.findForward(MENUSETUP_PATH);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return forward;
    }

    //菜单设置
    public ActionForward doMenuSetup(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        ActionForward forward;

        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            MenuSetupForm form = (MenuSetupForm) actionform;

            //如果是返回
            if (request.getParameter("back") != null) {
                return mapping.findForward(this.DEFAULT_PATH);
            }

            String strUserId = getUserID(form, request);

            String[] values = request.getParameterValues("addRightListBox");
            String nodeid = request.getParameter("nodeid");
            String nodename = request.getParameter("nodename");

            UserMenuVO vo = new UserMenuVO();

            vo.setNodeid(nodeid);
            vo.setNodename(form.getVo().getNodename());
            vo.setEmpid(form.getUserID());

            dao.update(vo);

            dao.move(values, form.getUserID(), nodeid);
            forward = mapping.findForward(MENUSETUP_PATH);
            setMenuSetupData(form, nodeid);
            request.setAttribute("isLoad", "true");
            request.setAttribute("MenuSetupForm", form);
            super.addLog(request, this.module, "save", "执行菜单自定义动作");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return forward;
    }

    //设置定制页面数据
    public void setMenuSetupData(MenuSetupForm form, String nodeid)
        throws DAOException {
        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            Collection cMenus = dao.queryAll(form.getUserID());
            this.level = getLevel(nodeid, (ArrayList) cMenus);
            form.setSelMenu(dao.getNextMenu(nodeid, (ArrayList) cMenus));

            if ((form.getSelMenu().size() < this.MAX_MENUS) &&
                    ((this.level + 1) < this.MAX_LEVEL)) {
                form.setHasInsert("true");
            } else {
                form.setHasInsert("false");
            }

            form.setMaxMenus(String.valueOf(this.MAX_MENUS));
            form.getVo().setNodeid(nodeid);
            form.setOutOption(dao.getTreeOption(cMenus));
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }
    }

    // 得到xml的数据
    public ActionForward toXTree(ActionMapping mapping, ActionForm actionform,
        HttpServletRequest request, HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
        MenuSetupForm form = (MenuSetupForm) actionform;
        String strForward = this.DEFAULT_PATH;
        ActionForward forward = null;
        String curNodeid = request.getParameter("nodeid");

        // curNodeid = curNodeid.trim();
        String curNodeName = request.getParameter("nodename");
        String isFolder = request.getParameter("isFolder");
        String strUserId = getUserID(form, request);
        String context = request.getContextPath();

        //        Log.setUselog4j(false);
        log.showLog("---curNodeid-----" + curNodeid);

        //根节点时
        form.setCurNodeList((ArrayList) dao.queryNextMenus(form.getUserID(),
                curNodeid));

        strForward = this.XTREE_PATH;
        forward = mapping.findForward(strForward);
        request.setAttribute("MenuSetupForm", form);

        return forward;
    }

    // 得到xml的数据
    public ActionForward toRoot(ActionMapping mapping, ActionForm actionform,
        HttpServletRequest request, HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
        MenuSetupForm form = (MenuSetupForm) actionform;
        String strForward = this.DEFAULT_PATH;
        ActionForward forward = null;
        String strUserId = getUserID(form, request);
        String context = request.getContextPath();

        //        Log.setUselog4j(false);
        //根节点时
        strForward = this.LEFT_PATH;
        forward = mapping.findForward(strForward);
        request.setAttribute("MenuSetupForm", form);

        return forward;
    }

    //得到当前层级
    private int getLevel(String nodeid, List allMenus) {
        UserMenuVO vo;
        int curLevel = 0;

        for (int idx = 0; idx < allMenus.size(); idx++) {
            vo = new UserMenuVO();
            vo = (UserMenuVO) allMenus.get(idx);

            if (nodeid.equals(vo.getNodeid())) {
                curLevel = NumberUtils.stringToInt(vo.getLevel());

                break;
            }
        }

        return curLevel;
    }

    // 到隐藏页面当中
    public ActionForward toHideData(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        ActionForward forward;
        String strForward;

        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            MenuSetupForm form = (MenuSetupForm) actionform;
            strForward = this.HIDE_DATA_PATH;
            forward = null;

            String strUserId = getUserID(form, request);
            form.setNodeid(request.getParameter("nodeid"));

            String pNode = request.getParameter("pnodeid");
            log.showLog("pNode==" + pNode);

            // form.setNoSelMenu((ArrayList)dao.queryNextMenus(userId,form.getNodeid()));
            //       Log.setUselog4j(false);
            List allData = (ArrayList) dao.queryAll(strUserId);
            String allNode = dao.getFullNodeid(pNode, allData);
            log.showLog("allNode=" + allNode);

            //当前层级
            this.level = this.getLevel(pNode, allData);

            List arySource = (ArrayList) dao.getNextMenu(pNode, allData);
            int curLevel = this.getLevel(form.getNodeid(), allData);
            int nextLevel = 0;
            List aryTmp = (ArrayList) dao.getNextMenu(form.getNodeid(), allData);
            List aryLeft = new ArrayList();
            boolean bIsFind = false;

            for (int idx = 0; idx < aryTmp.size(); idx++) {
                UserMenuVO vo = new UserMenuVO();
                vo = (UserMenuVO) aryTmp.get(idx);
                bIsFind = false;

                for (int row = 0; row < arySource.size(); row++) {
                    UserMenuVO svo = new UserMenuVO();
                    svo = (UserMenuVO) arySource.get(row);

                    if (vo.getNodeid().equals(svo.getNodeid())) {
                        bIsFind = true;

                        break;
                    }
                }

                nextLevel = NumberUtils.stringToInt(vo.getNextlevel());
                log.showLog("curLevel = " + curLevel + " level=" + level +
                    " nextLevel=" + nextLevel + " bIsFind=" + bIsFind);

                if ((((level + nextLevel + 1) <= this.MAX_LEVEL) &&
                        (bIsFind == false) &&
                        (allNode.indexOf(vo.getNodeid()) < 0)) ||
                        ("0".equals(vo.getIsfolder()) && (bIsFind == false))) {
                    aryLeft.add(vo);
                }
            }

            // form.setNoSelMenu((ArrayList)dao.getNextMenu(form.getNodeid(),allData));
            // log.showLog("-----getNoSelMenu-----" + form.getNoSelMenu().size());
            request.setAttribute("curMenus", aryLeft);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        forward = mapping.findForward(strForward);

        return forward;
    }

    // 转到隐藏菜单当中
    public ActionForward toHideMenu(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        MenuSetupForm form;
        ActionForward forward;

        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            form = (MenuSetupForm) actionform;
            forward = null;

            String strUserId = getUserID(form, request);

            //如果是返回
            if (request.getParameter("back") != null) {
                return mapping.findForward(this.DEFAULT_PATH);
            }

            if (((form.getNodeid() == null) || "".equals(form.getNodeid())) ||
                    this.ROOT_NODE.equals(form.getNodeid())) {
                form.setNodeid("0");
                form.setNodename("根菜单");
            }

            UserMenuVO vo = new UserMenuVO();
            vo.setNodeid(form.getNodeid());
            vo.setEmpid(form.getUserID());

            form.setVo(dao.queryByPk(vo));

            List menus = (ArrayList) dao.queryNextMenus(form.getUserID(),
                    form.getNodeid());

            for (int idx = 0; idx < menus.size(); idx++) {
                vo = new UserMenuVO();
                vo = (UserMenuVO) menus.get(idx);

                if ("1".equals(vo.getIshided())) {
                    form.getShowMenu().add(vo);
                } else {
                    form.getHideMenu().add(vo);
                }
            }

            form.setMaxMenus(String.valueOf(this.MAX_MENUS));
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("MenuSetupForm", form);
        forward = mapping.findForward(this.HIDE_PATH);

        return forward;
    }

    // 转到隐藏菜单当中
    public ActionForward toDelMenu(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        MenuSetupForm form;
        ActionForward forward;

        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            form = (MenuSetupForm) actionform;
            forward = null;

            String strUserId = getUserID(form, request);

            //如果是返回
            if (request.getParameter("back") != null) {
                return mapping.findForward(this.DEFAULT_PATH);
            }

            UserMenuVO vo = new UserMenuVO();
            vo.setNodeid(form.getNodeid());
            vo.setEmpid(form.getUserID());

            form.setVo(dao.queryByPk(vo));

            List menus = (ArrayList) dao.queryByDelete(form.getUserID());

            form.setShowMenu(menus);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("MenuSetupForm", form);
        forward = mapping.findForward(this.DEL_PATH);

        return forward;
    }

    // 执行删除菜单当中
    public ActionForward doDelMenu(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            MenuSetupForm form = (MenuSetupForm) actionform;

            String strUserId = getUserID(form, request);

            String[] showMenus = request.getParameterValues("addRightListBox");

            if (showMenus == null) {
                showMenus = new String[0];
            }

            form.setUserID(strUserId);

            int intRet = dao.delete(form.getUserID(), showMenus);
            List menus = (ArrayList) dao.queryByDelete(form.getUserID());
            form.setShowMenu(menus);

            StringBuffer sb = new StringBuffer();

            for (int idx = 0; idx < showMenus.length; idx++) {
                sb.append(showMenus[idx]).append(",");
            }

            form.setNewNodes(sb.toString());
            request.setAttribute("MenuSetupForm", form);
            request.setAttribute("isLoad", "true");
            super.addLog(request, this.module, "del", "执行删除菜单操作");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        ActionForward forward = mapping.findForward(this.DEL_PATH);

        return forward;
    }

    // 执行隐藏菜单当中
    public ActionForward doHideMenu(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            MenuSetupForm form = (MenuSetupForm) actionform;

            String strUserId = getUserID(form, request);
            String[] hideMenus = request.getParameterValues("addLeftListBox");
            String[] showMenus = request.getParameterValues("addRightListBox");
            String nodeid = request.getParameter("nodeid");
            form.setUserID(strUserId);

            int intRet = dao.hide(showMenus, hideMenus, form.getUserID());
            UserMenuVO vo = new UserMenuVO();
            List menus = (ArrayList) dao.queryNextMenus(form.getUserID(),
                    form.getNodeid());

            for (int idx = 0; idx < menus.size(); idx++) {
                vo = new UserMenuVO();
                vo = (UserMenuVO) menus.get(idx);

                if ("1".equals(vo.getIshided())) {
                    form.getShowMenu().add(vo);
                } else {
                    form.getHideMenu().add(vo);
                }
            }

            request.setAttribute("MenuSetupForm", form);
            request.setAttribute("isLoad", "true");
            super.addLog(request, this.module, "save", "执行隐藏菜单动作");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        ActionForward forward = mapping.findForward(this.HIDE_PATH);

        return forward;
    }

    // 转到架构菜单页面
    public ActionForward toEditMenu(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        MenuSetupForm form;
        ActionForward forward;

        try {
            forward = null;

            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            form = (MenuSetupForm) actionform;

            //如果是返回
            if (request.getParameter("back") != null) {
                return mapping.findForward(this.DEFAULT_PATH);
            }

            String action = request.getParameter("actionFlag");
            String strUserId = getUserID(form, request);
            form.setActionFlag(action);

            //如果是恢复为默认值
            if ("modify".equals(action)) {
                String nodeid = request.getParameter("nodeid");

                UserMenuVO vo = new UserMenuVO();
                vo.setNodeid(nodeid);
                vo.setEmpid(form.getUserID());
                form.setVo(dao.queryByPk(vo));

                vo = new UserMenuVO();
                vo.setNodeid(form.getVo().getParentid());
                vo.setEmpid(form.getUserID());
                form.setPvo(dao.queryByPk(vo));
            } else {
                String nodeid = form.getNodeid();
                log.showLog("---setParentid--nodeid----" + nodeid);

                UserMenuVO vo = new UserMenuVO();

                if (this.ROOT_NODE.equals(nodeid)) {
                    nodeid = "0";
                }

                vo.setNodeid(nodeid);
                vo.setParentid(nodeid);
                vo.setEmpid(strUserId);

                try {
                    form.setPvo(dao.queryByPk(vo));
                } catch (DAOException ex) {
                    throw new DAOException("ORG_MenuSetup_009",
                        DAOException.ERROR, ex);
                }

                vo = new UserMenuVO();
                form.setVo(vo);

                if ("0".equals(nodeid)) {
                    form.getPvo().setNodename("根目录");
                }
            }
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("MenuSetupForm", form);
        forward = mapping.findForward(this.EDIT_PATH);

        return forward;
    }

    // 增加新或修改菜单
    public ActionForward doEditMenu(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response)
        throws DAOException, ServletException, IOException {
        try {
            MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
            MenuSetupForm form = (MenuSetupForm) actionform;
            HttpSession session = request.getSession();

            String strUserId = getUserID(form, request);
            //String userId = session.getAttribute("userid");
            String nodeid = request.getParameter("nodeid");
            String parentid = request.getParameter("pvo.nodeid");
            UserMenuVO vo = new UserMenuVO();
            form.setUserID(strUserId);

            if ("modify".equals(form.getActionFlag())) {
                log.showLog("-------nodeid----" + nodeid);
                log.showLog("-------getParentid----" + parentid);
                form.getVo().setNodeid(nodeid);
                form.getVo().setEmpid(form.getUserID());
                form.getVo().setParentid(parentid);
                dao.update(form.getVo());

                request.setAttribute("actionType", "modify");
                super.addLog(request, this.module, "save", "执行修改菜单名称动作");
            } else {
                if (this.ROOT_NODE.equals(nodeid))
                    nodeid = "0";
                form.getVo().setParentid(nodeid);
                form.getVo().setEmpid(strUserId);
                form.getVo().setIsfolder("1");
                form.getVo().setIshided("0");
                form.getVo().setIsmenu("0");
                form.getVo().setFlag("1");
                dao.insert(form.getVo());
                request.setAttribute("actionType", "add");
                super.addLog(request, this.module, "save", "执行新增菜单名称动作");
            }

            session.setAttribute("menuvo", form.getVo());
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        ActionForward forward = mapping.findForward(this.DEFAULT_PATH);

        return forward;
    }
}
