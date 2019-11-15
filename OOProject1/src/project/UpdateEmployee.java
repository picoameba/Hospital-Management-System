package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UpdateEmployee extends JFrame implements ActionListener, PrintToFile{

	//declare the JFrame elements
	private JButton cancel = new JButton("Cancel");
	protected JButton update = new JButton("Update");
	private JLabel name = new JLabel("Name of The Cashier:");
	private JLabel id = new JLabel("ID of The Cashier:");
	private JLabel pass = new JLabel("Password of The Cashier:");
	protected JTextField tname;
	protected JTextField tid;
	private JTextField tpass;

	//**********************************************************************************************
	//constructor

	public UpdateEmployee(Employee c) {
		super("Update Cashier"); //the title of the JFrame
		setLayout(new GridLayout(4,2)); //set Layout to GridLayout with 9 rows and two columns

		tname = new JTextField(c.getName());
		tid = new JTextField(c.getId()+"");
		tid.setEditable(false);
		tpass = new JTextField(c.getPass());

		// adding the JFrame elements
		add(name);	add(tname);
		add(id);	add(tid);
		add(pass);	add(tpass);
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
	//updates the details of the cashier in the Cashiers file

	public void printToFile(Object c) throws IOException{
		Scanner fin =  new Scanner (new FileReader("Cashier.txt"));
		ArrayList<Employee> cashiers = new ArrayList<>();
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int id =  cin.nextInt();
			String password = cin.next();
			cashiers.add(new Employee (name,id,password));
			cin.close();
		}
		fin.close();

		PrintWriter fout =  new PrintWriter ("Cashier.txt");
		for (Employee cash: cashiers) 
			if (cash.getId() ==  ((Employee)c).getId())
				fout.println(c);
			else
				fout.println(cash);
		fout.close();
	}

	//**********************************************************************************************
	//checks if name already exists 
	public boolean checkUserExists(String user) throws FileNotFoundException{
		Scanner fin =  new Scanner (new FileReader("Cashier.txt"));
		while (fin.hasNextLine()) {
			String name = fin.next();				
			if (user.equals(name)) {
				fin.close();
				return true;
			}
			else
				fin.nextLine(); //skips to next line
		}
		fin.close();
		return false;
	}


	//**********************************************************************************************
	//actionLister 

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) { //actions for the cancel Button
			try {
				dispose();
				new RegisterEmployee();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Product File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}

		}
		else if (e.getSource() == update){ //action for the update Button
			try {
				String name = tname.getText();
				if (!name.equals("")) {
					int id =  Integer.parseInt(tid.getText());
					String password = tpass.getText();
					Scanner fin = new Scanner(new FileReader("Cashier.txt"));
					boolean check=true;
					while (fin.hasNext()) {
						String name1= fin.next();
						int id1= fin.nextInt();
						String pass1=fin.next();
						if (name1.equals(name) && id1 != id)
							check=false;
					}
					if (check) {
					if (name.equals("") || password.equals("")){ //check if one of the fields are empty (name,catagory,size and picture)
						JOptionPane.showMessageDialog(null, "Please provide the name and password" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);
					}
					else {
						Employee cash = new Employee(name,id,password);
						printToFile(cash);
						JOptionPane.showMessageDialog(null, "Cashier List Updated" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new RegisterEmployee();
					}
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter a new name" , "Invaild Name" , JOptionPane.ERROR_MESSAGE);
				}
				else 
					JOptionPane.showMessageDialog(null, "Please enter a new name" , "Invaild Name" , JOptionPane.ERROR_MESSAGE);

			}catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}