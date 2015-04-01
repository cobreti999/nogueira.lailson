import java.util.Iterator;
public class BuildingIterator implements Iterator {
	private FacilityInterface[] facility;
	private int location = 0;
	public BuildingIterator(FacilityInterface[] c)
	{
		facility = c;
	}
	public FacilityInterface next()
	{
		return facility[location++];
	}
	public boolean hasNext() {
		if(location < facility.length && facility[location] != null){ 
			return true;
		} else {
			return false;
		}
	}
	public void remove() {
	}
}