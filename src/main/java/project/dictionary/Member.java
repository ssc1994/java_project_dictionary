package project.dictionary;

import java.util.Scanner;

public class Member { //입력 받는 부분
	
	Scanner sc = new Scanner(System.in);
	MemberDAO dao = new MemberDAO();
	
	MemberException me = new MemberException();
	
	//회원가입
	public void insert() throws AuthenException {
		
		String pw2 = null;
		boolean id = true;
		boolean pw = true;
		boolean name = true;
		boolean phone = true;
		boolean birth = true;
		
		System.out.println("=====회원가입=====");
		
		try {
			
			MemberVO vo = new MemberVO();
			
			do {
				try {
					System.out.print("아이디 > ");
					vo.setId(sc.next());
					me.idCheck(vo.getId());
					
					id = false;
				} catch (AuthenException e) {
					System.out.println(e.toString());
				}
			} while(id);
			
			do {
				try {
					System.out.print("비밀번호 > ");
					vo.setPw(sc.next());
					System.out.print("비밀번호 확인 > ");
					pw2 = sc.next();
					me.pwCheck(vo.getPw(), pw2);
					
					pw = false;
				} catch (AuthenException e) {
					System.out.println(e.toString());
				}
			} while(pw);
			
			do {
				try {
					System.out.print("이름 > ");
					vo.setName(sc.next());
					me.nameCheck(vo.getName());
					
					name = false;
				} catch (AuthenException e) {
					System.out.println(e.toString());
				}
			} while(name);
			
			do {
				try {
					System.out.print("생년월일 > ");
					vo.setBirth(sc.next());
					me.birthCheck(vo.getBirth());
					
					birth = false;
				} catch (AuthenException e) {
					System.out.println(e.toString());
				}
			} while(birth);
			
			do {
				try {
					System.out.print("연락처 > ");
					vo.setPhone(sc.next());
					me.phoneCheck(vo.getPhone());
					
					phone = false;
				} catch (AuthenException e) {
					System.out.println(e.toString());
				}
			} while(phone);
			
			int result = dao.InsertData(vo);
			
			if(result != 0) {
				System.out.println("===== 회원가입이 완료되었습니다 =====");
				System.out.println("===== 회원가입 확인 =====");
				System.out.println(vo.toString());
			} else {
				System.out.println("XXXXX 회원가입에 실패했습니다 XXXXX");
			}
			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
