package com.gever.exception;

/**
 * <p>Title: 异常处理类 </p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

import java.sql.SQLWarning;
import java.util.Properties;

public class DefaultException extends Exception {
	/**
	 * 静态常量，表示错误级别。
	 */
	public final static String INFO = "info";
	public final static String WARN = "warn";
	public final static String DEBUG = "debug";
	public final static String ERROR = "error";

	public static boolean isFileMsg = true; // 是否是文件配置的错误信息
	private Throwable eThrow; 				// Exception接口

	private String errorCode; 				// 错误代码
	private String errorDescription = ""; 	// 错误信息
	private String errorModel = null; 		// 错误模块
	private String errorLevel = WARN; 		// 错误级别,默认为WARN

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public String getErrorModel() {
		return errorModel;
	}

	public String getErrorLevel() {
		return errorLevel;
	}

	public String toString() {

		return new StringBuffer().append("Error code:").append(errorCode)
				.append(" Error level:").append(errorLevel).append(
						" Error model:").append(errorModel).append(
						" Error description:").append(errorDescription)
				.toString();
	}

	public DefaultException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public DefaultException(String errorCode, String errorLevel) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorLevel = errorLevel;
	}

	public DefaultException(String errorCode, String errorLevel,
			String errorModel) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorModel = errorModel;
		this.errorLevel = errorLevel;
	}

	public DefaultException(String errorCode, String errorDescription,
			String errorModel, String errorLevel) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.errorModel = errorModel;
		this.errorLevel = errorLevel;
	}

	public DefaultException(String errorCode, Throwable aThrow) {
		super(errorCode, aThrow);
		this.errorCode = errorCode;
		this.errorDescription = aThrow.getMessage();
		this.eThrow = aThrow;
	}

	public DefaultException(String errorCode, String errorLevel,
			Throwable aThrow) {
		super(errorCode, aThrow);
		this.errorCode = errorCode;
		this.errorLevel = errorLevel;
		this.errorDescription = aThrow.getMessage();
		this.eThrow = aThrow;
	}

	public DefaultException(Throwable eThrow) {
		super(eThrow);
		String errCode = "";
		String errLevel = "";
		String errMsg = "";

		// eThrow.printStackTrace();
		if (eThrow instanceof DefaultException) {
			DefaultException de = (DefaultException) eThrow;
			errCode = de.errorCode;
			errMsg = de.getErrorDescription();
			errLevel = de.errorLevel;
		} else if (eThrow instanceof SQLWarning) {
			errCode = "err.system.SQLWarning";
			errMsg = eThrow.getMessage();
			errLevel = WARN;
		} else {
			errCode = eThrow.getClass().getName();
			errMsg = eThrow.getMessage();
		}

		this.errorCode = errCode;
		this.errorLevel = errLevel;
		this.errorDescription = errMsg;

	}

	/**
	 * 从错误信息属性文件中读取错误信息
	 * 
	 * @param strErrorCode
	 * @return
	 */
	public String getErrorMessage(String strErrorCode) {
		String strMsg = "";

		try {
			ErrorConfig config = ErrorConfig.getInstance();
			Properties prop = config.getProperties();
			strMsg = prop.getProperty(strErrorCode);
			if (strMsg == null || "".equals(strMsg))
				strMsg = strErrorCode;
			this.errorDescription = strMsg;
		} catch (java.io.IOException e) {
			isFileMsg = false; // 出错说明没有这个错误配置文件
		}
		return strMsg;
	}

	public void setIsFileMsg(boolean isFileMsg) {
		this.isFileMsg = isFileMsg;
	}

	public boolean getIsFileMsg() {
		return isFileMsg;
	}
}