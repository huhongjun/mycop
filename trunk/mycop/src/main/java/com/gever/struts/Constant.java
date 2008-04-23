package com.gever.struts;

/**
 * <p>Title:常量类 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class Constant {
    //动作常量
    public final static String ADD_ACTION = "add";
    public final static String INSERT_ACTION = "insert";
    public final static String MODIFY_ACTION = "modify";
    public final static String UPDATE_ACTION = "update";
    public final static String DELETE_ACTION = "delete";
    public final static String REMOVE_ACTION = "remove";
    protected final static String QUERY_ACTION = "query";
    protected final static String PAGE_ACTION = "doPage";
    protected final static String COPY_ACTION = "copy";
    protected final static String LIST_ACTION = "toList";
    protected final static String EDIT_ACTION = "toEdit";
    protected final static String GOURL_ACTION = "goUrl"; //页面跳转

    protected final static String FORWORD_LIST_PAGE = "index";
    protected final static String FORWORD_EDIT_PAGE = "edit";
    protected final static String QUERY_PAGE = "query";
    public final static String LAST_PAGE = "last";
    public final static String FIRST_PAGE = "first";

    /*----------------- session 部分 ----------------------*/
    public final static String USER_ID = "USERID";   //用户ID号
    public final static String USER_NAME = "loginname";   //用户名称
    public final static String PASS_WORD = "Pass_wORD";   //登入密码
    public final static String DEPT_CODES = "DEPT_CODES";   //部门id(1001,1002)
    public final static String DEPT_NAMES = "DEPT_NAMES";   //部门名称(开发部,行财部)
    public final static String NAME = "username";   //用户名称
    public final static String USER_FILTER = "userfilter";  //用户层级
    public final static String LIST_DEPTS = "listdepts";   //当前用户所对应的部门列表
    public final static String USER_PERMISSION = "USERPERMISSION";   //用户权限表

    public static final String DAO_TYPE = "daoType";
    public static final String DAO_TYPE_HIBERNATE = "hibernate";
    public static final String SESSION_FACTORY = "hibernate/sessionFactory";
    public static final String DATA_SOURCE =  "goa";

    public static final String CSS_PATH = "/css/goas.css";  //是否
    public static final String IS_ADMIN = "false";  //是否系统管理员
    public static final String LIST_FORM = "LIST_FORMNAME";
    public static final String MENU_STYLE = "MENU_STYLE";
    public static final String SCREEN_XY = "SCREEN_XY";
}