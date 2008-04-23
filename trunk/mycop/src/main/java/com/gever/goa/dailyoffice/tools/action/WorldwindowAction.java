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

/**世界之窗模块
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorldwindowAction extends BaseAction {
    private WorldwindowDao cDao;
    private WorldwindowTypeDao typeDao;
    public WorldwindowAction() {

    }

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {
        WorldwindowForm myForm = (WorldwindowForm) cfg.getBaseForm(); //得到form变量
        cDao = WorldwindowFactory.getInstance().createWorldwindowDao(super.
                dbData); //得到相对应dao的实例
        typeDao = WorldwindowFactory.getInstance().createWorldwindowTypeDao(super.
                dbData);
        cfg.setBaseDao( (BaseDAO) cDao); //设置父类dao
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new WorldwindowVO());
        return;
    }

    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
            Exception {
        //以下搜索出WorldwindowTypeVO置于request中
        WorldwindowTypeVO typevo = new WorldwindowTypeVO();
        WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
        cfg.getRequest().setAttribute("Worldwindow_types",
                                      typeDao.queryAll(typevo));
        String forword = super.toEdit(cfg);
        //进入edit前 置form.windowtype为vo.info_type;即置默认值
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        WorldwindowVO windowvo = (WorldwindowVO) windowform.getVo();
        String info_type = windowvo.getInfo_type();
        if (!"".equals(info_type)) {
            windowform.setWindowType(info_type);
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
        WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();
        log.showLog("----start-where-");
        String nodeid = StringUtils.nullToString(
                request.getParameter("nodeid"));
        if (!"".equals(nodeid)) { //判断是否传人父节点
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
        //insert和update之后置form中的windowtype为vo.info_type；
        //以便toList时按windowtype进行搜索；
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        WorldwindowVO windowvo = (WorldwindowVO) windowform.getVo();
        windowvo.setInfo_type(windowform.getWindowType());
        if (!isBack) { //保存，不返回，重新load一次Worldwindow Type
            //以下搜索出WorldwindowTypeVO置于request中
            WorldwindowTypeVO typevo = new WorldwindowTypeVO();
            WorldwindowForm form = (WorldwindowForm) cfg.getBaseForm();
            cfg.getRequest().setAttribute("Worldwindow_types",
                                          typeDao.queryAll(typevo));
        }
        cfg.getBaseDao().setIsIdMng(false);
        windowform.setSearchValue(""); //插入一条记录以后应置页面搜索值为空为空
        windowform.setSqlWhere("");
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
        //insert和update之后置form中的windowtype为vo.info_type；
        WorldwindowForm windowform = (WorldwindowForm) cfg.getBaseForm();
        WorldwindowVO windowvo = (WorldwindowVO) windowform.getVo();
        windowvo.setInfo_type(windowform.getWindowType());
        if (!isBack) { //保存，不返回，重新load一次WorldwindowType
            //以下搜索出WorldwindowTypeVO置于request中
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
        //将其对应的typeVO查询出来，在页面上将显示type_name;
        String info_type = ( (WorldwindowVO) cfg.getBaseForm().getVo()).
                getInfo_type();
        WorldwindowTypeVO typevo = new WorldwindowTypeVO();
        typevo.setInfo_type(info_type);
        cfg.getRequest().setAttribute("WindowTypeVO", typeDao.queryByPK(typevo));

        return forword;
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
     * 排序动作；
     * @param cfg 当前Action相关配置类
     * @return forward地址
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
 * 删除动作(继续返回到本页面新增)
 * @param cfg 当前Action相关配置类
 * @return forward地址
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
    //cardform.setSqlWhere(""); //清空条件

    return forword;
}


}
