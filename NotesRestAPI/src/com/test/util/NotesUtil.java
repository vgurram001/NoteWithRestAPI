package com.test.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.test.Dao.JDBCDAO;

public class NotesUtil {
	
	private static JDBCDAO jdbcDAOObj=new JDBCDAO();
	private static Connection con=null;	
	private NotesUtil(){
		
	}
	
	public static synchronized JDBCDAO getJDBCDAO(){
		return jdbcDAOObj;
	}
	
	public static Connection getSession() throws SQLException, ClassNotFoundException{
		if(con ==null){
		
			Class.forName("com.mysql.jdbc.Driver");
		
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root", "root");
		}
		
		return con;
	}
	
	
}
