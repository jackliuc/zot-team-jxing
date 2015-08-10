/**
 * 
 */ 
package com.zot.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import com.zot.sys.config.SystemInit;

/**
 * @author jack
 *
 */
public class DBDataSource {

	private static DataSource dataSource = null;

	private static Lock lock = new ReentrantLock();

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			lock.lock();
			if (dataSource == null) {
				try {
					Class.forName(SystemInit.getDBConfig("db.driver"));
					ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
							SystemInit.getDBConfig("db.url"), SystemInit.getDBConfig("db.username"),
							SystemInit.getDBConfig("db.password"));

					PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
							connectionFactory, null);

					ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool(poolableConnectionFactory);

					poolableConnectionFactory.setPool(connectionPool); 

					PoolingDataSource<PoolableConnection> ds = new PoolingDataSource(connectionPool);

					dataSource = ds;

				} catch (ClassNotFoundException e) {
				}
			}
		}
		return dataSource.getConnection();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connection conn = DBDataSource.getConnection();
			
			conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}