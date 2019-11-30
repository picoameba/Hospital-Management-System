package project;

public class Admin {

	private String name;
	private String id;
	private String pass;
	
	//**********************************************************************************************
	//getter methods
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}
	
	//**********************************************************************************************
	//full constructor
	
	public Admin(String name, String id, String pass) {
		this.name = name;
		this.id = id;
		this.pass = pass;
	}
	
	

}


