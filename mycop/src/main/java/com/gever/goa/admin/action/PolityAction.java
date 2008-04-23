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
 * <p>Title: ���ο�����</p>
 * <p>Description: ���ο�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class PolityAction
    extends BaseAction {
  PolityDao polityDao = null;
  public PolityAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    PolityForm myForm = (PolityForm) cfg.getBaseForm(); //�õ�form����
    polityDao = AdminFactory.getInstance().createPolity(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) polityDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
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
      request.setAttribute("ErrorMsg", "������ò�����Ǻ���Ϊ��λ���������룡");
      return this.FORWORD_EDIT_PAGE;
    }

    try {
      refoword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("pk")) {
        request.setAttribute("ErrorMsg", "������ò�����ظ����������룡");
        return this.FORWORD_EDIT_PAGE;
        // throw new DefaultException("������ò�����ظ�,��������д!");
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
      request.setAttribute("ErrorMsg", "������ò�����Ǻ���Ϊ��λ���������룡");
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
