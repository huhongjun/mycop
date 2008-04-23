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
 * <p>Title:�������Action �� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
     * ��ʼ��ҳ������
     * @param actionform ��ǰ��vo����
     * @return ��ǰ���õ�vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailboxMgrForm myForm = (MailboxMgrForm) cfg.getBaseForm(); //�õ�form����
        mailboxMgrDao = MailMgrFactory.getInstance().createMailboxMgr(super.
            dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) mailboxMgrDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao
        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null) {
            myForm.setVo(new MailCapacityVO());
        }
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
        //Ϊ�¼��û���ʼ��������һ���µļ�¼
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
        //���NODEIDΪ���в���
        if ("false".equals(nodeid)) {
            cfg.getBaseForm().setSqlWhere("");
        }
        System.out.println("------getSqlWhere is :------->" +
                           cfg.getBaseForm().getSqlWhere());
        super.doPage(cfg, "");
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * ����
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
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
     * * ����
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
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
           // System.out.println("------������-------");
            e.printStackTrace();
        }
        return this.toList(cfg);
    }

    /**
     * �����û�����ѯ��������
     * @param cfg
     * @return
     * @throws DefaultException
     */
    public String doQueryByUser(GoaActionConfig cfg) throws DefaultException,
        Exception {
        VOInterface mailVo=cfg.getBaseForm().getVo();
        String userName=mailVo.getValue("name").trim();
        //��������
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
                                      mailboxMgrDao.getTreeData(nodeid));
        return TREE_PAGE;
    }

}
