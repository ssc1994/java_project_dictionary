package project.dictionary;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberException {
	static Scanner sc = new Scanner(System.in);
	public static final String p1 = "^[A-Za-z[0-9]]{6,12}$"; //id검사
	//pw검사
	public static final String p2 = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,12}$";
	public static final String p3 = "^[가-힣\\s]+$"; //이름 검사
	//생년월일 검사 - YYYYMMdd
	public static final String p4 = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
	//전화번호 검사
	public static final String p5 = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
	
	
	static Matcher m;
	
	public boolean idCheck(String a) throws AuthenException {
		
		boolean check = false;
		m = Pattern.compile(p1).matcher(a);
		if(m.find()) {
			check = true;
		}
		if(check == false) {
			throw new AuthenException("ID는 영문 포함, 6~12자리를 입력하세요");
		}
		return check;
	}
	
	public boolean pwCheck(String pw1, String pw2) throws AuthenException {
		
		boolean check = false;
		m = Pattern.compile(p2).matcher(pw1);
		if(m.find()) {
			check = true;
		}
		if(check == false) {
			throw new AuthenException("비밀번호는 8~12자 사이, 영문과 숫자, 특수문자를 포함해야 합니다");
		}
		
		if(!pw1.equals(pw2)) {
			throw new AuthenException("비밀번호가 다릅니다");
		}
		return check;
	}
	
	public boolean pwCheck1(String pw1) throws AuthenException {
		
		boolean check = false;
		m = Pattern.compile(p2).matcher(pw1);
		if(m.find()) {
			check = true;
		}
		if(check == false) {
			throw new AuthenException("비밀번호는 8~12자 사이, 영문과 숫자, 특수문자를 포함해야 합니다");
		}
		return check;
	}
	
	public boolean nameCheck(String a) throws AuthenException {
		
		boolean check = false;
		m = Pattern.compile(p3).matcher(a);
		if(m.find()) {
			check = true;
		}
		if(check == false) {
			throw new AuthenException("이름은 한글만 입력해주세요");
		}
		return check;
	}
	
	public boolean birthCheck(String a) throws AuthenException {
		
		boolean check = false;
		m = Pattern.compile(p4).matcher(a);
		if(m.find()) {
			check = true;
		}
		if(check == false) {
			throw new AuthenException("생년월일은 숫자만 입력해주세요"
					+ "\n (YYYYMMdd)");
		}
		return check;
	}
	
	public boolean phoneCheck(String a) throws AuthenException {

		boolean check = false;
		m = Pattern.compile(p5).matcher(a);
		if(m.find()) {
			check = true;
		}
		if(check == false) {
			throw new AuthenException("하이픈(-) 제외 13자리를 입력해주세요");
		}
		return check;
	}
}