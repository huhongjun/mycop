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

        //正常数据测试
        strDate = "2004-05-20";
        actualReturn = numberUtil.stringToCalendar(strDate);
        if ("Thu May 20 00:00:00 CST 2004".equals(actualReturn.getTime().toString())) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }

        //极端数据测试
        strDate = "0001-05-20";
        actualReturn = numberUtil.stringToCalendar(strDate);
        if ("Fri May 20 00:00:00 CST 0001".equals(actualReturn.getTime().toString())) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }

        //错误数据测试
        strDate = "错误数据测试.....";
        actualReturn = numberUtil.stringToCalendar(strDate);
        if (actualReturn == null) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }

        //空数据测试
        strDate = null;
        actualReturn = numberUtil.stringToCalendar(strDate);
        if (actualReturn == null) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToCalendar("2004-05-27");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToCalendar("2004-05-27");
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToDate() {
        String strDate = null;
        Date expectedReturn = null;
        Date actualReturn = null;
        System.out.println("Method :public static Date stringToDate(String strDate)");

        //正常数据测试
        strDate = "2004-05-20";
        actualReturn = numberUtil.stringToDate(strDate);
        String expectReturn = "Thu May 20 00:00:00 GMT+08:00 2004";
//        assertTrue("正常数据测试[期望<>实际]："+expectReturn+" <> "+actualReturn.toString(),expectReturn.equals(actualReturn.toString()));

        //极端数据测试
        strDate = "0001-05-20";
        actualReturn = numberUtil.stringToDate(strDate);
        expectReturn = "Fri May 20 00:00:00 GMT+08:00 0001";
//        assertTrue("极端数据测试[期望<>实际]："+expectReturn+" <> "+actualReturn.toString(),expectReturn.equals(actualReturn.toString()));

        //错误数据测试
        strDate = "错误数据测试.....";
        actualReturn = numberUtil.stringToDate(strDate);
        assertNull("错误数据测试[期望<>实际]: null - ?",actualReturn);

        //空数据测试
        strDate = null;
        actualReturn = numberUtil.stringToDate(strDate);
        if (actualReturn == null) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToDate("2004-05-27");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToDate("2004-05-27");
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        //assertEquals("return value", expectedReturn, actualReturn);
        /**@todo fill in the test code*/
    }

    public void testStringToDouble() {
        String strData = null;
        double expectedReturn = 0;
        double actualReturn = 0;
        System.out.println("Method :public static double stringToDouble(String strData)");

        //正常数据测试
        strData = "2004.8885555555555555";
        actualReturn = numberUtil.stringToDouble(strData);
        if (2004.8885555555555555 == actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //极端数据测试
        strData = "00000000000000000";
        actualReturn = numberUtil.stringToDouble(strData);
        if (0.0 == actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //错误数据测试
        strData = "我想去打球！";
        actualReturn = numberUtil.stringToDouble(strData);
        if (0 == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //空数据测试
        strData = null;
        actualReturn = numberUtil.stringToDouble(strData);
        if (0 == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToDouble("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToDouble("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToDouble1() {
        String strData = null;
        double defaultValue = 0.0;
        double expectedReturn = 0;
        double actualReturn = 0;
        System.out.println(
            "Method :public static double stringToDouble(String strData,double defaultValue)");

        //正常数据测试
        strData = "2004.8885555555555555";
        defaultValue = 0.0;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (2004.8885555555555555 == actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //极端数据测试
        strData = "55.333333";
        defaultValue = 6.6;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (defaultValue != actualReturn && 55.333333 == actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //错误数据测试
        strData = "我想去打球！";
        defaultValue = 6.6;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        //空数据测试
        strData = null;
        defaultValue = 0.0;
        actualReturn = numberUtil.stringToDouble(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Double.MAX_VALUE);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToDouble("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToDouble("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToFloat() {
        String strData = null;
        float expectedReturn = 0.0f;
        float actualReturn = 0;

        System.out.println("Method :public static float stringToFloat(String strData)");

        //正常数据测试
        strData = "123.44";
        expectedReturn = 123.44f;
        actualReturn = numberUtil.stringToFloat(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //极端数据测试
        strData = "123.8885555555555555";
        expectedReturn = 123.88856f;
        actualReturn = numberUtil.stringToFloat(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //错误数据测试
        strData = "我想去吹泡泡！";
        actualReturn = numberUtil.stringToFloat(strData);
        if (0 == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //空数据测试
        strData = null;
        actualReturn = numberUtil.stringToFloat(strData);
        if (0 == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToFloat("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToFloat("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToFloat1() {
        String strData = null;
        float defaultValue = 0.0f;
        float expectedReturn = 0.0f;
        float actualReturn = 0;

        System.out.println(
            "Method :public static float stringToFloat(String strData,float defaultValue)");

        //正常数据测试
        strData = "123.44";
        defaultValue = 8.8f;
        expectedReturn = 123.44f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //极端数据测试
        strData = "123.8885555555555555";
        defaultValue = 8.8f;
        expectedReturn = 123.88856f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //错误数据测试
        strData = "我想去吹泡泡！";
        defaultValue = 8.8f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        //空数据测试
        strData = null;
        defaultValue = 8.8f;
        actualReturn = numberUtil.stringToFloat(strData, defaultValue);
        if (defaultValue == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn, Float.MAX_VALUE);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToFloat("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToFloat("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToInt() {
        String strData = null;
        int expectedReturn = 0;
        int actualReturn = 0;
        System.out.println("Method :public static int stringToInt(String strData)");

        //正常数据测试
        strData = "555";
        expectedReturn = 555;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //极端数据测试
        strData = "2147483647";
        expectedReturn = 2147483647;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //错误数据测试
        strData = "今晚去玩CS，把小董打成菜鸟！";
        expectedReturn = 0;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //空数据测试
        strData = null;
        expectedReturn = 0;
        actualReturn = numberUtil.stringToInt(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToInt("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToInt("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToInt1() {
        String strData = null;
        int defaultValue = 0;
        int expectedReturn = 0;
        int actualReturn = 0;
        System.out.println(
            "Method :public static int stringToInt(String strData,int defaultValue)");

        //正常数据测试
        strData = "555";
        defaultValue = 100;
        expectedReturn = 555;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //极端数据测试
        strData = "2147483647";
        defaultValue = 789;
        expectedReturn = 2147483647;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //错误数据测试
        strData = "今晚去玩CS，把小董打成菜鸟！";
        defaultValue = 123456;
        expectedReturn = 123456;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //空数据测试
        strData = null;
        defaultValue = 123456;
        expectedReturn = 123456;
        actualReturn = numberUtil.stringToInt(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToInt("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToInt("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToLong() {
        String strData = null;
        long expectedReturn = 0L;
        long actualReturn = 0L;
        System.out.println("Method :public static int stringToLong(String strData)");

        //正常数据测试
        strData = "555";
        expectedReturn = 555;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //极端数据测试
        strData = "9223372036854775807";
        expectedReturn = 9223372036854775807L;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //错误数据测试
        strData = "go go girl！follow me！";
        expectedReturn = 0;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //空数据测试
        strData = null;
        expectedReturn = 0;
        actualReturn = numberUtil.stringToLong(strData);
        if (expectedReturn == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToLong("20040527");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToLong("20040527");
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testStringToLong1() {
        String strData = null;
        long defaultValue = 0L;
        long expectedReturn = 0L;
        long actualReturn = 0L;
        System.out.println(
            "Method :public static int stringToLong(String strData,long defaultValue)");

        //正常数据测试
        strData = "555";
        defaultValue = 6688L;
        expectedReturn = 555;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("正常数据测试:成功！");
        } else {
            System.out.println("正常数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //极端数据测试
        strData = "9223372036854775807";
        defaultValue = 6688L;
        expectedReturn = 9223372036854775807L;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue != actualReturn) {
            System.out.println("极端数据测试:成功！");
        } else {
            System.out.println("极端数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //错误数据测试
        strData = "go go girl！follow me！";
        defaultValue = 6688L;
        expectedReturn = 6688L;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("错误数据测试:成功！");
        } else {
            System.out.println("错误数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //空数据测试
        strData = null;
        defaultValue = 6688L;
        expectedReturn = 6688L;
        actualReturn = numberUtil.stringToLong(strData, defaultValue);
        if (expectedReturn == actualReturn && defaultValue == actualReturn) {
            System.out.println("空数据测试:成功！");
        } else {
            System.out.println("空数据测试:失败！");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * 性能测试
         */
        //循环100次测试
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            numberUtil.stringToLong("20040527", 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("循环100次耗用时间:" + (endTime - startTime));

        //循环10000次测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numberUtil.stringToLong("20040527", 0);
        }
        endTime = System.currentTimeMillis();
        System.out.println("循环10000次耗用时间:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }
}