package com.gever.goa.web.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * <p>Title: 报表模板操作通用方法类</p>
 * <p>Description: 报表模板操作通用方法类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

/**
 * @author Hu.Walker
 *
 */
public class TemplateUtils {
	
	/**
	 * 华表临时文件的相对路径
	 */
	public final static String TempCellReportPath = "/cell/report";
	/**
	 * 华表模板的存放路径
	 */
	public final static String TemplateCellReportPath = "/cell/template";
	public TemplateUtils() {
	}
	
	
	//以当前时间为文件名，并把以前的超过一定时间的文件删除
	public String makeCellName(String strDir) throws Exception {
		//十分钟
		final int TEN_MINUTE = 10 * 60 * 1000;
		//取当前时间
		long ll_cur = System.currentTimeMillis();
		//旧的文件名
		long ll_old;
		String strName;
		
		//取目录文件列表
		File dir = new File(strDir);
		File[] files = dir.listFiles();
		int len = files.length;
		int i = 0;
		int j = 0;
		for (i = 0; i < len; i++) {
			//取文件名，去掉扩展名
			strName = files[i].getName();
			j = strName.indexOf(".");
			try{
				ll_old = Long.parseLong(strName.substring(0, j));
			}catch(NumberFormatException e){
				ll_old = 0L;
			}
			
			//把十分钟前的文件删除
			if (ll_cur - ll_old > TEN_MINUTE) {
				files[i].delete();
			}
		}
		return String.valueOf(ll_cur);
	}
	/**
	 * 用系统时间做为临时文件的名字
	 * @param servletcontextrealpath String ServletContext.getRealPath()取得
	 * @return String 文件名字(包含文件相对路径)
	 */
	private static String getTempReportName(String servletcontextrealpath) {
		//取当前时间
		long ll_cur = System.currentTimeMillis();
		return TempCellReportPath + "/" + String.valueOf(ll_cur) + ".cll";
	}
	/**
	 * 创建临时文件
	 * @param bcell byte[] 文件的二进制字节流
	 * @param servletcontextrealpath String context的路径
	 * @throws Exception
	 * @return String 临时文件名称(包含文件相对路径)
	 */
	public static String makeTempReport(byte[] bcell,
			String servletcontextrealpath) throws
			Exception {
		String filedirpath = servletcontextrealpath + TempCellReportPath;
		String filename = getTempReportName(servletcontextrealpath);
		File file = new File(servletcontextrealpath + filename);
		OutputStream fos = new FileOutputStream(file);
		OutputStream os = new BufferedOutputStream(fos);
		os.write(bcell);
		os.close();
		
		//把十分钟前的文件删除
		final int TEN_MINUTE = 10 * 60 * 1000;
		deleteTempReport(filedirpath, TEN_MINUTE);
		
		return filename;
		
	}
	/**
	 * 删除临时文件
	 * @param filedir String 临时文件目录路径
	 * @param time int 时间期限
	 * @throws Exception
	 */
	private static void deleteTempReport(String filedir, int time) throws
	Exception {
		long ll_cur = System.currentTimeMillis();
		long ll_old;
		String strName;
		
		//取目录文件列表
		File dir = new File(filedir);
		File[] files = dir.listFiles();
		int len = files.length;
		int i = 0;
		int j = 0;
		for (i = 0; i < len; i++) {
			//取文件名，去掉扩展名
			strName = files[i].getName();
			j = strName.indexOf(".");
			ll_old = Long.parseLong(strName.substring(0, j));
			
			if (ll_cur - ll_old > time) {
				files[i].delete();
			}
		}
		
	}
}
