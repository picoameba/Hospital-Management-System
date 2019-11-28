package project;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//class of the frame for the admin that will show all the patient with some option like add new patient, remove patient and update exsists patient

class CheckPatient extends JFrame implements ActionListener{
	
	//declare an array to use it in the JLabel
	private String [] labelNames =  {"Name","age", "sex", "bloodType"};
	//Declare the JFrame elements
	private JLabel j[] = new JLabel[7];
	private JTextArea [] a =  new JTextArea[7];
	private JScrollPane scrollPane; //scrollPane that will include the textAreas
	private JButton add = new JButton ("Add new patient");
	private JButton remove =  new JButton ("Remove patient");
	private JButton update =  new JButton("Update patient");
	private JButton close =  new JButton("Close");
	private JPanel p1 =  new JPanel(new GridLayout(1,4));
	private JPanel p2 =  new JPanel(new GridLayout(1,4));
	private JPanel p3 =  new JPanel(new GridLayout(1,4));
	//Declare patient arraylist
	private ArrayList<Patient> patientsList;

	//**********************************************************************************************
	//constructor

	public CheckPatient () throws FileNotFoundException {
		super ("Check patients"); //title of the Jframe

		for (int i = 0; i<labelNames.length; i++) { 
			j[i] = new JLabel(labelNames[i]);
			p1.add(j[i]);
			a[i] = new JTextArea();
			a[i].setEditable(false);
			if (i != 6)
				p2.add(a[i]);
			a[i].setSelectionStart(0);
			a[i].setSelectionEnd(0);
		}
		JScrollPane horizontal = new JScrollPane(a[6]);
		horizontal.setPreferredSize(new Dimension(100, p2.getHeight()));
		horizontal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		p2.add(horizontal);
		scrollPane = new JScrollPane(p2);
		//read a file ("patients.txt") and save the it to arraylist patients
		Scanner fin =  new Scanner (new FileReader("patient.txt"));
		patientsList = new ArrayList<>();
		
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int age =  cin.nextInt();
			String sex = cin.next();
			String bloodType = cin.next();
			
			
			patientsList.add(new Patient (name,age,sex,bloodType));
			cin.close();
		}
		fin.close();

		//sort the patients list by age
		Collections.sort(patientsList, new Comparator<Patient>() {
			public int compare (Patient o1, Patient o2) {
				int comp = o1.getAge() - o2.getAge();
				return comp;
			}
		});


		for (Patient p: patientsList) { // add all the patients elements to the JTextArea to  manage the patients by the admin
			a[0].append(p.getName()+"\n");
			a[1].append(p.getAge()+"\n");
			a[2].append(p.getSex()+"\n");
			a[3].append(p.getBloodType()+"\n");
		
		}
		
		for (int i = 0; i < 4; i++) { //set the text to start from the top, useful when the text area is too large
			a[i].setSelectionStart(0);
			a[i].setSelectionEnd(0);
		}
		
		p3.add(add);
		p3.add(remove);
		p3.add(update);
		p3.add(close);
		add.addActionListener(this);
		remove.addActionListener(this);
		update.addActionListener(this);
		close.addActionListener(this);
		add(p1, "North");
		add(scrollPane);
		add(p3,"South");
		setVisible(true);
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);
		
		
	}

	//**********************************************************************************************
	//actionLister

	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == add) { //the actions for the Add Button
			new Patient_Registration().setVisible(true);
			dispose();
		}
		else if(e.getSource() == remove) {
			//ask the admin to enter the age of the patient to remove it from the list
			String s =  JOptionPane.showInputDialog(null, "Enter The patient age", "Search patient by age", JOptionPane.QUESTION_MESSAGE);
			int i;
			try {
				i = Integer.parseInt(s);
				PrintWriter fout =  new PrintWriter("patient.txt");
				boolean check = false;
				for (Patient Pro: patientsList) 
					if (Pro.getAge() == i)
						check = true;
					else
						fout.println(Pro);
				fout.close();
				if(check) {
					JOptionPane.showMessageDialog(null, "The patient has removed successfully" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new CheckPatient();
				}
				else
					JOptionPane.showMessageDialog(null, "The patient age was not found" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);

			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "patients File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			} 
			catch (Exception e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == update){ //the update for the Add Button
			//asking the admin to enter the age to update its information
			String s =  JOptionPane.showInputDialog(null, "Enter The patient age", "Search patient by age", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				boolean check = false;
				for (Patient Pro: patientsList) {
					if (Pro.getAge() == i) {
						dispose();
						new Patient_Registration().setVisible(true);;
						check = true;
					}
				}
				if (!check)
					JOptionPane.showMessageDialog(null, "The patient age is not correct" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);

			}catch (Exception e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}

		}
		else if(e.getSource() == close) {
			dispose();
		}
	}
}




