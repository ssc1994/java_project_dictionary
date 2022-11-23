package project.dictionary;

public class MemberVO { //회원 정보 입력
	
	private String id, pw, name, birth, phone;
	
	public MemberVO() {
	}
	
	public MemberVO(String id, String pw, String name, String birth, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	public String getPhone() {
		return phone;
	}
	
	
	public String getPw() {
		return pw;
	}


	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		return "-----회원 정보-----\n" 
	+ "ID : " + id + "\n이름 : " + name + "\n생년월일 : "
				+ birth + "\n연락처 : " + phone;
	}
}
