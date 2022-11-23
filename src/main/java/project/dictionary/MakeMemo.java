package project.dictionary;


public class MakeMemo {
	
	
	public static void main(String[] args) {
		D_DB data = new D_DB();

		data.getConnection();
		
		//테이블 생성문
		try {
			data.createTables();
			System.out.println("테이블 생성완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("테이블 미생성");
		}
		
		//단어 단어장에 추가하기
		data.insertWord("안녕하세요", "인삿말",1);
		
		//단어장에 있는 단어목록
		data.showTable();

		/*
		 * 단어장과 어울리는 테이블 구상
		 * 
		 * 1. 단어장에 있는 단어들에 대한 문제 맞추기 테이블
		 * (낱말보고 단어 맞추기, 의미보고 단어 맞추기)
		 * 
		 * 2. 수준별로 (중요도) 에 따라 컬럼별로 나눈 테이블
		 * (예를 들면 초등, 중등, 고등처럼 수준별로)
		 * 
		 * 3. 단어 중에 마음에 드는 단어나 복습하고 싶은 단어가 생기면
		 * 나만의 단어장에 저장하는 테이블
		 * 
		 * 4. 
		 * 
		 */
	}
	
}
