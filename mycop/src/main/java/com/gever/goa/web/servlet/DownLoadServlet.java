package com.gever.goa.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: 2004-10-23</p>
 * <p>Company:天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DownLoadServlet extends javax.servlet.http.HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

	}

	public void service(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		final String CONTENT_TYPE = "application/octet-stream; charset=ISO-8859-1";
		final String CONTENT_TYPE_TEXT = "text/html; charset=GB2312";
		String strFileName = new String("");
		String strFilePath = new String("");
		strFileName = request.getParameter("filename");
		strFilePath = request.getParameter("filepath");

		if (strFilePath == null)
			throw new IllegalArgumentException("File '" + strFilePath
							+ "' not found (1040).");
		if (strFilePath.equals(""))
			throw new IllegalArgumentException("File '" + strFilePath
							+ "' not found (1040).");
		if (isVirtual(strFilePath, request))
			strFilePath = request.getRealPath("\\") + strFilePath;
              //System.out.println("----filepath----------***-----" + strFilePath);
	      //System.out.println("----strFileName-------****----" + strFileName);
		File file = new File(strFilePath);
		FileInputStream fileinputstream = new FileInputStream(file);
		long lngSize = file.length();
		boolean flag = false;
		int idx = 0;
		int btLen = 8192;
		byte abyte0[] = new byte[btLen];
		String strContentType = new String();
		// 设置下载类型
		if (strContentType == null)
			response.setContentType("application/x-msdownload");
		else if (strContentType.length() == 0)
			response.setContentType("application/x-msdownload");
		else
			response.setContentType(strContentType);

		response.setContentLength((int) lngSize);

		String m_contentDisposition = "attachment;";
		String strFile2 = new String();
		String strDisp = new String();
		//System.out.println("--------filename--" + strFileName);

		if (strFileName == null)
			strFileName = "";

		if (strFileName.equals("")) {
			strFileName = this.getFileName(strFilePath);
		} else {
			strFileName = this.getFileName(strFileName);
		}
		//System.out.println("------0--filename--" + strFileName);
		strFile2 = new String(strFileName.getBytes(), "ISO-8859-1");
		//System.out.println("------*--filename--" + strFile2);
		response.setHeader("Content-Disposition", m_contentDisposition
						+ " filename=" + strFile2);

		int j;
		response.setBufferSize((int) file.length());
		OutputStream out = response.getOutputStream();

		while ((j = fileinputstream.read(abyte0, 0, btLen)) != -1) {
			out.write(abyte0, 0, j);
		}
		fileinputstream.close();
		out.flush();
		fileinputstream.close();
		out.close();
	}

	/**
	 * 得到文件名
	 * @param strFileName 完整的路径文件名
	 * @return 当前文件的名字
	 */
	private String getFileName(String strFileName) {
		int iPos = 0;
		iPos = strFileName.lastIndexOf('/');
		if (iPos != -1)
			return strFileName.substring(iPos + 1, strFileName.length());
		iPos = strFileName.lastIndexOf('\\');
		if (iPos != -1)
			return strFileName.substring(iPos + 1, strFileName.length());
		else
			return strFileName;
	}

	/**
	 * 判断文件是否存在服务器端
	 * @param strFileName 相对路径的文件名
	 * @return 真与假
	 */
	private boolean isVirtual(String strFileName, HttpServletRequest request) {
		if (request.getRealPath(strFileName) != null) {
			File file = new File(request.getRealPath(strFileName));
			return file.exists();
		} else {
			return false;
		}
	}

}
