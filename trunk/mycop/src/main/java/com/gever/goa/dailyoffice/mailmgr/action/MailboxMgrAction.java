package com.gever.goa.dailyoffice.mailmgr.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailCapacityDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.dao.MailboxMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title:邮箱管理Action 类 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class MailboxMgrAction extends BaseAction {
    private MailboxMgrDAO mailboxMgrDao = null;
    MailCapacityDAO mailCapacityDao = MailMgrFactory.getInstance().
        creatMailCapacity(super.dbData);
    public MailboxMgrAction() {
    }

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailboxMgrForm myForm = (MailboxMgrForm) cfg.getBaseForm(); //得到form变量
        mailboxMgrDao = MailMgrFactory.getInstance().createMailboxMgr(super.
            dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) mailboxMgrDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null) {
            myForm.setVo(new MailCapacityVO());
        }
    }

    /**
     * 到清单页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        //为新加用户初始化，插入一条新的记录
        mailCapacityDao.multiInsert(cfg.getBaseForm().getVo());
        String nodeid = cfg.getRequest().getParameter("nodeid");
        MailboxMgrForm mailForm = (MailboxMgrForm) cfg.getBaseForm();
        //System.out.println("node id is :--->" + nodeid);
        /*nodeid=mailForm.getNodeid();
                 System.out.println("The node id is:"+nodeid);
                 if(!("0".equals(mailForm.getNodeid()) || StringUtils.isNull(mailForm.getNodeid()))){
            System.out.println("The node id is:"+nodeid);
                 }*/
        StringBuffer sb = new StringBuffer(5);
        if (! ("0".equals(nodeid) || StringUtils.isNull(nodeid)) ||
            "false".equals(nodeid)) {
            sb.append(
                " and t_user.id  in ( select id from t_department_person ");
            sb.append(" where department_id = ").append(nodeid).append(")");
            cfg.getBaseForm().setSqlWhere(sb.toString());
        }
        //如果NODEID为所有部门
        if ("false".equals(nodeid)) {
            cfg.getBaseForm().setSqlWhere("");
        }
        System.out.println("------getSqlWhere is :------->" +
                           cfg.getBaseForm().getSqlWhere());
        super.doPage(cfg, "");
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * 保存
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    public String doSave(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String[] values = cfg.getRequest().getParameterValues("fid");
        if (values == null) {
            return this.FORWORD_LIST_PAGE;
        }
        if (values.length <= 0) {
            return this.FORWORD_LIST_PAGE;
        }
        VOInterface vo = cfg.getBaseForm().getVo();
        String mail_capacity = vo.getValue("mail_capacity");
        mailCapacityDao.update(Double.parseDouble(mail_capacity), values);
        return this.toList(cfg);
    }

    /**
     * * 更新
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    public String doDefault(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String nodeId = ( (MailboxMgrForm) cfg.getBaseForm()).getNodeid();
        String mailCapacity = ( (MailCapacityVO) cfg.getBaseForm().getVo()).
            getMail_capacity();
        //System.out.println("mail_capacity is:--->" + mailCapacity);
        try {
            mailCapacityDao.setAllCapacity(Double.parseDouble(mailCapacity),
                                           nodeId);
        } catch (Exception e) {
           // System.out.println("------出错了-------");
            e.printStackTrace();
        }
        return this.toList(cfg);
    }

    /**
     * 根据用户名查询邮箱容量
     * @param cfg
     * @return
     * @throws DefaultException
     */
    public String doQueryByUser(GoaActionConfig cfg) throws DefaultException,
        Exception {
        VOInterface mailVo=cfg.getBaseForm().getVo();
        String userName=mailVo.getValue("name").trim();
        //处理单引号
        userName=StringUtils.replaceText(userName);
        //userName=userName.replaceAll("'","''");
       // System.out.println("The userName is;------"+userName);
        /* String temp=userName;
        int pos=userName.indexOf("'");
        System.out.println("The pos is :-----"+pos);

      /*  while(pos!=-1){
            userName=userName.substring(0,pos+1)+"'"+userName.substring(pos+1,userName.length());
            pos=(userName.substring(pos+1,userName.length())).indexOf("'");
        }*/
        StringBuffer sb=new StringBuffer();
        if(!("null".equals(userName))){
            sb.append(" and name like");
            sb.append("'%"+userName+"%'");
            cfg.getBaseForm().setSqlWhere(sb.toString());
            super.doPage(cfg,"");
        }
        else if("".equals(userName)){
            this.toList(cfg);
        }
        return this.FORWORD_LIST_PAGE;
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
        String nodeid = StringUtils.nullToString(cfg.getRequest().getParameter(
            "nodeid"));
        cfg.getRequest().setAttribute("treeData",
                                      mailboxMgrDao.getTreeData(nodeid));
        return TREE_PAGE;
    }

}
