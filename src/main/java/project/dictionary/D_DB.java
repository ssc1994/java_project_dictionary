package project.dictionary;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import dictionary.DTable.RowListener;

public class D_DB {

	static Connection con = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;

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

	// 테이블 생성 메서드
	public static void createTables() {
		Connection con = getConnection();
		String query;
		Statement stmt;

		try {
			query = "create table Word " + "(name varchar(32) primary key, "
					+ "mean varchar(200) not null, " + "importance number(38) , "
					+ "count  number(38))";
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertWord(String W_word, String W_mean, int importance, String W_writer) {
		Connection con = getConnection();

		String insert = "insert into Word values('" + W_word + "', '" + W_mean
				+ "', " + importance + ", " + 1 + ")";
		String select = "SELECT name, count FROM Word WHERE name = '" + W_word + "'";

		try {

			pstmt = con.prepareStatement(insert);
			ResultSet result = stmt.executeQuery(select);
			if (result.first() == false) {
				pstmt.executeUpdate(insert);
			} else {
				int count = result.getInt(2);
				count++;
				String update = "update Word set mean = '" + W_mean + "', count = " + count
						+ " where name = '" + W_word+ "'";

				pstmt.executeUpdate(update);
			}

			pstmt.close();
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
		Connection con = D_DB.getConnection();
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

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getData(Object[] data, String word){
		Connection con = getConnection();

		String select = "SELECT name,mean FROM Word where name = '" +
		word + "'";

		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(select);

			while(result.next()){
				data[0] = result.getInt(1);
				data[1] = result.getString(2);
				data[2] = result.getString(3);
				data[3] = result.getString(4);
				data[4] = result.getInt(5);
				
				System.out.println("단어번호 : " + data[0] + " 영어 단어 : " + data[1] + " 단어 뜻 : " + 
							data[2] + " 회원ID" + data[3] + " 단어 난이도 : " + data[4]);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
