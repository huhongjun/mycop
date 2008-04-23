package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.DutyDao;
import com.gever.goa.admin.vo.DutyVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: ְ�������</p>
 * <p>Description: ְ�������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DutyAction extends BaseAction {
    DutyDao dutyDao = null;
    public DutyAction() {
    }

    /**
     * ��ʼ��ҳ������
     * @param actionform ��ǰ��vo����
     * @return ��ǰ���õ�vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        DutyForm myForm = (DutyForm) cfg.getBaseForm(); //�õ�form����
        dutyDao = AdminFactory.getInstance().createDuty(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) dutyDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null)
            myForm.setVo(new DutyVO());
    }
    protected String doUpdate(GoaActionConfig cfg,boolean isBack) throws DefaultException ,Exception{
      String forward=null;
      HttpServletRequest request=cfg.getRequest();
      DutyForm dutyForm=(DutyForm)cfg.getBaseForm();
      DutyVO dutyVO=(DutyVO)dutyForm.getVo();
      String duty_code=dutyVO.getDuty_code();
      byte[] duty_code_byte=duty_code.getBytes();
      int duty_code_int=duty_code_byte.length;
      if(duty_code_int>4){
        request.setAttribute("ErrorMsg","ְ�����Ϊ����Ϊ��λ���������룡");
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

 protected String doInsert(GoaActionConfig cfg,boolean isBack) throws DefaultException,
     Exception {
   String retforword = null;
   HttpServletRequest request=cfg.getRequest();
   DutyForm dutyForm=(DutyForm)cfg.getBaseForm();
   DutyVO dutyVO=(DutyVO)dutyForm.getVo();
   String duty_code=dutyVO.getDuty_code();
   byte[] duty_code_byte=duty_code.getBytes();
   int duty_code_int=duty_code_byte.length;
   if(duty_code_int>4){
     request.setAttribute("ErrorMsg","ְ�����Ϊ����Ϊ��λ���������룡");
     return this.FORWORD_EDIT_PAGE;
   }
      try {
     retforword = super.doInsert( cfg, isBack );
   }
   catch ( DefaultException e ) {
     if ( e.getMessage().equals( "PK repeat!" ) ) {
         request.setAttribute("ErrorMsg","ְ������ظ�,��������д!");
         return this.FORWORD_EDIT_PAGE;
       //throw new DefaultException( "ְ������ظ�,��������д!" );
     }
     else {
       throw e;
     }
   }
   return retforword;
   }
}
