package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbConnect {
	public static void main(String[] args) {
		
	
		ArrayList<WordVO> list = new ArrayList<>();

		String sql = "select W_Word from Word";


		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select결과를 반환받을 객체

		String uid = "com02";
		String upw = "com02";		//아이디
		String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";
		
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
				String word = rs.getString("W_WORD"); //컬럼명

				//한번 회전할 때마다 하나씩 생성
				//vo에 행 데이터 저장, vo를 리스트에 add를 이용해서 저장
				WordVO vo = new WordVO(word);
				list.add(vo);

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
		System.out.println(list);
	}
}


