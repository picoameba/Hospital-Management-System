package kuGrocery;

public class Cashier extends Admin {
	
	//constructor
	public Cashier() {
		super("", 0, "");
	}
	
	//**********************************************************************************************
	//full constructor
	public Cashier(String user, int id, String pass) {
		super(user, id, pass);
	}

	//**********************************************************************************************
	//toString method
	
	public String toString() {
		return getName() + " " + getId() + " " + getPass();
	}

}
