package com.gever.goa.dailyoffice.bbs.dao.impl;

import com.gever.goa.dailyoffice.bbs.dao.BBSTopicListDAO;
import com.gever.jdbc.BaseDAO;
/**
 * <p>Title: BBS 主题列表</p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BBSTopicListDAOImpl extends BaseDAO implements BBSTopicListDAO{
    private  static String QUERY_SQL=
            "SELECT serial_num,topic_num,bbs_user_code,content,ip_address,file_path,file_name,reply_date,awoke_flag FROM do_bbs_topiclist WHERE 1=1 ";

    public BBSTopicListDAOImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql(){
        return QUERY_SQL;
    }

}
