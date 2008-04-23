package com.gever.goa.util.timer;

import com.gever.jdbc.BaseDAO;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MessageBox extends BaseDAO{
    private int count = 0;
    public MessageBox() {
            }
    public MessageBox(String dbData) {
        super(dbData);
    }

    public boolean checkPre() {
        ++count;
       if (count >2){
            return true;
        }
        return false;
    }

    public boolean doSendMessageMethod() {
               return true;
    }

}
