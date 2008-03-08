package org.sakaiproject.topicstore.tool.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class SelectDB {

	public List moduleidList, sectionidList;

	/**
	 * ͨ��eid��user_status�в�status
	 * 
	 * @param conn
	 * @param eid
	 * @return
	 */
	public String[] getStatus(String eid) {
		Connection conn = new DBLocalConnection().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT real_name,status FROM USER_STATUS where eid = ?";
		String[] str = new String[2];
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, eid);

			rs = ps.executeQuery();
			while (rs.next()) {
				str[0] = rs.getString(1);
				str[1] = String.valueOf(rs.getInt(2));
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				DBLocalConnection.closeConnection(conn);
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return str == null ? null : str;
	}

	/**
	 * ͨ��tool_id �� sakai_site_tool �� site_id
	 * 
	 * @param conn
	 * @param toolid
	 * @return
	 */
	public String getSiteId(String toolid) {
		Connection conn = new DBLocalConnection().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT site_id FROM SAKAI_SITE_TOOL where tool_id = ?";
		String siteId = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, toolid);
			rs = ps.executeQuery();
			while (rs.next()) {
				siteId = rs.getString(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				DBLocalConnection.closeConnection(conn);
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return siteId == null ? null : siteId;
	}

	public String getSiteTitle(String siteid) {
		Connection conn = new DBLocalConnection().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT title FROM SAKAI_SITE where site_id = ?";
		String title = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, siteid);
			rs = ps.executeQuery();
			while (rs.next()) {
				title = rs.getString(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				DBLocalConnection.closeConnection(conn);
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return title == null ? null : title;
	}

	public boolean synchronzingExam(String siteid, String stitle) {
		Connection conn = new DBLocalConnection().getConnection();
		Connection remoteconn = new DBRemoteConnection().getConnection();

		UuidComponent uuid = new UuidComponent();
		boolean bflag = false;

		moduleidList = new ArrayList();
		sectionidList = new ArrayList();

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		ResultSet module = null;
		ResultSet section = null;

		try {
			remoteconn.setAutoCommit(false);
			String guid = null;
			String cid = null;
			cid = getZjCourseId(siteid);
			if (null == cid) {
				guid = uuid.createUuid();
				cid = guid.replaceAll("-", "");

				bflag = insertZjCourse(cid, siteid, stitle, "0", cid,
						remoteconn);
				if (bflag) {
					remoteconn.rollback();
					return bflag;
				}
			}

			String sql = "select mm.module_id,mm.title,mcm.seq_no,mm.seq_xml"
					+ " from MELETE_COURSE_MODULE mcm,MELETE_MODULE mm"
					+ " where mcm.module_id = mm.module_id and mcm.ARCHV_FLAG=0 and mcm.DELETE_FLAG=0 and mcm.course_id=?"
					+ " order by mm.module_id asc";

			ps1 = conn.prepareStatement(sql);
			ps1.setString(1, siteid);
			module = ps1.executeQuery();

			while (module.next()) {
				String mid = null;
				String moduelid = siteid + String.valueOf(module.getInt(1));
				moduleidList.add(moduelid);
				mid = getZjCourseId(moduelid);
				if (null == mid) {
					guid = uuid.createUuid();
					mid = guid.replaceAll("-", "");

					bflag = insertZjCourse(mid, moduelid, module.getString(2),
							"1", cid, remoteconn);
					if (bflag) {
						remoteconn.rollback();
						return bflag;
					}
				} else {
					updateZjQuestion(moduelid, "title", module.getString(2),
							remoteconn);
				}
				if (null != module.getString(4)) {
					List sectionidlist = XmlDealUtil
							.getIds(module.getString(4));
					int len = sectionidlist.size();
					for (int i = 0; i < len; i++) {
						String sid = null;
						String sectionid = sectionidlist.get(i).toString();
						String sectionids = siteid + sectionid;
						sectionidList.add(sectionids);
						sid = getZjCourseId(sectionids);
						sql = "select title from MELETE_SECTION where section_id =? ";
						ps2 = conn.prepareStatement(sql);
						ps2.setInt(1, Integer.parseInt(sectionid));
						section = ps2.executeQuery();

						while (section.next()) {
							if (null == sid) {
								guid = uuid.createUuid();
								sid = guid.replaceAll("-", "");

								bflag = insertZjCourse(sid, sectionids, section
										.getString(1), "2", mid, remoteconn);
								if (bflag) {
									remoteconn.rollback();
									return bflag;
								}
							} else {
								updateZjQuestion(sectionids, "title", section
										.getString(1), remoteconn);
							}
						}
					}
				}
			}
			remoteconn.commit();
		} catch (Exception ex) {
			try {
				remoteconn.rollback();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		} finally {
			try {
				if (null != ps1) {
					ps1.close();
				}
				if (null != ps2) {
					ps2.close();
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
			DBLocalConnection.closeConnection(conn);
			DBRemoteConnection.closeConnection(remoteconn);
		}
		return bflag;
	}

	public boolean updateExam(String siteid) {
		Connection remoteconn = new DBRemoteConnection().getConnection();
		boolean bflag = false;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		ResultSet module = null;
		ResultSet section = null;
		String sql = "select zj.id,zj.origin_id from ZJ_COURSE zj where zj.item_type='1' and zj.delflag='0' "
				+ "and zj.parent=(select zjc.id from ZJ_COURSE zjc where zjc.origin_id=?)";
		try {
			remoteconn.setAutoCommit(false);
			ps1 = remoteconn.prepareStatement(sql);
			ps1.setString(1, siteid);
			module = ps1.executeQuery();
			String originid = null;
			String id = null;

			while (module.next()) {
				id = module.getString(1);
				originid = module.getString(2);
				boolean lean = moduleidList.contains(originid);
				if (!lean) {
					bflag = updateZjCourse(id, "1",remoteconn);
					if (bflag) {
						remoteconn.rollback();
						return bflag;
					}
					// bflag = updateZjQuestion(id, "1", null, remoteconn);
					// if (bflag) {
					// remoteconn.rollback();
					// return bflag;
					// }
				} else {
					sql = "select zj.id,zj.origin_id from ZJ_COURSE zj where zj.item_type='2' and zj.delflag='0' and parent=?";
					ps2 = remoteconn.prepareStatement(sql);
					ps2.setString(1, id);
					section = ps2.executeQuery();
					while (section.next()) {
						originid = section.getString(2);
						lean = sectionidList.contains(originid);
						if (!lean) {
							id = section.getString(1);
							bflag = updateZjCourse(id, null,remoteconn);
							if (bflag) {
								remoteconn.rollback();
								return bflag;
							}
							// bflag = updateZjQuestion(id, "2", null,
							// remoteconn);
							// if (bflag) {
							// remoteconn.rollback();
							// return bflag;
							// }
						}
					}
				}
			}
			remoteconn.commit();
		} catch (SQLException ex) {
			try {
				remoteconn.rollback();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		} finally {
			try {
				if (null != module) {
					module.close();
				}
				if (null != section) {
					section.close();
				}
				if (null != ps1) {
					ps1.close();
				}
				if (null != ps2) {
					ps2.close();
				}
				DBRemoteConnection.closeConnection(remoteconn);
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return bflag;
	}

	public boolean updateZjCourse(String id, String flag,Connection remoteconn) {
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		boolean lean = false;
		String sql = "update ZJ_COURSE set delflag = '1' where id=?";
		try {
			ps1 = remoteconn.prepareStatement(sql);
			ps1.setString(1, id);
			lean = ps1.execute();
			if("1".equals(flag)){
				sql="update ZJ_COURSE set delflag = '1' where parent=?"; 
				ps2 = remoteconn.prepareStatement(sql);
				ps2.setString(1, id);
				lean = ps2.execute();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != ps1) {
					ps1.close();
				}
				if (null != ps2) {
					ps2.close();
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return lean;
	}

	public boolean updateZjQuestion(String id, String flag, String title,
			Connection remoteconn) {
		PreparedStatement ps1 = null;
		boolean lean = false;
		String sql = null;
		if ("1".equals(flag)) {
			sql = "update ZJ_QUESTION set delflag = '1' where chapter=?";
		}
		if ("2".equals(flag)) {
			sql = "update ZJ_QUESTION set delflag = '1' where section=?";
		}
		if ("title".equals(flag)) {
			sql = "update ZJ_COURSE set title ='" + title
					+ "' where origin_id=?";
		}
		try {
			ps1 = remoteconn.prepareStatement(sql);
			ps1.setString(1, id);
			lean = ps1.execute();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != ps1) {
					ps1.close();
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return lean;
	}

	public String getZjCourseId(String originid) {
		Connection remoteconn = new DBRemoteConnection().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT ID FROM ZJ_COURSE where origin_id = ?";
		String id = null;
		try {
			ps = remoteconn.prepareStatement(sql);
			ps.setString(1, originid);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				DBRemoteConnection.closeConnection(remoteconn);
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return id == null ? null : id;
	}

	public boolean insertZjCourse(String id, String originid, String title,
			String itemtype, String parent, Connection remoteconn) {
		boolean lean = false;

		PreparedStatement ps = null;
		String sql = "insert into ZJ_COURSE(ID,origin_id,title,item_type,parent) values(?,?,?,?,?)";
		try {
			ps = remoteconn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, originid);
			ps.setString(3, title);
			ps.setString(4, itemtype);
			ps.setString(5, parent);
			lean = ps.execute();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != ps) {
					ps.close();
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return lean;
	}

	/**
	 * select couresId from site_subject
	 * 
	 * @param conn
	 * @param siteid
	 * @return
	 */
	public static int getCouroseId(String siteid) {
		Connection conn = new DBLocalConnection().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT distinct s.course_id FROM SITE_SUBJECT s where s.site_id= ?";
		int couresId = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, siteid);
			rs = ps.executeQuery();
			while (rs.next()) {
				couresId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				DBLocalConnection.closeConnection(conn);
			} catch (SQLException ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return couresId == 0 ? 0 : couresId;
	}

}
