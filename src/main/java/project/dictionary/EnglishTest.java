package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EnglishTest {

	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호

	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수
	static ArrayList<WordVO> myword = new ArrayList<>();
	
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
	
	public static void getData() {//검색기능
		Connection con = getConnection();
		Scanner scan = new Scanner(System.in);
		System.out.print("난이도 > ");
				int num = scan.nextInt();
		String select = "select aa.* \r\n"
				+ "from(select rownum rn, a.* \r\n"
				+ "    from(select * \r\n"
				+ "        from word)a \r\n"
				+ "        where w_level = ?\r\n"
				+ "        order by DBMS_RANDOM.RANDOM)aa"
				+ "        where rn<=10";
		String W_Word = "";
		String W_Mean = "";
		String W_Writer = "";
		int W_Level = 0;
		int score = 0;
		int test = 0;
		int count = 0;
		try {
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, num);
			result = pstmt.executeQuery();
			
			while(result.next()){ // 컬럼 이름
				W_Word = result.getString("W_Word");
				W_Mean = result.getString("W_Mean");
				W_Level = result.getInt("W_Level");
				W_Writer = result.getString("W_Writer");
				//단어의 정보 출력
				count++;
				System.out.println(count + "번 문제");
				System.out.print("단어의 정보\n뜻 : " + W_Mean + "   |   단어 :   ");
				String ans = scan.next();
				if(W_Word.equals(ans)) {
					System.out.println("정답입니다.");
					score ++;
				}else {
					System.out.println("오답입니다.  정답 : " + W_Word);
					System.out.println();
					
//--------------------------------myword에 추가
					WordVO vo = new WordVO(W_Level, W_Word, W_Mean, W_Writer);
					myword.add(vo);
					
				}
				test++;
			}
			System.out.println("맞은 갯수 : " + score+"/"+ test);
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void wrongAnswer() {
		
		System.out.println("오답노트  ");
		
		for(WordVO vo : myword) {
			
			System.out.println(vo.toString());
		}
		
	}
	
}
