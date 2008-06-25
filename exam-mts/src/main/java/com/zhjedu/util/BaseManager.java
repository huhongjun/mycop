package com.zhjedu.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseManager extends HibernateDaoSupport implements IBaseManager{
	
	/**
	 * ִ�в�ѯ
	 * @param hql HQL���
	 * @return ����List��
	 */
	public List findObject(String hql) throws HibernateException{
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public Object findObject(Object obj) throws HibernateException{
		List list = this.getHibernateTemplate().findByExample(obj);
		if(list != null && list.size() == 1)
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * ִ��HQL ɾ�� ����
	 * @param hql HQL���
	 * @return ����������
	 */
	public int execute(final String hql) throws HibernateException{		
		int num = ((Integer)this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session s) throws HibernateException,
                    SQLException {
            	int deletedEntities = 0;
				deletedEntities = s.createQuery(hql).executeUpdate();
                return new Integer(deletedEntities);
            }
        })).intValue();
		return num;
	}
	
	/**
	 * ִ��������(��������)
	 * @param ve װ����HQL����Vector����
	 * @return ����������
	 */
	public int executeBatch(final Vector ve) throws HibernateException{		
		int num = ((Integer)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
			SQLException {
				int deletedEntities = 0;
			    for(int i = 0; i < ve.size(); i ++){
			    	deletedEntities += s.createQuery((String)ve.get(i)).executeUpdate();
			    }
			    return new Integer(deletedEntities);
			}
		})).intValue();
		return num;
	}
	
	/**
	 * ����(����)����
	 * @param obj ��Ҫ�������µĶ���
	 */
	public void saveOrUpdateObject(Object obj) throws HibernateException{
		System.out.print("BaseManater111"+obj.getClass());
		
		this.getHibernateTemplate().saveOrUpdate(obj);
		System.out.print("BaseManater");
	}
	
	/**
	 * ɾ�����
	 * @param obj ��Ҫɾ��Ķ���
	 */
	public void deleteObject(Object obj) throws HibernateException{
		this.getHibernateTemplate().delete(obj);
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param counthql ��ѯ��ݸ���HQL���
	 * @param hql ��ѯHQL���
	 * @param intPage ��ǰҳ
	 * @param intPageSize ÿҳ����
	 * @return ��ҳ����PageObject
	 */
	public PageObject executePage(final String counthql, final String hql, int intPage, final int intPageSize) throws HibernateException{
		PageObject po = new PageObject();
		final int totalRecord = Integer.parseInt(this.getHibernateTemplate().find(counthql).iterator().next().toString());
		int totalPage1 = 0;
    	if(totalRecord % intPageSize == 0){
    		totalPage1 = totalRecord / intPageSize;
    	}else{
    		totalPage1 = totalRecord / intPageSize + 1;
    	}
    	final int totalPage = totalPage1;
    	if(intPage > totalPage)
    		intPage = totalPage;
    	if(intPage <= 0)
    		intPage = 0;
    	final int currentPage = intPage;
    	po.setTotalRecord(totalRecord);
    	po.setCurrentPage(currentPage);
    	po.setPageSize(intPageSize);
    	po.setTotalPage(totalPage);
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session s) throws HibernateException,
                    SQLException {
//            	 ������ѯ
            	List list = new ArrayList();
            	int firstResult = 0;
                if (currentPage > 1) {
                  firstResult = (currentPage - 1) * intPageSize;
                }
            	if(totalRecord > 0){
	        		Query query = s.createQuery(hql);                
	        		//�����α����ʼ��                    
	                query.setFirstResult(firstResult);
	                //�����α�ĳ���
	                query.setMaxResults(intPageSize);
	                //�õ���¼
	                list = query.list();
            	}
                return list;
            }
        });
		po.setDatas((ArrayList)list);
		return po;
	}
	
	/**
	 * ���������
	 */
	public void saveOrUpdateBatch(List list) throws HibernateException{
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				this.getHibernateTemplate().saveOrUpdate(list.get(i));
			}
		}		
	}
	
	/**
	 * ִ��sql��� ɾ�� ����
	 * @param sql SQL���
	 * @return ����������
	 * @throws SQLException 
	 */
	public int executeSql(final String sql) throws HibernateException, SQLException{	
		int num = 0;
		Session s = this.getSession();
    	Connection conn = s.connection();
    	Statement stmt = conn.createStatement();
    	num = stmt.executeUpdate(sql);
		return num;
	}
	
	/**
	 * ȡ��Connection
	 */
	public Connection getConnection(){	
		Session s = this.getSession();
    	Connection conn = s.connection();
		return conn;
	}
}
