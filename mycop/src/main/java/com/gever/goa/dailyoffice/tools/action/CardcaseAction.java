package com.gever.goa.dailyoffice.tools.action;

/**
 * <p>Title: ��Ƭ��Action</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
     * ��ʼ��ҳ������
     * @param cfg ��ǰ��������Ϣ
     * @return  ���ĺ��������Ϣ
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {

        CardcaseForm myForm = (CardcaseForm) cfg.getBaseForm(); //�õ�form����
        CardcaseDao cDao = ToolsFactory.getInstance().createCardcaseDao(super.
                dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) cDao); //���ø���dao

        log.showLog("******myForm.getSearchValue()****"+myForm.getSearchValue());
        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null)
            myForm.setVo(new CardcaseVO());

        return;
    }

    /**
     * ��ת���޸�����ҳ��
     * @param cfg GoaActionConfig ������Ϣ
     * @throws DefaultException
     * @throws Exception
     * @return String һ��forword������
     */
    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
            Exception {
        //����������CardcaseTypeVO����request��
        CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
        CardcaseTypeVO typevo = new CardcaseTypeVO();
        CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
        typevo.setUser_code(form.getUserId());
        cfg.getRequest().setAttribute("cardcase_types",
                                      typeDao.queryByUser(typevo));

        String forword = super.toEdit(cfg);

        //����editǰ ��form.cardtypeΪvo.type_code;����Ĭ��ֵ
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        CardcaseVO cardvo = (CardcaseVO) cardform.getVo();
        String type_code = cardvo.getType_code();
        if (!"".equals(type_code)) {
            cardform.setCardType(type_code);
        }
        return forword;
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
        //BaseDAO basedao=cfg.getBaseDao();
        CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        log.showLog("----start-where-");
        String nodeid = StringUtils.nullToString(
                request.getParameter("nodeid"));
        if (!"".equals(nodeid)) { //�ж��Ƿ��˸��ڵ�
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

        //form.setSqlWhere(""); //�������ֵ��

        return forword;
    }

    /**
     * ��������(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */
    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        //insert��update֮����form�е�cardtypeΪvo.type_code��
        //�Ա�toListʱ��cardtype����������
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        CardcaseVO cardvo = (CardcaseVO) cardform.getVo();
        //cardform.setCardType(cardvo.getType_code());
        cardvo.setType_code(cardform.getCardType());

        if (!isBack) { //���棬�����أ�����loadһ��Cardcase Type
            CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
            //����������CardcaseTypeVO����request��
            CardcaseTypeVO typevo = new CardcaseTypeVO();
            // CardcaseForm form = (CardcaseForm) cfg.getBaseForm();
            typevo.setUser_code(cardform.getUserId());
            //form.setCardcase_types(typeDao.queryByUser(vo));
            cfg.getRequest().setAttribute("cardcase_types",
                                          typeDao.queryByUser(typevo));

        }
        cardform.setSearchValue(""); //����һ����¼�Ժ�Ӧ��ҳ������ֵΪ��Ϊ��
        cardform.setSqlWhere("");
        return super.doInsert(cfg, isBack);
    }

    /**
     * �޸Ķ���(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @param isBack �Ƿ񷵻�
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        //insert��update֮����form�е�cardtypeΪvo.type_code��
        //�Ա�toListʱ��cardtype����������
        CardcaseForm cardform = (CardcaseForm) cfg.getBaseForm();
        CardcaseVO cardvo = (CardcaseVO) cardform.getVo();
        //cardform.setCardType(cardvo.getType_code());
        cardvo.setType_code(cardform.getCardType());

        if (!isBack) { //���棬�����أ�����loadһ��Cardcase Type
            //����������CardcaseTypeVO����request��
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
     * �������������в���
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String ����forword������
     */
    public String doSearchByName(GoaActionConfig cfg) throws
            DefaultException, Exception {
        //��BForm�����ò�ѯ����������������
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
     * ɾ������(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
        //cardform.setSqlWhere(""); //�������

        return forword;
    }

    /**
     * ����鿴ҳ�档����
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String һ��forword
     */
    public String toView(GoaActionConfig cfg) throws DefaultException,
            Exception {
        String forword = super.toView(cfg);

        //�����Ӧ��typeVO��ѯ��������ҳ���Ͻ���ʾtype_name;
        CardcaseTypeDao typeDao = this.getCardcaseTypeDAO(cfg);
        String type_code = ( (CardcaseVO) cfg.getBaseForm().getVo()).
                getType_code();
        CardcaseTypeVO typevo = new CardcaseTypeVO();
        typevo.setType_code(type_code);
        cfg.getRequest().setAttribute("CardTypeVO", typeDao.queryByPK(typevo));

        return forword;
    }

    /**
     * ��������
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
