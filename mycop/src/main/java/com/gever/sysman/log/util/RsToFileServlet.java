package com.gever.sysman.log.util;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.gever.exception.*;
import com.gever.jdbc.sqlhelper.*;
import com.gever.util.log.Log;

/**
 * <p>Title:Log文件导出 </p>
 * <p>Description:Log文件导出 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: GEVER</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class RsToFileServlet extends HttpServlet {
    private String dbData = "gdp";
    Log log = Log.getInstance(RsToFileServlet.class);
    public RsToFileServlet() {

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
    
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException,
        IOException {
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
        	// =============================================================================
        	// 胡用添加，"%" 符号不能当作参数传递
        	sqlwhere = request.getParameter("sqlwhere");
        	if(sqlwhere != null) sqlwhere = new String(replace(sqlwhere,"|","%").getBytes("ISO8859-1"),"GB2312");
        	// =============================================================================
            sqlwhere = nullToString(sqlwhere);
            String strsql = getSql(type, sqlwhere);
            try {
                this.createDirtory(strRealPath);
                RsToFileUtil rs = new RsToFileUtil(); 
                strPath = rs.sqlRsToFile(strsql, strRealPath, type);

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

    public String getSql(String type, String sqlwhere) {

        String strsql = new String();
        StringBuffer strBuf = new StringBuffer();
        if (type.equals("syslog")) {
            strsql = "select otime 操作时间,username 操作人,module 模块,ipAddress 客户端IP地址,action 动作类型,memo 备注  from T_SYSTEM_LOG where 1=1 " +
                sqlwhere;
        }
        return strsql;
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

        realPath = realPath + "uploadfiles" + File.separator;
        File dirName = new File(realPath);
        if (!dirName.exists()) {
            dirName.mkdirs();
        }
        String filePath;
        filePath = realPath + strType + ".txt";
        //filePath = replace(realPath + strType + ".txt", "\\", "/");
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

}
