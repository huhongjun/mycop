package com.zhjedu.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplictionContext {

  private static ApplictionContext instance;

  private AbstractApplicationContext appContext;

  public synchronized static ApplictionContext getInstance() {
    if (instance == null) {
      instance = new ApplictionContext();
    }
    return instance;
  }

  private ApplictionContext() {
	  if(System.getProperty("os.name").endsWith("Linux")){
			//this.appContext = new FileSystemXmlApplicationContext("/webapps/exam/WEB-INF/classes/applicationContext*.xml");
			this.appContext = new ClassPathXmlApplicationContext("/applicationContext*.xml");
	  }else{
		  this.appContext = new ClassPathXmlApplicationContext("/applicationContext*.xml");
	  }
  }

  public AbstractApplicationContext getApplictionContext() {
    return appContext;
  }
}
