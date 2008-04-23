package com.gever.goa.infoservice.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.BaseTreeVO;

import junit.framework.TestCase;

public class IsInfoManageDaoTest extends TestCase{

	private IsInfoManageDao testDAO;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(IsInfoManageDaoTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		testDAO = IsCustomerFactory.getInstance().createIsInfoManage("unittest");
	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.insert(VOInterface)'
	 */
	public void testInsert() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.update(VOInterface)'
	 */
	public void testUpdate() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.queryAll(VOInterface)'
	 */
	public void testQueryAll() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.queryByModuleFlag(String, VOInterface)'
	 */
	public void testQueryByModuleFlag() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.queryByPage(VOInterface, long, long)'
	 */
	public void testQueryByPage() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.queryByCount(VOInterface)'
	 */
	public void testQueryByCount() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.infoservice.dao.IsInfoManageDao.getTreeData(String, String)'
	 */
	public void testGetTreeData() throws DefaultException {
		String paraFlag = "0";
		String nodeID = "";
		List info;
		info = testDAO.getTreeData(paraFlag,nodeID);
		assertTrue(info.size()>0);

		paraFlag = "7";
		nodeID = "PXZL";
		info = testDAO.getTreeData(paraFlag,nodeID);
		assertTrue("ÅàÑµ×ÊÁÏ",info.size()>0);
		
		List treeData = info;
		String context ="";
		StringBuffer sb = new StringBuffer();
        for (int idx = 0; idx < treeData.size(); idx++) {
            BaseTreeVO vo = new BaseTreeVO();
            vo = (BaseTreeVO) treeData.get(idx);
            if ("1".equals(vo.getIsfolder())) {
                sb.append("<tree nodeid =\"").append(vo.getNodeid());
                sb.append("\" text=\"").append(vo.getNodename());
                sb.append("\" src=\"").append(context);
                sb.append(vo.getSrc()).append("\"");
				String action=vo.getAction();
				if(action!=null && !action.equals("")){
				 sb.append(" action=\"").append(context);
                 sb.append(action).append("\"");	
				}else{
					sb.append(" action=\"\"");
				}
                sb.append(" isFolder=\"true\"  target=\"middle\" />");
            } else {
                sb.append("<tree  nodeid =\"").append(vo.getNodeid());
                sb.append("\" text=\"").append(vo.getNodename());
                sb.append("\" src=\"").append(context);
                sb.append(vo.getSrc()).append("\"");
                String action=vo.getAction();
				if(action!=null && !action.equals("")){
				 sb.append(" action=\"").append(context);
                 sb.append(action).append("\"");	
				}else{
					sb.append(" action=\"\" ");
				}
                sb.append(" target=\"middle\" isFolder=\"false\" />");

            }
        }
	}
}
