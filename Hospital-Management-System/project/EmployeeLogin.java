package project;

public class EmployeeLogin extends Admin {
	
	//constructor
	public EmployeeLogin() {
		super("", "", "");
	}
	
	//**********************************************************************************************
	//full constructor
	public EmployeeLogin(String user, String id, String pass) {
		super(user, id, pass);
	}

	//**********************************************************************************************
	//toString method
	
	public String toString() {
		return getName() + " " + getId() + " " + getPass();
	}

}
