/**
 * 
 */
package com.zot.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jack
 *
 */
public abstract class ResultSetHandler<T> 
{
	public abstract T rsHandler(ResultSet rs) throws SQLException;
}
