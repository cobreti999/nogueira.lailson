import java.util.LinkedList;


public class Database implements Subject{

	private LinkedList<Observer> observers; 
	private String operation;
	private String record;
	
	public Database() {
		observers = new LinkedList<Observer>(); 
	}
	
	public void registerObserver(Observer o) {
		observers.add(o); 
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o); 
	}
	
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) { 
			Observer observer = (Observer)observers.get(i); 
			observer.update(operation, record);
		} 
	}
	
	public void editRecord(String operation, String record) {
		this.operation = operation; 
		this.record = record; 
		notifyObservers();
	}
}
