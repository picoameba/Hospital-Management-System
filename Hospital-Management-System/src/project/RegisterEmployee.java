package project;

import javax.swing.*;

import project.AddEmployee;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class RegisterEmployee extends JFrame implements ActionListener{

	private JLabel employeeName = new JLabel ("employee Name");
	private JLabel employeeID = new JLabel ("employee ID");
	private JLabel employeePass = new JLabel ("employee Pass");
	private JTextArea []textAreas = new JTextArea[3];
	private JButton addemployee = new JButton ("Add employee");
	private JButton updateemployee = new JButton ("Update employee");
	private JButton removeemployee = new JButton ("Remove employee");
	private JButton close = new JButton ("Close");
	private JScrollPane scrollPane; //JScrollPane that will include the textAreas
	private JPanel p1 = new JPanel(new GridLayout(1,3));
	private JPanel p2 = new JPanel(new GridLayout(1,3));
	private JPanel p3 = new JPanel(new GridLayout(1,4));
	private ArrayList<Employee> employeesList;

	//**********************************************************************************************
	//constructor

	public RegisterEmployee() throws FileNotFoundException {
		textAreas[0] = new JTextArea();
		textAreas[1] = new JTextArea();
		textAreas[2] = new JTextArea();
		textAreas[0].setEditable(false);
		textAreas[1].setEditable(false);
		textAreas[2].setEditable(false);

		Scanner fin = new Scanner(new FileReader("employee.txt"));
		employeesList = new ArrayList<>();

		while (fin.hasNext()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int id = cin.nextInt();
			String pass = cin.next();
			textAreas[0].append(name+"\n");
			textAreas[1].append(id+"\n");
			textAreas[2].append(pass+"\n");
			employeesList.add(new Employee (name,id,pass));
			cin.close();
		}
		fin.close();

		for (int i = 0; i < 3; i++) 
			p2.add(textAreas[i]);
		scrollPane = new JScrollPane(p2);

		p1.add(employeeName);
		p1.add(employeeID);
		p1.add(employeePass);
		add(scrollPane);
		p3.add(addemployee);
		p3.add(updateemployee);
		p3.add(removeemployee);
		p3.add(close);

		addemployee.addActionListener(this);
		removeemployee.addActionListener(this);
		updateemployee.addActionListener(this);
		close.addActionListener(this);

		add(p1, "North");
		add(p2);
		add(p3,"South");

		setVisible(true);
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);

	}
	//**********************************************************************************************
	//actionListener

	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == close) {	//close the frame
			dispose();
		}
		else if(e.getSource() == removeemployee) {	//remove a employee
			String s =  JOptionPane.showInputDialog(null, "Enter The employee ID", "Search employee by ID", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				PrintWriter fout =  new PrintWriter ("employee.txt");
				boolean check = false;
				Scanner rname = new Scanner(textAreas[0].getText());
				Scanner rid = new Scanner(textAreas[1].getText());
				Scanner rpass = new Scanner(textAreas[2].getText());
				while (rname.hasNext()) { //check if the ID entered is correct or not, if yes it will print all the elements except the entered ID
					String name = rname.next();
					int id = rid.nextInt();
					String size = rpass.next();
					if (id == i)
						check = true;
					else
						fout.println(name + " " + id + " " + size + " ");
				}
				rname.close();
				rid.close();
				rpass.close();
				fout.close();
				if (check) {
					JOptionPane.showMessageDialog(null, "The employee was removed seccussfully" , "Successful" , JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new RegisterEmployee();
				}
				else
					JOptionPane.showMessageDialog(null, "ID not found" , "Invaild ID" , JOptionPane.ERROR_MESSAGE);

			}catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild ID" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == addemployee) {//add a employee
			new AddEmployee(employeesList);
			dispose();
		}
		else if (e.getSource() == updateemployee){ //update a employee
			String s =  JOptionPane.showInputDialog(null, "Enter The employee ID", "Search employee by ID", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				boolean check = false;
				for (Employee employee: employeesList) {
					if (employee.getId() == i) {
						dispose();
						new UpdateEmployee(employee);
						check = true;
					}
				}
				if (!check)
					JOptionPane.showMessageDialog(null, "The employee ID is not found" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);

			}catch (Exception e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}


