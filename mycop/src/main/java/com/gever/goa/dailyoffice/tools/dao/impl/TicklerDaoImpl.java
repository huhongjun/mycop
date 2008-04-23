package com.gever.goa.dailyoffice.tools.dao.impl;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.TicklerDao;
import com.gever.goa.dailyoffice.tools.vo.TicklerVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;


public class TicklerDaoImpl extends BaseDAO implements TicklerDao {
    private static String QUERY_SQL = "SELECT serial_num,user_code,content,create_date,remind_flag,awoke_time,finish_flag FROM do_tickler where 1=1 ";

    public TicklerDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        //set where 设置条件 该条件只在查询时有效
        return QUERY_SQL;

        //+"' order by create_date desc";
    }

    /**
     * 新增
     * @param vo 当前vo对象
     * @return 新增多少笔,-1为失败
     * @throws DefaultException
     */
    public int insert(VOInterface vo) throws DefaultException {
        return super.insert(vo);
    }

    /**
     * 通过内容模糊查找
     * @param vo VOInterface
     * @throws DefaultException
     * @return List
     */
    public List queryByContent(VOInterface vo) throws DefaultException {
        SQLHelper dbHelper = new DefaultSQLHelper(super.dbData);
        String sql = QUERY_SQL;
        String content = ((TicklerVO) vo).getContent();

        if ((!"".equals(content)) && (!"'".equals(content))) {
            sql += (" and content like '%" + content + "%'");
        }

        String user_code = ((TicklerVO) vo).getUser_code();

        if (!"".equals(user_code)) {
            sql += (" and user_code =" + user_code);
        }

        return dbHelper.queryByListVo(sql, vo);
    }

    /**
     * 按日期精确查找
     * @param vo VOInterface
     * @throws DefaultException
     * @return List
     */
    public List queryByDate(VOInterface vo) throws DefaultException {
        SQLHelper dbHelper = new DefaultSQLHelper(super.dbData);
        String sql = QUERY_SQL+"and create_date =? and user_code =?";
        String create_date = ((TicklerVO) vo).getCreate_date();

//        if (!"".equals(create_date)) {
//            sql += (" and create_date =' " + create_date + "'");
//        }

        String user_code = ((TicklerVO) vo).getUser_code();

//        if (!"".equals(user_code)) {
//            sql += (" and user_code =" + user_code);
//        }
        String[] strsql = {
                sql,
                "date,int"
        };
        String[] values={
                create_date,
                user_code
        };
        dbHelper.query(strsql, values, vo, DataTypes.RS_LIST_VO);
        return dbHelper.queryByListVo(sql, vo);
    }
}
