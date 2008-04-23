package com.gever.goa.dailyoffice.smsmgr.dao.impl;

import com.gever.goa.dailyoffice.message.dao.impl.MessageDaoImpl;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsInBoxDAO;
import com.gever.web.util.ActiveUsers;

/**
 * <p>Title:手机短信收件箱 </p>
 * <p>Description: 手机短信收件箱</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsInBoxDAOImpl extends MessageDaoImpl implements SmsInBoxDAO{
    private static StringBuffer QUERY_SQL = new StringBuffer(20);
    static{
        QUERY_SQL.append("SELECT SERIAL_NUM,USER_CODE ,receiver,SEND_TIME,CONTENT, \n");
        QUERY_SQL.append("    READ_FLAG,READ_TIME,WEB_URL,MSG_TYPE,MEMO ,\n");
        QUERY_SQL.append("    bb.user_name from DO_INNER_MSG aa left join (\n");
        QUERY_SQL.append("        Select b.name as user_name ,a.mobile from HR_ARCHIVE a \n");
        QUERY_SQL.append("            inner join t_user b on b.code = a.employee_code \n");
        QUERY_SQL.append("        where length(a.mobile)>5 \n");
        QUERY_SQL.append("        union all \n");
        QUERY_SQL.append("        Select a.customer as user_name, a.mobile \n");
        QUERY_SQL.append("        from DO_CARDCASE a inner join \n");
        QUERY_SQL.append("        (select b.user_code,b.type_name,b.type_code,t_user.name \n");
        QUERY_SQL.append("         from DO_CARDCASE_type b \n");
        QUERY_SQL.append("         inner join t_user on t_user.id =b.user_code ) c \n");
        QUERY_SQL.append("         on a.type_code = c.type_code where 1=1 and length(a.mobile)>5 \n");
        QUERY_SQL.append("        ) bb on aa.user_code = bb.mobile where msg_type= '2'\n");
    }

    public SmsInBoxDAOImpl(String dbData) {
        super(dbData);
    }
    ActiveUsers au = ActiveUsers.getInstance();
    public void setOracleSQL(){
        QUERY_SQL.setLength(0);
        QUERY_SQL.append("  SELECT SERIAL_NUM,USER_CODE ,receiver,SEND_TIME,CONTENT, ");
        QUERY_SQL.append("          READ_FLAG,READ_TIME,WEB_URL,MSG_TYPE,MEMO , ");
        QUERY_SQL.append("           bb.user_name from DO_INNER_MSG aa , ( ");
        QUERY_SQL.append("       Select b.name as user_name ,a.mobile from HR_ARCHIVE a  ");
        QUERY_SQL.append("            , t_user b  WHERE b.code = a.employee_code  AND  ");
        QUERY_SQL.append("          length(a.mobile)>5  ");
        QUERY_SQL.append("   union all  ");
        QUERY_SQL.append("   Select a.customer as user_name, a.mobile  ");
        QUERY_SQL.append("    from DO_CARDCASE a , ");
        QUERY_SQL.append("         (select b.user_code,b.type_name,b.type_code,t_user.name  ");
        QUERY_SQL.append("          from DO_CARDCASE_type b  ");
        QUERY_SQL.append("        , t_user where t_user.id =b.user_code ) c  ");
        QUERY_SQL.append("         where a.type_code = c.type_code  and length(a.mobile)>5  ");
        QUERY_SQL.append("      ) bb where aa.user_code = bb.mobile and  msg_type= '2' ");

    }

    public String getPageSql() {

        if (au.isOracle())
            setOracleSQL();
        return QUERY_SQL.toString();
    }

}