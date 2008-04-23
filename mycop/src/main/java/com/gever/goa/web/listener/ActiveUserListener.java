package com.gever.goa.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.gever.struts.Constant;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title: 监听用户登陆，管理在线用户对象和人数统计</p>
 * <p>Description:</p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: 
 * </p>
 */

public class ActiveUserListener implements HttpSessionAttributeListener {
	
	private static int counter = 0;
	
	public ActiveUserListener() {
	}

	public static int getActiveCount() {
		return counter;
	}

	// Increment the counter if we add a "pagehit" attribute...
	public void attributeAdded(HttpSessionBindingEvent se) {
		if (se.getName().equals(Constant.USER_ID)) {
			System.out.println(this.getClass().getName()+"->"+"有登陆用户,ID="+ se.getValue());
			++counter;
		}
	}

	// ...or replace it
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

	// Not interested in attributes being removed
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (se.getName().equals(Constant.USER_ID)) {
			--counter;
			System.out.println(this.getClass().getName()+"->"+"有用户离去,ID="+ se.getValue());
			ActiveUsers users = ActiveUsers.getInstance();
			users.removeLogin((String) se.getValue());
		}

	}

	public static int getCounter() {
		return counter;
	}

}
