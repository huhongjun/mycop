package com.gever.sysman.organization.webapp.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gever.sysman.util.BaseAction;
import com.gever.sysman.organization.dao.DepOrderDAO;
import com.gever.sysman.organization.dao.impl.DepOrderDAOIMP;
import com.gever.sysman.organization.dao.OrganizationFactory;

/**
 * 
 * <p>
 * Title:部门排序Action
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004-11-24
 * </p>
 * <p>
 * Company:GEVER
 * </p>
 * 
 * @author Hu.Walker
 * @version 1.0
 */

public class OrderDepartmentAction extends BaseAction {
	public ActionForward exchengId(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id1 = Integer.parseInt(request.getParameter("id1"));
		int id2 = Integer.parseInt(request.getParameter("id2"));
		DepOrderDAO rsDAO = new DepOrderDAOIMP();
		rsDAO.exchangeResID(id1, id2);
		return mapping.findForward("ordertree");
	}

	public ActionForward moveId(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id1 = Integer.parseInt(request.getParameter("id1"));
		int id2 = Integer.parseInt(request.getParameter("id2"));
		DepOrderDAO rsDAO = new DepOrderDAOIMP();
		//  System.out.println("==============the start
		// moveResID====================");
		rsDAO.moveResID(id1, id2);
		return mapping.findForward("ordertree");

	}

	public ActionForward exchengParentId(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id1 = Integer.parseInt(request.getParameter("id1"));
		int id2 = Integer.parseInt(request.getParameter("id2"));
		DepOrderDAO rsDAO = new DepOrderDAOIMP();
		rsDAO.exchangePID(id1, id2);
		return mapping.findForward("ordertree");

	}

	//  public ActionForward moveOpeOrderid(
	//    ActionMapping mapping,
	//    ActionForm actionform,
	//    HttpServletRequest request,
	//    HttpServletResponse response) throws Exception{
	//      int id1 = Integer.parseInt(request.getParameter("id1"));
	//      int id2 = Integer.parseInt(request.getParameter("id2"));
	//      String resid= request.getParameter("resid");
	//      ResOrderDAO rsDAO = new ResOrderDAOIMP();
	//      rsDAO.moveOptionID(id1,id2);
	//      request.setAttribute("resid",resid);
	//
	//      return mapping.findForward("optorder");
	//#############################################以上代码是部门结构调整的代码，暂时没有使用######
	public ActionForward moveDeparmentOrderId(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id1 = Integer.parseInt(request.getParameter("id1"));
		int id2 = Integer.parseInt(request.getParameter("id2"));
		String resid = request.getParameter("resid");
		String selectid = request.getParameter("id2");
		//DepOrderDAOIMP rsDAO = new DepOrderDAOIMP();

		DepOrderDAO depDAO = OrganizationFactory.getInstance().getDepOrderDAO();
		depDAO.moveDepartmentOrderId(id1, id2);
		request.setAttribute("resid", resid);
		request.setAttribute("selectid", selectid);

		return mapping.findForward("orderdepartment");

	}

}

