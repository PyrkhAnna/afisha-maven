package by.htp.afisha.afisha_maven.runner;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class DBMain {

	public static void main(String[] args) {
		//load driver
		//connect to database
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afisha_anna", "root", "root");
			/*Enumeration<Driver> dr = DriverManager.getDrivers();
			while (dr.hasMoreElements()){
				System.out.println(dr.nextElement());
			}*/
			
			Statement st =  connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `event`");
			
			while (rs.next()){
				String title = rs.getString("title");
				System.out.println(title);
			}
			
			//	Statement st =  connection.prepareStatement("SELECT * FROM `afisha_anna`.`event` WHERE time < "16:00:00";");
			//connection
			
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//MySQLConnection.getConnection
		//run query
		//close connect
	}

}
