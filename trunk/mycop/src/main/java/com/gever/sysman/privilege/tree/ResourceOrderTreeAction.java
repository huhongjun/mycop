package com.gever.sysman.privilege.tree;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gever.sysman.privilege.dao.impl.ResOrderDAOIMP;
import com.gever.sysman.privilege.dao.ResOrderDAO;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
/**
 *
 * <p>Title:资源排序Action </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004-11-17</p>
 * <p>Company:GEVER </p>
 * @author Hu.Walker
 * @version 1.0
 */


public class ResourceOrderTreeAction extends BaseAction {
  //##############################以下代码的作用是资源结构调整的代码，现在没有被使用##################
  public ActionForward exchengId(
      ActionMapping mapping,
      ActionForm actionform,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception{
      int id1 = Integer.parseInt(request.getParameter("id1"));
      int id2 = Integer.parseInt(request.getParameter("id2"));
      ResOrderDAO rsDAO = new ResOrderDAOIMP();
      rsDAO.exchangeResID(id1,id2);
      return mapping.findForward("ordertree");
  }
    public ActionForward moveId(
    ActionMapping mapping,
    ActionForm actionform,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception{
    int id1 = Integer.parseInt(request.getParameter("id1"));
    int id2 = Integer.parseInt(request.getParameter("id2"));
    ResOrderDAO rsDAO = new ResOrderDAOIMP();
    rsDAO.moveResID(id1,id2);
    return mapping.findForward("ordertree");

  }
  public ActionForward exchengParentId(
      ActionMapping mapping,
      ActionForm actionform,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception{
        int id1 = Integer.parseInt(request.getParameter("id1"));
        int id2 = Integer.parseInt(request.getParameter("id2"));
        ResOrderDAO rsDAO = new ResOrderDAOIMP();
        rsDAO.exchangePID(id1,id2);
        return mapping.findForward("ordertree");

    }
    public ActionForward moveOpeOrderid(
      ActionMapping mapping,
      ActionForm actionform,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception{
        int id1 = Integer.parseInt(request.getParameter("id1"));
        int id2 = Integer.parseInt(request.getParameter("id2"));
        String resid= request.getParameter("resid");
        ResOrderDAO rsDAO = new ResOrderDAOIMP();
        rsDAO.moveOptionID(id1,id2);
        request.setAttribute("resid",resid);

        return mapping.findForward("optorder");

    }
//##############################以上代码的作用是资源结构调整的代码，现在没有被使用##################
    public ActionForward moveResourceOrderId(
      ActionMapping mapping,
      ActionForm actionform,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception{

        int id1 = Integer.parseInt(request.getParameter("id1"));
        int id2 = Integer.parseInt(request.getParameter("id2"));
        String resid= request.getParameter("resid");
        String selectid=request.getParameter("id2");
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        ResOrderDAO rsDAO = factory.getResOrderDAO();
      //  ResOrderDAO rsDAO = new ResOrderDAOIMP();
        rsDAO.moveResourceOrderId(id1,id2);
        request.setAttribute("resid",resid);
        request.setAttribute("selectid",selectid);

       return mapping.findForward("orderresource");

    }




}
