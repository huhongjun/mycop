package  com.gever.goa.user.dao.impl;

import java.util.*;
import com.gever.exception.DefaultException;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.BaseDAO;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

import com.gever.goa.user.vo.UserVO;
import com.gever.goa.user.vo.UserTreeVO;
import com.gever.goa.user.dao.UserDao;

/**
 * Comment block is generated by javaClassComment.vsl
 *
 * <p>Title: </p>
 * <p>Description: TUser ??</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ${config.company}</p>
 * @Author:		 ${config.author}
 * @Version: ${config.version}
 *
 */
public class UserDaoImpl extends BaseDAO implements UserDao {
    private static final String QUERY_SQL = 
         "SELECT id,name,email from qiqu_user where 1=1 ";
    private static String TREE_SQL = 
    		     " Select id as nodeid,name as nodename,case when (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE (T_DEPARTMENT.id>b.id or b.id>T_DEPARTMENT.id) AND b.parent_id=T_DEPARTMENT.id  ) >0 then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";

    public UserDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    /**
     * @return 
     * @throws DefaultException
     */
    public List getTreeData()throws DefaultException{
        List treeData = new ArrayList();
        UserTreeVO vo = new UserTreeVO();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL, vo);
        return treeData;
    }

	    //				获取nodeid的子节点，当nodeid为空时默认为顶级节点
    public List getTreeData(String nodeid) throws DefaultException {
        System.out.println("----------getTreeDate In Impl-----------------");
        List treeData = new ArrayList();
        String strWhere = "";
        //nodeid为null，当成顶级的节点
        if ( StringUtils.isNull(nodeid)) { 		nodeid = "0"; } 

        strWhere = " and parent_id =" + nodeid; 
        UserTreeVO vo = new UserTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData);
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
        return treeData;
    }
        
  public int insert(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    return super.insert(vo);
  }

  private boolean isRepeat(com.gever.goa.user.vo.UserVO vo) throws
      DefaultException {
    String sql = QUERY_SQL + " and id = "+ vo.getValue("id") ;
    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    boolean bRet = false;
    Map map = (HashMap) helper.query(sql,com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
    if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {
      bRet = true;

    }
    return bRet;

  }    
}