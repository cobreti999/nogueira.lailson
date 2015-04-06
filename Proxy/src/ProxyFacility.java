
public class ProxyFacility implements FacilityInterface {
	private Employee employee;
	private FacilityInterface realFacility;
	
	public ProxyFacility(Employee emp){
		employee = emp;
		realFacility = new RealFacility();
	}
	
	public void hireEmployee(){
		if (employee.getAge() < 18)
			System.out.println("Can't hire " + employee.getName() + ". He is too young.");
		else
			realFacility.hireEmployee();
	}
}
