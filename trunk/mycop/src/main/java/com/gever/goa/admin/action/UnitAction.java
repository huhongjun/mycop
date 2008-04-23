package com.gever.goa.admin.action;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.UnitDao;
import com.gever.goa.admin.vo.UnitVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: ��λ������</p>
 * <p>Description: ��λ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class UnitAction
    extends BaseAction {
  UnitDao unitDao = null;
  public UnitAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    UnitForm myForm = (UnitForm) cfg.getBaseForm(); //�õ�form����
    unitDao = AdminFactory.getInstance().createUnit(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) unitDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new UnitVO());
    }
  }

  /**
  * ��������(�������ص���ҳ������)
  * @param cfg ��ǰAction���������
  * @param isBack �Ƿ񷵻�
  * @return forward��ַ
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
