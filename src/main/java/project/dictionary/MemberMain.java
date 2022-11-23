package project.dictionary;

import java.util.Scanner;

public class MemberMain {
	
	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";
	
	public static void main(String[] args) throws AuthenException {
		
		Scanner sc = new Scanner(System.in);
		
		Member mem = new Member();
		MemberDAO dao = new MemberDAO();
		
		dao.getConnection();
		dao.createMemberTable();
		mem.insert();
		
		
//		String menu = "";
//		boolean run = true;
//		while(run) {
//			System.out.print("회원가입을 하시겠습니까? (Y/N) > ");
//			menu = sc.nextLine();
//			
//			if(menu.toUpperCase().equals("Y")) {
//				mem.insert();
//				break;
//			} else if(menu.toUpperCase().equals("N")) {
//				System.out.println("회원가입을 취소하였습니다");
//				run = false;
//			} else {
//				System.out.println("올바른 값을 입력해주세요");
//			}
//			
//			
//		}
		
	}

}
