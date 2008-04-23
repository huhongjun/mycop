package com.gever.sysman.export.webapp.action;

import com.gever.sysman.export.dao.ExportDDLDAO;
import com.gever.sysman.util.BaseAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
/**
 *
 * <p>Title:导出资源DDL</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author:黎彪
 * @version 1.0
 */

public class ExportDDLAction extends BaseAction {
  public ActionForward exportDDL(
       ActionMapping mapping,
       ActionForm actionform,
       HttpServletRequest request,
       HttpServletResponse response) throws Exception{
       ExportDDLDAO dao = new ExportDDLDAO();
       //String ddl=dao.exportDB2DDL();
       Collection ddllist = dao.queryAll();
       request.setAttribute("ddllist",ddllist);
       return mapping.findForward("openddl");
   }

}
