/**
 * 
 */
package com.zot.sys.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.zot.util.ZOTException;

/**
 * @author jack
 *
 */
public class SystemInit {

	private static Properties db_config = null;

	private static Properties sys_config = null;
	
	/**
	 * 系统应用服务Action配置
	 */
	private static Properties app_service_config = null;

	private static Lock lock = new ReentrantLock();

	private static Logger logger = null;

	/**
	 * 系统日志初始化（系统启动的时候初始化）
	 */
	static {
		// 初始化日志系统
		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("conf/log4j.properties"));

		logger = Logger.getLogger(SystemInit.class);
	}

	/**
	 * 初始化系统数据库连接信息（第一次调用时候初始化）
	 * 
	 * @return
	 */
	public static String getDBConfig(String key) {
		if (db_config == null) {
			lock.lock();
			if (db_config == null) {
				Properties prop = new Properties();
				InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/jdbc.properties");
				try {
					prop.load(in);
				} catch (IOException e) {
					logger.error("system jdbc config init failed", e);
					throw new ZOTException(e);
				} finally {
					try {
						in.close();
					} catch (IOException e) {

					}
					db_config = prop;
					lock.unlock();
				}
			}
		}

		return db_config.getProperty(key);
	}

	/**
	 * 初始化系统全局配置数据（第一次调用的时候初始化）
	 * 
	 * @param key
	 * @return
	 */ 
	public static String getSysConfig(String key) {
		if (sys_config == null) {
			lock.lock();
			if (sys_config == null) {
				Properties prop = new Properties();
				InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sysconfig.properties");
				try {
					prop.load(in);
				} catch (IOException e) {
					logger.error("system config init failed", e);
					throw new ZOTException(e);
				} finally {
					try {
						in.close();
					} catch (IOException e) {

					}
					sys_config = prop;
					lock.unlock();
				}
			}

		}
		return sys_config.getProperty(key);
	}
	
	/**
	 * 初始化系统全局配置数据（第一次调用的时候初始化）
	 * 
	 * @param key
	 * @return
	 */
	public static String getAppServiceClazz(String key) {
		if (app_service_config == null) {
			lock.lock();
			if (app_service_config == null) {
				Properties prop = new Properties();
				InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("zot-application.properties");
				try {
					prop.load(in);
				} catch (IOException e) {
					logger.error("system app config init failed", e);
					throw new ZOTException(e);
				} finally {
					try {
						in.close();
					} catch (IOException e) {

					}
					app_service_config = prop;
					lock.unlock();
				}
			}

		}
		return app_service_config.getProperty(key);
	}
}
