import java.util.Iterator;

public class EmployeeIterator implements Iterator{
	private Employee emp;
	
	public EmployeeIterator(Employee e){
		emp = e;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return emp;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}