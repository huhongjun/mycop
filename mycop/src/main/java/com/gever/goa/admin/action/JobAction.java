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
 * <p>Title: 职称控制器</p>
 * <p>Description: 职称控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class JobAction
    extends BaseAction {
  JobDao jobDao = null;
  public JobAction() {
  }

  /**
   * 初始化页面数据
   * @param actionform 当前的vo对象
   * @return 当前所用的vo对象
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    JobForm myForm = (JobForm) cfg.getBaseForm(); //得到form变量
    jobDao = AdminFactory.getInstance().createJob(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) jobDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
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
      request.setAttribute("ErrorMsg", "职称代码为汉字为两位，重新输入！");
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
      request.setAttribute("ErrorMsg", "代码是汉字则为两位，重新输入！");
      return FORWORD_EDIT_PAGE;
    }
    try {
      retforword = super.doInsert(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("PK repeat!")) {
        request.setAttribute("ErrorMsg", "职称代码相同,请重新填写！");
        return FORWORD_EDIT_PAGE;
      }
      else {
        throw e;
      }
    }
    return retforword;

  }

}
