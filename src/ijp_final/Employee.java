package ijp_final;

public class Employee {
	long id;
	String name;
	int age;
	String address;
	double salary;
	int sumTimeWork;

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee: ID: " + id + ", name: " + name + ", age: " + age + ", address: " + address + ", salary: "
				+ salary + ", sumTimeWork: " + sumTimeWork + "\n";
	}
}
