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

import com.gever.exception.DefaultException;
import com.gever.util.EncodeUtil;

/**
 * @author Hu.Walker
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 *
 */
public class DefaultExceptionHandler extends ExceptionHandler {
    private String message;
    private String returnPage;
    /**
     *
     */
    public DefaultExceptionHandler() {
    //super(s);

    }

    public ActionForward execute(
        Exception ex,
        ExceptionConfig config,
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException {

        DefaultException e = (DefaultException) ex;
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

        String forward = "info_page";
        System.out.println(this.getClass().getName()+"->"+"eror info is "+level);
        if (DefaultException.INFO.equals(level)) {
            forward = "info_page";
        } else if (DefaultException.WARN.equals(level)) {
            forward = "info_ok_page";
        } else {
            forward = "error_page";
        }
        return (mapping.findForward(forward));
    }

}