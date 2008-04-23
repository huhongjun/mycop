package com.gever.sysman.log.util;


import java.io.*;
import java.sql.*;

import com.gever.exception.*;
import com.gever.jdbc.sqlhelper.*;
import com.gever.util.log.Log;

/**
 * <p>Title:Log文件导出 </p>
 * <p>Description:Log文件导出 </p>
 * @version 1.0
 */

public class RsToFileUtil {
    private String dbData = "gdp";
    private String fileName="";
    Log log = Log.getInstance(RsToFileUtil.class);
    public RsToFileUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String getDbData() {
        return this.dbData;
    }

    //private String downFileName = new String("");

    private String nullToString(Object value) {
        String strRet = (String) value;
        if (null == strRet) {
            return strRet = "";
        }
        return strRet;
    }

    /**
     * @param type		－	取值：syslog
     * @param sqlWhere	－	SQL where 子句
     * @param fileName	－	保存的文件名
     * @param strRealPath	
     */
    public String execute(String type,String sqlWhere,String strRealPath)
    {
        log.showLog("----servlet--start---");
        String strPath = new String();

        if (!type.equals("")) 
        {
            String strsql = getSql(type, sqlWhere);
            this.createDirectory(strRealPath);
            try {
				strPath = this.sqlRsToFile(strsql, strRealPath, type);
			} catch (DefaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        return strPath;
    }

    /**
     * 建立目录
     * @param strDirName 目录名
     */
    private void createDirectory(String strDirName) {
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
            strsql = "select otime OperationTime,username OperationUser,module Module,ipAddress IPAddress,action ActionType,memo Memo  from T_SYSTEM_LOG where 1=1 " +
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
            this.setFileName(filePath);
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

    public void setDbData(String dbData) {
        this.dbData = dbData;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
