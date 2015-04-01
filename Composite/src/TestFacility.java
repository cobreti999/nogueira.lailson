public class TestFacility {
	Facility facility;
	
	public TestFacility() {
		facility = new Facility();
		Building bld = new Building("Building 1"); 
		bld.add(new Employee("Lailson", "Building 1")); 
		bld.add(new Employee("Thiago", "Building 1")); 
		Building bld2 = new Building("Building 2");
		bld2.add(new Employee("Markus", "Building 2")); 
		bld2.add(new Employee("Luke", "Building 2"));
		Building bld3 = new Building("Building 3"); 
		bld3.add(new Employee("Andrea", "Building 3")); 
		bld2.add(bld3);
		Employee employee = new Employee("Briggy", "Facility");
		facility.add(bld); 
		facility.add(bld2); 
		facility.add(employee);
		facility.print();
	}
	public static void main(String args[]) {
		new TestFacility(); 
	}	
}