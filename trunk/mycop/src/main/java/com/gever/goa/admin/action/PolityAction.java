package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.PolityDao;
import com.gever.goa.admin.vo.PolityVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: 政治控制器</p>
 * <p>Description: 政治控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class PolityAction
    extends BaseAction {
  PolityDao polityDao = null;
  public PolityAction() {
  }

  /**
   * 初始化页面数据
   * @param actionform 当前的vo对象
   * @return 当前所用的vo对象
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    PolityForm myForm = (PolityForm) cfg.getBaseForm(); //得到form变量
    polityDao = AdminFactory.getInstance().createPolity(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) polityDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
    if (myForm.getVo() == null)
      myForm.setVo(new PolityVO());
  }

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    String refoword = null;
    HttpServletRequest request = cfg.getRequest();
    PolityForm polityForm = (PolityForm) cfg.getBaseForm();
    PolityVO polityVO = (PolityVO) polityForm.getVo();
    String polity_code = polityVO.getPolity_code();
    byte[] polity_code_byte = polity_code.getBytes();
    int polity_code_int = polity_code_byte.length;
    if (polity_code_int > 4) {
      request.setAttribute("ErrorMsg", "政治面貌代码是汉字为两位，重新输入！");
      return this.FORWORD_EDIT_PAGE;
    }

    try {
      refoword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("pk")) {
        request.setAttribute("ErrorMsg", "政治面貌代码重复，重新输入！");
        return this.FORWORD_EDIT_PAGE;
        // throw new DefaultException("政治面貌代码重复,请重新填写!");
      }
      else {
        throw e;
      }
    }
    return refoword;
  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    String forward = null;
    HttpServletRequest request = cfg.getRequest();
    PolityForm polityForm = (PolityForm) cfg.getBaseForm();
    PolityVO polityVO = (PolityVO) polityForm.getVo();
    String polity_code = polityVO.getPolity_code();
    byte[] polity_code_byte = polity_code.getBytes();
    int polity_code_int = polity_code_byte.length;
    if (polity_code_int > 4) {
      request.setAttribute("ErrorMsg", "政治面貌代码是汉字为两位，重新输入！");
      return this.FORWORD_EDIT_PAGE;
    }
    try {
      forward = super.doUpdate(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("PK")) {
        return this.FORWORD_LIST_PAGE;
      }
      else throw e;
    }
    return forward;
  }

}
