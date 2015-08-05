/**
 * 
 */
package com.zot.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jack
 *
 */
public class JDBCTemplate<T> {

	/**
	 * 执行查询语句，返回的值通过ResultSetHandler处理
	 * 
	 * @param sql
	 * @param params
	 * @param rsh
	 * @return
	 */
	public List<T> query(String sql, List<Object> params, ResultSetHandler<T> rsh) {
		List<T> lst = null;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBDataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			for (Object object : params) {
				pstmt.setObject(++idx, object);
			}
			rs = pstmt.executeQuery();
			
			lst = rsh.rsHandler(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
				{
					rs.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

			}
		}
		
		return lst;
	}

	public int execute(String sql, List<Object> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBDataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			for (Object object : params) {
				pstmt.setObject(idx++, object);
			}

			return pstmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

		return -1;
	}
}
