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
 * <p>Title: ���������</p>
 * <p>Description: ���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class NationAction
    extends BaseAction {
  NationDao nationDao = null;
  public NationAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    NationForm myForm = (NationForm) cfg.getBaseForm(); //�õ�form����
    nationDao = AdminFactory.getInstance().createNation(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) nationDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
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
      request.setAttribute("ErrorMsg", "��������Ǻ���Ϊ��λ���������룡");
      return this.FORWORD_EDIT_PAGE;
    }

    try {
      retforword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("PK repeat!")) {
        request.setAttribute("ErrorMsg", "��������ظ�,��������д!");
        return this.FORWORD_EDIT_PAGE;
        // throw new DefaultException( "��������ظ�,��������д!" );
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
      request.setAttribute("ErrorMsg", "��������Ǻ���Ϊ��λ���������룡");
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
