package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import application.AppMain;

public class DBUtility {
	//SQL�� �����Ѵ�.
	public static Connection getConnetction() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://192.168.123/internetcafe","root", "123456");
		} catch (Exception e) {
			AppMain.callAlert("�������:DB ���ῡ ������"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return con;
	}	
}
