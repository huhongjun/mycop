package com.gever.goa.dailyoffice.impowermgr.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerDao;
import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerFactory;
import com.gever.goa.dailyoffice.impowermgr.vo.DoImpowerVO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.goa.web.util.TemplateUtils;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.Codes;
import com.gever.util.DateTimeUtils;

/**
 * Title: 授权管理控制器
 * Description: 授权管理控制器
 * Copyright: Copyright (c) 2004
 * Company: 
 * @author 
 * @version 1.0
 */

public class DoImpowerAction
    extends BaseAction {
  DoImpowerDao doimpowerDao = null; //定义接口
  public DoImpowerAction() {
  }

  /**
   * 初始化页面数据
   * @param cfg 当前的vo对象
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    DoImpowerForm myForm = (DoImpowerForm) cfg.getBaseForm(); //得到form变量
    doimpowerDao = DoImpowerFactory.getInstance().createDoImpower(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) doimpowerDao); //cfg--Goa Action获取类--设置超类中的Dao
//容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new DoImpowerVO()); //设置VO到Form中
    }
    super.setVoSql(false);
  }

  /**
   * 新增动作--这里重载BaseAction里面的方法，实现添加模板的功能
   * @param cfg 当前Action相关配置类
   * @param isBack 是否返回
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    DoImpowerForm doimpowerForm = (DoImpowerForm) cfg.getBaseForm();
    DoImpowerVO vo = (DoImpowerVO) doimpowerForm.getVo();
    vo.setContent(Codes.decode(doimpowerForm.getCellcontent().toCharArray()));
     log.showLog(" vo.getSend_flag() 1= " + vo.getSend_flag());
    String forward = super.doInsert(cfg, isBack);

    sendMeetNoticeByMail(vo,doimpowerForm.getCellHtml(),doimpowerForm.getSendflag());
     log.showLog(" vo.getSend_flag() 2= " + vo.getSend_flag());
    return forward;
  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    DoImpowerForm doimpowerForm = (DoImpowerForm) cfg.getBaseForm();
    DoImpowerVO vo = (DoImpowerVO) doimpowerForm.getVo();
    vo.setContent(Codes.decode(doimpowerForm.getCellcontent().toCharArray()));
    String forward = super.doUpdate(cfg, isBack);
    sendMeetNoticeByMail(vo,doimpowerForm.getCellHtml(),doimpowerForm.getSendflag());
    return forward;
  }

  /**
   * 到检视页面
   * @param cfg 当前Action相关配置类
   * @return forward 地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forwardpath = super.toView(cfg);
    DoImpowerForm dform = (DoImpowerForm) cfg.getBaseForm();
    DoImpowerVO vo = (DoImpowerVO) dform.getVo();
    byte[] bcell = vo.getContent();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    //华表文件名
    String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
    dform.setCellName(strFilename);
    return forwardpath;
  }


  protected String toEdit(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forward = super.toEdit(cfg);
    DoImpowerForm dform = (DoImpowerForm) cfg.getBaseForm();
    if (!dform.getActionType().equals(this.MODIFY_ACTION)) {
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance().
          createIsStandardModel(super.
                                dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(12);
      dform.setTemplatepath(ismVO.getFile_path());
    }
    else{
      DoImpowerVO vo = (DoImpowerVO) dform.getVo();
      byte[] bcell = vo.getContent();
      String strPath = super.getServlet().getServletContext().getRealPath("/");
      //华表文件名
      String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
      dform.setCellName(strFilename);
    }
    return forward;
  }

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    DoImpowerForm dform = (DoImpowerForm) cfg.getBaseForm();
    dform.setSqlWhere(" AND DO_IMPOWER.ACCEPTER='" + dform.getUserId() + "'");
    return super.toList(cfg);
  }

  /**
   * 发送邮件
   * @param meetNoticeVO MeetNoticeVO
   * @throws DefaultException
   */
  private void sendMeetNoticeByMail(DoImpowerVO doImpowerVO,String cellContent,String sendflag) throws
      DefaultException {
    if (sendflag.equals("1")) {
      //发送邮件
      MailVO mailVO = new MailVO();
      mailVO.setReceive_address(doImpowerVO.getPayer() + "," +
                                doImpowerVO.getNotice());
      mailVO.setPost_address(doImpowerVO.getAccepter());
      mailVO.setPost_username(doImpowerVO.getAcceptername());
      mailVO.setTitle(doImpowerVO.getAcceptername() + "授权通知");
      mailVO.setContent(setMailContent(doImpowerVO,cellContent));
      MailMgrDAO mailMgrDao = MailMgrFactory.getInstance().createMailMgr(
          super.dbData);
      mailMgrDao.sendMail(mailVO, mailVO.getAttachList(), 0);
    }
  }

  /**
   *
   * @param DoImpowerVO doImpowerVO
   * @return String
   */
  private String setMailContent(DoImpowerVO doImpowerVO,String cellContent) {
    StringBuffer content = new StringBuffer();
    content.append("各位同事：");
    content.append("<br></br>  " + doImpowerVO.getComments());
    content.append("<br></br>具体如下：");
    content.append("<br></br>授权人：" + doImpowerVO.getAcceptername());
    content.append("<br></br>被授权人：" + doImpowerVO.getPayername());
    content.append("<br></br>时间：" + doImpowerVO.getBegin_time() + " 到 " +
                   doImpowerVO.getEnd_time());
    content.append("<br></br>授权书内容：<br></br>"+ cellContent);
    content.append("<br></br><br></br>" + doImpowerVO.getAcceptername());
    content.append("<br></br>" + DateTimeUtils.getCurrentDateTime());
    return content.toString();

  }

}
