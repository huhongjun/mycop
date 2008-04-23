package com.gever.goa.dailyoffice.workreport.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.dailyoffice.workreport.dao.DoWorkReportDao;
import com.gever.goa.dailyoffice.workreport.dao.DoWorkReportFactory;
import com.gever.goa.dailyoffice.workreport.util.WorkReportWhereTypes;
import com.gever.goa.dailyoffice.workreport.vo.DoWorkReportVO;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.goa.web.util.TemplateUtils;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.Codes;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DoWorkReportAction
    extends BaseAction {
  DoWorkReportDao doWorkReportDao = null;
  public DoWorkReportAction() {
  }

  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm(); //得到form变量
    doWorkReportDao = DoWorkReportFactory.getInstance().createDoWorkReport(super.
        dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) doWorkReportDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new DoWorkReportVO());
    }
    super.setVoSql(false);
  }

  protected String toEdit(GoaActionConfig cfg) throws DefaultException,
      Exception {

    String forward = super.toEdit(cfg);
    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm();
    if (!myForm.getActionType().equals(this.MODIFY_ACTION)) {
      IsStandardModelDao isStandardModelDao = IsCustomerFactory.getInstance().
          createIsStandardModel(super.
                                dbData);
      IsStandardModelVO ismVO = (IsStandardModelVO) isStandardModelDao.
          getTemplate(11);
      myForm.setTemplatepath(ismVO.getFile_path());
    }else{
    DoWorkReportVO vo = (DoWorkReportVO) myForm.getVo();
    byte[] bcell = vo.getContent();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    //华表文件名
    String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
    myForm.setCellName(strFilename);
    }
    return forward;
  }

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm(); //得到form变量
    doWorkReportDao = (DoWorkReportDao) cfg.getBaseDao();
    WorkReportWhereTypes workReportWhereTypes = new WorkReportWhereTypes(myForm.
        getUserId());
    StringBuffer sbWhere = new StringBuffer(workReportWhereTypes.
                                            getWhereStrById(Integer.parseInt(
        myForm.getListType())));
    if (!StringUtils.isNull(myForm.getSearchBeginTime())) {

      sbWhere.append(" AND CREATE_DATE >= " + this.toDate(myForm.getSearchBeginTime()) +
                     "");
    }
    if (!StringUtils.isNull(myForm.getSearchEndTime())) {
      sbWhere.append(" AND CREATE_DATE <= " + this.toDate(myForm.getSearchEndTime()) + "");
    }
    myForm.setSqlWhere(sbWhere.toString());
    myForm.setSearchBeginTime("");
    myForm.setSearchEndTime("");
    return super.toList(cfg);
  }

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm();
    myForm.setListType("0");
    DoWorkReportVO vo = (DoWorkReportVO) myForm.getVo();
    vo.setContent(Codes.decode(myForm.getCellcontent().toCharArray()));
    log.showLog("--** length **-----------"+vo.getContent().length);
    String forward = super.doInsert(cfg, isBack);
    if(vo.getSend_flag().equals("1")){
      this.creatorSentMessage(vo);
      myForm.setListType("1");
    }
    return forward;
  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm();
    DoWorkReportVO vo = (DoWorkReportVO) myForm.getVo();
    vo.setContent(Codes.decode(myForm.getCellcontent().toCharArray()));
    String forward = super.doUpdate(cfg, isBack);
    if(myForm.getListType().equals("3") && !StringUtils.isNull(vo.getChecker()) && !StringUtils.isNull(vo.getCheck_date())){
      this.CheckerSentMessage(vo);
      myForm.setListType("4");
    }
    return forward;
  }

  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forwardpath = super.toView(cfg);
    DoWorkReportForm dform = (DoWorkReportForm) cfg.getBaseForm();
    DoWorkReportVO vo = (DoWorkReportVO) dform.getVo();
    if(vo.getSerial_num().equals("")){
      throw new DefaultException("此工作汇报已经被删除！");
    }
    byte[] bcell = vo.getContent();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    //华表文件名
    String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
    dform.setCellName(strFilename);
    return forwardpath;
  }

  /**
   * 获取tree的数据
   * @param cfg 当前Action相关配置类
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  public String doTreeData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    doWorkReportDao = (DoWorkReportDao) cfg.getBaseDao();
    cfg.getRequest().setAttribute("treeData", doWorkReportDao.getTreeData());
    return TREE_PAGE;
  }

  /**
   * 创建汇报人发短信给被汇报人请求审批
   * @param messageAction String
   * @param DoWorkReportVO doWorkReportVO
   * @throws DefaultException
   * @throws Exception
   */
  private void creatorSentMessage(DoWorkReportVO doWorkReportVO) throws
      DefaultException,
      Exception {
    //发短消息
    MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
        dbData);
    StringBuffer content = new StringBuffer();
    content.append("\n您好：" + doWorkReportVO.getUser_name() + "给您递交了工作汇报，请及时查阅审批！");
    messageDao.sendMessage(doWorkReportVO.getUser_code(),
                           DateTimeUtils.getCurrentDateTime(), content.toString(),
                           doWorkReportVO.getRender(),
        "/dailyoffice/workreport/frame.jsp?listType=3&workReportId=" +
                           doWorkReportVO.getSerial_num(), "转到工作汇报");
  }

  /**
   * 创建汇报人发短信给被汇报人请求审批
   * @param messageAction String
   * @param DoWorkReportVO doWorkReportVO
   * @throws DefaultException
   * @throws Exception
   */
  private void CheckerSentMessage(DoWorkReportVO doWorkReportVO) throws
      DefaultException,
      Exception {
    //发短消息
    MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
        dbData);
    StringBuffer content = new StringBuffer();
    content.append("\n您好：您递交给" + doWorkReportVO.getChecker_name() + "工作汇报被审批了！");
    messageDao.sendMessage(doWorkReportVO.getChecker(),
                           DateTimeUtils.getCurrentDateTime(), content.toString(),
                           doWorkReportVO.getUser_code(),
        "/dailyoffice/workreport/frame.jsp?listType=2&workReportId=" +
                           doWorkReportVO.getSerial_num(), "转到工作汇报");
  }

}
