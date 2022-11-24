package project.dictionary;

import java.util.Scanner;

public class MainClass {

	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String uid = MainClass.uid;
		String url = MainClass.url;
		String upw = MainClass.upw;
		Member m = new Member();
		UserDAO udao = new UserDAO();
		WordDAO wdao = new WordDAO();
		EnglishTest et = new EnglishTest();
		boolean run = true;
	
		w:while(run) {
			System.out.println("========== 초기화면 ==========");
			System.out.print("1. 회원가입 | 2. 로그인 | 9. 종료 > ");
			String join = sc.nextLine();
			switch (join) {
			case "1": 
				try {
					m.insert();
				} catch (AuthenException e) {
					e.printStackTrace();
				}
				break;
			case "2": 
					boolean run2 = true;
					String result = udao.loginMember().getName();
					if(result != null) {
						ww:while(run2) {
							System.out.println("============================== 단어장 ==============================");
							System.out.print("1. 단어 등록/수정 | 2. 단어목록 조회 | 3. 단어 삭제 | 4. 단어 테스트 | 5. 오답노트 | 0. 로그 아웃 | > ");
							String menu = sc.nextLine();
							switch (menu) {
							case "1":
								System.out.println("===== 단어 등록 =====");
								wdao.insertWord(result);
								break;
							case "2": 
								System.out.println("===== 단어목록 조회 =====");
								wdao.selectEx();
								break;
							case "3": 
								System.out.println("===== 단어 삭제 =====");
								wdao.deleteWord();
								break;
							case "4": 
								System.out.println("===== 단어 시험 =====");
								et.getData();
								break;
							case "5":
								System.out.println("===== 오답 노트 =====");
								et.wrongAnswer();
								break;
							case "0":
								System.out.println("로그아웃 되었습니다");
								result = null;
								continue w;
							default:
								System.out.println("1~4까지의 숫자만 입력해주세요. \n로그아웃은 0번을 눌러주세요");
								break;
							}
						}
					}
				break;
			case "9":
				System.out.println("프로그램을 종료합니다");
				break w;
			
			default:
				System.out.println("회원가입은 1, 로그인은 2, 종료는 9를 눌러주세요");
				break;
			}
			
		}
		
		
	}
}
