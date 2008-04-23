package com.gever.sysman.basedata.action;

/**
 * <p>Title: ����ddl</p>
 * <p>Description:ͨ������database-config.xml�ļ��õ�Ҫ���������ݿ���������
 *    ��Ҫ�����ļ�λ�ã�����basedata-config.xml�ļ��õ�Ҫ�������ݵĲ�ͬ���ݿ������
 *    ִ�������ddl�ļ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author Hu.Walker
 * @version 1.0
 */
import java.io.*;
import java.util.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import com.gever.sysman.basedata.dao.BaseDataDAO;
import com.gever.sysman.basedata.dao.BaseDataDAOFactory;
import com.gever.sysman.util.*;

public class BaseDataAction
    extends BaseAction {
  /**
   * ����ddl����
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward doExport(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response
                                ) throws Exception {
    //String xmlfilename = request.getParameter("xmlfilename");
    String basedataxmlfile = request.getParameter("basedata");
    String databasexmlfile = request.getParameter("database");
    String filename = request.getParameter("filename");
    String exportFilePath = request.getParameter("exportFilePath");
    String exportFileName = exportFilePath+filename;
    boolean exportAllDB = Boolean.valueOf(request.getParameter("exportAllDB")).
        booleanValue();
    boolean exportStructure = Boolean.valueOf(request.getParameter(
        "exportStructure")).booleanValue();
    String command = null;
    BaseDataDAO dao = BaseDataDAOFactory.getBaseDataDAO();
    String dbname = dao.getDB(databasexmlfile);
    List tl = (ArrayList)dao.getTables(databasexmlfile);
    String[] tables = new String[tl.size()];
    tl.toArray(tables);

    //��õ���ddl������
    command = dao.getCommand(basedataxmlfile, databasexmlfile, exportFileName, exportAllDB,
                             exportStructure);
    String[] arr = command.split(";");
    if(arr.length > 1){
     for(int i = 0;i < arr.length;i++){
        command = arr[i].trim();
        int idx = command.indexOf("java");
        //ִ������ ���������
        if (idx == 0) {
          String classname = command.substring(4, command.length()).trim();
          if (exportAllDB) {
            dao.db2Export(null, exportFilePath+dbname+"_java.ddl", classname);
          }
          else {
            dao.db2Export(tables, exportFilePath+dbname+"_java.ddl", classname);
          }
       }
        else {
          dao.export(command);
        }
     }
    }else{
      //ִ������
      if (command.trim().indexOf("java") == 0) {
        if (exportAllDB) {
          dao.db2Export(null, exportFileName,null);
        }
        else {
          dao.db2Export(tables, exportFileName,null);
        }
      }
      else {
        dao.export(command);
      }
    }
    //����ddl
    response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    response.setContentType("application/octet-stream");
    String contentDisposition = null;
    contentDisposition = contentDisposition != null ? contentDisposition :
        "attachment;";
    response.setHeader("Content-Disposition",
                       contentDisposition + " filename=" + filename);
    OutputStream outstream = response.getOutputStream();
    File file = new File(exportFileName);
    File fileData = new File(exportFilePath+dbname+"_java.ddl");
    /*
    if(!file.exists()) {
      file.createNewFile();
    }
    */
    InputStream fis = new FileInputStream(file);
    InputStream fis2 = null;
    //byte[] byteBuffer = new byte[1024];
    //int size = fis.read(byteBuffer);
    //while (size > 0) {
    //  outstream.write(byteBuffer);
    //  size = fis.read(byteBuffer);
    //}
    int i = fis.read();
    while(i != -1) {
      outstream.write(i);
      i = fis.read();
    }
    fis.close();
    //�ļ��ϲ�����
    if(arr.length > 1 && fileData.exists()) {
      fis2 = new FileInputStream(fileData);
      int j;
      while((j = fis2.read()) != -1) {
        outstream.write(j);
      }
    }

    if(fis2 != null) {
      fis2.close();
    }
    outstream.close();

    return toExport(mapping,form,request,response);
  }

  /**
   * ת�򵼳�ddlҳ��
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward toExport(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    BaseDataDAO dao = BaseDataDAOFactory.getBaseDataDAO();
    String databasexmlfile = getServlet().getServletContext().getRealPath(
        "/WEB-INF/database-config.xml");
    String exportFilePath = getServlet().getServletContext().getRealPath("/ddlfile");
    String dbname = dao.getDB(databasexmlfile);
    Collection tables = dao.getTables(databasexmlfile);
    StringBuffer sb = new StringBuffer();
    String tablenames = null;
    Iterator it = tables.iterator();
    //�õ������ַ���
    while (it.hasNext()) {
      sb.append(it.next().toString()).append(",");
    }
    if (tables.size() > 0) {
      tablenames = sb.toString().substring(0, sb.toString().length() - 1);
    }
    sb.setLength(0);
    int sepIndex = exportFilePath.lastIndexOf("/");
    if (sepIndex != -1) {
      sb.append(exportFilePath).append("/");
    }
    else {
      sb.append(exportFilePath).append("\\");
    }
    String path = sb.toString();
    //�ļ�·�����������½�Ŀ¼
    File file = new File(path);
    if (!file.isDirectory()) {
      file.mkdirs();
    }
    request.setAttribute("exportFilePath", path);
    request.setAttribute("dbname", dbname);
    request.setAttribute("tablenames", tablenames);
    return mapping.findForward("toExport");
  }
  /*
    public ActionForward doImport(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
       HttpServletResponse response) throws Exception {
      String basedataxmlfile = request.getParameter("basedata");
      String databasexmlfile = request.getParameter("database");
      String command = null;
      BaseDataDAO dao = BaseDataDAOFactory.getBaseDataDAO();
      command = dao.getImportCommand(basedataxmlfile, databasexmlfile);
      dao.importDdl(command, databasexmlfile);
      return mapping.findForward("");
    }
   */
}
