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
 * <p>Title:  ��ʼ��servlet </p>
 * <p>Description: ����log,���������ļ�,��ʱ���ȹ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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

            //�õ�web.xml��InitServlet��������Ϣ
            boolean uselog4j = Boolean.valueOf(getInitParameter("uselog4j")).booleanValue();
            boolean debug = Boolean.valueOf(getInitParameter("debug")).booleanValue();
            String debugFile =  context.getRealPath( getInitParameter("debugFile") );
            String dbtype = getInitParameter("dbtype").toString();
            ActiveUsers au = ActiveUsers.getInstance();
            if (!StringUtils.isNull(dbtype)){
            	au.setDbtype(dbtype);
            }
            
            //��ʼ��Log��
            Log.init(debug, uselog4j, debugFile);
            Log log = Log.getInstance(InitServlet.class);
            com.gever.jdbc.sqlhelper.SQLHelperUtil sUtil = new SQLHelperUtil();
            sUtil.setDbtype(dbtype);
            
            //��ʱ��(�Զ�����)
            String timerFile =  context.getRealPath( getInitParameter("timerFile") );          
            auto = new AutoTasks();
            auto.start(timerFile);
            
            //�쳣�����ļ�
            String errorFile =  context.getRealPath( getInitParameter("errorFile") );
            ErrorConfig errorConfig = ErrorConfig.getInstance(errorFile);

            //TODO: css ����
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
