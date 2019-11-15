package kuGrocery;

import javax.swing.*;

import project.AddProduct;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//class of the frame for the admin that will show all the product with some option like add new product, remove product and update exsists product

class CheckProduct extends JFrame implements ActionListener{
	
	//declare an array to use it in the JLabel
	private String [] labelNames =  {"Name","ID", "Catagory", "Size" , "Price", "Quantity" , "Description"};
	//Declare the JFrame elements
	private JLabel j[] = new JLabel[7];
	private JTextArea [] a =  new JTextArea[7];
	private JScrollPane scrollPane; //scrollPane that will include the textAreas
	private JButton add = new JButton ("Add new Product");
	private JButton remove =  new JButton ("Remove Product");
	private JButton update =  new JButton("Update Product");
	private JButton close =  new JButton("Close");
	private JPanel p1 =  new JPanel(new GridLayout(1,7));
	private JPanel p2 =  new JPanel(new GridLayout(1,7));
	private JPanel p3 =  new JPanel(new GridLayout(1,4));
	//Declare product arraylist
	private ArrayList<Product> productsList;

	//**********************************************************************************************
	//constructor

	public CheckProduct () throws FileNotFoundException {
		super ("Check Products"); //title of the Jframe

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
		//read a file ("products.txt") and save the it to arraylist products
		Scanner fin =  new Scanner (new FileReader("products.txt"));
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
			productsList.add(new Product (name,id,catagory,size,price,picture,quant,description));
			cin.close();
		}
		fin.close();

		//sort the products list by id
		Collections.sort(productsList, new Comparator<Product>() {
			public int compare (Product o1, Product o2) {
				int comp = o1.getId() - o2.getId();
				return comp;
			}
		});


		for (Product p: productsList) { // add all the products elements to the JTextArea to  manage the products by the admin
			a[0].append(p.getName()+"\n");
			a[1].append(p.getId()+"\n");
			a[2].append(p.getCatagory()+"\n");
			a[3].append(p.getSize()+"\n");
			a[4].append(p.getPrice()+"\n");
			a[5].append(p.getQuantity()+"\n");
			a[6].append(p.getDescription()+"\n");
		}
		
		for (int i = 0; i < 7; i++) { //set the text to start from the top, useful when the text area is too large
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
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);
		
		
	}

	//**********************************************************************************************
	//actionLister

	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == add) { //the actions for the Add Button
			new AddProduct();
			dispose();
		}
		else if(e.getSource() == remove) {
			//ask the admin to enter the ID of the product to remove it from the list
			String s =  JOptionPane.showInputDialog(null, "Enter The Product ID", "Search Product by ID", JOptionPane.QUESTION_MESSAGE);
			int i;
			try {
				i = Integer.parseInt(s);
				PrintWriter fout =  new PrintWriter("products.txt");
				boolean check = false;
				for (Product Pro: productsList) 
					if (Pro.getId() == i)
						check = true;
					else
						fout.println(Pro);
				fout.close();
				if(check) {
					JOptionPane.showMessageDialog(null, "The Product has removed successfully" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new CheckProduct();
				}
				else
					JOptionPane.showMessageDialog(null, "The Product ID was not found" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);

			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Products File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			} 
			catch (Exception e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == update){ //the update for the Add Button
			//asking the admin to enter the ID to update its information
			String s =  JOptionPane.showInputDialog(null, "Enter The Product ID", "Search Product by ID", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				boolean check = false;
				for (Product Pro: productsList) {
					if (Pro.getId() == i) {
						dispose();
						new UpdateProduct(Pro);
						check = true;
					}
				}
				if (!check)
					JOptionPane.showMessageDialog(null, "The Product ID is not correct" , "Unsuccessfull!!" , JOptionPane.ERROR_MESSAGE);

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


