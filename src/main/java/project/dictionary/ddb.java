package project.dictionary;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import dictionary.DTable.RowListener;

public class ddb {

	static Connection con = null;
	static Statement stmt = null;

	static String userid = "COM02", password = "COM02";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";

	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName("myDriver.ClassName");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url, userid, password);

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return con;
	}

	public static void createTables() {
		Connection con = getConnection();
		String query;
		Statement stmt;

		try {
			query = "create table Word " + "(name varchar(32) primary key, "
					+ "mean varchar(200) not null, " + "importance varchar2(30) , "
					+ "count number(20))";
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertWord(String word, String mean, int importance) {
	      Connection con = getConnection();

	      String insert = "insert into Word values('" + word + "', '" + mean
	            + "', " + importance + ", " + 1 + ")";
	      String select = "SELECT name, count FROM Word WHERE name = '" + word + "'";

	      try {

	         stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	         ResultSet result = stmt.executeQuery(select);
	         if (result.first() == false) {
	            stmt.executeUpdate(insert);
	         } else {
	            int count = result.getInt(2);
	            count++;
	            String update = "update Word set mean = '" + mean + "', count = " + count
	                  + " where name = '" + word+ "'";

	            stmt.executeUpdate(update);
	         }

	         stmt.close();
	         con.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }

	public static void showTable() {
		Connection con = getConnection();

		String select = "SELECT * FROM Word ";

		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(select);

			while(result.next()){
				String word = result.getString(1);
				String mean = result.getString(2);
				int imp = result.getInt(3);
				int count = result.getInt(4);
				
				System.out.println("영어 단어 " + word + " 영어 뜻 " +
						mean + " 중요도" + imp + " 저장횟수 " + count);	
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void DeleteWord(String word) {
		Connection con = getConnection();

		String delete = "DELETE FROM Word where name = '" + word + "'";

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(delete);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setTable(JTable table,DefaultTableModel model) {
		Connection con = ddb.getConnection();
		String select = "SELECT name FROM Word";

		String title = "word";

		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(select);

			model.addColumn(title);
			while (result.next()) {
				String word = result.getString(1);

				model.addRow(new Object[] { word });

			}
			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getData(String[] data, String word){
		Connection con = getConnection();

		String select = "SELECT name,mean FROM Word where name = '" +
		word + "'";

		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(select);

			while(result.next()){
				data[0] = result.getString(1);
				data[1] = result.getString(2);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
