package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.JobDao;
import com.gever.goa.admin.vo.JobVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: ְ�ƿ�����</p>
 * <p>Description: ְ�ƿ�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class JobAction
    extends BaseAction {
  JobDao jobDao = null;
  public JobAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    JobForm myForm = (JobForm) cfg.getBaseForm(); //�õ�form����
    jobDao = AdminFactory.getInstance().createJob(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) jobDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null)
      myForm.setVo(new JobVO());
  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    String forward = null;
    HttpServletRequest request = cfg.getRequest();
    JobForm jobForm = (JobForm) cfg.getBaseForm();
    JobVO jobVO = (JobVO) jobForm.getVo();
    String job_code = jobVO.getJob_code();
    byte[] job_code_byte = job_code.getBytes();
    int job_code_int = job_code_byte.length;
    if (job_code_int > 4) {
      request.setAttribute("ErrorMsg", "ְ�ƴ���Ϊ����Ϊ��λ���������룡");
      return FORWORD_EDIT_PAGE;
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

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    String retforword = null;
    HttpServletRequest request = cfg.getRequest();
    JobForm form = (JobForm) cfg.getBaseForm();
    JobVO jobVO = (JobVO) form.getVo();
    String job_code = jobVO.getJob_code();
    byte[] job_code_byte = job_code.getBytes();
    int job_code_int = job_code_byte.length;
    if (job_code_int > 4) {
      request.setAttribute("ErrorMsg", "�����Ǻ�����Ϊ��λ���������룡");
      return FORWORD_EDIT_PAGE;
    }
    try {
      retforword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("PK repeat!")) {
        request.setAttribute("ErrorMsg", "ְ�ƴ�����ͬ,��������д��");
        return FORWORD_EDIT_PAGE;
      }
      else {
        throw e;
      }
    }
    return retforword;

  }

}
