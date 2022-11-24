package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//

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

			//sql 실행 ( select의 실행 )
			rs = pstmt.executeQuery();

			while(rs.next()) { //다음 row가 있다면 true

				//한 행에 대한 처리( getInt, getString, getDouble, getTimestamp, getDate )
				int importance = rs.getInt("W_LEVEL");
				String word = rs.getString("W_WORD"); //컬럼명
				String mean = rs.getString("W_MEAN");
				String writer = rs.getString("W_Writer");

				//한번 회전할 때마다 하나씩 생성
				//vo에 행 데이터 저장, vo를 리스트에 add를 이용해서 저장
				WordVO vo = new WordVO(importance, word, mean, writer);
				list.add(vo);

				System.out.println("난이도 : " + importance + ", 단어 : " + word + ", 뜻 : " + mean + ", 작성자 :" + writer);
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
			create = "Create Table Word  (W_Word varchar2(32)  primary key,\r\n"
					+ "                   W_Mean varchar2(200) not null,  \r\n"
					+ "                   W_Level number(2) , \r\n"
					+ "                   W_Writer varchar2(20))";
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


	public static void insertWord(String writer) {
		Connection con = getConnection();
		String insert;
		String select;
		String update;
		try {

			Scanner scan = new Scanner(System.in);
			System.out.print("단어 >");
			String word1 = scan.nextLine();
			System.out.print("\n뜻 >");
			String mean1 = scan.nextLine();
			System.out.print("\n난이도 >");
			int level1 = scan.nextInt();
			
			select = "SELECT * FROM WORD where W_Word = ?";
			pstmt=con.prepareStatement(select);
			pstmt.setString(1, word1);
			result = pstmt.executeQuery();
			
			if (result.next() == false) {
				insert = "Insert Into WORD Values(?, ?, ?, ?)";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, word1);
				pstmt.setString(2, mean1);
				pstmt.setInt(3, level1);
				pstmt.setString(4, writer);
				pstmt.executeUpdate();
				System.out.println("입력완료");
				
			} else {
				update = "Update Word Set  W_mean = ?, W_Level = ? WHERE W_Word = ?";
				pstmt = con.prepareStatement(update);
				pstmt.setString(1, mean1);
				pstmt.setInt(2, level1);
				pstmt.setString(3, word1);
				pstmt.executeUpdate();
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

	public static void searchWord() { //검색기능
		Connection con = getConnection();

		Scanner scan = new Scanner(System.in);
		String select = "SELECT * FROM Word WHERE W_Word = ?";
		try {
			System.out.print("검색할 단어 > ");
			String word = scan.next();
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, word);
			result = pstmt.executeQuery();

			while(result.next()) {
				String W_Word = result.getString("W_Word");
				String W_Mean = result.getString("W_Mean");
				int W_Level = result.getInt("W_Level");
				String W_Writer = result.getString("W_Writer");

				System.out.println("난이도 : " + W_Level + ", 단어 : " + W_Word + ", 뜻 : " + W_Mean + ", 작성자 :" + W_Writer);
			}
			pstmt.close();
			con.close();	
		} catch (Exception e) {
			e.printStackTrace();
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
			
				String W_WORD = result.getString("W_Word");
				String W_MEAN = result.getString("W_Mean");
				int W_LEVEL = result.getInt("W_LEVEL");
				String writer = result.getString("W_Writer");
				
				System.out.println("\n영어 단어 : " + W_WORD + "\n영어 뜻 : " +
						W_MEAN + "\n난이도 : level." + W_LEVEL + "\n작성자 : "+ writer + "\n" );	
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//입력한 문자를 삭제하고싶은데, 삭제오류 수정필요 
		public static void deleteWord() {
			Connection con = getConnection();
			
			Scanner scan1 = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			System.out.print("삭제하고 싶은 단어가 있습니까 ?\n (네 / 아니오) ");
			String l = scan1.nextLine();
		
			if(l.equals("네")) {
				showTable();
				System.out.print("삭제할 단어를 입력하세요 > ");
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