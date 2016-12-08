package by.htp.afisha.afisha_maven.runner;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class DBReaderMain {

public static void main(String[] args) {
	String title,date, time, place, cost, genre, rating;
				Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afisha_anna", "root", "root");
										
					Statement st =  connection.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM `film`");
					
					while (rs.next()){
						title = rs.getString("title");
						date = rs.getString("date");
						time = rs.getString("time");
						place = rs.getString("place");
						cost = rs.getString("cost");
						genre = rs.getString("genre");
						rating = rs.getString("rating");
						System.out.println(title+ date+time+place+cost+genre+rating);
					}
					/*
					int res = st.executeUpdate("INSERT INTO `film` (title, date, time, place, cost, genre, rating) VALUES (\"White Shark\", \"2016-12-27\", \"18:00:00\", \"Electron\", 12.4, \"horror\", 6.5)");
					System.out.println(res);*/
									
				} catch (ClassNotFoundException | SQLException e){
					e.printStackTrace();
				} finally {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			
	}

}
