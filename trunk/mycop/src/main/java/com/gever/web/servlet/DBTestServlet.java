package com.gever.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import com.gever.jdbc.connection.*;

/**
 * 用JDBC API执行sql语句
 *
 *	url参数对：
 *		source		数据源
 *		SQL			sql语句
 *	输出：
 *		HTML页面，显示sql语句执行的结果集
 */
public class DBTestServlet extends javax.servlet.http.HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=GBK";

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		java.sql.ResultSet rs = null;
		Connection conn = null;
		PrintWriter out = response.getWriter();
		
		String dbSource = request.getParameter("source");
		String sqlStr = request.getParameter("SQL");
		try {
			ConnectionProvider cp = ConnectionProviderFactory
					.getConnectionProvider(dbSource);
			conn = cp.getConnection();
			java.sql.Statement stat = conn.createStatement();

			rs = stat.executeQuery(sqlStr);

			out.println("<html>");
			out.println("<head><title>DBTestServlet 数据库测试执行结果</title></head>");
			out.println("<body bgcolor=\"#ffffff\">");
			out.println("<p>The connenction pool servlet has received a GET. This is the reply.</p>");
			java.sql.ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			out.println("<table>");
			out.println("<tr>");
			for (int i = 0; i < colCount; i++) {
				out.println("<td>");
				out.println(md.getColumnName(i));
				out.print("</td>");
			}
			out.println("</tr>");
			while (rs.next()) {
				out.println("<tr>");
				for (int i = 0; i < colCount; i++) {
					out.println("<td>");
					out.println(rs.getString(i + 1));
					out.print("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body></html>");
		} catch (Exception e) {
			out.print(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				rs = null;
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
	}

	// Clean up resources
	public void destroy() {
	}

}