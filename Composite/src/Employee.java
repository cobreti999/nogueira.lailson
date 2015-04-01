import java.util.*;
public class Employee implements FacilityInterface{
	private String name; 
	private String building;
	public Employee(String n, String d)
	{
		name = n;
		building = d; 
	}
	public String getName() {
		return name; 
	}
	public void print() {
		System.out.println("Name: " + name + ". Building: " + building); 
		System.out.println();
	}
	public Iterator iterator() {
		return new EmployeeIterator(this); 
	}
	@Override
	public void add(FacilityInterface c) {
		// TODO Auto-generated method stub
		
	}
}

