package com.gever.util;

import junit.framework.*;
import java.util.*;

public class TestNumberUtil
    extends TestCase {
    private NumberUtil numberUtil = null;

    public TestNumberUtil(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        /**@todo verify the constructors*/
        numberUtil = null;
    }

    protected void tearDown() throws Exception {
        numberUtil = null;
        super.tearDown();
    }

    public void testStringToCalendar() {
        String strDate = null;
        Calendar expectedReturn = null;
        Calendar actualReturn = null;
        System.out.println(
            "Method :public static Calendar stringToCalendar(String strDate)");

        //�������ݲ���
        strDate = "2004-05-20";
        actualReturn = numberUtil.stringToCalendar(strDate);
        if ("Thu May 20 00:00:00 CST 2004".equals(actualReturn.getTime().toString())) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }

        //�������ݲ���
        strDate = "0001-05-20";
        actualReturn = numberUtil.stringToCalendar(strDate);
        if ("Fri May 20 00:00:00 CST 0001".equals(actualReturn.getTime().toString())) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }

        //�������ݲ���
        strDate = "�������ݲ���.....";
        actualReturn = numberUtil.stringToCalendar(strDate);
        if (actualReturn == null) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }

        //�����ݲ���
        strDate = null;
        actualReturn = numberUtil.stringToCalendar(strDate);
        if (actualReturn == null) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToCalendar("2004-05-27");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToCalendar("2004-05-27");
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToDate() {
        String strDate = null;
        Date expectedReturn = null;
        Date actualReturn = null;
        System.out.println("Method :public static Date stringToDate(String strDate)");

        //�������ݲ���
        strDate = "2004-05-20";
        actualReturn = numberUtil.stringToDate(strDate);
        String expectReturn = "Thu May 20 00:00:00 GMT+08:00 2004";
//        assertTrue("�������ݲ���[����<>ʵ��]��"+expectReturn+" <> "+actualReturn.toString(),expectReturn.equals(actualReturn.toString()));

        //�������ݲ���
        strDate = "0001-05-20";
        actualReturn = numberUtil.stringToDate(strDate);
        expectReturn = "Fri May 20 00:00:00 GMT+08:00 0001";
//        assertTrue("�������ݲ���[����<>ʵ��]��"+expectReturn+" <> "+actualReturn.toString(),expectReturn.equals(actualReturn.toString()));

        //�������ݲ���
        strDate = "�������ݲ���.....";
        actualReturn = numberUtil.stringToDate(strDate);
        assertNull("�������ݲ���[����<>ʵ��]: null - ?",actualReturn);

        //�����ݲ���
        strDate = null;
        actualReturn = numberUtil.stringToDate(strDate);
        if (actualReturn == null) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToDate("2004-05-27");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToDate("2004-05-27");
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        //assertEquals("return value", expectedReturn, actualReturn);
        /**@todo fill in the test code*/
    }

    public void testStringToDouble() {
        String strData = null;
        double expectedReturn = 0;
        double actualReturn = 0;
        System.out.println("Method :public static double stringToDouble(String strData)");

        //�������ݲ���
        strData = "2004.8885555555555555";
        actualReturn = numberUtil.stringToDouble(strData);
        if (2004.8885555555555555 == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //�������ݲ���
        strData = "00000000000000000";
        actualReturn = numberUtil.stringToDouble(strData);
        if (0.0 == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //�������ݲ���
        strData = "����ȥ����";
        actualReturn = numberUtil.stringToDouble(strData);
        if (0 == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //�����ݲ���
        strData = null;
        actualReturn = numberUtil.stringToDouble(strData);
        if (0 == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToDouble("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToDouble("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToDouble1() {
        String strData = null;
        double defaultValue = 0.0;
        double expectedReturn = 0;
        double actualReturn = 0;
        System.out.println(
            "Method :public static double stringToDouble(String strData,double defaultValue)");

        //�������ݲ���
        strData = "2004.8885555555555555";
        defaultValue = 0.0;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (2004.8885555555555555 == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //�������ݲ���
        strData = "55.333333";
        defaultValue = 6.6;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (defaultValue != actualReturn && 55.333333 == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //�������ݲ���
        strData = "����ȥ����";
        defaultValue = 6.6;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //�����ݲ���
        strData = null;
        defaultValue = 0.0;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToDouble("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToDouble("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToFloat() {
        String strData = null;
        float expectedReturn = 0.0f;
        float actualReturn = 0;

        System.out.println("Method :public static float stringToFloat(String strData)");

        //�������ݲ���
        strData = "123.44";
        expectedReturn = 123.44f;
        actualReturn = numberUtil.stringToFloat(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //�������ݲ���
        strData = "123.8885555555555555";
        expectedReturn = 123.88856f;
        actualReturn = numberUtil.stringToFloat(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //�������ݲ���
        strData = "����ȥ�����ݣ�";
        actualReturn = numberUtil.stringToFloat(strData);
        if (0 == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //�����ݲ���
        strData = null;
        actualReturn = numberUtil.stringToFloat(strData);
        if (0 == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToFloat("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToFloat("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToFloat1() {
        String strData = null;
        float defaultValue = 0.0f;
        float expectedReturn = 0.0f;
        float actualReturn = 0;

        System.out.println(
            "Method :public static float stringToFloat(String strData,float defaultValue)");

        //�������ݲ���
        strData = "123.44";
        defaultValue = 8.8f;
        expectedReturn = 123.44f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //�������ݲ���
        strData = "123.8885555555555555";
        defaultValue = 8.8f;
        expectedReturn = 123.88856f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //�������ݲ���
        strData = "����ȥ�����ݣ�";
        defaultValue = 8.8f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //�����ݲ���
        strData = null;
        defaultValue = 8.8f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToFloat("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToFloat("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToInt() {
        String strData = null;
        int expectedReturn = 0;
        int actualReturn = 0;
        System.out.println("Method :public static int stringToInt(String strData)");

        //�������ݲ���
        strData = "555";
        expectedReturn = 555;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "2147483647";
        expectedReturn = 2147483647;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "����ȥ��CS����С����ɲ���";
        expectedReturn = 0;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�����ݲ���
        strData = null;
        expectedReturn = 0;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToInt("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToInt("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToInt1() {
        String strData = null;
        int defaultValue = 0;
        int expectedReturn = 0;
        int actualReturn = 0;
        System.out.println(
            "Method :public static int stringToInt(String strData,int defaultValue)");

        //�������ݲ���
        strData = "555";
        defaultValue = 100;
        expectedReturn = 555;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "2147483647";
        defaultValue = 789;
        expectedReturn = 2147483647;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "����ȥ��CS����С����ɲ���";
        defaultValue = 123456;
        expectedReturn = 123456;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�����ݲ���
        strData = null;
        defaultValue = 123456;
        expectedReturn = 123456;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToInt("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToInt("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToLong() {
        String strData = null;
        long expectedReturn = 0L;
        long actualReturn = 0L;
        System.out.println("Method :public static int stringToLong(String strData)");

        //�������ݲ���
        strData = "555";
        expectedReturn = 555;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "9223372036854775807";
        expectedReturn = 9223372036854775807L;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "go go girl��follow me��";
        expectedReturn = 0;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�����ݲ���
        strData = null;
        expectedReturn = 0;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToLong("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToLong("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToLong1() {
        String strData = null;
        long defaultValue = 0L;
        long expectedReturn = 0L;
        long actualReturn = 0L;
        System.out.println(
            "Method :public static int stringToLong(String strData,long defaultValue)");

        //�������ݲ���
        strData = "555";
        defaultValue = 6688L;
        expectedReturn = 555;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "9223372036854775807";
        defaultValue = 6688L;
        expectedReturn = 9223372036854775807L;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�������ݲ���
        strData = "go go girl��follow me��";
        defaultValue = 6688L;
        expectedReturn = 6688L;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�����ݲ���
        strData = null;
        defaultValue = 6688L;
        expectedReturn = 6688L;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * ���ܲ���
         */
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToLong("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToLong("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }
}