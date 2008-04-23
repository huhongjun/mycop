/*
 * 功能描述 处理DAO异常
 * 创建日期 2004-11-18 14:16:08
 */
package com.gever.struts.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import com.gever.exception.db.DAOException;
import com.gever.util.EncodeUtil;


/**
 * @author Hu.Walker
 */
public class DAOExceptionHandler extends ExceptionHandler {

    public DAOExceptionHandler() {
//        super();
    }

    public ActionForward execute(
        Exception ex,
        ExceptionConfig config,
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException {

        DAOException e = (DAOException) ex;
        String message = e.getMessage();
        if (message == null || "".equals(message.trim())) {
            message = "err.sys";
        }
        ex.printStackTrace(System.err);
        String errorCode = e.getErrorCode();
        String errorDesr = e.getErrorDescription();
        String errorMsg = e.getErrorMessage(errorCode);
        String level = e.getErrorLevel();
        HttpSession session = request.getSession();
        session.setAttribute("errorCode",errorCode);
        session.setAttribute("errorDesr",errorDesr);
        session.setAttribute("errorMsg",EncodeUtil.unicodeToNative(errorMsg));

//        ActionErrors errs = new ActionErrors();
//        errs.add(ActionErrors.GLOBAL_ERROR, new ActionError(message));
//        request.setAttribute(Globals.ERROR_KEY, errs);
        String forward = "info_page";
        if (DAOException.INFO.equals(level)) {
            forward = "info_page";
        } else if (DAOException.WARN.equals(level)) {
            forward = "info_ok_page";
        } else {
            forward = "error_page";
        }
        return (mapping.findForward(forward));
    }
}
