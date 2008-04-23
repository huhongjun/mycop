/*
 * �������� ���ڴ��г�ʼACL
 * �������� 2004-11-25 14:06:58
 */
package com.gever.sysman.privilege.util;

import java.io.File;
import java.io.FilenameFilter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionServlet;

import org.jdom.Document;
import org.jdom.Element;

import org.jdom.input.SAXBuilder;

import org.jdom.xpath.XPath;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.dao.PermissionMapDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.model.PermissionMap;
import com.gever.sysman.privilege.model.impl.DefaultPermissionMap;


/**
 * @author Hu.Walker
 */
public class PermissionMapACL {
    public final static String MAP_DB_ACL = "MAP_DB_ACL"; // д��context�е�����
    public final static String MAP_XML_ACL = "MAP_XML_ACL"; // д��context�е�����
    public final static String XML_FILE_PATH = "/WEB-INF/action_privilege/"; // XML�ļ��Ĵ洢·��
    public final static String XML_NODE = "/config/action"; // XML�еĽ��

    private PermissionMapACL() {
    }

    /**
     * ��õ�ǰ����Ȩ��ӳ���б�(��WEBӦ�÷�Χ��)�����ݿ���
     * @return
     */
    public synchronized static Map getDbACL(ServletContext context)
        throws DAOException {
        Object mapACL = context.getAttribute(MAP_DB_ACL);

        if (mapACL == null) {
            mapACL = initDbACL(context);
        }

        return (Map) mapACL;
    }

    /**
     * ��õ�ǰ����Ȩ��ӳ���б�(��WEBӦ�÷�Χ��)��XML�ļ���
     * @param context
     * @return
     * @throws DAOException
     */
    public synchronized static Map getXmlACL(ServletContext context)
        throws DAOException, DefaultException {
        try {
            Object mapACL = context.getAttribute(MAP_XML_ACL);

            if (mapACL == null) {
                mapACL = initXmlACL(context);
            }

            return (Map) mapACL;
        } catch (DefaultException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DefaultException("PRI_MAP_014", DefaultException.ERROR, ex);
        }
    }

    /**
     * ����actionPath��action�����Դ����Դ��������
     * @param context
     * @param actionPath   struts-config.xml �ļ��е�actionӳ��·��
     * @param action       ��ӳ��·������Ӧ����ľ��巽����
     * @return             PermissionMap����
     */
    public static PermissionMap getResOpratByActionPath(
        ServletContext context, String actionPath, String method)
        throws DAOException, DefaultException {
        PermissionMap permisMap = null;
        Map methodDbMap = null;
        Map methodXmlMap = null;
        Map mapDbACL = getDbACL(context);
        Map mapXmlACL = getXmlACL(context);
        Object objDb = null;
        Object objXml = null;

        // ȡ����Դӳ����룬�����ݿ�����
        if (mapDbACL == null) {
            if (mapXmlACL == null) {
                return null;
            } else {
                objXml = mapXmlACL.get(actionPath);
            }
        } else {
            objDb = mapDbACL.get(actionPath);

            if (mapXmlACL != null) {
                objXml = mapXmlACL.get(actionPath);
            }
        }

        if (objDb == null) {
            if (objXml == null) {
                return null;
            } else {
                methodXmlMap = (Map) objXml;
            }
        } else {
            methodDbMap = (Map) objDb;

            if (objXml != null) {
                methodXmlMap = (Map) objXml;
            }
        }

        if (objDb == null) {
            if (objXml == null) {
                return null;
            } else {
                objXml = methodXmlMap.get(method);
            }
        } else {
            objDb = methodDbMap.get(method);

            if (objXml != null) {
                objXml = methodXmlMap.get(method);
            }
        }

        if (objDb == null) {
            if (objXml == null) {
                return null;
            } else {
                permisMap = (PermissionMap) objXml;
            }
        } else {
            permisMap = (PermissionMap) objDb;
        }

        return permisMap;
    }

    /*
     * ��ʼ��ACL,�����ݿ���
     */
    protected static Map initDbACL(ServletContext context)
        throws DAOException {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        PermissionMapDAO perMapDAO = factory.getPerMissionMapDAO();
        Map permMap = perMapDAO.getAllMap();
        context.setAttribute(MAP_DB_ACL, permMap);

        return permMap;
    }

    /*
     * ��ʼ��ACL,��XML�ļ���
     * @param context
     * @throws DAOException
     */
    protected static Map initXmlACL(ServletContext context)
        throws DefaultException {
        try {
            List configList = null;
            File[] files = null;
            PermissionMap perMap = null;
            Map actionMap = new HashMap();
            Map methodMap = new HashMap();
            SAXBuilder builder = new SAXBuilder();
            XPath configNode = XPath.newInstance(XML_NODE);

            configList = getConfigFile(context);

            if (configList != null) {
                for (Iterator iter = configList.iterator(); iter.hasNext();) {
                    String dir = (String) iter.next();

                    if ((dir != null) && !"".equals(dir)) {
                        // ȡ��ָ��Ŀ¼�е������ļ�����
                        files = getFilesInDir(dir, context);

                        if ((files != null) && (files.length > 0)) {
                            for (int i = 0; i < files.length; i++) {
                                // ��ȡ�ļ�����
                                methodMap = getXmlFileContent(builder,
                                        files[i], configNode);

                                if ((methodMap != null) &&
                                        (methodMap.size() > 0)) {
                                    perMap = (PermissionMap) methodMap.get(methodMap.keySet()
                                                                                    .iterator()
                                                                                    .next());
                                    actionMap.put(perMap.getActionPath(),
                                        methodMap);
                                }
                            }
                        }
                    }
                }
            } else {
                return null;
            }

            context.setAttribute(MAP_XML_ACL, actionMap);

            return actionMap;
        } catch (DefaultException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DefaultException("PRI_MAP_013", DefaultException.ERROR, ex);
        }
    }

