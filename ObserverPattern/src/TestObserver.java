public class TestObserver {
	public static void main(String args[]) {
		Database database = new Database(); 
		Client1 client = new Client1();
		Client2 boss = new Client2();
		database.registerObserver(client); 
		database.registerObserver(boss); 
		database.editRecord("delete", "record 1");
	}
}

