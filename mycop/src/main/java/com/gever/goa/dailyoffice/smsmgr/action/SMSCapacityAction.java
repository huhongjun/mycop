package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.dao.SMSCapacityDAO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsFactory;
import com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.NumberUtil;
import com.gever.util.StringUtils;

/**
 * <p>Title: 权限</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SMSCapacityAction extends BaseAction {
    private SMSCapacityDAO smsDao = null;
    private static final String MOD_CORP_PAGE = "modcorp";
    public SMSCapacityAction() {
    }

    /**
     * 初始化页面数据
     * @param cfg 当前的vo对象
     * @throws DefaultException
     * @throws Exception
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        SMSCapacityForm myForm = (SMSCapacityForm) cfg.getBaseForm(); //得到form变量
        smsDao = SmsFactory.getInstance().createSmsCapacityDAO(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) smsDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new SMSCapacityVO());
        }
    }

    /**ConstantSet
     * 到清单页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String nodeid =  cfg.getRequest().getParameter("nodeid");

        StringBuffer sb = new StringBuffer(5);
        if (!("0".equals(nodeid) || StringUtils.isNull(nodeid))){
            sb.append(" and userid  in ( select id from t_department_person ");
            sb.append(" where department_id = ").append(nodeid).append(")");
        }

        SMSCapacityForm form = (SMSCapacityForm) cfg.getBaseForm(); //得到form变量
        if (!StringUtils.isNull(form.getFindName())){
            sb.append(" and name like '%");
            sb.append(StringUtils.replaceText(form.getFindName()));
            sb.append("%'");
            form.setFindName("");
        }
        form.setNodeid(nodeid);
        SMSCapacityVO vo =   smsDao.queryDefaultCapacity();
        form.setDefaultSize(vo.getNumbers());
        cfg.getBaseForm().setSqlWhere(sb.toString());
        cfg.getBaseForm().setActionType(""); // 恢复默认动作类型((add,modify)
        super.doPage(cfg,"");
        return this.FORWORD_LIST_PAGE;

    }

    /**ConstantSet
     * 到清单页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    public String doDefault(GoaActionConfig cfg) throws DefaultException,
        Exception {
        SMSCapacityForm form = (SMSCapacityForm) cfg.getBaseForm(); //得到form变量
        smsDao.updateAll(NumberUtil.stringToInt(form.getDefaultSize(), 10), 1);

        cfg.getBaseForm().setActionType(""); // 恢复默认动作类型((add,modify)
        return this.toList(cfg);

    }

    /**ConstantSet
     * 到清单页面
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    public String doSave(GoaActionConfig cfg) throws DefaultException,
        Exception {

        SMSCapacityForm form = (SMSCapacityForm) cfg.getBaseForm(); //得到form变量
        String[] idValues = cfg.getRequest().getParameterValues("fid");

        if (idValues == null)
            return FORWORD_LIST_PAGE;
        if (idValues.length <= 0)
            return FORWORD_LIST_PAGE;
        String[] aclValues = new String[idValues.length];
        String name = "";
        for (int idx = 0; idx < idValues.length; idx++) {
            name =cfg.getRequest().getParameter("timing" + idValues[idx]);
            log.showLog(" idValue-------->" + name + " name ==" + "timing" + idValues[idx]);
            if ("1".equals(name))
                aclValues[idx] = "1";
            else
                aclValues[idx] = "0";
        }

        smsDao.update(idValues, aclValues,
                      NumberUtil.stringToInt(form.getDefaultSize(), 10), 1);

        return this.toList(cfg);

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
        cfg.getRequest().setAttribute("treeData", smsDao.getTreeData(nodeid));
        return TREE_PAGE;
    }

    public String toModCorp(GoaActionConfig cfg) throws DefaultException,
        Exception {
        SMSCapacityVO vo = smsDao.queryDefaultCapacity();
        cfg.getBaseForm().setVo(vo);
        return MOD_CORP_PAGE;
    }

    public String doModCorp(GoaActionConfig cfg) throws DefaultException,
       Exception {
       int iRet = smsDao.updateCorpName(cfg.getBaseForm().getVo());
       if (iRet >0){
           cfg.getRequest().setAttribute("saveflag","true");
       }
       return MOD_CORP_PAGE;
   }

}