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
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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

    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm(); //�õ�form����
    doWorkReportDao = DoWorkReportFactory.getInstance().createDoWorkReport(super.
        dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) doWorkReportDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
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
    //�����ļ���
    String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
    myForm.setCellName(strFilename);
    }
    return forward;
  }

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    DoWorkReportForm myForm = (DoWorkReportForm) cfg.getBaseForm(); //�õ�form����
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
      throw new DefaultException("�˹����㱨�Ѿ���ɾ����");
    }
    byte[] bcell = vo.getContent();
    String strPath = super.getServlet().getServletContext().getRealPath("/");
    //�����ļ���
    String strFilename = TemplateUtils.makeTempReport(bcell, strPath);
    dform.setCellName(strFilename);
    return forwardpath;
  }

  /**
   * ��ȡtree������
   * @param cfg ��ǰAction���������
   * @return forward��ַ
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
   * �����㱨�˷����Ÿ����㱨����������
   * @param messageAction String
   * @param DoWorkReportVO doWorkReportVO
   * @throws DefaultException
   * @throws Exception
   */
  private void creatorSentMessage(DoWorkReportVO doWorkReportVO) throws
      DefaultException,
      Exception {
    //������Ϣ
    MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
        dbData);
    StringBuffer content = new StringBuffer();
    content.append("\n���ã�" + doWorkReportVO.getUser_name() + "�����ݽ��˹����㱨���뼰ʱ����������");
    messageDao.sendMessage(doWorkReportVO.getUser_code(),
                           DateTimeUtils.getCurrentDateTime(), content.toString(),
                           doWorkReportVO.getRender(),
        "/dailyoffice/workreport/frame.jsp?listType=3&workReportId=" +
                           doWorkReportVO.getSerial_num(), "ת�������㱨");
  }

  /**
   * �����㱨�˷����Ÿ����㱨����������
   * @param messageAction String
   * @param DoWorkReportVO doWorkReportVO
   * @throws DefaultException
   * @throws Exception
   */
  private void CheckerSentMessage(DoWorkReportVO doWorkReportVO) throws
      DefaultException,
      Exception {
    //������Ϣ
    MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
        dbData);
    StringBuffer content = new StringBuffer();
    content.append("\n���ã����ݽ���" + doWorkReportVO.getChecker_name() + "�����㱨�������ˣ�");
    messageDao.sendMessage(doWorkReportVO.getChecker(),
                           DateTimeUtils.getCurrentDateTime(), content.toString(),
                           doWorkReportVO.getUser_code(),
        "/dailyoffice/workreport/frame.jsp?listType=2&workReportId=" +
                           doWorkReportVO.getSerial_num(), "ת�������㱨");
  }

}
