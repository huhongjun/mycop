//Source file: E:\\lichm\\MyWorks\\GDP\\工作区\\GDP工程\\Sysman\\src\\com\\gever\\Extensible.java

package com.gever.sysman.privilege.util;

import java.util.Map;

/**
扩展接口
 */
public interface Extensible 
{
   
   /**
   获取扩展属性
   @return java.util.Map
   @roseuid 40B69DA500CB
    */
   public Map getProperties();
   
   /**
   设置扩展属性
   @param properties - 扩展属性
   @roseuid 40B69DB7005D
    */
   public void setProperties(Map properties);
   
   /**
   根据key获取扩展属性
   @param key
   @return java.lang.String
   @roseuid 40B69DBF0119
    */
   public String getProperty(String key);
   
   /**
   设置扩展属性
   @param key - 指定的key值
   @param value - 指定的属性值
   @roseuid 40B69DC30251
    */
   public void setProperty(String key, String value);
}
