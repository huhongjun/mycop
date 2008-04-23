package com.gever.goa.dailyoffice.tools.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTreeVO;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

public class CardcaseTypeDaoImpl extends BaseDAO implements CardcaseTypeDao {
    
    private static final String TREE_PUB_NODEID="-1";//公共目录节点id
    
    private static String TREE_SQL = "SELECT type_code as nodeid,type_name as nodename,user_code as memo,'1' as isfolder  from do_cardcase_type where 1=1";
    private  static String  QUERY_SQL =
            " Select type_code,user_code,type_name,remark,subnum from do_cardcase_type " +
            " left join (select type_code as code,count(*)as subnum from do_cardcase group by type_code) as tmp on type_code=code " +
            " where 1=1 ";

    //gw add--queryByUser_sql
    private String QUERYBYUSER_SQL =
            " select type_code,type_name,remark from do_cardcase_type where 1=1";

    //gw add--existTypeName_sql
    private String EXISTTYPENAME_SQL =
            " select count(*) as cnt from do_cardcase_type where 1=1 ";

    public CardcaseTypeDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        //set where 设置条件 该条件只在查询时有效
        //super.setSqlWhere("user_code='" + super.userID + "'"); //设置条件
    // System.out.println("getPageSql======"+QUERY_SQL+QUERY_SQL.hashCode());
        return QUERY_SQL.toString();
    }

    /**
     * 按用户分类查询
     * @param vo VOInterface 包含user_code参数
     * @throws DefaultException
     * @return List
     */
    public List queryByUser(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        CardcaseTypeVO typevo = (CardcaseTypeVO) vo;
        String sql;

        sql = QUERYBYUSER_SQL + " and user_code=" + typevo.getUser_code();

        return (List) helper.query(sql, typevo, DataTypes.RS_LIST_VO);
    }

    /**
     * 获得树信息
     * @throws DefaultException
     * @return List
     */
    public List getTreeData() throws DefaultException {
    	List treeData = new ArrayList();
    	
        CardcaseTreeVO vo = new CardcaseTreeVO();
        //公共目录节点vo
        vo.setNodeid(TREE_PUB_NODEID);
        vo.setNodename("公共名片夹");
        vo.setOtherProperty(null);
        treeData.add(vo);
        
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String sql = TREE_SQL + " and user_code=" + super.userID;
		treeData.addAll( helper.queryByListVo(sql, vo) );
		
        return treeData;
    }

    public Object queryByPK(VOInterface vo) throws DefaultException {
        return super.queryByPk(vo);
    }

    public boolean existTypeName(String typeName, String userID) throws
            DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String sql = EXISTTYPENAME_SQL + " and type_name = '" + typeName +
                "' and user_code =" +
                userID;
        Map map = (Map) helper.query(sql, DataTypes.RS_SINGLE_MAP);
        int count = Integer.parseInt( (String) map.get("cnt"));

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    //
    public void setOracleSQL(){
        QUERY_SQL=" Select type_code,user_code,type_name,remark,subnum from do_cardcase_type, (select type_code as code,count(*)as subnum from do_cardcase group by type_code) tmp where  type_code=tmp.code(+) ";       System.out.println("===============================in child dao==="+QUERY_SQL+QUERY_SQL.hashCode());
    }

}
