
public class TestProxy {
	
	public static void main(String args[]){
		Employee e1 = new Employee("Lailson Nogueira", 28);
		Employee e2 = new Employee("Thiago Puluceno", 15);
		FacilityInterface facility = new ProxyFacility(e1);
		facility.hireEmployee();
		
		facility = new ProxyFacility(e2);
		facility.hireEmployee();
	}

}
