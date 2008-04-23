package com.gever.goa.dailyoffice.worklog.dao;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.vo.WorkLogVO;
import com.gever.util.Codes;
import com.gever.util.InitTestUtil;
import junit.framework.TestCase;

public class WorkLogDaoTest extends TestCase {

	private WorkLogDao logDAO;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(WorkLogDaoTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		InitTestUtil.init();		
		logDAO = WorkLogFactory.getInstance().createWorkLog("unittest");
		//logDAO = WorkLogFactory.getInstance().createIBatisWorkLog("unittest");				
	}

	public void testGetPageSql() {
		// TODO Auto-generated method stub

	}

	public void testInsert() throws DefaultException {
		
		WorkLogVO workLog = new WorkLogVO();
		workLog.setCreate_time("2006-7-21 18:18:18");
		workLog.setDept_code("0201");
		workLog.setUser_code("1");
		workLog.setFill_date("2006-7-21");
		workLog.setLog_content(
				Codes.decode("Tester：我的测试".toCharArray()));
		int ret;
		ret = logDAO.insert(workLog);
		assertTrue("新增一条记录发挥结果应为1",ret==1);
	}

	
	public void testUpdateLogContent() throws Exception {
		String tserial_name = "2006072022465314";
		String log_content = "最快进步奖";
		String logGrjb = "谢谢";
		
		int ret = logDAO.updateLogContent(tserial_name,log_content,logGrjb);
		
		assertEquals(ret,0);		
	}
	

	public void testGetCurDayWorkLog() throws Exception {
		WorkLogVO wlog;
		String userID,curDate;
		userID = "1";
		curDate= "2006-7-20";
		wlog = (WorkLogVO)logDAO.getCurDayWorkLog(userID,curDate);
		//assertEquals("查询日志:["+wlog.getSerial_num()+"]",wlog.getSerial_num(),"2006072022465314");
	}

	public void testGetSerialNumByUseridAndCurdate() throws Exception {
		String userID,curDate;
		userID = "1";
		curDate= "2006-7-20";
		String serialNum = logDAO.getSerialNumByUseridAndCurdate(userID,curDate);
		//assertEquals("查询日志:["+serialNum+"]",serialNum,"2006072022465314");
		assertEquals("查询日志:["+serialNum+"]",serialNum, null);
	}
	
	public void testGetLogTotalStrByUseridAndCurdate() throws Exception {
		String logContent;
		String userID,curDate;
		userID = "1";
		curDate= "2006-7-20";
		logContent = logDAO.getLogTotalStrByUseridAndCurdate(userID,curDate);
		//assertTrue("查询日志:userID=["+userID+"],curDate:["+curDate+"]",logContent.length()>0);
		//System.out.println(logContent);
	}

}
