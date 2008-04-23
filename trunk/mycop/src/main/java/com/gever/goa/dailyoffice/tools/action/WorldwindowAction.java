package com.gever.goa.dailyoffice.tools.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowDao;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowFactory;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowTypeDao;
import com.gever.goa.dailyoffice.tools.vo.WorldwindowTypeVO;
import com.gever.goa.dailyoffice.tools.vo.WorldwindowVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;

/**����֮��ģ��
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorldwindowAction extends BaseAction {
    private WorldwindowDao cDao;
    private WorldwindowTypeDao typeDao;
    public WorldwindowAction() {

    }

    /**
     * ��ʼ��ҳ������
     * @param actionform ��ǰ��vo����
     * @return ��ǰ���õ�vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {
        WorldwindowForm myForm = (WorldwindowForm) cfg.getBaseForm(); //�õ�form����
        cDao = WorldwindowFactory.getInstance().createWorldwindowDao(super.
                dbData); //�õ����Ӧdao��ʵ��
        typeDao = WorldwindowFactory.getInstance().createWorldwindowTypeDao(super.
                dbData);
        cfg.setBaseDao( (BaseDAO) cDao); //���ø���dao
        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null)
            myForm.setVo(new WorldwindowVO());
        return;
    }

    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
            Exception {
        //����������WorldwindowTypeVO����request��
        WorldwindowTypeVO typevo = new WorldwindowTypeVO();
        WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
        cfg.getRequest().setAttribute("Worldwindow_types",
                                      typeDao.queryAll(typevo));
        String forword = super.toEdit(cfg);
        //����editǰ ��form.windowtypeΪvo.info_type;����Ĭ��ֵ
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        WorldwindowVO windowvo = (WorldwindowVO) windowform.getVo();
        String info_type = windowvo.getInfo_type();
        if (!"".equals(info_type)) {
            windowform.setWindowType(info_type);
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
        WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        log.showLog("----start-where-");
        String nodeid = StringUtils.nullToString(
                request.getParameter("nodeid"));
        if (!"".equals(nodeid)) { //�ж��Ƿ��˸��ڵ�
            form.setWindowType(nodeid);
            form.setSearchValue("");
            form.setSqlWhere(" and info_type = '" + nodeid + "'");
        }else {
           String sqlWhere="";
           String name = form.getSearchValue();
           if ( (!"".equals(name)) && (!"'".equals(name))) {
               sqlWhere += " and title like '%" + name + "%'";
           }
           String windowtype = form.getWindowType();
            if (windowtype!=null && !"".equals(windowtype)) {
                form.setSqlWhere(sqlWhere+" and info_type = '" +
                                 windowtype + "'");
            }



       }

        String forword = super.toList(cfg);
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
        //insert��update֮����form�е�windowtypeΪvo.info_type��
        //�Ա�toListʱ��windowtype����������
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        WorldwindowVO windowvo = (WorldwindowVO) windowform.getVo();
        windowvo.setInfo_type(windowform.getWindowType());
        if (!isBack) { //���棬�����أ�����loadһ��Worldwindow Type
            //����������WorldwindowTypeVO����request��
            WorldwindowTypeVO typevo = new WorldwindowTypeVO();
            WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
            cfg.getRequest().setAttribute("Worldwindow_types",
                                          typeDao.queryAll(typevo));
        }
        cfg.getBaseDao().setIsIdMng(false);
        windowform.setSearchValue(""); //����һ����¼�Ժ�Ӧ��ҳ������ֵΪ��Ϊ��
        windowform.setSqlWhere("");
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
        //insert��update֮����form�е�windowtypeΪvo.info_type��
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        WorldwindowVO windowvo = (WorldwindowVO) windowform.getVo();
        windowvo.setInfo_type(windowform.getWindowType());
        if (!isBack) { //���棬�����أ�����loadһ��WorldwindowType
            //����������WorldwindowTypeVO����request��
            WorldwindowTypeVO typevo = new WorldwindowTypeVO();
            WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
            cfg.getRequest().setAttribute("Worldwindow_types",
                                          typeDao.queryAll(typevo));
        }


        return super.doUpdate(cfg, isBack);
    }

    public String toView(GoaActionConfig cfg) throws DefaultException,
            Exception {
        String forword = super.toView(cfg);
        //�����Ӧ��typeVO��ѯ��������ҳ���Ͻ���ʾtype_name;
        String info_type = ( (WorldwindowVO) cfg.getBaseForm().getVo()).
                getInfo_type();
        WorldwindowTypeVO typevo = new WorldwindowTypeVO();
        typevo.setInfo_type(info_type);
        cfg.getRequest().setAttribute("WindowTypeVO", typeDao.queryByPK(typevo));

        return forword;
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
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        String sqlWhere = "";
        String name = windowform.getSearchValue();
        name=StringUtils.replaceText(name);
        if ( !"".equals(name)) {
            sqlWhere += " and title like '%" + name + "%'";
        }
        String info_type= windowform.getWindowType();
        if(!"".equals(info_type)){
            sqlWhere += " and info_type='"+info_type+"'";
            windowform.setSqlWhere(sqlWhere);
        }

        return super.toList(cfg);
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
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        String sqlWhere = "";

        if (!"".equals(windowform.getSearchValue())) {
            sqlWhere = sqlWhere + "and title like '%" +
                    windowform.getSearchValue() + "%'";
        }
        if (!"".equals(windowform.getWindowType())) {
            sqlWhere = sqlWhere + "and info_type ='" + windowform.getWindowType()
                    + "'";
            windowform.setSqlWhere(sqlWhere);
        }



        String forword = super.doOrderBy(cfg);

        //cardform.setSqlWhere("");
        return forword;
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
    WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
    String sqlWhere = "";

    if (!"".equals(form.getSearchValue())) {
        sqlWhere = sqlWhere + "and title like '%" +
                form.getSearchValue() + "%'";
    }
    if (!"".equals(form.getWindowType())) {
        sqlWhere = sqlWhere + "and info_type ='" + form.getWindowType() +
                "'";
         form.setSqlWhere(sqlWhere);
    }

    String forword = super.doDelete(cfg);
    //cardform.setSqlWhere(""); //�������

    return forword;
}


}
