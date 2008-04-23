package com.gever.goa.dailyoffice.calendararrange.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarRightVO;
import com.gever.sysman.organization.model.Department;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public interface CalendarArrangeDao {
  public int delBySelect(String[] aryPk, VOInterface vo)throws DefaultException;
  public String getPageSql();
  public int insert(VOInterface vo)throws DefaultException;
  public int update(VOInterface vo) throws DefaultException;
  public List queryAll(VOInterface vo)throws DefaultException;
  public List queryByPage(VOInterface vo, long start, long howMany)throws DefaultException;
  public long queryByCount(VOInterface vo)throws DefaultException;
  public int copy(String[] keyValue,VOInterface vo)throws DefaultException;
  public List queryAllInMonth(int year, int month,String userid) throws DefaultException;
  public List queryAllInWeek(int year, int month,int week,String userid) throws DefaultException;
  public List getTreeData(String nodedid)throws DefaultException;//获取树型结构列表
  public List getCalendarRightUsers(String userid) throws  DefaultException ;
  public List getUsersInDeptByRightType(Department dept, String righttype,String member) throws
      DefaultException ;
  public CalendarRightVO getCalendarRightByUser(String userid,String righttype) throws
      DefaultException;
       public List getTodayCalendarArrange(String userid)throws DefaultException;
}
