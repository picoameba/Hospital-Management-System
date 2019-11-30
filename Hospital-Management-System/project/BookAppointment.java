package project;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class BookAppointment extends JFrame implements ActionListener, PrintToFile {

	//declare the JFrame elements
	private JLabel confirmLabel =  new JLabel("Confirm the patient");
	private JLabel issue =  new JLabel("Choose the issue of the patient");
	
	private JTextArea details = new JTextArea();
	private JTextField patientIssue = new JTextField();
	private JButton cancel = new JButton ("Cancel");
	private JButton confirm = new JButton ("Confirm");
	private JPanel p1 = new JPanel (new GridLayout(2,2));
	private JPanel p2 = new JPanel (new GridLayout(1,2));
	private Patient selectedpatient = new Patient();

	//**********************************************************************************************
	//constructor

	public BookAppointment(Patient P) { // a frame which takes patient as a parameter
		super("patient details"); //the title of the frame and the layout is the default Border Layout
	//	selectedpatient.makeCopy(P); //Make a copy of the parameter patient to use it in the actionPerformed method
		details.setEditable(false); //disable the edit text for the patient details
		
		
		//adding the JPanel elements which will be in the center of the Border Layout
	
		p1.add(details);
		p1.add(issue);
		p1.add(patientIssue);
		p2.add(cancel);
		p2.add(confirm);

		//Print the patient information to check if it is the correct patient
//		details.append("Name:"+selectedpatient.getName()+"\nAge:"+selectedpatient.getAge()+"\nSex:"+selectedpatient.getSex()+"\nBloodType:"+selectedpatient.getBloodType()
	//	);

		//add the ActionListener to the JButtons
		cancel.addActionListener(this);
		confirm.addActionListener(this);

		// add the JFrame elements to the Border Layout
		add(confirmLabel, "North");
		add(p1);
		add(p2, "South");
		setVisible(true);
		setSize(600,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.RED);
	}
		


	//**********************************************************************************************
	//method that check if issue is positive or negative and 
	//checks if the issue that the employee asks for is more than the patient issue or not
/*
	public boolean checkpatientIssue(int issue, Patient p) {
		if (issue >= 0)
			return p.getissue()-issue<0?false:true;
		else
			return false;
	}
*/

	//**********************************************************************************************
	//method that checks if the patient id exists

	public boolean checkExists (int i) throws FileNotFoundException {
		Scanner fin =  new Scanner (new FileReader("Temp.txt"));
		int age;

		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			cin.next();
			age =  cin.nextInt();
			cin.close();
			if (age ==  i)
				return true;
		}
		fin.close();
		return false;
	}



	public void printToTemp(String name, int age, String bloodType, double gender, int patientIssue , int selectedpatientIssue) throws FileNotFoundException {
		PrintWriter fout = new PrintWriter(new FileOutputStream(new File("Temp.txt"),true));
		fout.println(name+" "+age+" "+bloodType+" "+gender+" "+patientIssue+" "+selectedpatientIssue);
		fout.close();
	}

	//**********************************************************************************************
	//method that updates the file ("patient.txt") with the new issue after the purchase done

	public void printToFile(Object p) throws FileNotFoundException{
		Scanner fin =  new Scanner (new FileReader("patient.txt"));
		ArrayList<Patient> patient = new ArrayList<>();
		String description;

		//Add all patient from file to arrayList
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int age =  cin.nextInt();
			String sex = cin.next();
			String bloodType = cin.next();
			double gender = cin.nextDouble();
			String picture = cin.next();
			int patientIssue = cin.nextInt();
			if (cin.hasNext()) 
				description =  cin.nextLine();
			else
				description = "";
		//	patient.add(new Patient (id, firstName, lastName, email, gender, bloodType, mobileNumber, telephone, insuranceType, age, weight, height));
			cin.close();
		}
		fin.close();

		//Print out the updated file
		PrintWriter fout =  new PrintWriter ("patient.txt");
		for (Patient Pro: patient) 
			if (Pro.getAge() == ((Patient)p).getAge())
				fout.println(p);
			else
				fout.println(Pro);
		fout.close();
	}

	//**********************************************************************************************
	//actionLister 

	public void actionPerformed (ActionEvent e){
		if (e.getSource() ==  cancel) { //the actions for cancel JButton
			dispose();
			try {
				new SelectPatient();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "patient File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
		}
	/*	else //the actions for Confirm JButton which will confirm the patient details and added to the bill
		{
			try {
				int issue =  Integer.parseInt(patientIssue.getText());
				if (checkpatientIssue(issue,selectedpatient) && issue != 0) {
					if (!checkExists(selectedpatient.getAge()))
						printToTemp(selectedpatient.getName(),selectedpatient.getAge(),
								selectedpatient.getBloodType(),selectedpatient.getgender(),selectedpatient.getissue(),issue);
					else
						JOptionPane.showMessageDialog(null, "You have this item already!!" , "Item Exists" , JOptionPane.ERROR_MESSAGE);
					new CheckOut();
					dispose();
				} 
				else
					JOptionPane.showMessageDialog(null, "Enter a vaild issue" , "Invaild issue" , JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Temp File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
		*/
	}
}