package project.dictionary;


public class MakeMemo {
	
	
	public static void main(String[] args) {
		D_DB data = new D_DB();

		data.getConnection();
		
		try {
			data.createTables();
			System.out.println("테이블 생성완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("테이블 미생성");
		}
		
		data.insertWord("안녕하세요", "인삿말",1);
		
		data.showTable();
	}
	
}
