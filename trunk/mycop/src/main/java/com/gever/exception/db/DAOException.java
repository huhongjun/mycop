package com.gever.exception.db;

import com.gever.exception.DefaultException;


/**
 * @author 
 * 1. ORG_Log_001 - 
 * 2. 
 *
 */
public class DAOException extends DefaultException {
    public DAOException() {
        super("");
    }

    /**
     * @param cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String msg) {
        super(msg);
    }
    
    public DAOException(String errorCode, String errorLevel, Throwable cause) {
        super(errorCode, errorLevel, cause);
    }
}
