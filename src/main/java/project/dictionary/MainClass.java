package project.dictionary;

import java.util.Scanner;

public class MainClass {

	//조회할 때 난이도 별로 오름차순
	//1번에 있는 것을 메서드 안에 넣기
	//getdata 스위치문에 추가
	//로그인 연결하기
	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";

	public static void main(String[] args) {
		WordDAO wordDAO = new WordDAO();
		Scanner scan = new Scanner(System.in);
		EnglishTest et = new EnglishTest();
	
		String word;
		String mean;
		int level;
		String writer;
		
		boolean flag = true;
		while(flag) {
			System.out.println("1. 입력   ||2. 삭제   ||3. 조회   ||4. 시험   ||5. 오답노트  || 0. 종료");
			
			int a = scan.nextInt();
			
			switch (a) {
			case 1:
				
				wordDAO.insertWord();
				
				break;
			case 2:
				wordDAO.DeleteWord();

				break;
			case 3:
				wordDAO.showTable();
				
				break;
			case 4:
				et.getData();
				break;
			case 5:
				et.wrongAnswer();
				break;

			case 0:
				flag = false;
			default:
				break;
			}
			
			
			
			
		}
	}
}
