package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getdata {

	static Connection con = null;
	static Statement stmt = null;

	static String userid = "COM02", password = "COM02";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";

	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName("myDriver.ClassName");
														// ?

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url, userid, password);

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}

		return con;
	}
	
	
	public static void getData(Object[] data, String word) {
		Connection con = getConnection();

		String select = "SELECT name,mean FROM Word where name = '" +
		word + "'";

		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(select);

			while(result.next()){
				data[0] = result.getString(1);
				data[1] = result.getString(2);
				data[2] = result.getString(3);
				data[3] = result.getInt(4);
				data[4] = result.getInt(5);
			
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
