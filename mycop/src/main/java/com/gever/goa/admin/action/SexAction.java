package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.SexDao;
import com.gever.goa.admin.vo.SexVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: 职务控制器</p>
 * <p>Description: 职务控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SexAction
    extends BaseAction {
  SexDao sexDao = null;
  public SexAction() {
  }

  /**
   * 初始化页面数据
   * @param actionform 当前的vo对象
   * @return 当前所用的vo对象
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    SexForm myForm = (SexForm) cfg.getBaseForm(); //得到form变量
    sexDao = AdminFactory.getInstance().createSex(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) sexDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
    if (myForm.getVo() == null)
      myForm.setVo(new SexVO());
  }

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    String reforword = null;
    HttpServletRequest request = cfg.getRequest();
    SexForm sexForm = (SexForm) cfg.getBaseForm();
    SexVO sexVO = (SexVO) sexForm.getVo();
    String sex_code = sexVO.getSex_code();
    byte[] sex_code_byte = sex_code.getBytes();
    int sex_code_int = sex_code_byte.length;
    if (sex_code_int > 4) {
      request.setAttribute("ErrorMsg", "性别代码是汉字为两位，重新输入！");
      return this.FORWORD_EDIT_PAGE;
    }

    try {
      reforword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("pk")) {
        // throw new DefaultException("性别代码重复,请重新填写!");
        request.setAttribute("ErrorMsg", "性别代码重复,请重新填写!");
        return this.FORWORD_EDIT_PAGE;
      }
      else throw e;
    }
    return reforword;
  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    String forward = null;
    HttpServletRequest request = cfg.getRequest();
    SexForm sexForm = (SexForm) cfg.getBaseForm();
    SexVO sexVO = (SexVO) sexForm.getVo();
    String sex_code = sexVO.getSex_code();
    byte[] sex_code_byte = sex_code.getBytes();
    int sex_code_int = sex_code_byte.length;
    if (sex_code_int > 4) {
      request.setAttribute("ErrorMsg", "性别代码是汉字为两位，重新输入！");
      return this.FORWORD_EDIT_PAGE;
    }
    try{
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
