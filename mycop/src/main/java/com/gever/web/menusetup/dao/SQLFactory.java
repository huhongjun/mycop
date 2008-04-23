/*
 * �������� 2004-10-12
 */
package com.gever.web.menusetup.dao;


/**
 *
 * @author Hu.Walker
 */
public interface SQLFactory {
    /**
     * Get the value for the query name
     *
     * @param queryName the name of the property
     * @return The value of the property
     */
    public abstract String get(String queryName);
}
