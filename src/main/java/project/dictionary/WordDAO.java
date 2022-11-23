package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class WordDAO {

	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호



	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수

	public static Connection getConnection() {

		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection
			con = DriverManager.getConnection(url, upw, uid);

		} catch (Exception e) {
			e.getStackTrace();
		} 
		return con;
	}

	public static void createTable() {

		Connection con = getConnection();
		String create;

		try {
			create = "Create Table Word  (W_Word varchar2(32)  primary key,\r\n"
					+ "                   W_Mean varchar2(200) not null,  \r\n"
					+ "                   W_Level number(2) , \r\n"
					+ "                   W_Writer varchar2(20))";
			pstmt = con.prepareStatement(create);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void insertWord(String word, String mean, int level) {
		Connection con = getConnection();
		String insert;
		String select;
		String update;
		try {

			select = "SELECT * FROM WORD where W_Word = ?";

			pstmt=con.prepareStatement(select);
			pstmt.setString(1, word);
			result = pstmt.executeQuery();
			
			if (result.next() == false) {
				insert = "Insert Into Word Values(?, ?, ?,'작성자' )";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, word);
				pstmt.setString(2, mean);
				pstmt.setInt(3, level);
				pstmt.executeUpdate();
				System.out.println("입력완료");
				
			} else {
				update = "Update Word Set  W_mean = ?, W_Level = ? WHERE W_Word = ?";
				pstmt = con.prepareStatement(update);
				pstmt.setString(3, word);
				pstmt.setString(1, mean);
				pstmt.setInt(2, level);
				System.out.println("수정완료");
				
				
			}

			
//			insert = "Insert Into Word Values(?, ?, ?,'작성자' )";
//			pstmt = con.prepareStatement(insert);
//			pstmt.setString(1, word);
//			pstmt.setString(2, mean);
//			pstmt.setInt(3, level);
//			pstmt.executeUpdate();
//			System.out.println("입력");
				
				
				



		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			System.out.println(e.getLocalizedMessage());
		}

	}




		
	}
	
	
	public static void showTable() {
		Connection con = getConnection();

		String select = "SELECT * FROM Word ";

		try {
			pstmt = (PreparedStatement) con.createStatement();
			ResultSet result = pstmt.executeQuery(select);
			System.out.println("\n 단어목록 ");
			while(result.next()){
				String word = result.getString(1);
				String mean = result.getString(2);
				int level = result.getInt(3);
				int count = result.getInt(4);
				
				System.out.println("영어 단어 : " + word + "\n영어 뜻 : " +
						mean + "\n난이도 : level." + level + "\n복습횟수 : " + count + "\n");	
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//입력한 문자를 삭제하고싶은데, 삭제오류 수정필요 
		public static void DeleteWord(String word) {
			Connection con = getConnection();
			
			Scanner scan1 = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			System.out.println("삭제하고 싶은 단어가 있습니까 ?\n (네 / 아니오)");
			String l = scan1.nextLine();
		
			if(l.equals("네")) {
				System.out.println("삭제할 단어를 입력하세요.");
				showTable();
				String w = scan2.nextLine();
				
				if(w.equals(word)) {
					try {
						String delete = "DELETE FROM Word where name = '" + word + "'";
						pstmt = (PreparedStatement) con.createStatement();
						pstmt.executeUpdate(delete);
						System.out.println("단어가 삭제되었습니다.");
						pstmt.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("단어가 존재하지 않습니다.");
				}
				
			}else {
				System.out.println("단어가 삭제되지 않았습니다.");
				
			}
			
		}
}
