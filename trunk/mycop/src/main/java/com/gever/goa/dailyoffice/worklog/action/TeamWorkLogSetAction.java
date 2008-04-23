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
 * Title: 团队日志控制器
 * Description: 团队日志控制器
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class TeamWorkLogSetAction
    extends BaseAction {
  TeamWorkLogSetDao teamworklogsetDao = null; //定义接口
  private boolean isFind = false;
  public TeamWorkLogSetAction() {
  }

  /**
   * 初始化页面数据
   * @param cfg 当前的vo对象
   * @throws DefaultException
   * @throws Exception
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {
    TeamWorkLogSetForm myForm = (TeamWorkLogSetForm) cfg.getBaseForm(); //得到form变量
    teamworklogsetDao = TeamWorkLogSetFactory.getInstance().
        createTeamWorkLogSet(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) teamworklogsetDao); //cfg--Goa Action获取类--设置超类中的Dao
    //容错处理,防止vo对象为null
    if (myForm.getVo() == null) {
      myForm.setVo(new TeamWorkLogSetVO()); //设置VO到Form中
    }
    isFind = false;

    //设置实现类里的上下文
    String context=cfg.getRequest().getRealPath("/");
    TeamWorkLogSetDao teamWorkLogDao=(TeamWorkLogSetDao)cfg.getBaseDao();
    teamWorkLogDao.setContext(context);


  }

  /**
   * @todo  翁乃彬修改
   * 到列表清单页面-这里是重载BaseAction中的toList方法
   * @param cfg 当前Action相关配置类
   * @return forward地址
   * @throws DefaultException
   * @throws Exception
   */

  protected String toList(GoaActionConfig cfg) throws DefaultException,
      Exception {

    HttpSession session = cfg.getSession();
    TeamWorkLogSetForm form = (TeamWorkLogSetForm) cfg.getBaseForm();
    TeamWorkLogSetVO vo = (TeamWorkLogSetVO) cfg.getBaseForm().getVo();
    String curUser = cfg.getBaseForm().getUserId(); //取得当前用户ID
    String queryFlag = cfg.getRequest().getParameter("queryFlag");
    String teamType = cfg.getRequest().getParameter("nodeid"); //获取团队类型
    String teamTypeOfForm = form.getTeamtype();
    form.setTeamtype(StringUtils.isNull(teamTypeOfForm) ? "0" : teamTypeOfForm);

    //获取从页面中传过来的团队类型-主要是为了处理返回时值被清空的情况
    if (!StringUtils.isNull(teamType)) {
      form.setSearchBeginTime("");
      form.setSearchEndTime("");
      form.setSearchUserName("");
      form.setSearchUserCode("");
      form.setTeamtype(teamType); //将团队类型设置为传过来的值
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
        if (!StringUtils.isNull(usrcode)) { //不为空时才截取,否则会出错
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
   * 翻页动作(继续返回到本页面新增)
   * @param cfg 当前Action相关配置类
   * @param pageType 页面类型
   * @return forward地址
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
   * 到团队设置页面
   * 同时设置好显示的团队日志设置列表html页面
   * @return forward 地址
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
      teamType = form.getTeamtype(); //如果不能从树那边取到团队类型则从formbean中获取
      //如果不能从session中获取的话从数据库中获取团队类型
      //因为父类中遇到页面跳转的情况时把session清空了
      form.setTeamtype(teamType); //将默认值赋给formbean
    }
    String outputhtml = teamworklogsetDao.queryList(curUser,
        Integer.parseInt(teamType), vo);
    form.setOutPutHtml(outputhtml);
    return "set";
  }

  /**
   * 到团队设置页面
   * 同时设置好显示的团队日志设置列表html页面
   * @return forward 地址
   * @throws DefaultException
   * @throws Exception
   */

  public String toSave(GoaActionConfig cfg) throws DefaultException,
      Exception {
    HttpSession session = cfg.getSession();
    TeamWorkLogSetForm form = (TeamWorkLogSetForm) cfg.getBaseForm();
    TeamWorkLogSetVO vo = (TeamWorkLogSetVO) cfg.getBaseForm().getVo();
    String curUser = cfg.getBaseForm().getUserId();
    String teamType = cfg.getRequest().getParameter("nodeid"); //获取团队类型
    teamType = vo.getTeam_type();
    //设置当前用户code
    vo.setUser_code(curUser); //设置当前用户
    vo.setTeam_type(teamType); //设置团队类型
    //vo.setTeam_member();//设置团队成员--这里已经通过页面给vo付值了
    //执行删除当前用户设置的当前团队的列表，这里不需要加入对部门的判断
    int iRet = teamworklogsetDao.save(vo);
    //执行设置方法然后重新取得列表，返回当前设置页面
    String outputhtml = teamworklogsetDao.queryList(curUser,
        Integer.parseInt(teamType), vo);
    form.setOutPutHtml(outputhtml);
    return "set";
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
    cfg.getRequest().setAttribute("treeData", teamworklogsetDao.getTreeData());
    return TREE_PAGE;
  }

}
