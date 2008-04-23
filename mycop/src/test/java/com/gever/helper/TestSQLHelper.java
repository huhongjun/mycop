package com.gever.helper;

import java.sql.*;
import java.util.*;

import com.gever.exception.*;
import com.gever.util.EncodeUtil;
import com.gever.vo.*;
import junit.framework.*;

import com.gever.jdbc.sqlhelper.*;
import com.gever.util.*;

public class TestSQLHelper extends TestCase {
    private SQLHelper sQLHelper = null;
    ErrorConfig cfg = null;
    Connection conn;

    public TestSQLHelper(String str) {
        super(str);
    }

    protected void setUp() throws Exception {
        super.setUp();
        ErrorConfig.setPath(".\\testDir\\resource\\");
        
        /**@todo verify the constructors*/
        cfg = ErrorConfig.getInstance();
        sQLHelper = new DefaultSQLHelper("unittest");
        conn = sQLHelper.getConnection();
    }

    protected void tearDown() throws Exception {
        sQLHelper.close(conn);
        sQLHelper.end();
        sQLHelper = null;
        super.tearDown();
    }

    public void testExecUpdate() throws DefaultException {
        sQLHelper.setAutoClose(true);
        String strsql = "update test set username='翁乃彬' ,price=66.33,col1='天行' where id=3 ";
        int expectedReturn = 1;
        int actualReturn = sQLHelper.execUpdate(conn, strsql);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testExecUpdate1() throws DefaultException, SQLException {
        String strsql = "update test set username=? ,price=?,col1=? where id=? ";
        String[] values = new String[4];
        values[0] = "翁乃彬";
        values[1] = "100.875";
        values[2] = "广东正泰";
        values[3] = "4";
        String types = "String,double,String,long";

        int expectedReturn = 1;
        int actualReturn = sQLHelper.execUpdate(conn, strsql, values, types);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testExecUpdate2() throws DefaultException {
        String strsql =
            "update test set username=? ,price=?,col1=? where id=? ";
        String columns = "username,price,col1,id";
        TestVO vo = new TestVO();
        vo.setUsername("余兰");
        vo.setPrice("33.33");
        vo.setCol1("ffffffffffffffffff");
        vo.setId("5");
        int expectedReturn = 1;
        int actualReturn = sQLHelper.execUpdate(conn, strsql, vo, columns);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testExecUpdate3() throws DefaultException {
        String strsql =
            "update test set username='龚  燕' ,price=66.33,col1='天行' where id=6 ";
        int expectedReturn = 1;
        int actualReturn = sQLHelper.execUpdate(strsql);

        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testExecUpdate4() throws DefaultException {
        String strsql =
            "update test set username=? ,price=?,col4=? where id>=? and id<? ";
        String[] values = new String[5];
        values[0] = "余欣";
        values[1] = "64400.875";
        values[2] = "广东正泰";
        values[3] = "7";
        values[4] = "10";
        String types = "String,double,String,long,long";

        int expectedReturn = 3;

        int actualReturn = sQLHelper.execUpdate(strsql, values, types);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testExecUpdate5() throws DefaultException {
        String strsql =
            "update test set username=? ,price=?,col1=?,bdate=?,eTime=? where id=? ";
        String columns = "username,price,col1,bdate,etime,id";
        TestVO vo = new TestVO();
        vo.setUsername("黄鹏英");
        vo.setPrice("133.33");
        vo.setCol1("ffffffffffffffffff");
        vo.setBdate("2003-10-15");
        vo.setId("11");
        vo.setEtime("20:20:30");
        int expectedReturn = 1;

        int actualReturn = sQLHelper.execUpdate(strsql, vo, columns);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testUpdate() throws DefaultException {
        VOInterface view = null;
        TestVO vo = new TestVO();
        System.out.println("----many---");
        vo.setUsername("黄小梅");
        vo.setUserid("3432432");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工工工工工");
        vo.setBdate("2024-10-15");
        vo.setEtime("20:20:30");
        vo.setId("12");

        int expectedReturn = 1;

        int actualReturn = sQLHelper.update(conn, vo);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testUpdate1() throws DefaultException {
        VOInterface view = null;
        TestVO vo = new TestVO();
        System.out.println("----many---");
        vo.setUsername("何小英");
        vo.setUserid("3432432");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工工工工工");
        vo.setBdate(DateTimeUtils.getCurrentDate() );
        vo.setEtime(DateTimeUtils.getCurrentTime() );
        vo.setId("2");

        int expectedReturn = 1;
        int actualReturn = sQLHelper.update(vo);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testGetConnection() throws DefaultException {
        Connection expectedReturn = sQLHelper.getConnection();

        Connection actualReturn = sQLHelper.getConnection();
        //assertEquals("return value", expectedReturn, actualReturn);
        /**@todo fill in the test code*/
        sQLHelper.close(expectedReturn);
        sQLHelper.close(actualReturn);

    }

    public void testInsert() throws DefaultException {
        TestVO vo = new TestVO();
        vo.setUsername("刘向英");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工工工工工");
        vo.setBdate("2004-10-15");

        vo.setEtime("20:20:30");

        int expectedReturn = 1;
        int actualReturn = sQLHelper.insert(conn, vo);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testDelete() throws DefaultException {
        TestVO vo = new TestVO();
        vo.setId("21");
        int expectedReturn = 1;
        int actualReturn = sQLHelper.delete(conn, vo);
        // assertEquals("return value", expectedReturn, actualReturn);

        /**@todo fill in the test code*/
    }

    public void testDelete1() throws DefaultException {

        TestVO vo = new TestVO();
        vo.setId("23");
        int expectedReturn = 1;
        int actualReturn = sQLHelper.delete(vo);
        // assertEquals("return value", expectedReturn, actualReturn);
        sQLHelper.end();
        /**@todo fill in the test code*/
    }

    public void testInsert1() throws DefaultException {
        TestVO vo = new TestVO();
        vo.setUsername("万娜");
        vo.setPrice("12233.33");
        vo.setAmt("11111");
        vo.setQty("77777");
        vo.setCol1("王王王王言言言言");
        vo.setBdate("2004-10-15");

        vo.setEtime("20:20:30");

        int expectedReturn = 1;

        int actualReturn = sQLHelper.insert(vo);
        assertEquals("return value", expectedReturn, actualReturn);
        sQLHelper.end();
        /**@todo fill in the test code*/
    }

    public void testMultiInsert() throws DefaultException {
        TestVO vo = new TestVO();
        System.out.println("----many---");
        List aryData = new ArrayList();
        vo.setUsername("刘向英");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工工工工工");
        vo.setBdate("2004-10-15");
        vo.setEtime("20:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("简建英");
        vo.setPrice("8612233.33");
        vo.setAmt("77754888888");
        vo.setQty("442222");
        vo.setCol1("是脸工工");
        vo.setBdate("2003-10-15");
        vo.setEtime("25:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("刘燕霞");
        vo.setPrice("62233.33");
        vo.setAmt("222888");
        vo.setQty("2222");
        vo.setCol1("中华人民共和国工工工");
        vo.setBdate("2004-10-13");
        vo.setEtime("10:20:30");
        aryData.add(vo);

        int expectedReturn = aryData.size();
        sQLHelper.setAutoClose(false);
        int actualReturn = sQLHelper.multiInsert(conn, aryData, vo);
        assertEquals("return value", expectedReturn, actualReturn);

        sQLHelper.setAutoClose(true);
    }

    public void testMultiInsert1() throws DefaultException {
        TestVO vo = new TestVO();
        System.out.println("----many---");
        List aryData = new ArrayList();
        vo.setUsername("何小英");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工工工工工");
        vo.setBdate("2004-10-15");
        vo.setEtime("20:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("任慧丽");
        vo.setPrice("8612233.33");
        vo.setAmt("77754888888");
        vo.setQty("442222");
        vo.setCol1("是脸工工");
        vo.setBdate("2003-10-15");
        vo.setEtime("25:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("吴青");
        vo.setPrice("62233.33");
        vo.setAmt("222888");
        vo.setQty("2222");
        vo.setCol1("中华人民共和国工工工");
        vo.setBdate("2004-10-13");
        vo.setEtime("10:20:30");
        aryData.add(vo);

        String columNames = "username,price,amt,qty,col1,bdate";
        sQLHelper.setAutoClose(false);
        int expectedReturn = aryData.size();
        int actualReturn = sQLHelper.multiInsert(conn, aryData, vo, columNames);
        assertEquals("return value", expectedReturn, actualReturn);
    }

    public void testMultiInsert2() throws DefaultException {
        TestVO vo = new TestVO();
        List aryData = new ArrayList();
        vo.setUsername("熊小红");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工423工工工工");
        vo.setBdate("2004-10-15");
        vo.setEtime("20:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("黄小坟");
        vo.setPrice("8612233.33");
        vo.setAmt("77754888888");
        vo.setQty("442222");
        vo.setCol1("是脸工22222工");
        vo.setBdate("2003-10-15");
        vo.setEtime("25:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("李玟");
        vo.setPrice("62233.33");
        vo.setAmt("222888");
        vo.setQty("2222");
        vo.setCol1("中323华人民共和国工工工");
        vo.setBdate("2004-10-13");
        vo.setEtime("10:20:30");
        aryData.add(vo);
        sQLHelper.setAutoClose(false);
        int expectedReturn = aryData.size();

        int actualReturn = sQLHelper.multiInsert(aryData, vo);
        assertEquals("return value", expectedReturn, actualReturn);
        sQLHelper.end();
    }

    public void testMultiInsert3() throws DefaultException {
        TestVO vo = new TestVO();
        List aryData = new ArrayList();
        vo.setUsername("何小英");
        vo.setPrice("12233.33");
        vo.setAmt("888888");
        vo.setQty("2222");
        vo.setCol1("工工工工工工工");
        vo.setBdate("2004-10-15");
        vo.setEtime("20:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("任慧丽");
        vo.setPrice("8612233.33");
        vo.setAmt("77754888888");
        vo.setQty("442222");
        vo.setCol1("是脸工工");
        vo.setBdate("2003-10-15");
        vo.setEtime("25:20:30");
        aryData.add(vo);

        vo = new TestVO();
        vo.setUsername("吴青");
        vo.setPrice("62233.33");
        vo.setAmt("222888");
        vo.setQty("2222");
        vo.setCol1("中华人民共和国工工工");
        vo.setBdate("2004-10-13");
        vo.setEtime("10:20:30");
        aryData.add(vo);

        String columNames = "username,price,amt,qty,col1,bdate";
        sQLHelper.setAutoClose(false);
        int expectedReturn = aryData.size();

        int actualReturn = sQLHelper.multiInsert(aryData, vo, columNames);
        assertEquals("return value", expectedReturn, actualReturn);
        sQLHelper.end();
        /**@todo fill in the test code*/
    }

    public void testQueryByListVo() throws DefaultException {
        String strsql = "select * from test where id>=33300";
        TestVO view = new TestVO();
        List list = sQLHelper.queryByListVo(strsql, view);
        TestVO vo = new TestVO();
        for (int idx = 0; idx < list.size() && idx < 30; idx++) {
            vo = new TestVO();
            vo = (TestVO) list.get(idx);
        }

    }

    public void testQueryByPK() throws DefaultException {
        TestVO vo = new TestVO();
        vo.setId("33001");
        vo = (TestVO) sQLHelper.queryByPK(vo);
    }

    public void testQueryByVo() throws DefaultException {
        String strsql = "select * from test where id=33422";
        TestVO vo = new TestVO();
        vo = (TestVO) sQLHelper.queryByVo(strsql, vo);
        System.out.println("-----single vo--username--" + vo.getUsername() +
                           " amt=" + vo.getAmt());
    }

    public void testQuery() throws DefaultException {
        String strsql = "select * from test where id <100";

        List ary = (ArrayList) sQLHelper.query(strsql);
        System.out.println("----size------" + ary.size());
        Map map = null;
        for (int idx = 0; idx < 30; idx++) {
            map = new HashMap();
            map = (HashMap) ary.get(idx);
            map = null;
        }
        ary = null;

        map = null;

    }

    public void testQuery1() throws DefaultException {

    	String strsql = "select * from test where userid=? and username=?";
        String fldNames = "userid,username";
        TestVO view = new TestVO();
        int type = 0;
        view.setUserid("SA1");
        view.setUsername("翁乃彬");

        List ary = (ArrayList) sQLHelper.query(strsql, fldNames, view,
                                               DataTypes.RS_LIST_MAP);

        Map map = null;
        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            map = new HashMap();
            map = (HashMap) ary.get(idx);
            System.out.println("--list map----username--" + map.get("username") +
                               " col1=" + map.get("col1"));
        }

        ary = (ArrayList) sQLHelper.query(strsql, fldNames, view,
                                          DataTypes.RS_LIST_VO);
        System.out.println("----size------" + ary.size());
        TestVO vo = new TestVO();
        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            vo = new TestVO();
            vo = (TestVO) ary.get(idx);
            System.out.println("--list vo-username--" + vo.getUsername() +
                               " amt =" + vo.getAmt());
        }

        vo = (TestVO) sQLHelper.query(strsql, fldNames, view,
                                      DataTypes.RS_SINGLE_VO);
        System.out.println("-----single vo--username--" + vo.getUsername() +
                           " amt=" + vo.getAmt());

        List list = (ArrayList) sQLHelper.query(strsql, fldNames, view,
                                                DataTypes.RS_META_LIST);
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.print("---meta list---" + idx + "---" +
                             String.valueOf(list.get(idx)));
        }

        map = (HashMap) sQLHelper.query(strsql, fldNames, view,
                                        DataTypes.RS_SINGLE_MAP);
        System.out.println("---- single map--username--" + map.get("username") +
                           " col1=" + map.get("col1"));
        ary = null;
        list = null;
        map = null;
        vo = null;

        /**@todo fill in the test code*/
    }

    public void testQuery2() throws DefaultException {
        System.out.println("************** testQuery2********");
        int type = 0;
        String strsql = "select * from test where id>=32860";
        TestVO view = new TestVO();

        List ary = (ArrayList) sQLHelper.query(strsql, view,
                                               DataTypes.RS_LIST_MAP);
        System.out.println("--list--size------" + ary.size());

        Map map = null;
        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            map = new HashMap();
            map = (HashMap) ary.get(idx);
            System.out.println("--list map----username--" + map.get("username") +
                               " col1=" + map.get("col1"));
        }

        ary = (ArrayList) sQLHelper.query(strsql, view, DataTypes.RS_LIST_VO);
        System.out.println("----size------" + ary.size());
        TestVO vo = new TestVO();
        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            vo = new TestVO();
            vo = (TestVO) ary.get(idx);
            System.out.println("----list vo-username--" + vo.getUsername() +
                               " amt =" + vo.getAmt());
            vo = null;
        }

        vo = (TestVO) sQLHelper.query(strsql, view, DataTypes.RS_SINGLE_VO);
        System.out.println("-----single vo--username--" + vo.getUsername() +
                           " amt=" + vo.getAmt());

        List list = (ArrayList) sQLHelper.query(strsql, view,
                                                DataTypes.RS_META_LIST);
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.print("---meta list---" + idx + "---" +
                             String.valueOf(list.get(idx)));
        }

        map = (HashMap) sQLHelper.query(strsql, view, DataTypes.RS_SINGLE_MAP);
        System.out.println("----single map--username--" + map.get("username") +
                           " col1=" + map.get("col1"));
        ary = null;
        list = null;
        map = null;
        vo = null;

        /**@todo fill in the test code*/
    }

    public void testQuery3() throws DefaultException {
        System.out.println("************** testQuery3 ********************");
        int type = 0;
        String strsql = "select * from test where id>=33000";
        TestVO view = new TestVO();

        List ary = (ArrayList) sQLHelper.query(strsql, DataTypes.RS_LIST_MAP);
        System.out.println("----list--size------" + ary.size());

        Map map = null;
        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            map = new HashMap();
            map = (HashMap) ary.get(idx);
            System.out.println("----list map----username--" +
                               map.get("username") + " col1=" + map.get("col1"));
            map = null;
        }
        TestVO vo = new TestVO();
        try {
            ary = (ArrayList) sQLHelper.query(strsql, DataTypes.RS_LIST_VO);
            System.out.println("----size------" + ary.size());

            for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
                vo = new TestVO();
                vo = (TestVO) ary.get(idx);
                System.out.println("----list vo-username--" + vo.getUsername() +
                                   " amt =" + vo.getAmt());
                vo = null;
            }
        } catch (DefaultException de) {
            String str = EncodeUtil.unicodeToGBK(de.getErrorMessage(de.
                getErrorCode()));
            de.printStackTrace();
            System.out.println("-----error message---" + str);
        }

        try {
            vo = (TestVO) sQLHelper.query(strsql, DataTypes.RS_SINGLE_VO);
            System.out.println("-----single vo--username--" + vo.getUsername() +
                               " amt=" + vo.getAmt());
        } catch (DefaultException de) {
            String str = EncodeUtil.unicodeToGBK(de.getErrorMessage(de.
                getErrorCode()));
            de.printStackTrace();
            System.out.println("-----error message---" + str);
        }

        List list = (ArrayList) sQLHelper.query(strsql, DataTypes.RS_META_LIST);
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.print("---meta list---" + idx + "---" +
                             String.valueOf(list.get(idx)));
        }

        map = (HashMap) sQLHelper.query(strsql, DataTypes.RS_SINGLE_MAP);
        System.out.println("----single map--username--" + map.get("username") +
                           " col1=" + map.get("col1"));

        try {
            // Object expectedReturn = null;
            Object actualReturn = sQLHelper.query(strsql, type);
            // assertEquals("return value", expectedReturn, actualReturn);
        } catch (DefaultException e) {

            String str = EncodeUtil.unicodeToGBK(e.getErrorMessage(e.
                getErrorCode()));
            e.printStackTrace();
            System.out.println("-----error message---" + str);
        }
        ary = null;
        list = null;
        map = null;
        /**@todo fill in the test code*/
    }

    public void testQuery4() throws DefaultException {
        String[] strsql = new String[2];

        System.out.println("************** testQuery[4] ********************");
        int type = 0;
        strsql[0] = "select * from test where id>=? and username=?";
        strsql[1] = "long,String";
        List values = new ArrayList();
        values.add("33101");
        values.add("万娜");
        TestVO view = new TestVO();
        sQLHelper.setAutoClose(true);

        Map map = new HashMap();
        System.out.println("strsql=" + strsql[1]);
        System.out.println("values[0]=" + values.get(0) + " values[1]" +
                           values.get(1));
        map = (HashMap) sQLHelper.query(strsql, values,
                                        DataTypes.RS_SINGLE_MAP);
        System.out.println("----single map--username--" + map.get("username") +
                           " col1=" + map.get("col1"));
        List ary = (ArrayList) sQLHelper.query(strsql, values,
                                               DataTypes.RS_LIST_MAP);
        System.out.println("----list--size------" + ary.size());

        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            map = new HashMap();
            map = (HashMap) ary.get(idx);
            System.out.println("----list map----username--" +
                               map.get("username") + " col1=" + map.get("col1"));
        }
        TestVO vo = new TestVO();

        try {
            ary = (ArrayList) sQLHelper.query(strsql, values,
                                              DataTypes.RS_LIST_VO);
            System.out.println("----size------" + ary.size());

            for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
                vo = new TestVO();
                vo = (TestVO) ary.get(idx);
                System.out.println("----list vo-username--" + vo.getUsername() +
                                   " amt =" + vo.getAmt());
            }
        } catch (DefaultException de) {
            String str = EncodeUtil.unicodeToGBK(de.getErrorMessage(de.
                getErrorCode()));
            de.printStackTrace();
            System.out.println("-----error message---" + str);
        }

        try {
            ary = (ArrayList) sQLHelper.query(strsql, values,
                                              DataTypes.RS_SINGLE_VO);
            System.out.println("-----single vo--username--" + vo.getUsername() +
                               " amt=" + vo.getAmt());
        } catch (DefaultException de) {
            String str = EncodeUtil.unicodeToGBK(de.getErrorMessage(de.
                getErrorCode()));
            de.printStackTrace();
            System.out.println("-----error message---" + str);
        }

        List list = (ArrayList) sQLHelper.query(strsql, values,
                                                DataTypes.RS_META_LIST);
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.print("---meta list---" + idx + "---" +
                             String.valueOf(list.get(idx)));
        }

        list = null;
        ary = null;
        values = null;
        strsql = null;

        /**@todo fill in the test code*/
    }

    public void testQuery5() throws DefaultException {

        int type = 0;

        System.out.println("\n************** testQuery[5] ********************");
        String[] strsql = new String[2];
        strsql[0] = "select * from test where id>=? and username=?";
        strsql[1] = "long,String";

        String[] values = new String[2];
        values[0] = "33001";
        values[1] = "万娜";
        TestVO view = new TestVO();
        sQLHelper.setAutoClose(true);
        Map map = new HashMap();
        map = (HashMap) sQLHelper.query(strsql, values, view,
                                        DataTypes.RS_SINGLE_MAP);
        System.out.println("----single map--username--" + map.get("username") +
                           " col1=" + map.get("col1"));

        List ary = (ArrayList) sQLHelper.query(strsql, values, view,
                                               DataTypes.RS_LIST_MAP);
        System.out.println("----list--size------" + ary.size());

        for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
            map = new HashMap();
            map = (HashMap) ary.get(idx);
            System.out.println("----list map----username--" +
                               map.get("username") + " col1=" + map.get("col1"));
        }
        TestVO vo = new TestVO();

        try {
            ary = (ArrayList) sQLHelper.query(strsql, values, view,
                                              DataTypes.RS_LIST_VO);

            for (int idx = 0; idx < ary.size() && idx < 30; idx++) {
                vo = new TestVO();
                vo = (TestVO) ary.get(idx);
                System.out.println("----list vo-username--" + vo.getUsername() +
                                   " amt =" + vo.getAmt());
            }
        } catch (DefaultException de) {
            String str = EncodeUtil.unicodeToGBK(de.getErrorMessage(de.
                getErrorCode()));
            de.printStackTrace();
            System.out.println("-----error message---" + str);
        }

        try {
            vo = (TestVO) sQLHelper.query(strsql, values, view,
                                          DataTypes.RS_SINGLE_VO);
            System.out.println("-----single vo--username--" + vo.getUsername() +
                               " amt=" + vo.getAmt());
        } catch (DefaultException de) {
            String str = EncodeUtil.unicodeToGBK(de.getErrorMessage(de.
                getErrorCode()));
            de.printStackTrace();
            System.out.println("-----error message---" + str);
        }

        List list = (ArrayList) sQLHelper.query(strsql, values, view,
                                                DataTypes.RS_META_LIST);
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.print("---meta list---" + idx + "---" +
                             String.valueOf(list.get(idx)));
        }

    }

}