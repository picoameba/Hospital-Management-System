package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//class of the frame that provides the options to select the products,
//either by ID or by the category then the name then the size

class SelectPatient extends JFrame implements ActionListener {
	
	private JButton id = new JButton("Choose Product By ID");
	private JButton select = new JButton("Choose Product By Category");
	private JButton close = new JButton("Close");
	private Scanner fin;
	private ArrayList<Patient> productsList; // declare arraylist for the products
	private Patient selectedProduct;	// declare a product which we will pass to the Purchase JFrame

	//**********************************************************************************************
	//constructor 

	public SelectPatient() throws FileNotFoundException {
		super("Choose Product"); //the title of the JFrame

		fin = new Scanner(new FileReader("products.txt"));
		productsList = new ArrayList<>();
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
				description =  cin.nextLine();
			else
				description = "";
			productsList.add(new Patient (name,id,catagory,size,price,picture,quant,description));
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
		setSize(500,400); //setting the size
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.RED);
	}

	//**********************************************************************************************
	//method that extracts the list of categories from the file

	public static ArrayList<String> makeCategoryList(ArrayList<Patient> productList) {
		//declare arraylist of type string
		ArrayList<String> categoryList = new ArrayList<>();
		for(Patient p : productList) {
			if(!categoryList.contains(p.getCatagory())) {
				categoryList.add(p.getCatagory());
			}
		}
		return categoryList;
	}

	//**********************************************************************************************
	//method that extracts the list of names from a certain category in the file

	public static ArrayList<String> makeNameList(String catSelect, ArrayList<Patient> productList) {
		//declare arraylist of type string
		ArrayList<String> nameList = new ArrayList<>();
		for(Patient p : productList) {
			if(!nameList.contains(p.getName()) && p.getCatagory().contains(catSelect)) {
				nameList.add(p.getName());
			}
		}
		return nameList;
	}

	//**********************************************************************************************
	//method that extracts the available sizes of a certain product in the file

	public static ArrayList<String> makeSizeList(String nameSelect, String catSelect, ArrayList<Patient> productList) {
		//declare arraylist of type string
		ArrayList<String> sizeList = new ArrayList<>();
		for(Patient p : productList) {
			if(!sizeList.contains(p.getName()) && p.getCatagory().contains(catSelect) && p.getName().contains(nameSelect)) {
				sizeList.add(p.getSize());
			}
		}
		return sizeList;
	}
	//**********************************************************************************************
	//method that checks if a product id exists

	public boolean checkExists (int i) throws FileNotFoundException {
		Scanner fin =  new Scanner (new FileReader("Temp.txt"));
		int id;
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			cin.next();	//to skip the name, as it won't be used
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
		if (e.getSource() ==  id) { // the actions for the ID button which search the product by ID
			String s =  JOptionPane.showInputDialog(null, "Enter The Product ID", "Search Product by ID", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				selectedProduct =  new Patient();
				boolean check = false;
				if (!checkExists(i)) {
					for (Patient p: productsList) {
						if (p.getId() == i) {
							selectedProduct.makeCopy(p);
							check = true;
							dispose();
							new BookAppointment(selectedProduct);
						}							
					}
					if (!check)
						JOptionPane.showMessageDialog(null, "Enter a vaild ID" , "Invaild ID" , JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "You have this Item already!!" , "Item Exsists" , JOptionPane.ERROR_MESSAGE);

			}catch(Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Enter a vaild quantity" , "Invaild Quantity" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == close) { // actions for the close Button
			dispose();
			try {
				new CheckOut();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Products File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		else { //actions for the category Button which will search the product by category then name then size

			String [] catList =  makeCategoryList(productsList).toArray(new String[makeCategoryList(productsList).size()]);
			String catSelect = (String)JOptionPane.showInputDialog(null, "Select the product catagory","Search Catagory"
					,JOptionPane.QUESTION_MESSAGE,null,catList,catList[0]);	
			String [] nameList =  makeNameList(catSelect,productsList).toArray(new String[makeNameList(catSelect,productsList).size()]);
			String nameSelect = (String)JOptionPane.showInputDialog(null, "Select the product name","Search Name"
					,JOptionPane.QUESTION_MESSAGE,null,nameList,nameList[0]);
			String [] sizeList =  makeSizeList(nameSelect,catSelect,productsList).toArray(new String[makeSizeList(nameSelect,catSelect,productsList).size()]);
			String sizeSelect = (String)JOptionPane.showInputDialog(null, "Select the product size","Search Size"
					,JOptionPane.QUESTION_MESSAGE,null,sizeList,sizeList[0]);
			selectedProduct =  new Patient();
			for (Patient p: productsList) {
				if (p.getCatagory().contains(catSelect) && p.getName().contains(nameSelect)&& p.getSize().contains(sizeSelect)) 
					selectedProduct.makeCopy(p);
			}
			dispose();
			new BookAppointment(selectedProduct);

		}
	}
}