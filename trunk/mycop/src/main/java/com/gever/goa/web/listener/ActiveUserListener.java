package com.gever.goa.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.gever.struts.Constant;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title: �����û���½�����������û����������ͳ��</p>
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
			System.out.println(this.getClass().getName()+"->"+"�е�½�û�,ID="+ se.getValue());
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
			System.out.println(this.getClass().getName()+"->"+"���û���ȥ,ID="+ se.getValue());
			ActiveUsers users = ActiveUsers.getInstance();
			users.removeLogin((String) se.getValue());
		}

	}

	public static int getCounter() {
		return counter;
	}

}
