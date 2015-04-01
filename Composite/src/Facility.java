import java.util.*;
public class Facility implements FacilityInterface{
	private ArrayList<FacilityInterface> facility;
	
	public Facility() {
		facility = new ArrayList<FacilityInterface>();
	}
	
	public void add(FacilityInterface f){
		facility.add(f);
	}
	
	public void print(){
		Iterator iterator = facility.iterator();
		while (iterator.hasNext()){
			FacilityInterface f = (FacilityInterface) iterator.next();
			f.print();
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
