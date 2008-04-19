package com.gever.goa.book.dao.impl;

import com.gever.goa.book.dao.BookFactory;
import com.gever.goa.book.dao.BookDao;

/**
 * Comment block is generated by javaClassComment.vsl
 *
 * <p>Title: </p>
 * <p>Description: Book 实体</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:OSMatrix 天行软件</p>
 * @Author:		HuHJ
 * @Version:1.0
 *
 */
public class DefaultBookFactory extends BookFactory{
    public DefaultBookFactory() {
    }
    /**
     * @param dbData datasource
     * @return BookDAO instance
     */
     public BookDao createBookDAO(String dbData){
         return new BookDaoImpl(dbData);
     }
}