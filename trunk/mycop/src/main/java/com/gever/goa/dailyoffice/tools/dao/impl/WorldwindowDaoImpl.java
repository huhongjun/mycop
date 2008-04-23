package com.gever.goa.dailyoffice.tools.dao.impl;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowDao;
import com.gever.goa.dailyoffice.tools.vo.WorldwindowVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;


/**世界之窗模块
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class WorldwindowDaoImpl extends BaseDAO implements WorldwindowDao {
    private String QUERY_SQL = "SELECT title,info_type,child_type,url from do_world_window where 1=1 ";

    //gw add--queryAllType_sql
    private String QUERYALLTYPE = " select distinct info_type from do_world_window";

    public WorldwindowDaoImpl(String dbData) {
        super(dbData);
    }

    public List queryAllType(VOInterface vo)
        throws DefaultException, DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        WorldwindowVO typevo = (WorldwindowVO) vo;
        String sql;

        sql = QUERYALLTYPE;

        return (List) helper.query(sql, typevo, DataTypes.RS_LIST_VO);
    }

    public String getPageSql() {
        return this.QUERY_SQL;
    }

    /**
    * 按主键查询
    * @param vo 当前vo对象
    * @return 查询的vo对象
    * @throws DefaultException
    */
    public Object queryByPk(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        vo.setOtherProperty(new String[0]);

        return helper.queryByPK(vo);
    }

    public int update(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        WorldwindowVO wwvo = (WorldwindowVO) vo;

        if (checkUpdate(wwvo) != 1) {
            throw new DefaultException("goa_sys_002");
        }

        String sql = "update " + wwvo.getTableName() + " set title='" +
            wwvo.getTitle() + "' ,info_type='" + wwvo.getInfo_type() +
            "',child_type='" + wwvo.getChild_type() + "',url='" +
            wwvo.getUrl() + "' where info_type='" + wwvo.getOld_info_type() +
            "' and title='" + wwvo.getOld_title() + "'";

        return helper.execUpdate(sql);
    }
}
