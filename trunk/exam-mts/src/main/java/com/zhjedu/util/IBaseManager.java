package com.zhjedu.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;

public interface IBaseManager {
	public List findObject(String hql) throws HibernateException;
	public Object findObject(Object obj) throws HibernateException;
	public int execute(final String hql) throws HibernateException;
	public int executeBatch(final Vector ve) throws HibernateException;
	public void saveOrUpdateObject(Object obj) throws HibernateException;
	public void deleteObject(Object obj) throws HibernateException;
	public PageObject executePage(final String counthql, final String hql, int intPage, final int intPageSize) throws HibernateException;
	public void saveOrUpdateBatch(List list) throws HibernateException;
	public int executeSql(final String sql) throws HibernateException, SQLException;
	public Connection getConnection();
}