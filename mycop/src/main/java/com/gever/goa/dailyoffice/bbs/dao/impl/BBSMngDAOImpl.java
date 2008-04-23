package com.gever.goa.dailyoffice.bbs.dao.impl;

import java.sql.Connection;
import java.sql.Statement;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSMngDAO;
import com.gever.goa.dailyoffice.bbs.vo.SBoardVO;
import com.gever.goa.dailyoffice.bbs.vo.TopBoardVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;


public class BBSMngDAOImpl extends BaseDAO implements BBSMngDAO{


  public String quest_sql="";

  public BBSMngDAOImpl(String db){
    super(db);
  }


  public void setQuest_Sql(String sql)throws DefaultException{
    quest_sql=sql;
  }
  //Extend baseDAO'mothed getPageSQL
  public String getPageSql() {
        return quest_sql;
    }

    /**
        * 删除所有的资料
        * @param aryPk 主键数组
        * @param vo 当前VO对象
        * @return 删除成功多少笔,-1为失败
        * @throws DefaultException
        */
       public int delBySelect(String[] aryPk, VOInterface vo) throws
           DefaultException {
           int intRet = 0;
           if(vo instanceof SBoardVO){
             intRet=this.deleteSBoard(aryPk);
           }else if(vo instanceof TopBoardVO){
             intRet=this.deleteTopSboard(aryPk);
           }else{
             intRet=super.delBySelect(aryPk,vo);
           }
           return intRet;
       }
       private int deleteSBoard(String[] sids) {
         int intRet = 0;
         String pks = this.arrayToString(sids);
         String[] sqls = new String[3];
         String sql = "delete from do_bbs_topiclist where topic_num in( select serial_num from do_bbs_topic where sboard_serial in (" +
             pks + ") )";
         sqls[0] = sql;
         sql = "delete from do_bbs_topic where sboard_serial in (" + pks + " ) ";
         sqls[1] = sql;
         sql = "delete from do_bbs_sboard where serial_num in(" + pks + ") ";
         sqls[2] = sql;
         this.excuteBatch(sqls);
         return intRet;
       }

       private int deleteTopSboard(String[] topids) {
         int intRet = 0;
         String pks = this.arrayToString(topids);
         String[] sqls = new String[4];
         String sql = "delete from do_bbs_topiclist where topic_num in( select serial_num from do_bbs_topic where sboard_serial in ( select serial_num from do_bbs_sboard where tboard_serial in(" +
             pks + ")  ) )";
         sqls[0] = sql;
         sql = "delete from do_bbs_topic where sboard_serial in ( select serial_num from do_bbs_sboard where tboard_serial in(" +
             pks + ")  ) ";
         sqls[1] = sql;
         sql = "delete from do_bbs_sboard where tboard_serial in(" + pks + ") ";
         sqls[2] = sql;
         sql = "delete from do_bbs_topboard where serial_num in(" + pks + ")";
         sqls[3] = sql;
         this.excuteBatch(sqls);
         return intRet;
       }

     //返回形如 '123','456' 的字符串
       private String arrayToString(String[] array) {
         String retStr = StringUtils.arrayToString(array, "','");
         return retStr = "'" + retStr + "'";
       }

       private void excuteBatch(String[] sqls) {
         SQLHelper helper = new DefaultSQLHelper(dbData);
         Connection conn = null;
         Statement stmn = null;
         try {
           helper.begin();
           conn = helper.getConnection();
           stmn=conn.createStatement();
           for (int i = 0; i < sqls.length; i++) {
            // System.out.println(i+"+++++++++++++++++++++++++++++"+sqls[i]);
             stmn.addBatch(sqls[i]);
           }
           stmn.executeBatch();
           helper.commit();
         }
         catch (Exception e) {
           helper.rollback();
           e.printStackTrace();
         }
         finally {
           try {
             if (conn != null) {
               conn.close();
             }
           }
           catch (Exception e) {
             e.printStackTrace();
           }
         }
       }

}
