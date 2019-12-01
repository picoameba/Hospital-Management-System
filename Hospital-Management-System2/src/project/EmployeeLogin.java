package project;

public class EmployeeLogin extends Admin {
	
	//constructor
	public EmployeeLogin() {
		super("", 0, "");
	}
	
	//**********************************************************************************************
	//full constructor
	public EmployeeLogin(String user, int id, String pass) {
		super(user, id, pass);
	}

	//**********************************************************************************************
	//toString method
	
	public String toString() {
		return getName() + " " + getId() + " " + getPass();
	}

}
