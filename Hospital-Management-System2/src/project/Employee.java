package project;

public class Employee extends Admin {
	
	//constructor
	public Employee() {
		super("", 0, "");
	}
	
	//**********************************************************************************************
	//full constructor
	public Employee(String user, int id, String pass) {
		super(user, id, pass);
	}

	//**********************************************************************************************
	//toString method
	
	public String toString() {
		return getName() + " " + getId() + " " + getPass();
	}

}
