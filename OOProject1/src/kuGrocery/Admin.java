package kuGrocery;

public class Admin {

	private String name;
	private int id;
	private String pass;
	
	//**********************************************************************************************
	//getter methods
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}
	
	//**********************************************************************************************
	//full constructor
	
	public Admin(String name, int id, String pass) {
		this.name = name;
		this.id = id;
		this.pass = pass;
	}
	
	

}
