package com.gever.goa.dailyoffice.worklog.action;

import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetDao;
import com.gever.goa.dailyoffice.worklog.dao.TeamWorkLogSetFactory;
import com.gever.goa.dailyoffice.worklog.vo.TeamWorkLogSetVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

/**
 * Title: �Ŷ���־������
 * Description: �Ŷ���־������
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */

public class TeamWorkLogSetAction
    extends BaseAction {
  TeamWorkLogSetDao teamworklogsetDao = null; //����ӿ�
  private boolean isFind = false;
  public TeamWorkLogSetAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param cfg ��ǰ��vo����
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    TeamWorkLogSetForm myForm = (TeamWorkLogSetForm) cfg.getBaseForm(); //�õ�form����
    teamworklogsetDao = TeamWorkLogSetFactory.getInstance().
        createTeamWorkLogSet(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) teamworklogsetDao); //cfg--Goa Action��ȡ��--���ó����е�Dao
    //�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new TeamWorkLogSetVO()); //����VO��Form��
    }
    isFind = false;

    //����ʵ�������������
    String context=cfg.getRequest().getRealPath("/");
    TeamWorkLogSetDao teamWorkLogDao=(TeamWorkLogSetDao)cfg.getBaseDao();
    teamWorkLogDao.setContext(context);


  }

  /**
   * @todo  ���˱��޸�
   * ���б��嵥ҳ��-����������BaseAction�е�toList����
   * @param cfg ��ǰAction���������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {

    HttpSession session = cfg.getSession();
    TeamWorkLogSetForm form = (TeamWorkLogSetForm) cfg.getBaseForm();
    TeamWorkLogSetVO vo = (TeamWorkLogSetVO) cfg.getBaseForm().getVo();
    String curUser = cfg.getBaseForm().getUserId(); //ȡ�õ�ǰ�û�ID
    String queryFlag = cfg.getRequest().getParameter("queryFlag");
    String teamType = cfg.getRequest().getParameter("nodeid"); //��ȡ�Ŷ�����
    String teamTypeOfForm = form.getTeamtype();
    form.setTeamtype(StringUtils.isNull(teamTypeOfForm) ? "0" : teamTypeOfForm);

    //��ȡ��ҳ���д��������Ŷ�����-��Ҫ��Ϊ�˴�����ʱֵ����յ����
    if (!StringUtils.isNull(teamType)) {
      form.setSearchBeginTime("");
      form.setSearchEndTime("");
      form.setSearchUserName("");
      form.setSearchUserCode("");
      form.setTeamtype(teamType); //���Ŷ���������Ϊ��������ֵ
    }
    StringBuffer sbWhere = new StringBuffer(10);
    String mems = teamworklogsetDao.getTermMens(form.getUserId(),
                                                      form.getTeamtype());
    if ("true".equals(queryFlag)) {
      if (!StringUtils.isNull(form.getSearchBeginTime())) {
        sbWhere.append(" and fill_date >= ");
        sbWhere.append(this.toDate(form.getSearchBeginTime()));
        sbWhere.append(" ");
      }
      if (!StringUtils.isNull(form.getSearchEndTime())) {
        sbWhere.append(" and fill_date <= ");
        sbWhere.append(this.toDate(form.getSearchEndTime()));
        sbWhere.append(" ");
      }
      if (!StringUtils.isNull(form.getSearchUserCode())) {
        mems = form.getSearchUserCode();
        teamworklogsetDao.setCacheMems(mems);
      }
      else if (!StringUtils.isNull(form.getSearchUserName())) {
        String usrcode = new com.gever.sysman.api.OrganizationUtil().
            getUserIdsByUserName(form.
                                 getSearchUserName());
        if (!StringUtils.isNull(usrcode)) { //��Ϊ��ʱ�Ž�ȡ,��������
          mems = usrcode.substring(0, usrcode.length() - 1);
        }
        else {
          mems = "-1";
        }
        teamworklogsetDao.setCacheMems(mems);
      }
    }
    else {
      String eDate = DateTimeUtils.getCurrentDate();
      String bDate = DateTimeUtils.getDateSum(eDate, -7);
      sbWhere.append(" and fill_date between ");
      sbWhere.append(this.toDate(bDate));
      sbWhere.append(" and ");
      sbWhere.append(this.toDate(eDate));
      sbWhere.append(" ");
    }
    isFind = true;
    sbWhere.append(" and a.user_code in (").append(mems).append(")");
    sbWhere.append(" group by FILL_DATE ");
    form.setSqlWhere(sbWhere.toString());
    return super.toList(cfg);
  }



  /**
   * ��ҳ����(�������ص���ҳ������)
   * @param cfg ��ǰAction���������
   * @param pageType ҳ������
   * @return forward��ַ
   * @throws DefaultException
   * @throws Exception
   */
  protected String doPage(GoaActionConfig cfg, String pageType) throws
      DefaultException, Exception {
    TeamWorkLogSetForm form = (TeamWorkLogSetForm) cfg.getBaseForm();
    if (isFind == false) {
      teamworklogsetDao.getTermMens(form.getUserId(), form.getTeamtype());
    }
    return super.doPage(cfg, pageType);
  }

  /**
   * ���Ŷ�����ҳ��
   * ͬʱ���ú���ʾ���Ŷ���־�����б�htmlҳ��
   * @return forward ��ַ
   * @throws DefaultException
   * @throws Exception
   */
  public String toSet(GoaActionConfig cfg) throws DefaultException,
      Exception {
    HttpSession session = cfg.getSession();
    TeamWorkLogSetForm form = (TeamWorkLogSetForm) cfg.getBaseForm();
    TeamWorkLogSetVO vo = (TeamWorkLogSetVO) cfg.getBaseForm().getVo();

    String curUser = cfg.getBaseForm().getUserId();
    String teamType = (String) session.getAttribute("teamType");
    if ("".equals(teamType) || teamType == null) {
      teamType = form.getTeamtype(); //������ܴ����Ǳ�ȡ���Ŷ��������formbean�л�ȡ
      //������ܴ�session�л�ȡ�Ļ������ݿ��л�ȡ�Ŷ�����
      //��Ϊ����������ҳ����ת�����ʱ��session�����
      form.setTeamtype(teamType); //��Ĭ��ֵ����formbean
    }
    String outputhtml = teamworklogsetDao.queryList(curUser,
        Integer.parseInt(teamType), vo);
    form.setOutPutHtml(outputhtml);
    return "set";
  }

  /**
   * ���Ŷ�����ҳ��
   * ͬʱ���ú���ʾ���Ŷ���־�����б�htmlҳ��
   * @return forward ��ַ
   * @throws DefaultException
   * @throws Exception
   */

  public String toSave(GoaActionConfig cfg) throws DefaultException,
      Exception {
    HttpSession session = cfg.getSession();
    TeamWorkLogSetForm form = (TeamWorkLogSetForm) cfg.getBaseForm();
    TeamWorkLogSetVO vo = (TeamWorkLogSetVO) cfg.getBaseForm().getVo();
    String curUser = cfg.getBaseForm().getUserId();
    String teamType = cfg.getRequest().getParameter("nodeid"); //��ȡ�Ŷ�����
    teamType = vo.getTeam_type();
    //���õ�ǰ�û�code
    vo.setUser_code(curUser); //���õ�ǰ�û�
    vo.setTeam_type(teamType); //�����Ŷ�����
    //vo.setTeam_member();//�����Ŷӳ�Ա--�����Ѿ�ͨ��ҳ���vo��ֵ��
    //ִ��ɾ����ǰ�û����õĵ�ǰ�Ŷӵ��б����ﲻ��Ҫ����Բ��ŵ��ж�
    int iRet = teamworklogsetDao.save(vo);
    //ִ�����÷���Ȼ������ȡ���б����ص�ǰ����ҳ��
    String outputhtml = teamworklogsetDao.queryList(curUser,
        Integer.parseInt(teamType), vo);
    form.setOutPutHtml(outputhtml);
    return "set";
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
    cfg.getRequest().setAttribute("treeData", teamworklogsetDao.getTreeData());
    return TREE_PAGE;
  }

}
