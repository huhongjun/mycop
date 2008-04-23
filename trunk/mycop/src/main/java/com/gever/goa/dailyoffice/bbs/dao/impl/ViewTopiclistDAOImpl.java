package com.gever.goa.dailyoffice.bbs.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.ViewTopiclistDAO;
import com.gever.jdbc.BaseDAO;
import com.gever.vo.VOInterface;

public class ViewTopiclistDAOImpl extends BaseDAO implements ViewTopiclistDAO {
    private static  String Query_SQL =
            "SELECT serial_num,bbs_user_code,content,ip_address,file_path,file_name,reply_date,nickname,user_icon,last_log_time,user_code,user_state,sex_code,topic_num FROM view_bbs_topiclist WHERE 1=1 ";

    public ViewTopiclistDAOImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return Query_SQL;
    }
    public VOInterface queryByPK(VOInterface vo) throws DefaultException{
           return (VOInterface)super.queryByPk(vo);
       }

}
