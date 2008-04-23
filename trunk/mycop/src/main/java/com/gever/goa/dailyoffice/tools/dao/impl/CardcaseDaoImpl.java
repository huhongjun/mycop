package com.gever.goa.dailyoffice.tools.dao.impl;

import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.CardcaseDao;
import com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO;
import com.gever.goa.dailyoffice.tools.vo.CardcaseVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.IdMng;
import com.gever.vo.VOInterface;


public class CardcaseDaoImpl extends BaseDAO implements CardcaseDao {
    private static String QUERY_SQL = "SELECT serial_num,type_code,customer,nickname,duty,company,address,post_code,e_mail,phone,fax,mobile,home_page,remark from do_cardcase where 1=1 ";

    //gw add--gettypecode_sql
    private static String GETTYPECODE_SQL = " select type_code from do_cardcase_type where 1=1 ";

    //gw add--queryByUser_sql
    //    private String QUERYBYUSER_SQL = "";
    //gw add--getNameByEmail
    private static String GETNAMEBYEMAIL =
        " select customer as name from do_cardcase a ,do_cardcase_type b where a.type_code=b.type_code ";

    public CardcaseDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        //set where 设置条件 该条件只在查询时有效
        //super.setWhere("user_code='" + super.userID + "'");
        return QUERY_SQL;
    }

    /**
     * 新增
     * @param vo 当前vo对象
     * @return 新增多少笔,-1为失败
     * @throws DefaultException
     */
    private int insertOneVO(CardcaseVO vo, int i) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        ;

        if (isIdMng == true) {
            String pkName = vo.getPkFields();
            pkName = toPkFld(pkName);
            vo.setValue(pkName, IdMng.getModuleID(this.userID, i));
            log.showLog("pkname--->" + pkName);
            log.showLog("pkValue--->" + vo.getValue(pkName));
        }

        if (checkInsert(vo) != 1) {
            throw new DefaultException("goa_sys_001");
        }

        helper.setAutoID(false);

        int iRet = helper.insert(vo);

        return iRet;
    }

    /**
     * 按输入的包含用户姓名进行模糊查找。
     * 同时输入的条件还包括类别代码
     * @param vo VOInterface
     * @throws DefaultException
     * @return List
     */
    public List queryByCustomer(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql = QUERY_SQL;
        String customer = ((CardcaseVO) vo).getCustomer();

        if ((!"".equals(customer)) && (!"'".equals(customer))) {
            sql += (" and customer like '%" + customer + "%' ");
        }

        String type_code = ((CardcaseVO) vo).getType_code();

        if (!"".equals(type_code)) {
            sql = sql + " and type_code ='" + type_code + "'";
        }

        return helper.queryByListVo(sql, vo);
    }

    /**
     * 通过类别代码来搜索
     * @param vo VOInterface
     * @throws DefaultException
     * @return List 包含一组VO对象
     */
    public List queryByType(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql = QUERY_SQL;
        String type_code = ((CardcaseVO) vo).getType_code();

        if (!"".equals(type_code)) {
            sql = sql + " and type_code ='" + type_code + "'";
        }

        return helper.queryByListVo(sql, vo);
    }

    public boolean existTypeName(String typeName, String userID)
        throws DefaultException {
        CardcaseTypeDao typeDAO = new CardcaseTypeDaoImpl(dbData);

        return typeDAO.existTypeName(typeName, userID);
    }

    private String getTypecode(String typeName, String userID)
        throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql = GETTYPECODE_SQL + " and type_name='" + typeName +
            "' and user_code=" + userID;
        Map map = (Map) helper.query(sql, DataTypes.RS_SINGLE_MAP);
        String type_code = (String) map.get("type_code");

        if (type_code == null) {
            return "";
        }

        return type_code;
    }

    private void createType(String typeName, String userID)
        throws DefaultException {
        CardcaseTypeDao typeDAO = new CardcaseTypeDaoImpl(super.dbData);
        ((BaseDAO) typeDAO).setUserID(userID);

        CardcaseTypeVO vo = new CardcaseTypeVO();
        vo.setType_name(typeName);
        vo.setUser_code(userID);
        typeDAO.insert(vo);
    }

    public int insertBatch(List sameVO, String userID)
        throws DefaultException {
        return this.insertBatch(sameVO, userID, "其他");
    }

    /**
     *
     * @param sameVO List
     * @param userID String
     * @throws DefaultException
     * @return int
     */
    public int insertBatch(List sameVO, String userID, String typeName)
        throws DefaultException {
        super.setUserID(userID);

        String type_code = this.getTypecode(typeName, userID);

        if ("".equals(type_code)) {
            this.createType(typeName, userID);
            type_code = this.getTypecode(typeName, userID);
        }

        int i = 0;
        int size = sameVO.size();

        for (i = 0; i < size; i++) {
            CardcaseVO vo = (CardcaseVO) sameVO.get(i);
            vo.setType_code(type_code);
            this.insertOneVO(vo, i);
        }

        return i;
    }

    public List queryByUser(String userID) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql = QUERY_SQL +
            " and type_code in (select type_code from do_cardcase_type where user_code = " +
            userID + ")";

        return helper.queryByListVo(sql, new CardcaseVO());
    }

    public String getNameByEmail(String userID, String email)
        throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(super.dbData);
        String sql = GETNAMEBYEMAIL + " and user_code="+ userID + " and e_mail='" + email + "'";
        Map map = (Map) helper.query(sql, DataTypes.RS_SINGLE_MAP);
        String name = (String) map.get("name");

        if (name == null) {
            name = "";
        }

        return name;
    }
}
