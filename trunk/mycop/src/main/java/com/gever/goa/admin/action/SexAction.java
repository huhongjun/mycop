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
 * <p>Title: ְ�������</p>
 * <p>Description: ְ�������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SexAction
    extends BaseAction {
  SexDao sexDao = null;
  public SexAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    SexForm myForm = (SexForm) cfg.getBaseForm(); //�õ�form����
    sexDao = AdminFactory.getInstance().createSex(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) sexDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
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
      request.setAttribute("ErrorMsg", "�Ա�����Ǻ���Ϊ��λ���������룡");
      return this.FORWORD_EDIT_PAGE;
    }

    try {
      reforword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("pk")) {
        // throw new DefaultException("�Ա�����ظ�,��������д!");
        request.setAttribute("ErrorMsg", "�Ա�����ظ�,��������д!");
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
      request.setAttribute("ErrorMsg", "�Ա�����Ǻ���Ϊ��λ���������룡");
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
