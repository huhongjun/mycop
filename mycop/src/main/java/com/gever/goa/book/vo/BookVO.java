package com.gever.goa.book.vo;

import java.util.*;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * Comment block is generated by javaClassComment.vsl
 *
 * <p>Title: </p>
 * <p>Description: Book ??</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: OSMatrix ????</p>
 * @Author:		 HuHJ
 * @Version: 1.0
 *
 */ 
public class BookVO extends BaseVO implements VOInterface {
   public BookVO(){ }
    
   private java.lang.String bookid;		 	//bookid
   private java.lang.String bookname;		 	//bookname
   private java.lang.String publisher;		 	//publisher
   private java.lang.String deptid;		 	//deptid

   public java.lang.String getBookid() { return this.bookid;   
   }
   public void setBookid(java.lang.String bookid) {this.bookid = bookid;   
   }
   public java.lang.String getBookname() { return this.bookname;   
   }
   public void setBookname(java.lang.String bookname) {this.bookname = bookname;   
   }
   public java.lang.String getPublisher() { return this.publisher;   
   }
   public void setPublisher(java.lang.String publisher) {this.publisher = publisher;   
   }
   public java.lang.String getDeptid() { return this.deptid;   
   }
   public void setDeptid(java.lang.String deptid) {this.deptid = deptid;   
   }

   private static final int bookid_col = 1 - 1; // ID
   private static final int bookname_col = 2 - 1; // ID
   private static final int publisher_col = 3 - 1; // ID
   private static final int deptid_col = 4 - 1; // ID

    public String getValue(String name) 
    {
				if("bookid".equalsIgnoreCase(name) == true) {
             return  this.bookid;
         }
				if("bookname".equalsIgnoreCase(name) == true) {
             return  this.bookname;
         }
				if("publisher".equalsIgnoreCase(name) == true) {
             return  this.publisher;
         }
				if("deptid".equalsIgnoreCase(name) == true) {
             return  this.deptid;
         }
	        return "";
	    }
	    
    public void setValue(String name, String value) 
    {
    if("bookid".equalsIgnoreCase(name) == true) {
             this.bookid = value;
         }
		    if("bookname".equalsIgnoreCase(name) == true) {
             this.bookname = value;
         }
		    if("publisher".equalsIgnoreCase(name) == true) {
             this.publisher = value;
         }
		    if("deptid".equalsIgnoreCase(name) == true) {
             this.deptid = value;
         }
			         return;
	    }

    public String getColType(String name) {
        String colType = new String();
		    	    if("bookid".equalsIgnoreCase(name) == true) {
             colType = "VARCHAR(20)";
         }
		    	    if("bookname".equalsIgnoreCase(name) == true) {
             colType = "VARCHAR(20)";
         }
		    	    if("publisher".equalsIgnoreCase(name) == true) {
             colType = "VARCHAR(20)";
         }
		    	    if("deptid".equalsIgnoreCase(name) == true) {
             colType = "VARCHAR(20)";
         }
        return colType;
    }
    
    public String getTableName() { return "qiqu_book"; 	     }
    public String getPkFields()  { return "bookid,";    }
    public String getModifyFields() {
    	    String fields="";
		        	fields = fields + "bookname,";
	        	fields = fields + "publisher,";
	        	fields = fields + "deptid,";
        return fields;
    }
    public String getAllFields() {
    	    String fields="";
        fields = fields + "bookid,";
        fields = fields + "bookname,";
        fields = fields + "publisher,";
        fields = fields + "deptid,";
        return fields;
    }
    public void setValues(String[] values) {
		this.bookid = values[bookid_col];
		this.bookname = values[bookname_col];
		this.publisher = values[publisher_col];
		this.deptid = values[deptid_col];
    }

    public void setOtherProperty(String[] values) {
    }
}