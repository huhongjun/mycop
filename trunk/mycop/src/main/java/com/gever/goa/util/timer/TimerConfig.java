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
 * <p>Title: ��ȡ��ʱ���������ļ�</p>
 * <p>Description: ��ȡ��ʱ���������ļ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
     * �õ����������ļ����ʵ��
     * @param fileName �ļ���
     * @return ʵ��
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
     * ��ȡxml�ļ�
     * @param inFile �ļ�����
     * @throws DefaultException
     */
    private void readXMLFile(String inFile) throws DefaultException {
        //Ϊ����XML��׼��������DocumentBuilderFactoryʵ��,ָ��DocumentBuilder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;

        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace(System.out);
            throw new DefaultException("xml������������!");
        }

        Document doc = null;
        try {
            doc = db.parse(inFile);
        } catch (DOMException dom) {
            System.err.println(dom.getMessage());
            throw new DefaultException("xml��ʽ����,����ʧ��!");
        } catch (IOException ioe) {
            System.err.println(ioe);
            throw new DefaultException("�ļ�δ�ҵ�!");
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace(System.out);
            throw new DefaultException("xml��ʽ����,����ʧ��!");
        }

        //�õ���Ԫ��
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
     * ִ�з���
     * @param obj ִ����
     * @param methodnName ����
     * @return �ɹ����
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
            throw new DefaultException("û���ҵ�����[" + obj.getClass().getName() + "]");
        } catch (IllegalAccessException e) {

            e.printStackTrace();
            throw new DefaultException("û���ҵ�����[" + obj.getClass().getName() + "]");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DefaultException("û���ҵ�����[" + obj.getClass().getName() + "]");
        }
        return bRet;
    }

    /**
     * ִ�з���
     * @param obj ִ����
     * @param methodnName ����
     * @return �ɹ����
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
            throw new DefaultException("û���ҵ�����[" + obj.getClass().getName() +
                                       "]");
        } catch (IllegalAccessException e) {

            e.printStackTrace();
            throw new DefaultException("û���ҵ�����[" + obj.getClass().getName() +
                                       "]");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DefaultException("û���ҵ�����[" + obj.getClass().getName() +
                                       "]");
        }
        return bRet;
    }

}
