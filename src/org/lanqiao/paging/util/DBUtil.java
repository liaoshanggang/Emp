package org.lanqiao.paging.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	public static final String URL;
	public static final String USER;
	public static final String PASSWORD;                                                                                
	static {
		InputStream inStream = DBUtil.class.getClassLoader()
				.getResourceAsStream("org/lanqiao/paging/util/db.properties");
		Properties props = new Properties();
		try {
			props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = props.getProperty("URL");
		USER = props.getProperty("USER");
		PASSWORD = props.getProperty("PASSWORD");
	}

	public static Connection getConn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动程序未加载成功");
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("连接信息URL，USER,PASSWORD不正确！");
		}
		return conn;
	}
}
