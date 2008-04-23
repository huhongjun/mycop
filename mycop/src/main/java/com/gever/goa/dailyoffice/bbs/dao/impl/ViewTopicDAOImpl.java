package com.gever.goa.dailyoffice.bbs.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.ViewTopicDAO;
import com.gever.jdbc.BaseDAO;
import com.gever.vo.VOInterface;

public class ViewTopicDAOImpl extends BaseDAO implements ViewTopicDAO{
    private static final String Query_SQL=
            "SELECT serial_num,topic,nickname,reply_cnt,hit_times,appear_date,reply_time,replyer,sboard_serial FROM view_bbs_topic WHERE 1=1 ";
    public ViewTopicDAOImpl(String dbData) {
        super(dbData);
    }
    public String getPageSql(){
        return Query_SQL;
    }
    public VOInterface queryByPK(VOInterface vo) throws DefaultException{
       return (VOInterface)super.queryByPk(vo);
   }

}
