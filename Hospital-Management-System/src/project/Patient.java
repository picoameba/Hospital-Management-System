package project;

public class Patient {
	
	// declare the patients variables
<<<<<<< HEAD
	private String name, category, size, description, picture, mobileNumber, email;
	private int id, quantity;
	private double price;
=======
	private String name,sex,bloodType;
	private int age;
	
>>>>>>> branch 'master' of https://github.com/picoameba/Hospital-Management-System
	// end declaration

	//**********************************************************************************************
	//initialize the patient to default with default constructor

	public Patient() {
		name = "";
		age=0;
		sex="";
		bloodType="";
	}

	//**********************************************************************************************
	//initialize the patients information
	public Patient(String name,int age,String sex,String bloodType) {
		this.name = name;
		this.age=age;
		this.sex=sex;
		this.bloodType=bloodType;
	}

	//**********************************************************************************************
	// setter and getter methods

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
<<<<<<< HEAD
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
=======

>>>>>>> branch 'master' of https://github.com/picoameba/Hospital-Management-System
	// end setter and getter methods

	//**********************************************************************************************
	//method that makes a copy of one patient and assigns it to another

	public void makeCopy(Patient p) {
		name =  p.getName();
		age =  p.getAge();
		sex = p.getSex();
		bloodType = p.getBloodType();
		
	}
	//**********************************************************************************************
	public String toString() {
		return name +" "+age+" "+ sex +" "+ bloodType;
	}
}//endpatient
