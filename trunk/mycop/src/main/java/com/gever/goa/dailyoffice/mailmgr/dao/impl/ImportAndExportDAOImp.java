package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.ImportAndExportDAO;
import com.gever.goa.dailyoffice.tools.vo.CardcaseVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.form.BaseForm;
import com.gever.util.IdMng;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description: ��Ƭ����Ϣ���뵼����ʵ����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class ImportAndExportDAOImp extends BaseDAO
    implements ImportAndExportDAO {
    BufferedReader file; //����һ����д�ļ��Ķ���

    public ImportAndExportDAOImp(String dbData) {
        super(dbData);
    }

    /**
     * ��Ƭ����Ϣ����
     * @param userID
     * @param filePath
     */
    public List importMailBox(VOInterface vo) throws
        DefaultException {
        VOInterface curVO = null;
        List listCardcase = new ArrayList();
        CardcaseVO cardCaseVO = new CardcaseVO();
        String filePath = vo.getValue("file_path");
        //System.out.println("---------�ļ���·��:-----" + filePath);
        try {
            file = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            //System.out.println("�����ҵ��ļ�");
            ex.printStackTrace();
        }
        String strCardcase = "";
        String strTempCardcase = "";
        try {
            strCardcase = file.readLine(); //��һ��Ϊ�ֶ���
            strTempCardcase = file.readLine(); //�ڶ��в�Ϊ����
        } catch (IOException ex1) {
            //System.out.println("���ļ���������");
            ex1.printStackTrace();
        }
        while (strTempCardcase != null) {
            strCardcase = strTempCardcase + ",";
           // System.out.println("----�ļ�ȡ��������Ϊ:->" + strCardcase); //��ӡ���
            List cardcaseList = this.toList(strCardcase); //����Ƭ����Ϣ����list
            if (cardcaseList.size() == 16) {
                cardCaseVO = new CardcaseVO();
                int i = 0;
                cardCaseVO.setCustomer( (String) cardcaseList.get(0));
                cardCaseVO.setE_mail( (String) cardcaseList.get(1));
                cardCaseVO.setAddress( (String) cardcaseList.get(8));
                cardCaseVO.setPost_code( (String) cardcaseList.get(10));
                cardCaseVO.setPhone( (String) cardcaseList.get(13));
                cardCaseVO.setCompany( (String) cardcaseList.get(14));
                cardCaseVO.setDuty( (String) cardcaseList.get(15));
                listCardcase.add(cardCaseVO);
            }
            else {
               // System.out.println("�����ļ��ֶεĸ�����ʵ�ʸ�����ƥ��");
            }
            try {
                strTempCardcase = file.readLine();
            } catch (IOException ex2) {
              //  System.out.println("���ļ���������");
                ex2.printStackTrace();
            }

        }
        try {
            file.close();
        } catch (IOException ex3) {
           // System.out.println("���ܹر��ļ�");
            ex3.printStackTrace();
        }
        return listCardcase;
    }

    /**
     * ����Ƭ����Ϣת��Ϊ����
     * @param strCardcase
     * @return
     */
    private List toList(String strCardcase) {
        List list = new ArrayList();
        //String[] arrTempCardcase = new String[7];
        int pos = strCardcase.indexOf(",");
        int start = 0;
        int i = 0;
        String temp;
        try {
            while (pos != -1) {
                temp = strCardcase.substring(start, pos);
                //System.out.println("------�ļ�����ת��ΪLIST֮��Ϊ:----" + temp);
                list.add(i++, temp);
                /*if (!temp.trim().equals("")) {
                    arrTempCardcase[i++] = temp;
                                 }*/
                strCardcase = strCardcase.substring(pos + 1, strCardcase.length());
                pos = strCardcase.indexOf(",");
            }
        } catch (Exception e) {
            //System.out.println("����Խ��");
        }
        return list;
    }

    /**
     * ��Ƭ����Ϣ����
     * @param form ���� vo,useid
     * @param exportList ��Ƭ���б�
     * @throws FileNotFoundException
     */
    public void exportMailBox(BaseForm form, List exportList) throws
        FileNotFoundException {
        VOInterface vo = form.getVo();
        String filePath = vo.getValue("file_path");
        //System.out.println("-----�ļ�������·��Ϊ:-----" + vo.getValue("file_path"));
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        String id = IdMng.getModuleID(form.getUserId());
        filePath = filePath + id + ".csv";
        PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
        pw.println("����,�����ʼ���ַ,��ͥ���ڽֵ�,��ͥ���ڳ���,��ͥ���ڵص���������,��ͥ����ʡ/������,��ͥ���ڹ���/����,��ͥ�绰,��˾���ڽֵ�,��˾���ڳ���,��˾���ڵص���������,��˾����ʡ/������,��˾���ڹ���/����,����绰,��˾,ְ��");
      //  System.out.println("Export list size is:" + exportList.size());
        for (int i = 0; i < exportList.size(); i++) {
            String outString = "";
            outString = outString +
                ( (CardcaseVO) exportList.get(i)).getCustomer().trim() + ","
                + ( (CardcaseVO) exportList.get(i)).getE_mail() + "," + "," +
                "," + "," + "," + "," + ","
                + ( (CardcaseVO) exportList.get(i)).getAddress() + "," + ","
                + ( (CardcaseVO) exportList.get(i)).getPost_code() + "," + "," +
                ","
                + ( (CardcaseVO) exportList.get(i)).getPhone() + ","
                + ( (CardcaseVO) exportList.get(i)).getCompany() + ","
                + ( (CardcaseVO) exportList.get(i)).getDuty();
            //System.out.println("д���ļ�������Ϊ:"+outString);
            pw.println(outString);
        }
        pw.close();

    }

    /**
     * �õ�ָ��Ŀ¼�µ������ļ�
     * @param vo
     * @return List
     */
    public List getFiles(VOInterface vo) {
        String filePath = vo.getValue("file_path");
       // System.out.println("The file path is :" + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] fileList = file.listFiles();
        List list = new ArrayList();
        if (fileList != null && fileList.length > 0) {
            for (int i = 0; i < fileList.length; i++) {
                HashMap hMap = new HashMap();
                String sFullName = fileList[i].getName();
                String sName = sFullName.substring(0, 4) + "��" +
                    sFullName.substring(4, 6) + "��"
                    + sFullName.substring(6, 8) + "��" +
                    sFullName.substring(8, 10) + "ʱ"
                    + sFullName.substring(10, 12) + "��" +
                    sFullName.substring(12, 14) + "��";
                hMap.put("sFullName",sFullName);
                hMap.put("sName",sName);
                list.add(hMap);
            }
        }
        return list;
    }

    /**
     * ɾ��ָ��Ŀ¼�µ��ļ�
     * @param vo
     */
    public void deleteFiles(VOInterface vo, String fileName[]) {
        String filePath = vo.getValue("file_path");
        for (int i = 0; i < fileName.length; i++) {
            File file = new File(filePath + fileName[i]);
            if (file.exists()) {
                file.delete();
            }else{
                System.out.println("��Ҫɾ�����ļ�������");
            }
        }

    }
}
