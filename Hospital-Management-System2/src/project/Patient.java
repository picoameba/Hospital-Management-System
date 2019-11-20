package project;

public class Patient {
	
	// declare the patients variables
	private String name, gender, appointments, medicinePrec ;
	private int id, quantity;
	private double bill;
	// end declaration

	//**********************************************************************************************
	//initialize the patient to default with default constructor

	public Patient() {
		this.name = "";
		this.id = 0;
		this.gender = "";
		this.appointments = "";
		
		this.quantity = 0;
		this.bill = 0;
		this.medicinePrec = "";
	}

	//**********************************************************************************************
	//initialize the patients information
	public Patient(String name, int id, String catagory, String appointments, double bill,
			  int quant, String discription) {
		this.name = name;
		this.id = id;
		this.gender = catagory;
		this.appointments = appointments;
	
		this.quantity = quant;
		this.bill = bill;
		this.medicinePrec = discription;
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
		return gender;
	}
	public void setCatagory(String catagory) {
		this.gender = catagory;
	}
	public String getappointments() {
		return appointments;
	}
	public void setappointments(String appointments) {
		this.appointments = appointments;
	}
	public String getmedicinePrec() {
		return medicinePrec;
	}
	public void setDiscription(String discription) {
		this.medicinePrec = discription;
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
	public double getbill() {
		return bill;
	}
	public void setbill(double bill) {
		this.bill = bill;
	}
	// end setter and getter methods

	//**********************************************************************************************
	//method that makes a copy of one patient and assigns it to another

	public void makeCopy(Patient p) {
		name =  p.getName();
		id =  p.getId();
		gender = p.getCatagory();
		appointments = p.getappointments();
		
		quantity = p.getQuantity();
		bill = p.getbill();
		medicinePrec = p.getmedicinePrec();
	}
	//**********************************************************************************************
	public String toString() {
		return name +" "+id+" "+ gender +" "+ appointments+" "+bill+" "+" "+quantity+" "+ medicinePrec;
	}
}//endpatient
