package com.gever.goa.web.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * <p>Title: ����ģ�����ͨ�÷�����</p>
 * <p>Description: ����ģ�����ͨ�÷�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

/**
 * @author Hu.Walker
 *
 */
public class TemplateUtils {
	
	/**
	 * ������ʱ�ļ������·��
	 */
	public final static String TempCellReportPath = "/cell/report";
	/**
	 * ����ģ��Ĵ��·��
	 */
	public final static String TemplateCellReportPath = "/cell/template";
	public TemplateUtils() {
	}
	
	
	//�Ե�ǰʱ��Ϊ�ļ�����������ǰ�ĳ���һ��ʱ����ļ�ɾ��
	public String makeCellName(String strDir) throws Exception {
		//ʮ����
		final int TEN_MINUTE = 10 * 60 * 1000;
		//ȡ��ǰʱ��
		long ll_cur = System.currentTimeMillis();
		//�ɵ��ļ���
		long ll_old;
		String strName;
		
		//ȡĿ¼�ļ��б�
		File dir = new File(strDir);
		File[] files = dir.listFiles();
		int len = files.length;
		int i = 0;
		int j = 0;
		for (i = 0; i < len; i++) {
			//ȡ�ļ�����ȥ����չ��
			strName = files[i].getName();
			j = strName.indexOf(".");
			try{
				ll_old = Long.parseLong(strName.substring(0, j));
			}catch(NumberFormatException e){
				ll_old = 0L;
			}
			
			//��ʮ����ǰ���ļ�ɾ��
			if (ll_cur - ll_old > TEN_MINUTE) {
				files[i].delete();
			}
		}
		return String.valueOf(ll_cur);
	}
	/**
	 * ��ϵͳʱ����Ϊ��ʱ�ļ�������
	 * @param servletcontextrealpath String ServletContext.getRealPath()ȡ��
	 * @return String �ļ�����(�����ļ����·��)
	 */
	private static String getTempReportName(String servletcontextrealpath) {
		//ȡ��ǰʱ��
		long ll_cur = System.currentTimeMillis();
		return TempCellReportPath + "/" + String.valueOf(ll_cur) + ".cll";
	}
	/**
	 * ������ʱ�ļ�
	 * @param bcell byte[] �ļ��Ķ������ֽ���
	 * @param servletcontextrealpath String context��·��
	 * @throws Exception
	 * @return String ��ʱ�ļ�����(�����ļ����·��)
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
		
		//��ʮ����ǰ���ļ�ɾ��
		final int TEN_MINUTE = 10 * 60 * 1000;
		deleteTempReport(filedirpath, TEN_MINUTE);
		
		return filename;
		
	}
	/**
	 * ɾ����ʱ�ļ�
	 * @param filedir String ��ʱ�ļ�Ŀ¼·��
	 * @param time int ʱ������
	 * @throws Exception
	 */
	private static void deleteTempReport(String filedir, int time) throws
	Exception {
		long ll_cur = System.currentTimeMillis();
		long ll_old;
		String strName;
		
		//ȡĿ¼�ļ��б�
		File dir = new File(filedir);
		File[] files = dir.listFiles();
		int len = files.length;
		int i = 0;
		int j = 0;
		for (i = 0; i < len; i++) {
			//ȡ�ļ�����ȥ����չ��
			strName = files[i].getName();
			j = strName.indexOf(".");
			ll_old = Long.parseLong(strName.substring(0, j));
			
			if (ll_cur - ll_old > time) {
				files[i].delete();
			}
		}
		
	}
}
