package com.gever.web.homepage.action;

import java.util.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gever.sysman.util.BaseAction;

import javax.servlet.http.HttpSession;
import com.gever.sysman.privilege.util.Constants;
import com.gever.util.log.Log;
import com.gever.web.homepage.dao.CreateDefaultMenuDAO;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.homepage.form.UserMenuForm;

import javax.servlet.ServletException;
import java.io.IOException;

import com.gever.exception.db.DAOException;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public class UserMenuAction extends BaseAction{
    private Log log = Log.getInstance(UserMenuAction.class);
    public UserMenuAction() {
    }

    /**
     * @param UserMenuForm form
     * @param HttpServletRequest request
     * @return 先request.getSession()再form获取用户ID
     */
    public String getUserID(UserMenuForm form, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(Constants.USERID);
        if (userId == null || "".equals(userId))
            userId = "0";
        if (form.getUserID() == null || (!userId.equals(form.getUserID()))) {
            form.setUserID(userId);
        }
        return userId;

    }

    /**
     * @param mapping
     * @param actionform
     * @param request
     * @param response
     * @return
     * @throws DAOException
     * @throws ServletException
     * @throws IOException
     */
    public ActionForward homepage(
        ActionMapping mapping,
        ActionForm actionform,
        HttpServletRequest request,
        HttpServletResponse response) throws DAOException, ServletException,
        IOException {

           ActionForward forward = mapping.findForward("homepage");
           UserMenuDao dao = UserMenuDAOFactory.getInstance().getUserMenuDao();
           CreateDefaultMenuDAO cdmDAO = UserMenuDAOFactory.getInstance().getCreateDefaultMenuDao();
           HttpSession session = request.getSession();
           UserMenuForm form = (UserMenuForm) actionform;

           String strUserId = this.getUserID(form,request);
           form.setIsAdmin("false");
           List menus = new ArrayList();
           menus = dao.queryAll(form.getUserID());//获得当前用户的所有菜单
           if(menus.size()==0) {				  //如果
             cdmDAO.copyDefaultMenu(form.getUserID());
             menus = dao.queryAll(form.getUserID());
           }
           request.setAttribute("menus", menus);
           request.setAttribute("xmldata", dao.queryMenus(form.getUserID()));

           return forward;
       }

}