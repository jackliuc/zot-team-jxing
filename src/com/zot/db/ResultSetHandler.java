/**
 * 
 */
package com.zot.db;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author jack
 *
 */
public abstract class ResultSetHandler<T> {
	public abstract List<T> rsHandler(ResultSet rs);
}
