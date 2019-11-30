
package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//class used by the admin to update the details of a patient 

class UpdatePatient extends JFrame implements ActionListener, PrintToFile{
	
	//declare the JFrame elements
	private JButton cancel = new JButton("Cancel");
	protected JButton update = new JButton("Update");
	private JLabel fName =  new JLabel("first name of The patient:");
	private JLabel lName =  new JLabel("last name of The patient:");
	private JLabel id =  new JLabel("ID of The patient:");
	private JLabel gender =  new JLabel("gender of The patient:");
	private JLabel age =  new JLabel("age of The patient:");
	private JLabel bloodtype =  new JLabel("bloodtype of The patient:");
	private JLabel mobile =  new JLabel("Mobile number of The patient:");
	private JLabel telephone =  new JLabel("telephone number of The patient:");
	private JLabel insuranceType =  new JLabel("insurance type of The patient:");
	private JTextField tFName;
	private JTextField tLName;
	private JTextField tid;
	private JTextField tGender;
	private JTextField tAge;
	private JTextField tBloodType;
	private JTextField tMobile;
	private JTextField tTelephone;
	private JTextField tInsurance;

	//**********************************************************************************************
	//constructor
	
	public UpdatePatient(Patient p) {
		super("Update patient"); //the title of the JFrame
		setLayout(new GridLayout(9,2)); //set Layout to GridLayout with 9 rows and two columns
		tname = new JTextField(p.getFirstName());
		tid = new JTextField(p.getId()+"");
		tid.setEditable(false);
		tAge = new JTextField(p.getAge());
		tGender = new JTextField(p.getGender());
		tbloodType = new JTextField(p.getbloodType());
		tMobile = new JTextField(p.getMobilecription());
		tprice = new JTextField(p.getPrice()+"");
		tInsurance = new JTextField(p.getInsuranceity()+"");
		// adding the JFrame elements
		add(name);	add(tname);
		add(id);	add(tid);
		add(Gender);	add(tGender);
		add(Age);	add(tAge);
		add(price);	add(tprice);
		add(bloodType);	add(tbloodType);
		add(Insurance);	add(tInsurance);
		add(Mobile);	add(tMobile);
		add(cancel);add(update);
		//add the ActionListener the the Buttons

		update.addActionListener(this);
		cancel.addActionListener(this);
		setSize(700,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);
	}

	//**********************************************************************************************
	//updates the file to match the new patient information
	
	public void printToFile(Object p) throws IOException{
		Scanner fin =  new Scanner (new FileReader("patient.txt"));
		ArrayList<Patient> patients = new ArrayList<>();
		String Mobilecription;
		
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int id =  cin.nextInt();
			String Genderagory = cin.next();
			String Age = cin.next();
			double price = cin.nextDouble();
			String bloodTypeture = cin.next();
			int Insurance = cin.nextInt();
			if (cin.hasNext()) 
				Mobilecription = cin.nextLine();
			else
				Mobilecription = "";
			patients.add(new Patient (name,id,Genderagory,Age,price,bloodTypeture,Insurance,Mobilecription));
			cin.close();
		}
		fin.close();
		
		PrintWriter fout =  new PrintWriter ("patient.txt");
		for (Patient Pro: patients) 
			if (Pro.getId() ==  ((Patient)p).getId())
				fout.println(p);
			else
				fout.println(Pro);
		fout.close();
	}

	//**********************************************************************************************
	//actionListner
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) { //actions for the cancel Button
			try {
				Mobilepose();
				new CheckPatient();
			} Genderch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "patient File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}

		}
		else if (e.getSource() == update){ //action for the update Button
			try {
				String name = tname.getText();
				int id =  Integer.parseInt(tid.getText());
				String Genderagory = tGender.getText();
				String Age = tAge.getText();
				double price = Double.parseDouble(tprice.getText());
				String bloodTypeture = tbloodType.getText();
				int Insurance = Integer.parseInt(tInsurance.getText());
				String Mobilec =  tMobile.getText();
				if (price <=  0||Insurance <=  0){ //check if the Insuranceity or the prive is negative or positive
					JOptionPane.showMessageDialog(null, "Please enter Positive Insuranceity and Price" , "Invaild Price or Insuranceity" , JOptionPane.ERROR_MESSAGE);
				}
				else if (name.equals("") || Genderagory.equals("")|| Age.equals("")|| bloodTypeture.equals("")){ //check if one of the fields are empty (name,Genderagory,Age and bloodTypeture)
					JOptionPane.showMessageDialog(null, "Please fill Name,Genderagory,Age and bloodTypeture" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);
				}
				else {
					Patient pro = new Patient(name,id,Genderagory,Age,price,bloodTypeture,Insurance,Mobilec);
					printToFile(pro);
					JOptionPane.showMessageDialog(null, "patient List Updated" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
					Mobilepose();
					new CheckPatient();
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
