/*
 * 创建日期 2004-11-8
 */
package com.gever.sysman.log.dao.impl.oracle;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.sysman.log.dao.LogDaoImpl;
import com.gever.util.NumberUtil;
import com.gever.vo.VOInterface;

/**
 * @author Hu.Walker
 */
public class OracleLogDaoImpl extends LogDaoImpl {

    /* （非 Javadoc）
     * @see com.gever.sysman.log.dao.LogDao#queryByCount(com.gever.vo.VOInterface)
     */
    public long queryByCount(VOInterface searchVo) {

        StringBuffer sbSql = new StringBuffer();
        long lngCnt = 0;
        try {
            sbSql.append("select count(*) as cnt from (");
            sbSql.append(QUERY_SQL).append(getSqlWhere(searchVo));
            sbSql.append(") countTable ");
            
            Map map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
            lngCnt = NumberUtil.stringToLong( (String) map.get("cnt"), 0);

        } catch (DefaultException e) {
           System.err.println(e.getMessage());
        }
        return lngCnt;

    }

}
