package com.gever.goa.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gever.exception.DefaultException;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.log.util.RsToFileServlet;
import com.gever.util.log.Log;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title:Log文件导出 </p>
 * <p>Description:Log文件导出 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: GEVER</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class RsToFile extends HttpServlet {
    private String dbData = "gdp";
    private static String QUERY_SYSLOG_SQL="select otime 操作时间,username 操作人,module 模块,ipAddress 客户端IP地址,action 动作类型,memo 备注  from T_SYSTEM_LOG where 1=1 ";
    private static String QUERY_SMSOUTBOX_SQL="SELECT SN 发送者,USERNAME 收信人,MBNO 手机号,MSG 短消息,SENDDATE 发送日期,SENDTIME 发送时间,SMSTYPE 发送类型 ,type 状态 FROM "
            +"(SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'已发送' as TYPE "
            +"FROM SendedOutBox "
            + "UNION ALL SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'正在发送' as TYPE "
            +"FROM OutBox  "
            +"UNION ALL SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'发送失败' as TYPE "
            +"FROM BadOutBox "
            +
                "UNION ALL SELECT ID,EXPRESSLEVEL,SN,USERNAME,MBNO,MSG,SENDDATE,SENDTIME,SMSTYPE,'定时发送' as TYPE "
            +"FROM TimingOutBox) "
            +"  ALLTABLE  WHERE 1=1 ";
    private static String QUERY_INBOX_SQL="SELECT USER_CODE 手机号码 , bb.user_name 接收人,send_time 发送时间,READ_TIME 阅读时间,CONTENT 消息内容 \n"
            +"   from DO_INNER_MSG  aa left join (\n"
            +"        Select b.name as user_name ,a.mobile from HR_ARCHIVE  a \n"
            +"            inner join t_user  b on b.code = a.employee_code \n"
            +"        where length(a.mobile)>5 \n"
            +"        union all \n"
            +"        Select a.customer as user_name, a.mobile \n"
            +"        from DO_CARDCASE  a inner join \n"
            +"        (select b.user_code,b.type_name,b.type_code,t_user.name \n"
            +"         from DO_CARDCASE_type  b \n"
            +"         inner join t_user on t_user.id =b.user_code )  c \n"
            +"         on a.type_code = c.type_code where 1=1 and length(a.mobile)>5 \n"
            +"  )  bb on aa.user_code = bb.mobile where msg_type= '2'\n";
    Log log = Log.getInstance(RsToFileServlet.class);
    public RsToFile() {

    }
    public void setOracleSQL() {

        QUERY_INBOX_SQL = "    SELECT USER_CODE 手机号码 , bb.user_name 接收人,send_time 发送时间,READ_TIME 阅读时间,CONTENT 消息内容 \n"
            + "    from DO_INNER_MSG  aa , (\n"
            + "                      Select b.name as user_name ,a.mobile from HR_ARCHIVE  a \n"
            +
            "                         , t_user  b where b.code = a.employee_code \n"
            + "                    and length(a.mobile)>5 \n"
            + "                  union all \n"
            + "                  Select a.customer as user_name, a.mobile \n"
            + "                      from DO_CARDCASE  a ,\n"
            + "                     (select b.user_code,b.type_name,b.type_code,t_user.name \n"
            + "                     from DO_CARDCASE_type  b \n"
            +
            "                     ,t_user where t_user.id =b.user_code )  c \n"
            + "                     where a.type_code = c.type_code(+) and 1=1 and length(a.mobile)>5 \n"
            +
            "             )  bb where aa.user_code = bb.mobile(+) and msg_type= '2'\n";


    }
    private String getDbData() {
        return this.dbData;
    }

    private String downFileName = new String("");

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

    }

    private String nullToString(Object value) {
        String strRet = (String) value;
        if (null == strRet) {
            return strRet = "";
        }
        return strRet;
    }
    ActiveUsers au = ActiveUsers.getInstance();
    
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException,
        IOException {
        if (au.isOracle() ){
            this.setOracleSQL();
        }
        ServletOutputStream out = response.getOutputStream();
        final String CONTENT_TYPE =
            "application/octet-stream; charset=ISO-8859-1";
        final String CONTENT_TYPE_TEXT = "text/html; charset=GB2312";
        log.showLog("----servlet--start---");
        String type = new String();
        String sqlwhere = new String();
        type = nullToString(request.getParameter("type"));
        if (!nullToString(request.getParameter("fileName")).equals(""))
            this.setDownFileName(request.getParameter("fileName"));
        String strRealPath = this.getServletContext().getRealPath("/");
        String strPath = new String();

        if (!type.equals("")) {
            sqlwhere = nullToString(request.getParameter("sqlwhere"));
            String strsql = getSql(type, sqlwhere);
            log.showLog("---strsql-----" + strsql);
            try {
                this.createDirtory(strRealPath);
                strPath = this.sqlRsToFile(strsql, strRealPath, type);

                //读文件
                java.io.File file = new java.io.File(strPath);
                java.io.FileInputStream fin = new java.io.FileInputStream(file);
                long len = file.length();
                Long lng = new Long(len);
                String strFile2 = new String(getDownFileName().getBytes(),
                                             "ISO-8859-1");
                response.addHeader("Content-Disposition",
                                   "attachment; filename=" + strFile2);
                response.addHeader("Content-Length", lng.toString());
                response.setContentType(CONTENT_TYPE);
                //把文件字节缓存到outStream中
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                int b;
                while ( (b = fin.read()) != -1) {
                    outStream.write(b);
                }

                //向客户端写文件
                out.write(outStream.toByteArray());
                out.flush();
                out.close();
                outStream.close();
                fin.close();

            } catch (DefaultException e) {
                response.setContentType(CONTENT_TYPE_TEXT);
                out.print("");
                out.print("Sorry, file not found!");
                out.print(" close ");

                e.printStackTrace(System.out);
            }
        } else {
            response.setContentType(CONTENT_TYPE_TEXT);
            out.print("");
            out.print("Sorry, file not found!");
            out.print(" close ");

        }

    }

    /**
     * 建立目录
     * @param strDirName 目录名
     */
    private void createDirtory(String strDirName) {
        File strDir = new File(strDirName);
        if (!strDir.isDirectory()) { //如果还没有建立目录当前用户的文件夹
            strDir.mkdir();
        }
        strDir = null;
    }



    /**
     * 字符串替换方法
     * Wengnb Add 2003-09-09
     * @param strSource String:字符串
     * @param strFrom   String:源字串
     * @param strTo     String:替换的字串
     * @return          String:最终替换后的字串结果
     */
    public static String replace(String strSource, String strFrom, String strTo) {
        if (strSource == null)
            return "";

        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos = 0;

        while ( (intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
    }

    /**
     * Wengnb Add 2003-09-09
     * 将"'"替换成为"''"
     * @param strSource String:字符串
     * @return          String:最终替换后的字串结果
     */
    public static String replaceText(String strSource) {
        return replace(strSource, "'", "''");
    }

    /**
     * 导出历史信息记录
     * @param strsql 条件子句
     * @param realPath 文件下载的路径
     * @param type 类型
     * @return 成功与否
     * @throws GeneralException
     */
    public synchronized String sqlRsToFile(String strsql, String realPath,
                                           String strType) throws
        DefaultException {

        SQLHelper helper = new DefaultSQLHelper(this.getDbData());
        Connection conn = helper.getConnection();
        Statement st = null;
        ResultSet rs = null;
        java.sql.ResultSetMetaData rsMeta = null;

        StringBuffer sBufSql = new StringBuffer();
        //StringBuffer sBufTxt = new StringBuffer();
        boolean bIsInBox = true;
        String strSubWhere = new String();
        String strFldName;
        String strValue;
        int iRet = 0;
        //先建文件

        realPath = realPath + "uploadfiles\\";
        File dirName = new File(realPath);
        if (!dirName.exists()) {
            dirName.mkdirs();
        }
        String filePath;
        filePath = replace(realPath + strType + ".txt", "\\",
                           "/");
//        filePath ="d:/GeverAS/webapps/autoload/goas/uploadfiles/smsinbox.goa";
        log.showLog("----realpath=" + filePath);
        StringBuffer sBufTxt = new StringBuffer();
        try {

            st = conn.createStatement();
            //PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
            //先从发短信息,后再从收短信息中导出
            rs = st.executeQuery(strsql);

            rsMeta = rs.getMetaData();
            int cols = 0;
            cols = rsMeta.getColumnCount();
            //先得到表头
            sBufTxt.setLength(0);

            for (int idx = 1; idx <= cols; idx++) {
                strFldName = (rsMeta.getColumnName(idx));
                sBufTxt.append(strFldName);
                if (idx < cols)
                    sBufTxt.append("\t");
            }
            //pw.println(sBufTxt);
            sBufTxt.append("\n\r");
            //得到所有记录
            while (rs.next()) {
                // sBufTxt.setLength(0);
                for (int idx = 1; idx <= cols; idx++) {
                    strFldName = (rsMeta.getColumnName(idx));
                    sBufTxt.append(rs.getString(strFldName));
                    if (idx < cols)
                        sBufTxt.append("\t");
                }
                //pw.println(sBufTxt);
                sBufTxt.append("\n\r");
                iRet++;
            }
            log.showLog("-------sql rows---" + iRet + filePath);
            rs.close();
            st.close();
            log.showLog("-------filePath---" + iRet + filePath);
            File myFilePath = new File(filePath);
            if (!myFilePath.exists())
                myFilePath.createNewFile();
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(sBufTxt.toString());
            resultFile.close();
            myFile.close();

        } catch (FileNotFoundException fnfe) {
            log.showLog("文件未找到..****..");
        } catch (IOException e) {
            //错误处理
            log.showLog("写入文件错误" + e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            throw new DefaultException(e);

        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (java.sql.SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }

        return filePath;
    }

    public String getDownFileName() {
        return downFileName;
    }

    public void setDownFileName(String downFileName) {
        this.downFileName = downFileName;
    }

    public void setDbData(String dbData) {
        this.dbData = dbData;
    }


    public String getSql(String type, String sqlwhere) {

        String strsql = new String();
        StringBuffer strBuf = new StringBuffer();
        if (type.equals("syslog")) {
            strsql = this.QUERY_SYSLOG_SQL+sqlwhere;
        } else if ("smsoutbox".equals(type)) {
        	strsql = this.QUERY_SMSOUTBOX_SQL+sqlwhere;
       //     System.out.println("=短信发件箱====" + strsql);
        } else if ("smsinbox".equals(type)) {

            strsql =this.QUERY_INBOX_SQL;
        }

        return strsql;
    }

}
