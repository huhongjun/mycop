package com.gever.util;

import junit.framework.*;

public class TestEncodeUtil
    extends TestCase {
    private EncodeUtil encodeUtil = null;

    public TestEncodeUtil(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        /**@todo verify the constructors*/
        encodeUtil = new EncodeUtil();
    }

    protected void tearDown() throws Exception {
        encodeUtil = null;
        super.tearDown();
    }

    public void testGBKToUnicode() {
        String gbk = null;
        String expectedReturn = null;
        String actualReturn = null;

        //�������ݲ���
        gbk = "���˿ްɿްɲ�����ټ�ǿ������Ҳ��Ȩ��ȥƣ���� ���� �� 88888";
        actualReturn = encodeUtil.GBKToUnicode(gbk);
        System.out.println("actualReturn:" + actualReturn);
        /*if ("Thu May 20 00:00:00 CST 2004".equals(actualReturn.getTime().toString())) {
            System.out.println("�������ݲ���:�ɹ���");
                 } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
                 }*/

        //assertEquals("return value", expectedReturn, actualReturn);
        /**@todo fill in the test code*/
    }

    public void testNativeToUnicode() {
        System.out.println(
            "Method :public static String nativeToUnicode(String n)");

        String strNative = null;
        String expectedReturn = null;
        String actualReturn = null;

        //�������ݲ���
        strNative = "���˿ްɿްɲ�����ټ�ǿ������Ҳ��Ȩ��ȥƣ���� ���� �� 88888";
        expectedReturn = "%C4%D0%C8%CB%BF%DE%B0%C9%BF%DE%B0%C9%B2%BB%CA%C7%D7%EF%A3%AC%D4%D9%BC%E1%C7%BF%B5%C4%C4%D0%C8%CB%D2%B2%D3%D0%C8%A8%C1%A6%C8%A5%C6%A3%B1%B9%A3%A1+%A3%BA%A3%A9+%A3%A1+88888";
        actualReturn = encodeUtil.nativeToUnicode(strNative);
        assertTrue("�������ݲ���[����<>ʵ��]��"+expectedReturn+" <> "+actualReturn,expectedReturn.equals(actualReturn));

        //�����ݲ���
        strNative = null;
        expectedReturn = null;
        actualReturn = encodeUtil.nativeToUnicode(strNative);
        assertTrue("�����ݲ���[����<>ʵ��]��"+expectedReturn+" <> "+actualReturn,expectedReturn==actualReturn);

        /**
         * ���ܲ���
         */
        strNative = "���˿ްɿްɲ�����ټ�ǿ������Ҳ��Ȩ��ȥƣ���� ���� �� 88888";
        expectedReturn = "%C4%D0%C8%CB%BF%DE%B0%C9%BF%DE%B0%C9%B2%BB%CA%C7%D7%EF%A3%AC%D4%D9%BC%E1%C7%BF%B5%C4%C4%D0%C8%CB%D2%B2%D3%D0%C8%A8%C1%A6%C8%A5%C6%A3%B1%B9%A3%A1+%A3%BA%A3%A9+%A3%A1+88888";
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            encodeUtil.nativeToUnicode(strNative);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            encodeUtil.nativeToUnicode(strNative);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

    public void testUnicodeToGBK() {
        String unicode = null;
        String expectedReturn = null;
        String actualReturn = null;

        //�������ݲ���
        unicode = "%C4%D0%C8%CB%BF%DE%B0%C9%BF%DE%B0%C9%B2%BB%CA%C7%D7%EF%A3%AC%D4%D9%BC%E1%C7%BF%B5%C4%C4%D0%C8%CB%D2%B2%D3%D0%C8%A8%C1%A6%C8%A5%C6%A3%B1%B9%A3%A1+%A3%BA%A3%A9+%A3%A1+88888";
        expectedReturn = "���˿ްɿްɲ�����ټ�ǿ������Ҳ��Ȩ��ȥƣ���� ���� �� 88888";
        actualReturn = encodeUtil.unicodeToGBK(unicode);
        /*if (actualReturn.equals(expectedReturn)) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }*/
        //assertEquals("return value", expectedReturn, actualReturn);

        //�����ݲ���
        unicode = null;
        expectedReturn = null;
        actualReturn = encodeUtil.unicodeToGBK(unicode);
        if (actualReturn == expectedReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**@todo fill in the test code*/
    }

    public void testUnicodeToNative() {
        System.out.println(
            "Method :public static String unicodeToNative(String u)");
        String unicode = null;
        String expectedReturn = null;
        String actualReturn = null;

        //�������ݲ���
        unicode = "%C4%D0%C8%CB%BF%DE%B0%C9%BF%DE%B0%C9%B2%BB%CA%C7%D7%EF%A3%AC%D4%D9%BC%E1%C7%BF%B5%C4%C4%D0%C8%CB%D2%B2%D3%D0%C8%A8%C1%A6%C8%A5%C6%A3%B1%B9%A3%A1+%A3%BA%A3%A9+%A3%A1+88888";
        expectedReturn = "���˿ްɿްɲ�����ټ�ǿ������Ҳ��Ȩ��ȥƣ���� ���� �� 88888";
        actualReturn = encodeUtil.unicodeToNative(unicode);
        if (actualReturn.equals(expectedReturn)) {
            System.out.println("�������ݲ���:�ɹ���");
        } else {
            System.out.println("�������ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        //�����ݲ���
        unicode = null;
        expectedReturn = null;
        actualReturn = encodeUtil.unicodeToNative(unicode);
        if (actualReturn == expectedReturn) {
            System.out.println("�����ݲ���:�ɹ���");
        } else {
            System.out.println("�����ݲ���:ʧ�ܣ�");
        }
        assertEquals("return value", expectedReturn, actualReturn);

        /**
         * ���ܲ���
         */
        unicode = "%C4%D0%C8%CB%BF%DE%B0%C9%BF%DE%B0%C9%B2%BB%CA%C7%D7%EF%A3%AC%D4%D9%BC%E1%C7%BF%B5%C4%C4%D0%C8%CB%D2%B2%D3%D0%C8%A8%C1%A6%C8%A5%C6%A3%B1%B9%A3%A1+%A3%BA%A3%A9+%A3%A1+88888";
        expectedReturn = "���˿ްɿްɲ�����ټ�ǿ������Ҳ��Ȩ��ȥƣ���� ���� �� 88888";
        //ѭ��100�β���
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            encodeUtil.unicodeToNative(unicode);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ѭ��100�κ���ʱ��:" + (endTime - startTime));

        //ѭ��10000�β���
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            encodeUtil.unicodeToNative(unicode);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ѭ��10000�κ���ʱ��:" + (endTime - startTime));

        /**@todo fill in the test code*/
    }

}