import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.database.dialect.Global;

import junit.framework.TestCase;

/**
 * Title: Desc:相对独立得数据库测试，用于测试MySQL的中文支持和测试驱动程序；
 * 
 * @author Hu.Walker
 * 
 */
public class DBConnTest extends TestCase {

	public DBConnTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3308/test?user=root");
			Statement stmt = conn.createStatement();
			String sql = "insert into book(bookname) values ('Java中文')";
			stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery("select bookname from book");
			System.out.print("BookNames: ");
			while (rs.next())
				System.out.print(rs.getString("bookname") + ",");
			System.out.println();
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testChinese() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			ConnectionProvider cp = ConnectionProviderFactory
					.getConnectionProvider("unittest");
			conn = cp.getConnection();

			String sql = "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEVEL,STATUS,VALIDDATE,NAME,GENDER,CODE FROM T_USER order by username";
			sql = Global.getDialect().getLimitString(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, 30, 2,
					0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String s = new String(rs.getString("Name").getBytes(
						"ISO-8859-1"), "GB2312");
				System.out.println("Chinese Name: " + rs.getString("Name")
						+ ", " + "Convert(ISO-8859-1): " + s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
