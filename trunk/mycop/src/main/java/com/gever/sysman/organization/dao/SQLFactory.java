/*
 * 创建日期 2004-10-12
 */
package com.gever.sysman.organization.dao;


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
