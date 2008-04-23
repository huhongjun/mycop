package com.gever.goa.dailyoffice.tools.dao.impl;

import java.util.List;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowTypeDao;
import com.gever.goa.dailyoffice.tools.vo.WorldwindowTreeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;


/**����֮��ģ��
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class WorldwindowTypeDaoImpl extends BaseDAO
    implements WorldwindowTypeDao {
    private String QUERY_SQL = "Select info_type,type_name from do_info_type where 1=1 ";
    private String TREE_SQL = "SELECT info_type as nodeid,type_name as nodename,'1' as isfolder  from do_info_type where 1=1";

    public WorldwindowTypeDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return this.QUERY_SQL;
    }

    /**
     * ����
     * @param vo ��ǰvo����
     * @return �������ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int insert(VOInterface vo) throws DefaultException {
        return super.insert(vo);
    }

    /**
     * �������Ϣ
     * @throws DefaultException
     * @return List
     */
    public List getTreeData() throws DefaultException {
        WorldwindowTreeVO vo = new WorldwindowTreeVO();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String sql = this.TREE_SQL;

        return helper.queryByListVo(sql, vo);
    }

    /**
     * queryByPK
     *
     * @param vo VOInterface
     * @return Object
     */
    public Object queryByPK(VOInterface vo) throws DefaultException {
        return super.queryByPk(vo);
    }
    public int delBySelect(String[] aryPk, VOInterface vo) throws
        DefaultException {
        int intRet = 0;
        SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;

        try {
            helper.begin();
            String name = "";
            String value = "";
            for (int idx = 0; idx < aryPk.length; idx++) {
                vo = getInstanceVO(vo);
                StringTokenizer stk = new StringTokenizer(aryPk[idx], "::");
                StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(),",");
                while (stk.hasMoreTokens()) {
                    name = stkPk.nextToken();
                    value = stk.nextToken();
                    vo.setValue(name,value);
                }
                intRet += helper.delete(vo);
            }
            helper.commit();
        } catch (DefaultException e) {
            intRet = -1;
            helper.rollback();
            throw new DefaultException("PK");

        } finally {
            helper.end();
        }
        return intRet;

    }

}
