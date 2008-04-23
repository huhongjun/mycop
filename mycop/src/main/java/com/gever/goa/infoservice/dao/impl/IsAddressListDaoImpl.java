package com.gever.goa.infoservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsAddressListDao;
import com.gever.goa.infoservice.vo.IsAddressListVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.IdMng;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 群组设置DAO实现类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsAddressListDaoImpl extends BaseDAO implements IsAddressListDao {
    //得到查询语句
    private static StringBuffer querySQL = new StringBuffer();

    static {
        querySQL.append(" SELECT ");
        querySQL.append(" IS_ADDRESS_LIST.SERIAL_NUM, ");
        querySQL.append(" IS_ADDRESS_LIST.TYPE_FLAG, ");
        querySQL.append(" IS_ADDRESS_LIST.GROUP_NAME, ");
        querySQL.append(" IS_ADDRESS_LIST.USER_CODE, ");
        querySQL.append(" IS_ADDRESS_LIST.MEMBER, ");
        querySQL.append(" IS_ADDRESS_LIST.GROUP_CODE, ");
        querySQL.append(" IS_ADDRESS_LIST.TABLE_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" IS_ADDRESS_LIST ");
        querySQL.append(" WHERE ");
        querySQL.append(" 1=1 ");
    }

    public IsAddressListDaoImpl() {
    }

    public IsAddressListDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * 连接oracle数据库SQL
     */
    public void setOracleSQL() {
    }

    public String getPageSql() {
        return querySQL.toString();
    }

    /**
     * 重载
     */
    public int insert(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsAddressListVO ialVO = (IsAddressListVO) vo;
        ialVO.setUser_code(super.userID);
        ialVO.setSerial_num(IdMng.getModuleID(this.userID));
        helper.setAutoID(false);

        int iRet = helper.insert(ialVO);

        return iRet;
    }

    public int update(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);

        if (checkUpdate(vo) != 1) {
            throw new DefaultException("goa_sys_002");
        }
        IsAddressListVO ialVO = (IsAddressListVO) vo;
        ialVO.setUser_code(super.userID);
        return helper.update(vo);
    }

    /**
     * 陈猛添加
     * 用于取得当前登录用户的群组
     * @param pkStr String
     * @throws DefaultException
     * @return List
     */
    public List queryAddressById(String pkStr) throws DefaultException {
        IsAddressListVO vo = new IsAddressListVO();
        List list = new ArrayList();
        this.setSqlWhere(" and type_flag=1 or USER_CODE=" + pkStr + "");
        list = this.queryAll(vo);

        return list;
    }

}
