package com.gever.goa.dailyoffice.bbs.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSTopicDAO;
import com.gever.goa.dailyoffice.bbs.vo.TopicVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BBSTopicDAOImpl extends BaseDAO implements BBSTopicDAO{
    private  static String QUERY_SQL=
            "SELECT serial_num,sboard_serial,topic,bbs_user_code,topic_type,topic_user,appear_date,topic_order,hit_times,isblock FROM do_bbs_topic WHERE 1=1 ";
    private String UPDATE_SQL=" UPDATE DO_BBS_TOPIC SET hit_times = hit_times+1 where 1=1 ";
    private String UPDATE_USER_SQL=" UPDATE DO_BBS_TOPIC SET ";
    public BBSTopicDAOImpl(String dbData) {
        super(dbData);
    }
    public String getPageSql(){
        return QUERY_SQL;
    }
    /**
     *
     * 添加人气
     * @param vo VOInterface
     * @throws DefaultException
     * @return int
     */
    public int addHitTimes(VOInterface vo) throws DefaultException{
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql="UPDATE "+vo.getTableName()
                +" SET hit_times =hit_times+1 where serial_num='"+((TopicVO)vo).getSerial_num()+"'";

        return helper.execUpdate(sql);
    }

    public int updateTopicUser(VOInterface vo)throws DefaultException{
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql=UPDATE_USER_SQL;
        sql=sql+" topic_user = '" + ((TopicVO)vo).getTopic_user() + "' where serial_num='" + "'";

        return helper.execUpdate(sql);

    }

    public VOInterface queryByPK(VOInterface vo) throws DefaultException{
        return (VOInterface)super.queryByPk(vo);
    }
}
