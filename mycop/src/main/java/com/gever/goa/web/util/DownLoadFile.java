package com.gever.goa.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * <p>Title: �����ļ����� </p>
 * <p>Description:�����ļ����� </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: GEVER</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DownLoadFile {

    protected HttpServletRequest m_request;
    protected HttpServletResponse m_response;
    protected ServletContext m_application;
    private String m_contentDisposition;

    public DownLoadFile() {
    }

    public final void initialize(PageContext pagecontext)
            throws ServletException {
        m_application = pagecontext.getServletContext();
        m_request = (HttpServletRequest)pagecontext.getRequest();
        m_response = (HttpServletResponse)pagecontext.getResponse();
    }

    public void downloadFile(String strFilePath,String strName)
            throws ServletException, IOException{
        downloadFile(strFilePath, strName, null, null);
    }

    public void downloadFile(String strFilePath, String strName,String strContentType)
            throws ServletException, IOException {
        downloadFile(strFilePath,strName, strContentType, null);
    }

    public void downloadFile(String strFilePath, String strFileName,
                             String strContentType, String strDisp)
            throws ServletException, IOException {
        downloadFile(strFilePath,strFileName, strContentType, strDisp, 8192);
    }

    /**
     * �ж��ļ��Ƿ���ڷ�������
     * @param strFileName ���·�����ļ���
     * @return �����
     */
    private boolean isVirtual(String strFileName){
        if(m_application.getRealPath(strFileName) != null) {
            File file = new File(m_application.getRealPath(strFileName));
            return file.exists();
        } else {
            return false;
        }
    }

    /**
     * �����ļ����ͻ���
     * @param strFileName �ļ���
     * @param strContentType ��������
     * @param s2 ��������
     * @param btLen byte����󳤶�
     * @throws ServletException
     * @throws IOException
     */
    public void downloadFile(String strFilePath, String strFileName,
                             String strContentType, String strDisp, int btLen)
            throws ServletException, IOException {

        if(strFilePath == null)
            throw new IllegalArgumentException("File '" + strFilePath + "' not found (1040).");
        if(strFilePath.equals(""))
            throw new IllegalArgumentException("File '" + strFilePath + "' not found (1040).");
        if(isVirtual(strFilePath)){
			//strFilePath = m_request.getRealPath("\\") +strFilePath;
			strFilePath = m_application.getRealPath(strFilePath);
			//System.out.println(path);
        }

//        System.out.println("----filepath----------***-----" + strFilePath);
//        System.out.println("----strFileName-------****----" + strFileName);
        File file = new File(strFilePath);
        FileInputStream fileinputstream = new FileInputStream(file);
        long lngSize = file.length();
        boolean flag = false;
        int idx = 0;
        byte abyte0[] = new byte[btLen];

        // ������������
        if(strContentType == null)
            m_response.setContentType("application/x-msdownload");
        else
            if(strContentType.length() == 0)
                m_response.setContentType("application/x-msdownload");
        else
            m_response.setContentType(strContentType);

        m_response.setContentLength((int)lngSize);

        m_contentDisposition = m_contentDisposition != null ? m_contentDisposition : "attachment;";
        String strFile2 = new String();
        if (strDisp == null){
            if (strFileName == null )
                strFileName = "";

            if (strFileName.equals("")){
                strFileName = this.getFileName(strFilePath);
            } else {
                strFileName =  this.getFileName(strFileName);
            }
           strFile2=new String(strFileName.getBytes("gb2312"),"ISO-8859-1");
            //strFile2 = new String(strFileName.getBytes(), "ISO-8859-1");
            m_response.setHeader("Content-Disposition", m_contentDisposition + " filename=" +strFile2);
        } else if (strDisp.length() == 0) {
            m_response.setHeader("Content-Disposition", m_contentDisposition);
        } else {
            m_response.setHeader("Content-Disposition", m_contentDisposition + " filename=" + strDisp);
        }
        int j;
        m_response.setBufferSize((int)file.length());
        OutputStream out = m_response.getOutputStream();

        while ( (j = fileinputstream.read(abyte0, 0, btLen)) != -1) {
            out.write(abyte0, 0, j);
        }
        fileinputstream.close();
        out.flush();
        fileinputstream.close();
        out.close();

    }

    /**
     * �õ��ļ���
     * @param strFileName ������·���ļ���
     * @return ��ǰ�ļ�������
     */
    private String getFileName(String strFileName){
        int iPos = 0;
        iPos = strFileName.lastIndexOf('/');
        if(iPos != -1)
            return strFileName.substring(iPos + 1, strFileName.length());
        iPos = strFileName.lastIndexOf('\\');
        if(iPos != -1)
            return strFileName.substring(iPos + 1, strFileName.length());
        else
            return strFileName;
    }


}
