package com.gever.goa.dailyoffice.message.action;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.dailyoffice.message.dao.impl.MessageDaoImpl;
import com.gever.goa.dailyoffice.message.vo.MessageVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MessageAction
    extends BaseAction {
  private MessageDao mDao;
  public MessageAction() {

  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */

  protected void initData(GoaActionConfig cfg) {
    System.out.println("===========to initData in messageaction===========>");
    MessageForm myForm = (MessageForm) cfg.getBaseForm();
   //�õ�form����
    mDao = MessageFactory.getInstance().createMessageDao(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) mDao); //���ø���dao
    cfg.getBaseDao().setQueryTblName("a");
    super.setVoSql(false);
    //�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new MessageVO());
    }

  }

  /**
   * ����doInsert����
   * @param cfg GoaActionConfig
   * @param isBack boolean
   * @throws DefaultException
   * @throws Exception
   * @return String
   */
  protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
      DefaultException, Exception {
    MessageForm messageForm = (MessageForm) cfg.getBaseForm();
    MessageVO vo = (MessageVO) messageForm.getVo();
    return super.doInsert(cfg, isBack);
  }

  /**
   * ���Ķ���־����ѯ��
   * ͬʱ���������������������
   * @param GoaActionConfig cfge
   * @throws DefaultException
   * @return List
   */

  public String doSearchReadFlag(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    String flagQuery = ( (MessageForm) cfg.getBaseForm()).getFlagQuery();

    if (flagQuery != null && flagQuery.equals("0")) {
      cfg.getBaseForm().setSqlWhere(" and read_flag=0 and receiver ='" +
                                    Integer.parseInt(curUser) + "'");
    }
    if (flagQuery != null && flagQuery.equals("1")) {
      cfg.getBaseForm().setSqlWhere(" and read_flag=1 and receiver ='" +
                                    Integer.parseInt(curUser) + "'");
    }

    if (flagQuery != null && flagQuery.equals("2")) {
      cfg.getBaseForm().setSqlWhere("  and receiver ='" +
                                    Integer.parseInt(curUser) + "'");
    }
    return super.toList(cfg);
  }

  /**
   * ������������ѯ
   * ͬʱ���������������������
   * @param GoaActionConfig cfg
   * @throws DefaultException,Exception
   * @return List
   */

  public String doSearchByContent(GoaActionConfig cfg) throws
      DefaultException, Exception {
    //��BForm�����ò�ѯ����������������
    MessageForm form = (MessageForm) cfg.getBaseForm();
    String sqlWhere = "";
    String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    String content = form.getContentQuery();
    content = StringUtils.replaceText(content);
    if ( (!"".equals(content)) || (!"'".equals(content))) {
      sqlWhere += " and content like '%" + content + "%' and receiver ='" +
          Integer.parseInt(curUser) + "'";

    }
    if ( ("".equals(content))) {
      sqlWhere += " and content like '%" + "" + "%' and receiver ='" +
          Integer.parseInt(curUser) + "' ";
    }

    form.setSqlWhere(sqlWhere);
    return super.toList(cfg);
  }

  /**
   * �ص�list�嵥ҳ��
   * @param cfg GoaActionConfig
   * @throws DefaultException
   * @throws Exception
   * @return String
   */
  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {
    HttpServletRequest request = cfg.getRequest();
    HttpSession session = cfg.getSession();
    MessageForm myform = (MessageForm) cfg.getBaseForm();
    String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    //String curListType = request.getParameter("nodeid"); //��ȡ��ǰ�б�����
    String curListType = myform.getNodeid(); //��ȡ��ǰ�б�����
    myform.setNodeid(curListType);
    if (StringUtils.isNull(myform.getNodeid())) { //�����һ�ε�½-û�д�����ֵ-��Ĭ��Ϊ0
      myform.setNodeid("0");
    }
    //String queryFlag = request.getParameter("queryFlag");
    String searchContent = ( (MessageForm) cfg.getBaseForm()).
        getContentQuery();
    StringBuffer sbSqlWhere = new StringBuffer();
    if ("0".equals(myform.getNodeid())) { //�ж�һ�µ�ǰ�����ĸ��б�״̬
      searchContent = StringUtils.replaceText(searchContent);
      // sbSqlWhere.append( "and content like '%" + searchContent + "%'" ); //������������ѯ
      sbSqlWhere.append("and receiver ='" + Integer.parseInt(curUser) + "' " +
                        "order by serial_num desc"); //���ݽ���������ѯ
    }
    else if ("1".equals(myform.getNodeid())) { //δ�鿴
      searchContent = StringUtils.replaceText(searchContent);
      // sbSqlWhere.append( "and content like '%" + searchContent + "%'" ); //������������ѯ
      sbSqlWhere.append(" and read_flag = 0 and receiver='" +
                        Integer.parseInt(curUser) + "'" +
                        "order by serial_num desc");
    }
    else if ("2".equals(myform.getNodeid())) { //�Ѿ��鿴
      searchContent = StringUtils.replaceText(searchContent);
      // sbSqlWhere.append( "and content like '%" + searchContent + "%'" ); //������������ѯ
      sbSqlWhere.append(" and read_flag = 1 and receiver='" +
                        Integer.parseInt(curUser) + "'" +
                        "order by serial_num desc");
    }
    cfg.getBaseForm().setSqlWhere(sbSqlWhere.toString());
    super.toList(cfg);

    return this.FORWORD_LIST_PAGE;

  }

  public String doReply(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forward = super.toEdit(cfg);
    MessageForm form = (MessageForm) cfg.getBaseForm();
    //MessageVO vo = new MessageVO();
    MessageVO vo = (MessageVO) form.getVo();
    form.setVo(vo);
    String receiver = cfg.getRequest().getParameter("id_replyto");
    String receivername = cfg.getRequest().getParameter("name_replyto");
    vo.setReceiver(receiver);
    vo.setReceivername(receivername);

    return forward;
  }

  /**
   * ��viewҳ��,�޸��Ķ���־���Ķ�ʱ��.
   * @param cfg GoaActionConfig
   * @throws DefaultException
   * @throws Exception
   * @return String
   */
  protected String toView(GoaActionConfig cfg) throws DefaultException,
      Exception {
    String forwardpath = super.toView(cfg);
    MessageForm messageForm = (MessageForm) cfg.getBaseForm();
    HttpServletRequest request = cfg.getRequest();

    MessageVO vo = (MessageVO) messageForm.getVo();
    if (vo.getRead_time() == "" || vo.getRead_time() == null) {
      mDao.updateReadFlag(vo); //����dao��update�������޸��Ķ���־���Ķ�ʱ��
    }
    return forwardpath;
  }

  public String writeInfor(GoaActionConfig cfg) throws DefaultException,
      Exception {
    log.showLog("------------------test");
    MessageForm messageForm = (MessageForm) cfg.getBaseForm();

    HttpServletRequest request = cfg.getRequest();
   String id = request.getParameter("id");
    log.showLog("receivername"+request.getParameter("receivername"));
    MessageVO vo = (MessageVO) messageForm.getVo();
    String content = request.getParameter("content");
    String receivers = request.getParameter("receiver");
    System.out.println("���"+receivers);
     String send_time =DateTimeUtils.getCurrentDateTime();
    String user_code = messageForm.getUserId();
    StringTokenizer stkElementName = new StringTokenizer(receivers, ","); //ȡ��ҳ�����еĸ�����������
while (stkElementName.hasMoreElements()) {
// MailAttchVO attchVO = new MailAttchVO();
  String elementName = stkElementName.nextToken();
  MessageDao dao = new MessageDaoImpl();
  dao.sendMessage(user_code, send_time, content, elementName, null);


}
    return "shortness";

  }
}
