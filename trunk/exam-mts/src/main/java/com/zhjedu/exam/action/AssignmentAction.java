package com.zhjedu.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zhjedu.exam.action.BaseAction;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.UserSessionInfo;

/**
 * @author Administrator
 * 
 */
public class AssignmentAction extends BaseAction {

	/**
	 * 根据作业的状态获得数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if(!UserSessionInfo.studentIsLogin(request)){
			return mapping.findForward("studentLogin");
		}
		ActionForward af = null;
//		String courseId = request.getParameter("courseId");
		String status = request.getParameter("status");
		request.setAttribute("status", status);
		request.setAttribute("courseId", "");
		String page = request.getParameter("page");

		String userid = UserSessionInfo.getStudentId(request) + "";
		PageObject po = this.getAssignmentService().getAssignmentObject(userid, status, "", page);
		request.setAttribute("po", po);

		af = mapping.findForward("assignment_list");

		return af;
	}

	/**
	 * 通过用户ID，作业状态和作业ID查询单条记录的明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getAssignment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if(!UserSessionInfo.studentIsLogin(request)){
			return mapping.findForward("studentLogin");
		}
		Object[] q = null;
		ActionForward af = null;
		String status = request.getParameter("status");
		String userid = UserSessionInfo.getStudentId(request) + "";
		String assignmentId = request.getParameter("assignmentId");

		if (userid != null) {

			try {
				q = this.getAssignmentService().getAssignment(userid, status,
						assignmentId);
				// System.out.println("------/////////----getAssignment---///////////-------");
				request.setAttribute("qq", q);
				af = mapping.findForward("addassignment_view_s");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return af;
	}

	/**
	 * 统计首页新作业的数目
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		if(!UserSessionInfo.studentIsLogin(request)){
			return actionMapping.findForward("studentLogin");
		}
		String userid = UserSessionInfo.getStudentId(request) + "";
//		String courseId = request.getParameter("courseId");
		int count = this.getAssignmentService().getList(userid, "");
		request.setAttribute("courseId", "");
		request.setAttribute("count", String.valueOf(count));
		return actionMapping.findForward("index_s");
	}
	

}