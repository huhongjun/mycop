package com.gever.web.servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.gever.exception.ErrorConfig;
import com.gever.goa.util.timer.AutoTasks;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.sqlhelper.SQLHelperUtil;
import com.gever.util.StringUtils;
import com.gever.util.log.Log;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title:  初始化servlet </p>
 * <p>Description: 包括log,错误配置文件,定时器等功能</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class InitServlet extends javax.servlet.http.HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=GBK";
    //Initialize global variables
    private  AutoTasks auto = null;
    
  public void init(javax.servlet.ServletConfig config) throws ServletException {
        try {
            ConnectionProviderFactory.init();
            super.init(config);
            ServletContext context = getServletContext();

            //得到web.xml中InitServlet的配置信息
            boolean uselog4j = Boolean.valueOf(getInitParameter("uselog4j")).booleanValue();
            boolean debug = Boolean.valueOf(getInitParameter("debug")).booleanValue();
            String debugFile =  context.getRealPath( getInitParameter("debugFile") );
            String dbtype = getInitParameter("dbtype").toString();
            ActiveUsers au = ActiveUsers.getInstance();
            if (!StringUtils.isNull(dbtype)){
            	au.setDbtype(dbtype);
            }
            
            //初始化Log类
            Log.init(debug, uselog4j, debugFile);
            Log log = Log.getInstance(InitServlet.class);
            com.gever.jdbc.sqlhelper.SQLHelperUtil sUtil = new SQLHelperUtil();
            sUtil.setDbtype(dbtype);
            
            //定时器(自动运行)
            String timerFile =  context.getRealPath( getInitParameter("timerFile") );          
            auto = new AutoTasks();
            auto.start(timerFile);
            
            //异常配置文件
            String errorFile =  context.getRealPath( getInitParameter("errorFile") );
            ErrorConfig errorConfig = ErrorConfig.getInstance(errorFile);

            //TODO: css 配置
            //

        } catch (Exception ex) {
            throw new ServletException(ex);

        }
    }
    public void destroy() {

        auto.close();
        super.destroy();

 }

}
