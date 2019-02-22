package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import application.AppMain;

public class DBUtility {
	public static Connection getConnetction() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://192.168.0.181/internetcafe","root", "123456");
		} catch (Exception e) {
			AppMain.callAlert("연결실패:DB 연결에 실패함"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return con;
	}	
}
