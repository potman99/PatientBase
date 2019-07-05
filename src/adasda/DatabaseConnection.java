package adasda;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class DatabaseConnection {
	
	final static String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final static String DB_URL="jdbc:mysql://localhost:3306/patients";
	
	final static String USER="root";
	final static String PAS="";
	
	public static Connection connection()
	{
		try {
			
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PAS);
			return conn;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
			return null;
		}
		
	}

}
