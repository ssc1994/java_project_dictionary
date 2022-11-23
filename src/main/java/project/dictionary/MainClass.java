package project.dictionary;

public class MainClass {
//이재원
	public static void main(String[] args) {
		D_DB db = new D_DB();
		db.getData(new Object[] {"name", "mean"}, "apple");
	}
}
