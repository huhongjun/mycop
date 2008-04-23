package com.gever.goa.dailyoffice.calendararrange.action;

import java.util.Calendar;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao;
import com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeFactory;
import com.gever.goa.dailyoffice.calendararrange.vo.ArrangeCalendarRightVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarArrangeVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarRightVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarUserVO;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.Department;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class CalendarArrangeAction
    extends BaseAction {
  protected final static String FORWORD_MONTH_PAGE = "monthindex";
  protected final static String FORWORD_WEEK_PAGE = "weekindex";
  protected final static String FORWORD_EDIT_CALENDAR_RIGTH = "editright";
  protected final static String MONTH_ACTION = "toListMonth";
  protected final static String WEEK_ACTION = "toListWeek";
  protected final static String UPDATERIGHT_ACTION = "toUpdateRight";
  protected final static String DELETE_MESSAGE_ACTION = "toDelete";
  protected final static String UPDATE_MESSAGE_ACTION = "toUpdate";
  protected final static String INSERT_MESSAGE_ACTION = "toInsert";
  protected final static String ARRANGE_CALENDAR_RIGTH = "1";
  protected final static String VIEW_CALENDAR_RIGTH = "0";

  CalendarArrangeDao calendarArrangeDao = null;

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    CalendarArrangeForm myForm = (CalendarArrangeForm) cfg.getBaseForm(); //�õ�form����

    //������ʼ��Ϊ��ǰʱ��
    if (myForm.getCalendar() == null) {
      Calendar calendar = Calendar.getInstance();
      myForm.setCalendar(calendar);
      myForm.setCalendarYear(calendar.get(Calendar.YEAR));
      myForm.setCalendarMonth(calendar.get(Calendar.MONTH) + 1);
      myForm.setCalendarWeek(calendar.get(Calendar.WEEK_OF_MONTH));
    }
    //��ʽ������
    else {
      Calendar calendar = myForm.getCalendar();
      calendar.set(Calendar.YEAR, myForm.getCalendarYear());
      calendar.set(Calendar.MONTH, myForm.getCalendarMonth() - 1);
      calendar.set(Calendar.WEEK_OF_MONTH, myForm.getCalendarWeek());
      myForm.setCalendar(calendar);
    }

    calendarArrangeDao = CalendarArrangeFactory.getInstance().
        createCalendarArrange(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) calendarArrangeDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new CalendarArrangeVO());
    }
    if (myForm.getCalendarRightVO() == null) {
      myForm.setCalendarRightVO(new CalendarRightVO());
    }

    super.setVoSql(false);
  }

  /**
   * ���嵥ҳ��
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */
  public String toListMonth(GoaActionConfig cfg) throws DefaultException,
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    bForm.setListType(this.MONTH_ACTION);
    if (bForm.getCalendarUserVO() == null) {
      CalendarUserVO cuv = new CalendarUserVO();
      cuv.setUserCode(bForm.getUserId());
      cuv.setUserName(bForm.getUserName());
      cuv.setRightType(this.ARRANGE_CALENDAR_RIGTH);
      bForm.setCalendarUserVO(cuv);
    }
    calendarArrangeDao = (CalendarArrangeDao) cfg.getBaseDao();
    bForm.setCalendarUserList(calendarArrangeDao.getCalendarRightUsers(bForm.
        getUserId()));
    int calendaryear = bForm.getCalendarYear();
    int calendarmonth = bForm.getCalendarMonth() - 1;
    bForm.setCalendarList(calendarArrangeDao.queryAllInMonth(calendaryear,
        calendarmonth, bForm.getCalendarUserVO().getUserCode()));
    return this.FORWORD_MONTH_PAGE;
  }

  /**
   * ���嵥ҳ��
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */
  public String toListWeek(GoaActionConfig cfg) throws DefaultException,
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    bForm.setListType(this.WEEK_ACTION);
    if (bForm.getCalendarUserVO() == null) {
      CalendarUserVO cuv = new CalendarUserVO();
      cuv.setUserCode(bForm.getUserId());
      cuv.setUserName(bForm.getUserName());
      cuv.setRightType(this.ARRANGE_CALENDAR_RIGTH);
      bForm.setCalendarUserVO(cuv);
    }
    calendarArrangeDao = (CalendarArrangeDao) cfg.getBaseDao();
    bForm.setCalendarUserList(calendarArrangeDao.getCalendarRightUsers(bForm.
        getUserId()));
    int calendaryear = bForm.getCalendarYear();
    int calendarmonth = bForm.getCalendarMonth() - 1;
    int calendarweek = bForm.getCalendarWeek();
    bForm.setCalendarList(calendarArrangeDao.queryAllInWeek(calendaryear,
        calendarmonth, calendarweek,
        bForm.getCalendarUserVO().getUserCode()));
    return this.FORWORD_WEEK_PAGE;
  }

  /**
   * ��ȡtoDept������
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */

  public String toEditRight(GoaActionConfig cfg) throws DefaultException,
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    calendarArrangeDao = (CalendarArrangeDao) cfg.getBaseDao();
    Department dept = OrganizationFactory.getInstance().createDepartment();
    dept.setId(Integer.parseInt(bForm.getDepartmentId()));
    CalendarRightVO calendarRightVO = calendarArrangeDao.getCalendarRightByUser(
        bForm.getUserId(), bForm.getRightType());
    bForm.setCalendarRightVO(calendarRightVO);
    bForm.setDeptCalendarUserList(calendarArrangeDao.getUsersInDeptByRightType(
        dept,
        bForm.getRightType(), calendarRightVO.getMember()));
    //���Ȩ��
    return this.FORWORD_EDIT_CALENDAR_RIGTH;
  }

  /**
   * ���嵥ҳ��
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    calendarArrangeDao = (CalendarArrangeDao) cfg.getBaseDao();
    bForm.setSqlWhere(" AND DO_CALENDAR_ARRANGE.USER_CODE=" +
                      bForm.getCalendarUserVO().getUserCode() +
                      " AND CALENDAR = " + DateTimeUtils.toDate(bForm.getListDate()) + "");
    return super.toList(cfg);
  }

  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forward = super.toView(cfg);
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    CalendarArrangeVO cavo = (CalendarArrangeVO)bForm.getVo();
    if(cavo.getUser_code().equals("")){
      throw new DefaultException("���ճ��Ѿ���ɾ����");
    }
    if(bForm.getCalendarUserVO() == null) {
      CalendarUserVO cuv = new CalendarUserVO();
      cuv.setUserCode(cavo.getUser_code());
      cuv.setUserName(cavo.getUser_name());
      cuv.setRightType(this.ARRANGE_CALENDAR_RIGTH);
      bForm.setCalendarUserVO(cuv);
    }
    if(bForm.getListDate().equals(""))bForm.setListDate(cavo.getCalendar());
    return forward;
  }

  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    CalendarArrangeVO calendarArrangeVO = (CalendarArrangeVO) bForm.getVo();
    String forward = super.doInsert(cfg, isBack);
    this.sentMessage(bForm.getMessageAction(), calendarArrangeVO);
    return forward;

  }

  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    CalendarArrangeVO calendarArrangeVO = (CalendarArrangeVO) bForm.getVo();
    String forward = super.doUpdate(cfg, isBack);
    this.sentMessage(bForm.getMessageAction(), calendarArrangeVO);
    return forward;

  }


  /**
   * ����Ȩ��
   * @param cfg GoaActionConfig
   * @throws DefaultException
   * @throws Exception
   * @return String
   */
  public String toUpdateRight(GoaActionConfig cfg) throws DefaultException,
      Exception {
    CalendarArrangeForm bForm = (CalendarArrangeForm) cfg.getBaseForm();
    calendarArrangeDao = (CalendarArrangeDao) cfg.getBaseDao();
    CalendarRightVO calendarRightVO = bForm.getCalendarRightVO();
    String[] pks = {
        calendarRightVO.getUser_code()};
    cfg.getBaseDao().setIsIdMng(false);
    if (calendarRightVO.getRighttype().equals(this.ARRANGE_CALENDAR_RIGTH)) {
      ArrangeCalendarRightVO arrange = new ArrangeCalendarRightVO();
      arrange.setUser_code(calendarRightVO.getUser_code());
      arrange.setArrange(calendarRightVO.getMember());
      arrange.setRighttype(this.ARRANGE_CALENDAR_RIGTH);
      calendarArrangeDao.delBySelect(pks, arrange);
      if (!arrange.getArrange().equals("")) {
        calendarArrangeDao.insert(arrange);
      }
    }
    else if (calendarRightVO.getRighttype().equals(this.VIEW_CALENDAR_RIGTH)) {
      calendarArrangeDao.delBySelect(pks, calendarRightVO);
      if (!calendarRightVO.getMember().equals("")) {
        calendarArrangeDao.insert(calendarRightVO);
      }
    }
    cfg.getBaseDao().setIsIdMng(true);
    //�������ݿ�󷵻�����ҳ��
    if (bForm.getActionType().equals(this.UPDATERIGHT_ACTION)) {
      if (bForm.getListType().equals(this.MONTH_ACTION)) {
        return this.FORWORD_MONTH_PAGE;
      }
      if (bForm.getListType().equals(this.WEEK_ACTION)) {
        return this.FORWORD_WEEK_PAGE;
      }
    }
    return this.FORWORD_EDIT_CALENDAR_RIGTH;
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
    String nodeid = StringUtils.nullToString(cfg.getRequest().getParameter(
        "nodeid"));
    cfg.getRequest().setAttribute("treeData",
                                  calendarArrangeDao.getTreeData(nodeid));
    return TREE_PAGE;

  }

  /**
   * �����Žӿ�
   * @param messageAction String
   * @param calendarArrangeVO CalendarArrangeVO
   * @throws DefaultException
   * @throws Exception
   */
  private void sentMessage(String messageAction,
                           CalendarArrangeVO calendarArrangeVO) throws
      DefaultException,
      Exception {
    //������Ϣ
    String recuser = (calendarArrangeVO.getArrange().equals(calendarArrangeVO.getUser_code())) ? calendarArrangeVO.getArrange() : (calendarArrangeVO.getArrange() + "," +calendarArrangeVO.getUser_code());
    String mescontent = this.setMessageContent(calendarArrangeVO);
    if (this.INSERT_MESSAGE_ACTION.equals(messageAction)) {
      MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
          dbData);
      String btime = calendarArrangeVO.getCalendar() + " " +
          calendarArrangeVO.getBegin_time();
       messageDao.sendMessageInfo(calendarArrangeVO.getArrange(),
                                 DateTimeUtils.
                                 getDateTimeByAddMinute(btime,
          (Integer.parseInt(calendarArrangeVO.getAwoke_time())) * -1),
                                 mescontent,
                                 recuser,
                                 "/dailyoffice/calendararrange/viewcalendar.do?fid=" +
                                 calendarArrangeVO.getSerial_num(), "1",
                                 calendarArrangeVO.getSerial_num(),"ת���ճ̰���");
    }

    else if (this.DELETE_MESSAGE_ACTION.equals(messageAction)) {
      MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
          dbData);
      messageDao.deleteMessageInfo("1", calendarArrangeVO.getSerial_num());
    }
    else if (this.UPDATE_MESSAGE_ACTION.equals(messageAction)) {
      MessageDao messageDao = MessageFactory.getInstance().createMessageDao(super.
          dbData);
      String btime = calendarArrangeVO.getCalendar() + " " +
          calendarArrangeVO.getBegin_time();
      messageDao.updateMessageInfo(DateTimeUtils.getDateTimeByAddMinute(
          btime,
          (Integer.parseInt(calendarArrangeVO.getAwoke_time())) * -1),
                                   mescontent, "1",
                                   calendarArrangeVO.getSerial_num());
    }
   }

   private String setMessageContent(CalendarArrangeVO calendarArrangeVO){
         StringBuffer content = new StringBuffer();
         content.append("\n���ã�");
         content.append("\n"+ calendarArrangeVO.getUser_name() + "��" +calendarArrangeVO.getCalendar() + " " + calendarArrangeVO.getBegin_time() +"���ճ̰���������ע�����������ˡ�");
         content.append("\n�����Ҫ�� "+calendarArrangeVO.getTask_sum());
         content.append("\n������ϸ���ݣ�"+calendarArrangeVO.getTask_content());
         content.append("\n�����Ե����ճ̰��š��˵��в鿴���ճ̰��ŵ�ȫ�����ݡ�");
         return content.toString();
   }

}
