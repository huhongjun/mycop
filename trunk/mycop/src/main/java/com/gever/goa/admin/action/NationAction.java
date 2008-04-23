package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.NationDao;
import com.gever.goa.admin.vo.NationVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: 民族控制器</p>
 * <p>Description: 民族控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class NationAction
    extends BaseAction {
  NationDao nationDao = null;
  public NationAction() {
  }

  /**
   * 初始化页面数据
   * @param actionform 当前的vo对象
   * @return 当前所用的vo对象
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    NationForm myForm = (NationForm) cfg.getBaseForm(); //得到form变量
    nationDao = AdminFactory.getInstance().createNation(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) nationDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
    if (myForm.getVo() == null)
      myForm.setVo(new NationVO());
  }

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    String retforword = null;
    HttpServletRequest request = cfg.getRequest();
    NationForm nationForm = (NationForm) cfg.getBaseForm();
    NationVO nationVO = (NationVO) nationForm.getVo();
    String national_code = nationVO.getNational_code();
    byte[] national_code_byte = national_code.getBytes();
    int national_code_int = national_code_byte.length;
    if (national_code_int > 4) {
      request.setAttribute("ErrorMsg", "民族代码是汉字为两位，重新输入！");
      return this.FORWORD_EDIT_PAGE;
    }

    try {
      retforword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("PK repeat!")) {
        request.setAttribute("ErrorMsg", "民族代码重复,请重新填写!");
        return this.FORWORD_EDIT_PAGE;
        // throw new DefaultException( "民族代码重复,请重新填写!" );
      }
      else {
        throw e;
      }
    }
    return retforword;
  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    String forward = null;
    HttpServletRequest request = cfg.getRequest();
    NationForm nationForm = (NationForm) cfg.getBaseForm();
    NationVO nationVO = (NationVO) nationForm.getVo();
    String national_code = nationVO.getNational_code();
    byte[] national_code_byte = national_code.getBytes();
    int national_code_int = national_code_byte.length;
    if (national_code_int > 4) {
      request.setAttribute("ErrorMsg", "民族代码是汉字为两位，重新输入！");
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
