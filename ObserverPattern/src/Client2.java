public class Client2 implements Observer {
	public Client2() {
	}
	public void update(String operation, String record) {
		System.out.println("Client2 says: a " + operation + " operation was performed on " + record);
	}
}
