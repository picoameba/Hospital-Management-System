package project;

public class Patient {
	
	// declare the patients variables
	private String name,sex,bloodType;
	private int age;
	
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
