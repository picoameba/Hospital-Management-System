package project;
import java.io.*;
import java.util.*;

public class Employee {

	private String firstName, lastName, id, email, username, password;
	private int age;
	private EmployeeType ocupation;
	private Speciality speciality;



	public Employee(String firstName, String lastName, String id, String email, int age, String username, String password, EmployeeType ocupation, Speciality speciality) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.age = age;
		this.username = username;
		this.password = password;
		this.ocupation = ocupation;
		this.speciality = speciality;
	}
	public Employee(String username, String id, String password) {

		this.username = username;
		this.password = password;
		this.id=id;

	}
	public Employee() {
		this.firstName = "";
		this.lastName = "";
		this.id = "";
		this.email = "";
		this.age = 0;
		this.username = "";
		this.password = "";
		this.ocupation = null;
		this.speciality = null;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeType getOcupation() {
		return ocupation;
	}

	public void setOcupation(EmployeeType ocupation) {
		this.ocupation = ocupation;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void Save_Employee(Vector<Employee> save) {
		try {
			PrintWriter S = new PrintWriter("Employee.txt");
			for(int i = 0;i<save.size();i++) {
				S.println(save.elementAt(i).getFirstName()+" "+save.elementAt(i).getLastName()+" "+save.elementAt(i).getId()+" "+save.elementAt(i).getEmail()+" "+save.elementAt(i).getAge()+" "+save.elementAt(i).getUsername()+" "+save.elementAt(i).getPassword()+" "+save.elementAt(i).getOcupation()+" "+save.elementAt(i).getSpeciality());
				//System.out.println(save.elementAt(i).getFirstName()+" "+save.elementAt(i).getLastName()+" "+save.elementAt(i).getId()+" "+save.elementAt(i).getEmail()+" "+save.elementAt(i).getAge()+" "+save.elementAt(i).getUsername()+" "+save.elementAt(i).getPassword()+" "+save.elementAt(i).getOcupation()+" "+save.elementAt(i).getSpeciality());
			}
			S.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}
	public Vector<Employee> Load_Employee() {
		Vector<Employee> load = new Vector<Employee>();
		try {
			Scanner L = new Scanner(new FileReader("Employee.txt"));
			while (L.hasNext()) {
				load.add(new Employee(L.next(), L.next(), L.next(), L.next(), L.nextInt(), L.next(), L.next(), ocupation.valueOf(L.next()), speciality.valueOf(L.next())));
			}
			L.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return load;
	}

}