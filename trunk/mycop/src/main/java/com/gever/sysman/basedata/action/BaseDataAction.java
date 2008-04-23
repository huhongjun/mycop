package com.gever.sysman.basedata.action;

/**
 * <p>Title: 导出ddl</p>
 * <p>Description:通过解析database-config.xml文件得到要导出的数据库名及表名
 *    和要导出文件位置，解析basedata-config.xml文件得到要导出数据的不同数据库的命令
 *    执行命令导出ddl文件</p>
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
   * 导出ddl方法
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

    //获得导出ddl的命令
    command = dao.getCommand(basedataxmlfile, databasexmlfile, exportFileName, exportAllDB,
                             exportStructure);
    String[] arr = command.split(";");
    if(arr.length > 1){
     for(int i = 0;i < arr.length;i++){
        command = arr[i].trim();
        int idx = command.indexOf("java");
        //执行命令 （多条命令）
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
      //执行命令
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
    //下载ddl
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
    //文件合并下载
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
   * 转向导出ddl页面
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
    //得到表名字符串
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
    //文件路径不存在则新建目录
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
