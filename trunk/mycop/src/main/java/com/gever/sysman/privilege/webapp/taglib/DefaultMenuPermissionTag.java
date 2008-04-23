package com.gever.sysman.privilege.webapp.taglib;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.gever.sysman.privilege.util.PermissionUtil;

/**
 * <p>���⣺Ȩ�ޱ�ǩ-ϵͳĬ�ϲ˵�����Ȩ��</p>
 * <p>����: �жϵ�ǰ�û��Ƿ�ӵ�ж���ϵͳĬ�ϲ˵�Ȩ��</p>
 * <p>�÷�: 
 * 		<perm:defaultmenu rescode="GDP-CDDZ" optcode="MRCD">
 * 	  		......
 * 		</perm:defaultmenu></p>
 * <p>����: rescode-��Դ���룬optcode-��������</p>
 * 
 * @version 1.0
 */

public class DefaultMenuPermissionTag extends TagSupport {
  private String optcode;
  private String rescode;

  public int doStartTag() throws JspException {
    HttpSession session = pageContext.getSession();

    try {

      if (PermissionUtil.checkPermissionByCode(session, optcode, rescode)) {
        return 1;
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public int doEndTag() {
    return (EVAL_BODY_INCLUDE);
  }

  public String getOptcode() {
    return optcode;
  }

  public void setOptcode(String optcode) {
    this.optcode = optcode;
  }


  public String getRescode() {
    return rescode;
  }

  public void setRescode(String rescode) {
    this.rescode = rescode;
  }

  public void setError(ServletRequest request, String msg) {
    //error.setMessage(msg);
    //request.setAttribute("Error",error);
  }

}
