package org.sakaiproject.topicstore.tool;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.server.RemoteCall;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.tool.api.Session;
import org.sakaiproject.tool.cover.SessionManager;
import org.sakaiproject.topicstore.tool.util.DBLocalConnection;
import org.sakaiproject.topicstore.tool.util.SelectDB;

import sun.rmi.server.Dispatcher;

public class TopicStoreAction extends HttpServlet {

	/**
	 * �õ�����
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) {

		doGet(req, res);

	}

	/**
	 * ��������
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			dispatch(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ת��url
	 * 
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	protected void dispatch(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException {
		try {
			Session s = SessionManager.getCurrentSession();
			SelectDB sdb = new SelectDB();
			
			String toolids = getToolid(req.getContextPath());
			String siteid = sdb.getSiteId(toolids);
			String title = sdb.getSiteTitle(siteid);
		
			boolean flag = synchronzingExam(siteid, title,sdb);
			if(flag){
				synchronzingExam(siteid, title,sdb);
			}
			
//			String eid = s.getUserEid();
//			String[] str= sdb.getStatus(eid);
//			String status = str[1];
//			String param = "&logintype=zhj&eid="+eid+"&stieid="+siteid+"&status="+status;
			
			//int couroseId = SelectDB.getCouroseId(siteid);
			
//			String forwardurl = getForwardUrl();
//			
//			if(null!=forwardurl){
//				forwardurl = "/login/link.jsp?"+forwardurl+param;
//				res.sendRedirect(forwardurl);
//			}
			return;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} 
	}
	
	public String getToolid(String contextpath) {
		String[] uris = null;
		String toolid = null;
		if (null != contextpath) {
			uris = contextpath.split("/");
			toolid = uris[3];
		}
		return toolid == null ? null : toolid;
	}
	
	public boolean synchronzingExam(String siteid,String title,SelectDB sdb){
		boolean lean= sdb.synchronzingExam(siteid,title);
		if(!lean){
			lean = sdb.updateExam(siteid);
		}
		return lean;
	}
	
	
	public String getForwardUrl() {
		String forwardurl = null;
		Properties m_props = DBLocalConnection
				.getProperties("linkcon.properties");
		String host = getHost(m_props);
		host = host.replace(".", "ab");
		String porst = getPorst(m_props);
		String forward = getForward(m_props);
		String[] st = forward.split("/");
		StringBuffer sbf = new StringBuffer();
		int len = st.length;
		for (int i = 0; i < len; i++) {
			if (!st[i].endsWith(".jsp")) {
				sbf.append(st[i]);
				sbf.append("78");
			} else {
				sbf.append(st[i]);
			}
		}
		forward = sbf.toString();

		forwardurl = "host=" + host + "&porst=" + porst + "&forward=" + forward;

		return forwardurl == null ? null : forwardurl;
	}

	public String getHost(Properties m_props) {
		String host = null;
		if (null != m_props) {
			host = (String) m_props.get("HOST");
		}
		return host == null ? null : host;
	}

	public String getPorst(Properties m_props) {
		String post = null;
		if (null != m_props) {
			post = (String) m_props.get("PORST");
		}
		return post == null ? null : post;
	}

	public String getForward(Properties m_props) {
		String forward = null;
		if (null != m_props) {
			forward = (String) m_props.get("FORWARD");
		}
		return forward == null ? null : forward;
	}
	
}
