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
	private JLabel name =  new JLabel("Name of The patient:");
	private JLabel id =  new JLabel("ID of The patient:");
	private JLabel cat =  new JLabel("Category of The patient:");
	private JLabel size =  new JLabel("Size of The patient:");
	private JLabel price =  new JLabel("Price of The patient:");
	private JLabel pic =  new JLabel("Picture of The patient:");
	private JLabel quant =  new JLabel("Quantity of The patient:");
	private JLabel dis =  new JLabel("Description of The patient:");
	private JTextField tname;
	protected JTextField tid;
	private JTextField tcat;
	private JTextField tsize;
	private JTextField tprice;
	private JTextField tpic;
	private JTextField tquant;
	private JTextField tdis;

	//**********************************************************************************************
	//constructor
	
	public UpdatePatient(Patient p) {
		super("Update patient"); //the title of the JFrame
		setLayout(new GridLayout(9,2)); //set Layout to GridLayout with 9 rows and two columns
		tname = new JTextField(p.getName());
		tid = new JTextField(p.getId()+"");
		tid.setEditable(false);
		tsize = new JTextField(p.getSize());
		tcat = new JTextField(p.getCatagory());
		tpic = new JTextField(p.getPicture());
		tdis = new JTextField(p.getDescription());
		tprice = new JTextField(p.getPrice()+"");
		tquant = new JTextField(p.getQuantity()+"");
		// adding the JFrame elements
		add(name);	add(tname);
		add(id);	add(tid);
		add(cat);	add(tcat);
		add(size);	add(tsize);
		add(price);	add(tprice);
		add(pic);	add(tpic);
		add(quant);	add(tquant);
		add(dis);	add(tdis);
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
		Scanner fin =  new Scanner (new FileReader("patients.txt"));
		ArrayList<Patient> patients = new ArrayList<>();
		String description;
		
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int id =  cin.nextInt();
			String catagory = cin.next();
			String size = cin.next();
			double price = cin.nextDouble();
			String picture = cin.next();
			int quant = cin.nextInt();
			if (cin.hasNext()) 
				description = cin.nextLine();
			else
				description = "";
			patients.add(new Patient (name,id,catagory,size,price,picture,quant,description));
			cin.close();
		}
		fin.close();
		
		PrintWriter fout =  new PrintWriter ("patients.txt");
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
				dispose();
				new CheckPatient();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "patient File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}

		}
		else if (e.getSource() == update){ //action for the update Button
			try {
				String name = tname.getText();
				int id =  Integer.parseInt(tid.getText());
				String catagory = tcat.getText();
				String size = tsize.getText();
				double price = Double.parseDouble(tprice.getText());
				String picture = tpic.getText();
				int quant = Integer.parseInt(tquant.getText());
				String desc =  tdis.getText();
				if (price <=  0||quant <=  0){ //check if the quantity or the prive is negative or positive
					JOptionPane.showMessageDialog(null, "Please enter Positive Quantity and Price" , "Invaild Price or Quantity" , JOptionPane.ERROR_MESSAGE);
				}
				else if (name.equals("") || catagory.equals("")|| size.equals("")|| picture.equals("")){ //check if one of the fields are empty (name,catagory,size and picture)
					JOptionPane.showMessageDialog(null, "Please fill Name,Catagory,Size and Picture" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);
				}
				else {
					Patient pro = new Patient(name,id,catagory,size,price,picture,quant,desc);
					printToFile(pro);
					JOptionPane.showMessageDialog(null, "patient List Updated" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
					dispose();
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