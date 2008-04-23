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
 * <p>Description: 名片夹信息导入导出的实现类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class ImportAndExportDAOImp extends BaseDAO
    implements ImportAndExportDAO {
    BufferedReader file; //定义一个读写文件的对象

    public ImportAndExportDAOImp(String dbData) {
        super(dbData);
    }

    /**
     * 名片夹信息导入
     * @param userID
     * @param filePath
     */
    public List importMailBox(VOInterface vo) throws
        DefaultException {
        VOInterface curVO = null;
        List listCardcase = new ArrayList();
        CardcaseVO cardCaseVO = new CardcaseVO();
        String filePath = vo.getValue("file_path");
        //System.out.println("---------文件的路径:-----" + filePath);
        try {
            file = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            //System.out.println("不能找到文件");
            ex.printStackTrace();
        }
        String strCardcase = "";
        String strTempCardcase = "";
        try {
            strCardcase = file.readLine(); //第一行为字段名
            strTempCardcase = file.readLine(); //第二行才为内容
        } catch (IOException ex1) {
            //System.out.println("读文件发生错误");
            ex1.printStackTrace();
        }
        while (strTempCardcase != null) {
            strCardcase = strTempCardcase + ",";
           // System.out.println("----文件取出来内容为:->" + strCardcase); //打印输出
            List cardcaseList = this.toList(strCardcase); //把名片夹信息存入list
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
               // System.out.println("导入文件字段的个数与实际个数不匹配");
            }
            try {
                strTempCardcase = file.readLine();
            } catch (IOException ex2) {
              //  System.out.println("读文件发生错误");
                ex2.printStackTrace();
            }

        }
        try {
            file.close();
        } catch (IOException ex3) {
           // System.out.println("不能关闭文件");
            ex3.printStackTrace();
        }
        return listCardcase;
    }

    /**
     * 将名片夹信息转换为数组
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
                //System.out.println("------文件内容转换为LIST之后为:----" + temp);
                list.add(i++, temp);
                /*if (!temp.trim().equals("")) {
                    arrTempCardcase[i++] = temp;
                                 }*/
                strCardcase = strCardcase.substring(pos + 1, strCardcase.length());
                pos = strCardcase.indexOf(",");
            }
        } catch (Exception e) {
            //System.out.println("数组越界");
        }
        return list;
    }

    /**
     * 名片夹信息导出
     * @param form 包含 vo,useid
     * @param exportList 名片夹列表
     * @throws FileNotFoundException
     */
    public void exportMailBox(BaseForm form, List exportList) throws
        FileNotFoundException {
        VOInterface vo = form.getVo();
        String filePath = vo.getValue("file_path");
        //System.out.println("-----文件导出的路径为:-----" + vo.getValue("file_path"));
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        String id = IdMng.getModuleID(form.getUserId());
        filePath = filePath + id + ".csv";
        PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
        pw.println("姓名,电子邮件地址,家庭所在街道,家庭所在城市,家庭所在地的邮政编码,家庭所在省/自治区,家庭所在国家/地区,家庭电话,公司所在街道,公司所在城市,公司所在地的邮政编码,公司所在省/自治区,公司所在国家/地区,商务电话,公司,职务");
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
            //System.out.println("写入文件的内容为:"+outString);
            pw.println(outString);
        }
        pw.close();

    }

    /**
     * 得到指定目录下导出的文件
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
                String sName = sFullName.substring(0, 4) + "年" +
                    sFullName.substring(4, 6) + "月"
                    + sFullName.substring(6, 8) + "日" +
                    sFullName.substring(8, 10) + "时"
                    + sFullName.substring(10, 12) + "分" +
                    sFullName.substring(12, 14) + "秒";
                hMap.put("sFullName",sFullName);
                hMap.put("sName",sName);
                list.add(hMap);
            }
        }
        return list;
    }

    /**
     * 删除指定目录下的文件
     * @param vo
     */
    public void deleteFiles(VOInterface vo, String fileName[]) {
        String filePath = vo.getValue("file_path");
        for (int i = 0; i < fileName.length; i++) {
            File file = new File(filePath + fileName[i]);
            if (file.exists()) {
                file.delete();
            }else{
                System.out.println("所要删除的文件不存在");
            }
        }

    }
}
