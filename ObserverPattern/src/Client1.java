
public class Client1 implements Observer{
	public Client1(){
	}
	public void update(String operation, String record){
		System.out.println("Client1 says: a " + operation + " operation was performed on " + record); 
	}
}
