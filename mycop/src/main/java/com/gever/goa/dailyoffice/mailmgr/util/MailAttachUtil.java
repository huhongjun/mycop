/*
 * 创建日期 2004-11-22
 */
package com.gever.goa.dailyoffice.mailmgr.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.gever.goa.dailyoffice.mailmgr.vo.MailAttchVO;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;


/**
 * 
 * @author Hu.Walker
 */
public class MailAttachUtil {
    private MailAttachUtil(){
        
    }
    
    public static List stringToAttachList(String strAttach){
        List attachList = new ArrayList();
        if(StringUtils.isNull(strAttach)){
        	return attachList;
        }
        String[] str = strAttach.split(";");
        for (int i = 0; i < str.length; i++) {
			attachList.add(stringToAttachVO(str[i]));
		}
        return attachList;
        
    }
    /**
     * @param attachList
     * @return
     */
    /**
     * @param attachList
     * @return
     */
    public static String attachListToString(List attachList){
        if(attachList==null){
        	return "";
        }
        StringBuffer sb=new StringBuffer();
        for (Iterator iter = attachList.iterator(); iter.hasNext();) {
			MailAttchVO vo = (MailAttchVO) iter.next();
			sb.append(voToString(vo)).append(";");
		}
        return sb.toString();
    }
    public static String voToString(VOInterface vo){
    	if(vo==null){
    		return "";
    	}
    	StringBuffer sb = new StringBuffer();
    	String fields = vo.getAllFields();
    	StringTokenizer token = new StringTokenizer(fields,",");
    	while(token.hasMoreTokens()){
    		String tmp = token.nextToken();
			sb.append(vo.getValue(tmp)).append(",");
       		System.out.println(tmp+"|"+vo.getValue(tmp));
       	}
       	String retStr = sb.toString();
       	if(retStr.endsWith(",")){
       		retStr = retStr.substring(0,retStr.length()-1);
       	}
       	
       	
       	return retStr;
    }
    private static VOInterface stringToAttachVO(String strvo){
    	return stringToVO(strvo, new MailAttchVO());    	
    }
    public static VOInterface stringToVO(String value,VOInterface vo){
		
		VOInterface retvo = null;		
		try{
			 retvo = (VOInterface)vo.getClass().newInstance();			
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
		if(StringUtils.isNull(value)){
			return retvo;
		}
		String[] fields =  retvo.getAllFields().split(",");
    	String[] values = value.split(",");
    	int len = Math.min(fields.length, values.length);
    	System.out.println(len);
    	for (int i = 0; i < len; i++) {
    		System.out.println(fields[i]+"|"+values[i]);
			retvo.setValue(fields[i], values[i]);
		}
    	return retvo;
    }
    public static void main(String[] args) {
    	testList();
//    	String values="";
//		System.out.println( voToString(stringToAttachVO(values)));
//		System.out.println(":"+ voToString(stringToAttachVO(voToString(stringToAttachVO(values)))));
    }
    private static void testList(){
    	List list = new ArrayList();
    	for (int i = 0; i < 10; i++) {
			MailAttchVO vo = new MailAttchVO();
			list.add(i, vo);
		}
		String tmp = attachListToString(list);
    	System.out.println(tmp);
    	
    	list  = stringToAttachList(tmp);
    	System.out.println(list);
		System.out.println(list.size());
    }
}