    /*
     * ȡ��xml�ļ������ݣ�����MAP
     * @param builder
     * @param file
     * @param configPath
     */
    protected static Map getXmlFileContent(SAXBuilder builder, File file,
        XPath configNode) throws DefaultException {
        PermissionMap permMap = null;
        Map map = new HashMap();
        String path = null;
        String method = null;
        String resCode = null;
        String optCode = null;

        try {
            Document doc = builder.build(file);
            List configPaths = getCols(configNode, doc);

            for (Iterator iter = configPaths.iterator(); iter.hasNext();) {
                Element action = (Element) iter.next();
                permMap = new DefaultPermissionMap();

                path = file.getPath();

                // ��ϳ��µ�ACTIONPATH
                int begin = path.indexOf("config\\");
                int end = 0;

                if (begin > 0) {
                    path = path.replaceAll("config\\\\", "config..");
                    end = path.lastIndexOf(".xml");
                } else {
                    begin = path.indexOf("config");
                    end = path.lastIndexOf(".xml");
                }

                path = path.substring(begin, end);
                path = path.replaceAll("\\\\", ".");
                path = path.replaceAll("\\.", "/");

                method = action.getAttributeValue("method");
                resCode = action.getAttributeValue("rescode");
                optCode = action.getAttributeValue("optcode");

                if ((path != null) && !"".equals(path) && (method != null) &&
                        !"".equals(method) && (resCode != null) &&
                        !"".equals(resCode) && (optCode != null) &&
                        !"".equals(optCode)) {
                    permMap.setActionPath(path);
                    permMap.setMethodName(method);
                    permMap.setResCode(resCode);
                    permMap.setResOpCode(optCode);

                    map.put(method, permMap);
                }
            }

            return map;
        } catch (Exception ex) {
            throw new DefaultException("PRI_MAP_014", DefaultException.ERROR, ex);
        }
    }

    /*
     * ��ȡ���е������ļ�,������XML��Ŀ¼·��
     * @return
     * @throws DefaultException
     */
    protected static List getConfigFile(ServletContext context)
        throws DefaultException {
        List list = new ArrayList();
        ActionServlet as = (ActionServlet) context.getAttribute(org.apache.struts.Globals.ACTION_SERVLET_KEY);
        Enumeration names = as.getServletConfig().getInitParameterNames();

        // ��ȡ���е������ļ�
        list.add(XML_FILE_PATH + "config");

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();

            if (!name.startsWith("config/")) {
                continue;
            }

            // ������·��
            name = name.replaceAll("/", ".");
            name = XML_FILE_PATH + name;
            list.add(name);
        }

        return list;
    }

    /*
     * ��ȡĳĿ¼�ڵ������ļ�
     * @param dir  Ŀ¼
     * @return
     * @throws DefaultException
     */
    protected static File[] getFilesInDir(String dir, ServletContext context) {
        // ����Ĺ�������ֻѡȡXML�ļ�
        class xmlFileNameFilter implements FilenameFilter {
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf(".xml") > -1) {
                    return true;
                }

                return false;
            }
        }

        if ((dir == null) || "".equals(dir)) {
            return null;
        }

        File dirs = new File(context.getRealPath(dir));

        if (!dirs.isDirectory()) {
            return null;
        }

        File[] files = dirs.listFiles(new xmlFileNameFilter());

        return files;
    }

    /*
     * �����ĵ�
     * @param builder
     * @param filePath
     * @return
     * @throws Exception
     */
    protected static Document buildDoc(SAXBuilder builder, String filePath)
        throws DefaultException {
        Document doc = null;

        try {
            if ((filePath == null) || "".equals(filePath)) {
                return null;
            }

            File file = new File(filePath);

            if (file.isFile()) {
                doc = builder.build(file);

                return doc;
            }
        } catch (Exception ex) {
            throw new DefaultException("PRI_MAP_011", DefaultException.ERROR, ex);
        }

        return null;
    }

    /*
     * ��ȡ�ӽ��
     * @param xpath
     * @param doc
     * @return
     * @throws DefaultException
     */
    protected static List getCols(XPath xpath, Document doc)
        throws DefaultException {
        try {
            List list = xpath.selectNodes(doc);

            return list;
        } catch (Exception ex) {
            throw new DefaultException("PRI_MAP_012", DefaultException.ERROR, ex);
        }
    }
}
