package com.gever.goa.util.timer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gever.exception.DefaultException;

/**
 * <p>Title: 读取定时器的配置文件</p>
 * <p>Description: 读取定时器的配置文件</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TimerConfig {
    private TimerConfig() {
    }

    private List configList = new ArrayList();
    private static final String CLASS_NAME = "className";
    private static final String CHECK_METHOD = "checkMethod";
    private static final String DO_METHOD = "doMethod";
    private static final String CHECK_SECONDS = "checkSeconds";
    private static final String IS_DAO = "isDao";
    private static TimerConfig timerCfg = null;

    /**
     * 得到错误配置文件类的实例
     * @param fileName 文件名
     * @return 实例
     * @throws DefaultException
     */
    public static TimerConfig getInstance(String fileName) throws
        DefaultException {
        if (timerCfg == null) {
            synchronized (TimerConfig.class) {
                TimerConfig inst = timerCfg;
                if (inst == null) {
                    synchronized (TimerConfig.class) {
                        timerCfg = new TimerConfig();
                        timerCfg.readXMLFile(fileName);

                    }
                }
            }
        }
        return timerCfg;
    }

    public List getTimerList() {
        return configList;
    }


    /**
     * 读取xml文件
     * @param inFile 文件名称
     * @throws DefaultException
     */
    private void readXMLFile(String inFile) throws DefaultException {
        //为解析XML作准备，创建DocumentBuilderFactory实例,指定DocumentBuilder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;

        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace(System.out);
            throw new DefaultException("xml解析包有问题!");
        }

        Document doc = null;
        try {
            doc = db.parse(inFile);
        } catch (DOMException dom) {
            System.err.println(dom.getMessage());
            throw new DefaultException("xml格式不对,解析失败!");
        } catch (IOException ioe) {
            System.err.println(ioe);
            throw new DefaultException("文件未找到!");
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace(System.out);
            throw new DefaultException("xml格式不对,解析失败!");
        }

        //得到根元素
        Element root = doc.getDocumentElement();
        NodeList configs = root.getElementsByTagName("timer");
        //System.out.println("------- configs length ------" + configs.getLength());
        String strType = "";

        for (int i = 0; i < configs.getLength(); i++) {
            Element elTimer = (Element) configs.item(i);
            TimerVO cfgVO = new TimerVO();
            NodeList curTimer = elTimer.getChildNodes();
            cfgVO = new TimerVO();

            for (int idx = 0; idx < curTimer.getLength(); idx++) {
                Node node = curTimer.item(idx);
                if (node.getNodeType() != root.ELEMENT_NODE)
                    continue;
                Node subNode = node.getFirstChild();
                //System.out.println("-------getNodeValue---"+subNode.getNodeValue());
                if (this.CLASS_NAME.equals(node.getNodeName())) {
                    cfgVO.setClassName(subNode.getNodeValue());
                } else if (this.CHECK_METHOD.equals(node.getNodeName())) {
                    cfgVO.setCheckMethod(subNode.getNodeValue());
                } else if (this.DO_METHOD.equals(node.getNodeName())) {
                    cfgVO.setDoMethod(subNode.getNodeValue());
                } else if (this.CHECK_SECONDS.equals(node.getNodeName())) {
                    cfgVO.setSeconds(subNode.getNodeValue());
                } else if (this.IS_DAO.equals(node.getNodeName())) {
                    cfgVO.setIsDao(subNode.getNodeValue());
                }
            }

            configList.add(cfgVO);
        }
    }



    /**
     * 执行方法
     * @param obj 执行类
     * @param methodnName 方法
     * @return 成功与否
     * @throws IOException
     * @throws java.lang.Exception
     */
    public boolean performMethod(Object obj, String methodnName) throws
        DefaultException {

        Method method = null;
        try {
            Class types[] = {null};
            method = obj.getClass().getMethod(methodnName, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new DefaultException("perform");
        }

        boolean bRet = false;
        try {

            Object oRet = method.invoke(obj, null);
            if (String.valueOf(oRet).equalsIgnoreCase("true"))
                bRet = true;

        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new DefaultException("没有找到该类[" + obj.getClass().getName() + "]");
        } catch (IllegalAccessException e) {

            e.printStackTrace();
            throw new DefaultException("没有找到该类[" + obj.getClass().getName() + "]");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DefaultException("没有找到该类[" + obj.getClass().getName() + "]");
        }
        return bRet;
    }

    /**
     * 执行方法
     * @param obj 执行类
     * @param methodnName 方法
     * @return 成功与否
     * @throws IOException
     * @throws java.lang.Exception
     */
    public boolean execSetDBMethod(Object obj,String dbData) throws
        DefaultException {

        Method method = null;
        final String SET_DBDATA = "setDbData";
        try {
            Class types[] = {String.class};
            method = obj.getClass().getMethod("setDbData", types);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new DefaultException("perform");
        }

        boolean bRet = false;
        try {
            String values[] = {dbData};
            Object oRet = method.invoke(obj, values);
            if (String.valueOf(oRet).equalsIgnoreCase("true"))
                bRet = true;

        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new DefaultException("没有找到该类[" + obj.getClass().getName() +
                                       "]");
        } catch (IllegalAccessException e) {

            e.printStackTrace();
            throw new DefaultException("没有找到该类[" + obj.getClass().getName() +
                                       "]");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DefaultException("没有找到该类[" + obj.getClass().getName() +
                                       "]");
        }
        return bRet;
    }

}
