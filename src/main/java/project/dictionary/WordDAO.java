package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import Project.WordVO;

public class WordDAO {
	
	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호
	

	
	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수
	
	//입력된 데이터 전부 출력하는 메서드
	public ArrayList<WordVO> selectEx() {
		ArrayList<WordVO> list = new ArrayList<>();

		String sql = "select * from Word";


		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select결과를 반환받을 객체

		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//conn
			conn = DriverManager.getConnection(url, uid, upw);

			//stmt
			pstmt = conn.prepareStatement(sql);

			//?값에 대한 처리 ( 생략 )

			//sql 실행 ( select의 실행 )
			rs = pstmt.executeQuery();

			while(rs.next()) { //다음 row가 있다면 true

				//한 행에 대한 처리( getInt, getString, getDouble, getTimestamp, getDate )
				int importance = rs.getInt("W_LEVEL");
				String word = rs.getString("W_WORD"); //컬럼명
				String mean = rs.getString("W_MEAN");

				//한번 회전할 때마다 하나씩 생성
				//vo에 행 데이터 저장, vo를 리스트에 add를 이용해서 저장
				WordVO vo = new WordVO(importance, word, mean);
				list.add(vo);

				System.out.println("난이도 : " + importance + ", 단어 : " + word + ", 뜻 : " + mean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				System.out.println("close 에러");
			}
		}

		return list;
	}
	
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
			create = "Create Table Word  (W_No NUMBER(5) primary key,\r\n"
					+ "                W_Word varchar2(32)  not null,\r\n"
					+ "                W_Mean varchar2(200) not null,  \r\n"
					+ "                W_Level number(2) , \r\n"
					+ "                W_Writer varchar2(20))";
			pstmt = con.prepareStatement(create);
			int result = pstmt.executeUpdate();
			if (result == 1 ) {
		         System.out.println("성공");
		      }else {
		         System.out.println("실패");
		      }
		         
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//새로 만든 메서드
//	public static void insertWord(String word, String mean, int level) {
//			Connection con = getConnection();
//			String insert;
//			String select;
//			try {
//				insert = "Insert Into Word Values(word_no_seq,"
//						        + "'"+ word +"',"
//								+ " '"+ mean +"',"
//								+ "  "+ level +" ,"
//								+ " '작성자' )";
//				
//				pstmt = con.prepareStatement(insert);
//				pstmt.executeQuery();
////				if(result)
//				
//				
//				
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			
//			
//		
//	}
	
	//기존에 있던 메서드
	public static void insertWord(int no, String word, String mean, int level, String writer) {
		Connection con = getConnection();
		String insert;
		String select;
		try {
			insert = "Insert Into Word values(?,?,?,?,?)";
			pstmt.setInt(1, no);
			pstmt.setString(2, word);
			pstmt.setString(3, mean);
		    pstmt.setInt(4, level);
		    pstmt.setString(5, writer);
		    
		    pstmt = con.prepareStatement(insert);
			
			int result = pstmt.executeUpdate();
		      
		      if (result == 1 ) {
		         System.out.println("성공");
		      }else {
		         System.out.println("실패");
		      }
		         
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	
}
	
	
	public static void showTable() {
		Connection con = getConnection();

		String select = "SELECT * FROM Word ";

		try {
			pstmt = con.prepareStatement(select);
			ResultSet result = pstmt.executeQuery(select);
			System.out.println("\n -----단어목록----- ");
			while(result.next()){
				int W_NO = result.getInt("W_NO");
				String W_WORD = result.getString("W_Word");
				String W_MEAN = result.getString("W_Mean");
				int W_LEVEL = result.getInt("W_LEVEL");
				
				System.out.println("NO." + W_NO + "\n영어 단어 : " + W_WORD + "\n영어 뜻 : " +
						W_MEAN + "\n난이도 : level." + W_LEVEL + "\n" );	
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//입력한 문자를 삭제하고싶은데, 삭제오류 수정필요 
		public static void DeleteWord() {
			Connection con = getConnection();
			
			Scanner scan1 = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			System.out.println("삭제하고 싶은 단어가 있습니까 ?\n (네 / 아니오)");
			String l = scan1.nextLine();
		
			if(l.equals("네")) {
				System.out.println("삭제할 단어를 입력하세요.");
				showTable();
				String w = scan2.nextLine();
				
				
					try {
						String delete = "DELETE FROM Word where W_word = '" + w + "'";
						pstmt = con.prepareStatement(delete);
						pstmt.executeUpdate();
						System.out.println("단어가 삭제되었습니다.");
						pstmt.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("단어가 존재하지 않습니다.");
					}
				
			}else {
				System.out.println("단어가 삭제되지 않았습니다.");
				
			}
			
		}
		
		
		
		
}