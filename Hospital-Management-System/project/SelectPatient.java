package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//class of the frame that provides the options to select the patients,
//either by ID or by the category then the firstName then the sex

class SelectPatient extends JFrame implements ActionListener {
	
	private JButton id = new JButton("Choose patient By ID");
	private JButton select = new JButton("Choose patient By firstName");
	private JButton close = new JButton("Close");
	private Scanner fin;
	private ArrayList<Patient> patientsList; // declare arraylist for the patients
	private Patient selectedpatient;	// declare a patient which we will pass to the Purchase JFrame

	//**********************************************************************************************
	//constructor 

	public SelectPatient() throws FileNotFoundException {
		super("Choose patient"); //the title of the JFrame

		fin = new Scanner(new FileReader("patient.txt"));
		patientsList = new ArrayList<>();
		String description;

		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String ID=cin.next();
			String firstName = cin.next();
			String lastName = cin.next();
			String email = cin.next();
			String gender = cin.next();
			int age =  cin.nextInt();
			String mobileNumber = cin.next();
			String sex = cin.next();
			String bloodType = cin.next();
			String telephone = cin.next();
			String insuranceType = cin.next();
			double weight =  cin.nextDouble();
			double height =  cin.nextDouble();
			patientsList.add(new Patient (ID, firstName, lastName, email, gender, bloodType, mobileNumber, telephone, insuranceType, age, weight, height));
			cin.close();
		}
		
		fin.close();
		setLayout(new GridLayout(3,1)); //set the layout to Gridlayout with 3 rows and one close
		
		//add the elements to the JFrame
		add(id);
		add(select);
		add(close);
		//add ActionListener to the Buttons
		
		id.addActionListener(this);
		select.addActionListener(this);
		close.addActionListener(this);
		
		setVisible(true);
		setSize(500,400); //setting the sex
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.RED);
	}

	//**********************************************************************************************
	//method that extracts the list of categories from the file

	public static ArrayList<String> makeCategoryList(ArrayList<Patient> patientList) {
		//declare arraylist of type string
		ArrayList<String> sexList = new ArrayList<>();
		for(Patient p : patientList) {
			
		}
		return sexList;
	}

	//**********************************************************************************************
	//method that extracts the list of firstNames from a certain category in the file

	/* public static ArrayList<String> makefirstNameList(String catSelect, ArrayList<Patient> patientList) {
		//declare arraylist of type string
		ArrayList<String> firstNameList = new ArrayList<>();
		for(Patient p : patientList) {
			if(!firstNameList.contains(p.getfirstName()) /*&& p.getSex().contains(catSelect)) {
				firstNameList.add(p.getfirstName());
			}
		}
		return firstNameList; 
	} */

	//**********************************************************************************************
	//method that extracts the available sexs of a certain patient in the file

	public static ArrayList<String> makesexList(String firstNameSelect, String catSelect, ArrayList<Patient> patientList) {
		//declare arraylist of type string
		ArrayList<String> sexList = new ArrayList<>();
		for(Patient p : patientList) {
	/*		if(!sexList.contains(p.getfirstName())  && p.getSex().contains(catSelect) && p.getfirstName().contains(firstNameSelect)) {
				sexList.add(p.getsex());
			} */
		}
		return sexList;
	}
	//**********************************************************************************************
	//method that checks if a patient id exists

	public boolean checkExists (int i) throws FileNotFoundException {
		Scanner fin =  new Scanner (new FileReader("Temp.txt"));
		int id;
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			cin.next();	//to skip the firstName, as it won't be used
			id =  cin.nextInt();
			cin.close();
			if (id ==  i)
				return true;
		}
		fin.close();
		return false;
	}
	

	//**********************************************************************************************
	//actionLister

	public void actionPerformed (ActionEvent e) {
		if (e.getSource() ==  id) { // the actions for the ID button which search the patient by ID
			String s =  JOptionPane.showInputDialog(null, "Enter The patient ID", "Search patient by ID", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				selectedpatient =  new Patient();
				boolean check = false;
				if (!checkExists(i)) {
					for (Patient p: patientsList) {
						if (p.getAge() == i) {
					//		selectedpatient.makeCopy(p);
							check = true;
							dispose();
							new BookAppointment(selectedpatient);
						}							
					}
					if (!check)
						JOptionPane.showMessageDialog(null, "Enter a vaild ID" , "Invaild ID" , JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Patient alreadt exist!!" , "patient Exsists" , JOptionPane.ERROR_MESSAGE);

			}catch(Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Enter a vaild quantity" , "Invaild Quantity" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == close) { // actions for the close Button
			dispose();
			try {
				new Bill();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "patient File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		/*
		else { //actions for the category Button which will search the patient by  firstName then sex

			String [] catList =  makeCategoryList(patientsList).toArray(new String[makeCategoryList(patientsList).sexList()]);
			String catSelect = (String)JOptionPane.showInputDialog(null, "Select the patient Sex","Search Sex"
					,JOptionPane.QUESTION_MESSAGE,null,catList,catList[0]);	
			String [] firstNameList =  makefirstNameList(catSelect,patientsList).toArray(new String[makefirstNameList(catSelect,patientsList).sex()]);
			String firstNameSelect = (String)JOptionPane.showInputDialog(null, "Select the patient firstName","Search firstName"
					,JOptionPane.QUESTION_MESSAGE,null,firstNameList,firstNameList[0]);
			String [] sexList =  makesexList(firstNameSelect,catSelect,patientsList).toArray(new String[makesexList(firstNameSelect,catSelect,patientsList).sex()]);
			String sexSelect = (String)JOptionPane.showInputDialog(null, "Select the patient sex","Search sex"
					,JOptionPane.QUESTION_MESSAGE,null,sexList,sexList[0]);
			selectedpatient =  new Patient();
			for (Patient p: patientsList) {
				if (p.getSex().contains(catSelect) && p.getfirstName().contains(firstNameSelect)&& p.getSex().contains(sexSelect)) 
					selectedpatient.makeCopy(p);
			} 
			*/
			dispose();
			new BookAppointment(selectedpatient);

		} 
	}
