package project;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Patient{
//Variables
private String firstName, lastName, id, email, gender, bloodType, mobileNumber, telephone, insuranceType;
private int age;
private double weight, height;
//Constructors
public Patient(String id, String firstName, String lastName, String email, String gender, String bloodType, String mobileNumber,
		String telephone, String insuranceType, int age, double weight, double height) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.gender = gender;
	this.bloodType = bloodType;
	this.mobileNumber = mobileNumber;
	this.telephone = telephone;
	this.insuranceType = insuranceType;
	this.age = age;
	this.weight = weight;
	this.height = height;
}
public Patient() {
	super();
	this.id = "";
	this.firstName = "";
	this.lastName = "";
	this.email = "";
	this.gender = "";
	this.bloodType = "";
	this.mobileNumber = "";
	this.telephone = "";
	this.insuranceType = "";
	this.age = 0;
	this.weight = 0.0;
	this.height = 0.0;
}
//Setters and getters
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getBloodType() {
	return bloodType;
}
public void setBloodType(String bloodType) {
	this.bloodType = bloodType;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getInsuranceType() {
	return insuranceType;
}
public void setInsuranceType(String insuranceType) {
	this.insuranceType = insuranceType;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public double getWeight() {
	return weight;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public double getHeight() {
	return height;
}
public void setHeight(double height) {
	this.height = height;
}
//Other functions
public void printToFile(Patient p) throws IOException{
	try {
		PrintWriter pout = new PrintWriter("Patient.txt");
			pout.println(p.getId()+" "+p.getFirstName()+" "+p.getLastName()+" "+p.getEmail()+" "+p.getGender()+" "+p.getBloodType()+" "+p.getMobileNumber()+" "+p.getTelephone()+" "
			+p.getInsuranceType()+" "+p.getAge()+" "+p.getWeight()+" "+p.getHeight());
		pout.close();
	}
	catch(FileNotFoundException b){
		//Returns location of Exception
		System.out.println(b.getStackTrace().toString());
	}
}
public void Save_Patient(Vector<Patient> save) {
	try {
		PrintWriter pout = new PrintWriter("Patient.txt");
		for(int i =0; i<save.size();i++)
		{
			//Write data to file
			pout.println(save.get(i).getId()+" "+save.get(i).getFirstName()+" "+save.get(i).getLastName()+" "+save.get(i).getEmail()+" "+save.get(i).getGender()+" "+save.get(i).getBloodType()+" "+save.get(i).getMobileNumber()+" "+save.get(i).getTelephone()+" "
			+save.get(i).getInsuranceType()+" "+save.get(i).getAge()+" "+save.get(i).getWeight()+" "+save.get(i).getHeight());
		}
		pout.close();
	}
	catch(IndexOutOfBoundsException a) {
		//Returns location of Exception
		System.out.println(a.getStackTrace().toString());
	}
	catch(FileNotFoundException b){
		//Returns location of Exception
		System.out.println(b.getStackTrace().toString());
	}
}
public Vector<Patient> Loader_Patient(){
	Vector<Patient> temp=new Vector<Patient>();
	try {
		Scanner pin = new Scanner(new FileReader("Patient.txt"));
		temp.add(new Patient(id, firstName, lastName, email, gender, bloodType, mobileNumber, telephone, insuranceType, age, weight, height));
		pin.close();
		return temp;
	}
	catch(FileNotFoundException c){
		//Returns location of Exception
		System.out.println(c.getStackTrace().toString());
		return temp;
	}
}
public void Patient_History_Writer(HistoryHolder h) {
	try {
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)));
		pout.println(h.getpId()+" "+h.getdId()+" "+h.getTime()+" "+h.getDate());
		pout.println(h.getStatus()+" "+h.getEventType());
		pout.println(h.getDiagnosis());
		pout.println(h.getPrescription());
		pout.println(h.getLabRequest());
		pout.close();
	}
	catch(FileNotFoundException e) {
		e.printStackTrace();
	}
}

