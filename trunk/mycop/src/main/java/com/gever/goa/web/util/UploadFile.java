package com.gever.goa.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

import com.gever.exception.DefaultException;
import com.gever.struts.form.BaseForm;
import com.gever.util.IdMng;
import com.gever.util.log.Log;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: �ϴ������ļ�</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class UploadFile {
    private String userID;
    private String module = "";
    private String dir = "/uploadfiles/";
    private String elementName = "theFile";
    private long fileSize = 0l;
    private int count = 0;
    private Log log = Log.getInstance(UploadFile.class);
    public UploadFile() {
    }

    /**
     * ����Ŀ¼
     * @param strDirName Ŀ¼��
     */
    public void createDirtory(String strDirName) {
        File strDir = new File(strDirName);
        log.showLog("----�ϴ�---strDirName--"+strDirName);
        if (!strDir.isDirectory()) { //�����û�н���Ŀ¼��ǰ�û����ļ���
            strDir.mkdir();
        }
        strDir = null;
    }

    /**
     * �ϴ������ļ�
     * @param request HttpServletRequest
     * @param mForm ��ǰform����
     * @param vo ��ǰvo����
     * @param pathNameColumn ·������
     * @param fileNameColumn �ļ�����
     * @throws DefaultException
     */
	public void saveOneFile(HttpServletRequest request,
								BaseForm mForm,
								VOInterface vo,
								String pathNameColumn,
								String fileNameColumn) throws
			DefaultException {

			String userId = mForm.getUserId();
			String strRealPath = request.getRealPath("/");
			java.util.Hashtable hTable = mForm.getMultipartRequestHandler().
				getFileElements();

			FormFile myFormFile = null;
			String curFileName = "";
			String outPutFileName = "";
			String tmpDir = "";

			try {
				myFormFile = (FormFile) hTable.get(elementName); //ȡ����һ��Ԫ��
				curFileName = myFormFile.getFileName();
                                int dot=curFileName.lastIndexOf(".");
                                 String rename="";
                                 String exName="";
                                if(dot!=-1){
                                   exName = curFileName.substring(dot);
                                  if (exName.equals(".DOC") || exName.equals(".XLS"))
                                    exName = exName.toLowerCase();
                                   rename = String.valueOf(System.currentTimeMillis());
                                }
				this.fileSize = 0;
				if (curFileName.equals("") || curFileName == null) { //���Ϊ��ʱҲ����Ҫ�ϴ��ļ�
					return;
				}


				this.createDirtory(strRealPath+dir+this.module + "/");
				tmpDir = IdMng.getModuleID(userId,count);
				this.createDirtory(strRealPath+dir+this.module + "/"+ tmpDir +"/");

				//�õ����Ӧ��
                                String fileName="";
                                 if(dot!=-1){
                                   fileName = this.getDir() + this.module + "/" + tmpDir + "/" + rename +exName;

                                 }
                                 else{
                                      fileName = this.getDir() + this.module + "/" + tmpDir + "/" + rename + exName;
                                 }
//				  }int iExt = curFileName.lastIndexOf("."); //�ȵõ���չ��λ��
//				  if (iExt >= 0) //��ֹû����չ����
//					  fileName += curFileName.substring(iExt);
				vo.setValue(pathNameColumn, fileName);    //�ļ�����
				vo.setValue(fileNameColumn,curFileName);  //�ļ�����
				outPutFileName = strRealPath + fileName; //�õ�����ļ�����

				//�ļ��洢
				java.io.InputStream stream = myFormFile.getInputStream();
				java.io.FileOutputStream fos = new FileOutputStream(outPutFileName);
				byte[] buffer = new byte[8192];
				int bytesRead = 0;
				log.showLog("-----��ǰ������С--->"+ myFormFile.getFileSize());
				this.fileSize= myFormFile.getFileSize();
				if (this.fileSize > 250*1024000){
					fos.close();
					stream.close();
					myFormFile.destroy();
					throw new DefaultException("��������̫��,�������ϴ�!");
				}
				while ( (bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					fos.write(buffer, 0, bytesRead);
				}
				//close the stream
				fos.close();
				stream.close();
				myFormFile.destroy();
				myFormFile = null;
				++count;
			} catch (FileNotFoundException fnfe) {
				throw new DefaultException(fnfe.getMessage());
			} catch (IOException ioe) {
				throw new DefaultException(ioe.getMessage());
			}

		}

    public long getFileSize(){
        return this.fileSize;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getDir() {
        return dir;
    }
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
    public String getElementName() {
        return elementName;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
