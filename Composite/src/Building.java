import java.util.*;
public class Building implements FacilityInterface{
	private FacilityInterface[] facility = new FacilityInterface[50	];
	private int number = 0; 
	private String name;
	public Building(String n) {
		name = n;
	}
	public String getName() {
		return name; 
	}
	public void add(FacilityInterface f) {
		facility[number++] = f; 
	}
	public Iterator iterator() {
		return new BuildingIterator(facility);
	}
	public void print() {
		Iterator iterator = iterator();
		while (iterator.hasNext()){
			FacilityInterface c = (FacilityInterface) iterator.next();
			c.print();
		} 
	}
}