public class Patient {
	//Variables
	private String firstName, lastName, id, email, gender, bloodType, mobileNumber, telephone, insuranceType;
	private int age;
	private double weight, height;
	//Constructors
	public Patient(String id, String firstName, String lastName, String email, String gender, String bloodType, String mobileNumber,
			String telephone, String insuranceType, int age, double weight, double height) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.bloodType = bloodType;
		this.mobileNumber = mobileNumber;
		this.telephone = telephone;
		this.insuranceType = insuranceType;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}
	public Patient() {
		super();
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.gender = "";
		this.bloodType = "";
		this.mobileNumber = "";
		this.telephone = "";
		this.insuranceType = "";
		this.age = 0;
		this.weight = 0.0;
		this.height = 0.0;
	}
	//Setters and getters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	//Other functions
	public void Save_Patient(Vector<Patient> save) {
		try {
			PrintWriter pout = new PrintWriter("Patient.txt");
			for(int i =0; i<save.size();i++)
			{
				//Write data to file
				pout.println(save.get(i).getId()+" "+save.get(i).getFirstName()+" "+save.get(i).getLastName()+" "+save.get(i).getEmail()+" "+save.get(i).getGender()+" "+save.get(i).getBloodType()+" "+save.get(i).getMobileNumber()+" "+save.get(i).getTelephone()+" "
						+save.get(i).getInsuranceType()+" "+save.get(i).getAge()+" "+save.get(i).getWeight()+" "+save.get(i).getHeight());
			}
			pout.close();
		}
		catch(IndexOutOfBoundsException a) {
			//Returns location of Exception
			System.out.println(a.getStackTrace().toString());
		}
		catch(FileNotFoundException b){
			//Returns location of Exception
			System.out.println(b.getStackTrace().toString());
		}
	}
	public Vector<Patient> Loader_Patient(){
		Vector<Patient> temp=new Vector<Patient>();
		try {
			Scanner pin = new Scanner(new FileReader("Patient.txt"));
			temp.add(new Patient(id, firstName, lastName, email, gender, bloodType, mobileNumber, telephone, insuranceType, age, weight, height));
			pin.close();
			return temp;
		}
		catch(FileNotFoundException c){
			//Returns location of Exception
			System.out.println(c.getStackTrace().toString());
			return temp;
		}
	}
	public void Patient_History_Writer() {

	}
	public void Send_email(String ID) throws AddressException, MessagingException {
		//Sending the appointment email
		String recipient = "None";
		String sender = "KU_Hospital@ku.ac.ae";
		String host = "localhost";
		String pFname,docID, docFname, docLname, dept, time, date;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
			//Open the required to be scanned for additional information
			Scanner ap = new Scanner(new FileReader("Appointments.txt"));
			Scanner pat = new Scanner(new FileReader("Patient.txt"));
			Scanner doc = new Scanner(new FileReader("Doctors.txt"));
			while(ap.hasNext()) {
				if(ap.next()==ID) {
					//Getting doctor ID, time and date
					docID = ap.next();
					time = ap.next();
					date = ap.next();
				}
				else {
					for(int i=0;i<3;i++) {
						ap.next();//Cycling through the file
					}
				}
			}
			while(pat.hasNext()) {
				//Finding the Patient first name and email
				if (pat.next()==ID) {
					pFname = pat.next();
					pat.next();
					recipient = pat.next();
					for(int i=0;i<8;i++) {
						pat.next();//Cycling through the file
					}
				}
				else {
					for(int i=0;i<11;i++) {
						pat.next();//Cycling through the file
					}
				}
			}
			while(doc.hasNext()) {
				//Getting the Doctor's name and department
				if (doc.next()==ID) {
					docFname = doc.next();
					docLname = doc.next();
					dept = doc.next();
					for(int i=0;i<8;i++) {
						doc.next();//Cycling through the file
					}
				}
				else {
					for(int i=0;i<11;i++) {
						doc.next();//Cycling through the file
					}
				}
			}
		}
		//Preparing the message to be sent
		/*    if(!recipient.equals("None")) {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            //message.addRecipients(Message.RecipientType.TO, new Address[...]); // email to multiple recipients
            message.setSubject("Appointment Confirmation: KU Hospital");
            message.setText("Your appointment has been booked");
            //Closing the files and sending
            ap.close();
            pat.close();
            doc.close();
            //Sending the message
            Transport.send(message);

        }
*/
    } 
    catch(FileNotFoundException c) {
    	//Printing origin of exception
    	c.printStackTrace();
    }
    catch (AddressException e) {
    	//Printing origin of exception
        e.printStackTrace();
    } catch (MessagingException e) {
    	//Printing origin of exception
        e.printStackTrace();
    }
}

        } 


		catch(FileNotFoundException c) {
			//Printing origin of exception
			c.printStackTrace();
		}


	}  





}




