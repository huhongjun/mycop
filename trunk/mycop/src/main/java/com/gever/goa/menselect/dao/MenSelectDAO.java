package com.gever.goa.menselect.dao;

import java.util.ArrayList;

import com.gever.exception.DefaultException;

public interface MenSelectDAO {
     public ArrayList getUsersInfo();
     public ArrayList getConstCode(String sTable, String sCode,
                                     String sContent, String sCondition);
     public String getDeptInfoArray();
     public ArrayList getDeptArray() throws DefaultException;
     public ArrayList getAddressList(String userId);
     public String getTheamArray() throws DefaultException;
     public ArrayList getCardUsersInfo() throws DefaultException;
     public ArrayList getUsersInfo(boolean isRa, String org);
}
