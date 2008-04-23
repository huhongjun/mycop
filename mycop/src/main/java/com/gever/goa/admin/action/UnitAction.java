package com.gever.goa.admin.action;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.UnitDao;
import com.gever.goa.admin.vo.UnitVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: 单位控制器</p>
 * <p>Description: 单位控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class UnitAction
    extends BaseAction {
  UnitDao unitDao = null;
  public UnitAction() {
  }

  /**
   * 初始化页面数据
   * @param actionform 当前的vo对象
   * @return 当前所用的vo对象
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    UnitForm myForm = (UnitForm) cfg.getBaseForm(); //得到form变量
    unitDao = AdminFactory.getInstance().createUnit(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) unitDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new UnitVO());
    }
  }

  /**
  * 新增动作(继续返回到本页面新增)
  * @param cfg 当前Action相关配置类
  * @param isBack 是否返回
  * @return forward地址
  * @throws DefaultException
  * @throws Exception
  */
 protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
     DefaultException,
     Exception {
   String retforword=null;
   try{
     retforword=super.doInsert(cfg, isBack);
   }catch(DefaultException e){
     if(e.getMessage().equals("PK repeat!")||e.getMessage().equals("sys_no_insert")){
       retforword=super.FORWORD_EDIT_PAGE;
     }else{
       throw e;
     }
   }
   return retforword;
 }
 protected String doPage(GoaActionConfig cfg,String pageType)throws DefaultException,
     Exception{
   UnitForm form = (UnitForm)cfg.getBaseForm();
   String forword=super.doPage(cfg,pageType);
   form.setDatasize(form.getPageControl().getData().size());
   return forword;
 }
}
