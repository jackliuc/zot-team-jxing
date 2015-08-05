/**
 * 
 */
package com.zot.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jack
 *
 */
public interface ResultSetHandler<T> {
	List<T> rsHandler(ResultSet rs) throws SQLException;
}
