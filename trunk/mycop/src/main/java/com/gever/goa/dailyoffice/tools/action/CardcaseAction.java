package com.gever.goa.dailyoffice.tools.action;

/**
 * <p>Title: 名片夹Action</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.CardcaseDao;
import com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao;
import com.gever.goa.dailyoffice.tools.dao.ToolsFactory;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO;
import com.gever.goa.dailyoffice.tools.vo.CardcaseVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;

public class CardcaseAction extends BaseAction {
    // private CardcaseDao cDao;
    // private CardcaseTypeDao typeDao;

    public CardcaseAction() {

    }

    /**
     * 初始化页面数据
     * @param cfg 当前的配置信息
     * @return  更改后的配置信息
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {

        CardcaseForm myForm = (CardcaseForm) cfg.getBaseForm(); //得到form变量
        CardcaseDao cDao = ToolsFactory.getInstance().createCardcaseDao(super.
                dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) cDao); //设置父类dao

        log.showLog("******myForm.getSearchValue()****"+myForm.getSearchValue());
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new CardcaseVO());

        return;
    }

    /**
     * 跳转到修改增加页面
     * @param cfg GoaActionConfig 配置信息
     * @throws DefaultException
     * @throws Exception
     * @return String 一个forword对象名
     */
    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
            Exception {
        //以下搜索出CardcaseTypeVO置于request中
        CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
        CardcaseTypeVO typevo = new CardcaseTypeVO();
        CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
        typevo.setUser_code(form.getUserId());
        cfg.getRequest().setAttribute("cardcase_types",
                                      typeDao.queryByUser(typevo));

        String forword = super.toEdit(cfg);

        //进入edit前 置form.cardtype为vo.type_code;即置默认值
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        CardcaseVO cardvo = (CardcaseVO) cardform.getVo();
        String type_code = cardvo.getType_code();
        if (!"".equals(type_code)) {
            cardform.setCardType(type_code);
        }
        return forword;
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
        //BaseDAO basedao=cfg.getBaseDao();
        CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        log.showLog("----start-where-");
        String nodeid = StringUtils.nullToString(
                request.getParameter("nodeid"));
        if (!"".equals(nodeid)) { //判断是否传人父节点
            form.setCardType(nodeid);
            form.setSearchValue("");
            form.setSqlWhere(" and type_code = '" + nodeid + "'");
        } else {
            String sqlWhere="";
            String name = form.getSearchValue();
        name=StringUtils.replaceText(name);
            if ( (!"".equals(name)) && (!"'".equals(name))) {
                sqlWhere += " and customer like '%" + name + "%'";
            }
            String cardtype = form.getCardType();
            if (cardtype!=null && !"".equals(cardtype)) {
                form.setSqlWhere(sqlWhere+" and type_code = '" +
                                 cardtype + "'");
            }
        }
        String forword = super.toList(cfg);

        //form.setSqlWhere(""); //条件设初值；

        return forword;
    }

    /**
     * 新增动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        //insert和update之后置form中的cardtype为vo.type_code；
        //以便toList时按cardtype进行搜索；
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        CardcaseVO cardvo = (CardcaseVO) cardform.getVo();
        //cardform.setCardType(cardvo.getType_code());
        cardvo.setType_code(cardform.getCardType());

        if (!isBack) { //保存，不返回，重新load一次Cardcase Type
            CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
            //以下搜索出CardcaseTypeVO置于request中
            CardcaseTypeVO typevo = new CardcaseTypeVO();
            // CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
            typevo.setUser_code(cardform.getUserId());
            //form.setCardcase_types(typeDao.queryByUser(vo));
            cfg.getRequest().setAttribute("cardcase_types",
                                          typeDao.queryByUser(typevo));

        }
        cardform.setSearchValue(""); //插入一条记录以后应置页面搜索值为空为空
        cardform.setSqlWhere("");
        return super.doInsert(cfg, isBack);
    }

    /**
     * 修改动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param isBack 是否返回
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        //insert和update之后置form中的cardtype为vo.type_code；
        //以便toList时按cardtype进行搜索；
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        CardcaseVO cardvo = (CardcaseVO) cardform.getVo();
        //cardform.setCardType(cardvo.getType_code());
        cardvo.setType_code(cardform.getCardType());

        if (!isBack) { //保存，不返回，重新load一次Cardcase Type
            //以下搜索出CardcaseTypeVO置于request中
            CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
            CardcaseTypeVO typevo = new CardcaseTypeVO();
            // CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
            typevo.setUser_code(cardform.getUserId());
            //form.setCardcase_types(typeDao.queryByUser(vo));
            cfg.getRequest().setAttribute("cardcase_types",
                                          typeDao.queryByUser(typevo));
        }
        return super.doUpdate(cfg, isBack);
    }

    /**
     * 按输入姓名进行查找
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String 返回forword的名字
     */
    public String doSearchByName(GoaActionConfig cfg) throws
            DefaultException, Exception {
        //在BForm里设置查询条件。。。。。。
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        String sqlWhere = "";
        String name = cardform.getSearchValue();
        name=StringUtils.replaceText(name);
        log.showLog("name............."+name);
        if ( !"".equals(name)) {
            sqlWhere += " and customer like '%" + name + "%'";
        }
        String type_code = cardform.getCardType();
        log.showLog("type_code............."+type_code);
        if (!"".equals(type_code)) {
            sqlWhere += " and type_code = '" + type_code + "'";
            cardform.setSqlWhere(sqlWhere);
        }
        return super.toList(cfg);
    }

    /**
     * 删除动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doDelete(GoaActionConfig cfg) throws DefaultException,
            Exception {
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        String sqlWhere = "";
        String name = cardform.getSearchValue();
        name=StringUtils.replaceText(name);
        if (!"".equals(name)) {
            sqlWhere = sqlWhere + "and customer like '%" +
                    name + "%'";
        }
        if (!"".equals(cardform.getCardType())) {
            sqlWhere = sqlWhere + "and type_code ='" + cardform.getCardType() +
                    "'";
            cardform.setSqlWhere(sqlWhere);
        }

        String forword = super.doDelete(cfg);
        //cardform.setSqlWhere(""); //清空条件

        return forword;
    }

    /**
     * 进入查看页面。。。
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String 一个forword
     */
    public String toView(GoaActionConfig cfg) throws DefaultException,
            Exception {
        String forword = super.toView(cfg);

        //将其对应的typeVO查询出来，在页面上将显示type_name;
        CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
        String type_code = ( (CardcaseVO) cfg.getBaseForm().getVo()).
                getType_code();
        CardcaseTypeVO typevo = new CardcaseTypeVO();
        typevo.setType_code(type_code);
        cfg.getRequest().setAttribute("CardTypeVO", typeDao.queryByPK(typevo));

        return forword;
    }

    /**
     * 排序动作；
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    public String doOrderBy(GoaActionConfig cfg) throws DefaultException,
            Exception {
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        String sqlWhere = "";
        String name = cardform.getSearchValue();
        name=StringUtils.replaceText(name);
        if (!"".equals(name)) {
            sqlWhere = sqlWhere + "and customer like '%" +
                    name + "%'";
        }
        if (!"".equals(cardform.getCardType())) {
            sqlWhere = sqlWhere + "and type_code ='" + cardform.getCardType() +
                    "'";
            cardform.setSqlWhere(sqlWhere);
        }
        String forword = super.doOrderBy(cfg);
        //cardform.setSqlWhere("");
        return forword;
    }

    private CardcaseTypeDao getCardcaseTypeDAO(GoaActionConfig cfg) {
        CardcaseTypeDao typeDao = ToolsFactory.getInstance().
                createCardcaseTypeDao(super.dbData);
        ( (BaseDAO) typeDao).setUserID(cfg.getBaseForm().getUserId());
        return typeDao;
    }
}
