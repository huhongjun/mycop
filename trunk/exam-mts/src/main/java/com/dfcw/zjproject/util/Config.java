/*
 * Created on Mar 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.util;


import java.io.*;
import java.util.*;

/**
 * Config property file reader
 */
public class Config {

    private static Properties config = null;
    private static String PROPERTY_FILE ="zjproject.properties";
    private static void readConfig()
    {
    
        if (config != null)
            return;
        try {
            
            // Loads the configuration file
            InputStream is = Class.forName("com.dfcw.zjproject.util.Config").getClassLoader().getResourceAsStream(PROPERTY_FILE);
            
            if (is != null) {
                config = new Properties();
                config.load(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    /**
     * Get property value.
     * @param       name            property name.
     * @return      the value.
     */
    public static String getProperty(String name) {
	    return getProperty(name, null);
    }

    /**
     * Get property value.
     * @param       name            property name.
     * @param       lang            language name.
     * @return      the value.
     */
    public static String getProperty(String name, String lang) {
	    return getProperty(name, lang, null);
    }

    /**
     * Get property value.
     * @param       name            property name.
     * @param       lang            language name.
     * @param       defaultValue    value if property not found.
     * @return      the value.
     */
    public static String getProperty(String name, String lang, String defaultValue) {
	if (config == null)
            readConfig();
        if (config != null)
        {
            if (lang == null)
            {
            	String ret=config.getProperty(name, defaultValue);
	        return ret==null?ret:ret.trim();
	    }
            else
            {
            	String ret=config.getProperty(name + "." + lang, defaultValue);
                return ret==null?ret:ret.trim();
            }
        }
	return defaultValue;
    }
}
