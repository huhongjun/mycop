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
				Codes.decode("Tester���ҵĲ���".toCharArray()));
		int ret;
		ret = logDAO.insert(workLog);
		assertTrue("����һ����¼���ӽ��ӦΪ1",ret==1);
	}

	
	public void testUpdateLogContent() throws Exception {
		String tserial_name = "2006072022465314";
		String log_content = "��������";
		String logGrjb = "лл";
		
		int ret = logDAO.updateLogContent(tserial_name,log_content,logGrjb);
		
		assertEquals(ret,0);		
	}
	

	public void testGetCurDayWorkLog() throws Exception {
		WorkLogVO wlog;
		String userID,curDate;
		userID = "1";
		curDate= "2006-7-20";
		wlog = (WorkLogVO)logDAO.getCurDayWorkLog(userID,curDate);
		//assertEquals("��ѯ��־:["+wlog.getSerial_num()+"]",wlog.getSerial_num(),"2006072022465314");
	}

	public void testGetSerialNumByUseridAndCurdate() throws Exception {
		String userID,curDate;
		userID = "1";
		curDate= "2006-7-20";
		String serialNum = logDAO.getSerialNumByUseridAndCurdate(userID,curDate);
		//assertEquals("��ѯ��־:["+serialNum+"]",serialNum,"2006072022465314");
		assertEquals("��ѯ��־:["+serialNum+"]",serialNum, null);
	}
	
	public void testGetLogTotalStrByUseridAndCurdate() throws Exception {
		String logContent;
		String userID,curDate;
		userID = "1";
		curDate= "2006-7-20";
		logContent = logDAO.getLogTotalStrByUseridAndCurdate(userID,curDate);
		//assertTrue("��ѯ��־:userID=["+userID+"],curDate:["+curDate+"]",logContent.length()>0);
		//System.out.println(logContent);
	}

}
