package project;

public class Patient {
	
	// declare the patients variables
	private String name, category, size, description, picture, mobileNumber, email;
	private int id, quantity;
	private double price;
	// end declaration

	//**********************************************************************************************
	//initialize the patient to default with default constructor

	public Patient() {
		this.name = "";
		this.id = 0;
		this.category = "";
		this.size = "";
		this.picture = "";
		this.quantity = 0;
		this.price = 0;
		this.description = "";
	}

	//**********************************************************************************************
	//initialize the patients information
	public Patient(String name, int id, String catagory, String size, double price,
			String picture,  int quant, String discription) {
		this.name = name;
		this.id = id;
		this.category = catagory;
		this.size = size;
		this.picture = picture;
		this.quantity = quant;
		this.price = price;
		this.description = discription;
	}

	//**********************************************************************************************
	// setter and getter methods

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatagory() {
		return category;
	}
	public void setCatagory(String catagory) {
		this.category = catagory;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDiscription(String discription) {
		this.description = discription;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuant(int quant) {
		this.quantity = quant;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	// end setter and getter methods

	//**********************************************************************************************
	//method that makes a copy of one patient and assigns it to another

	public void makeCopy(Patient p) {
		name =  p.getName();
		id =  p.getId();
		category = p.getCatagory();
		size = p.getSize();
		picture = p.getPicture();
		quantity = p.getQuantity();
		price = p.getPrice();
		description = p.getDescription();
	}
	//**********************************************************************************************
	public String toString() {
		return name +" "+id+" "+ category +" "+ size+" "+price+" "+picture+" "+quantity+" "+ description;
	}
}//endpatient
