/**
 * 
 */ 
package com.zot.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
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
import org.apache.log4j.Logger;

import com.zot.sys.config.SystemInit;

import sun.swing.text.CountingPrintable;

/**
 * @author jack
 *
 */
public class DBDataSource {

	private static DataSource dataSource = null;

	private static Lock lock = new ReentrantLock();
	
	private static Logger logger = Logger.getLogger(DBDataSource.class);

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			lock.lock();
			if (dataSource == null) {
				String userName = SystemInit.getDBConfig("db.username");
				String passWord = SystemInit.getDBConfig("db.password");
				try {
					Class.forName(SystemInit.getDBConfig("db.driver")).newInstance();
					Properties connProp = new Properties();
					connProp.setProperty("username",userName );
					connProp.setProperty("password", passWord);
					connProp.setProperty("maxActive","1");
					connProp.setProperty("initialSize","1");
					connProp.setProperty("maxIdle", "1");
					connProp.setProperty("minIdle","1");
					connProp.setProperty("removeAbandoned","true" );
					connProp.setProperty("removeAbandonedTimeout","180" );
				
					ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
							SystemInit.getDBConfig("db.url"),userName ,passWord);
					
					PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
							connectionFactory, null);

					ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool(poolableConnectionFactory);
					
					poolableConnectionFactory.setPool(connectionPool); 
					
					PoolingDataSource<PoolableConnection> ds = new PoolingDataSource(connectionPool);

					dataSource = ds;

				} catch (ClassNotFoundException e) {
					logger.error(e.getMessage(),e);
				} catch (InstantiationException e) {
					logger.error(e.getMessage(),e);
				} catch (IllegalAccessException e) {
					logger.error(e.getMessage(),e);
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
