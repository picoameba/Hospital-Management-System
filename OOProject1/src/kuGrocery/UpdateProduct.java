package kuGrocery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//class used by the admin to update the details of a product 

class UpdateProduct extends JFrame implements ActionListener, PrintToFile{
	
	//declare the JFrame elements
	private JButton cancel = new JButton("Cancel");
	protected JButton update = new JButton("Update");
	private JLabel name =  new JLabel("Name of The Product:");
	private JLabel id =  new JLabel("ID of The Product:");
	private JLabel cat =  new JLabel("Category of The Product:");
	private JLabel size =  new JLabel("Size of The Product:");
	private JLabel price =  new JLabel("Price of The Product:");
	private JLabel pic =  new JLabel("Picture of The Product:");
	private JLabel quant =  new JLabel("Quantity of The Product:");
	private JLabel dis =  new JLabel("Description of The Product:");
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
	
	public UpdateProduct(Product p) {
		super("Update Product"); //the title of the JFrame
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
	//updates the file to match the new product information
	
	public void printToFile(Object p) throws IOException{
		Scanner fin =  new Scanner (new FileReader("products.txt"));
		ArrayList<Product> products = new ArrayList<>();
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
			products.add(new Product (name,id,catagory,size,price,picture,quant,description));
			cin.close();
		}
		fin.close();
		
		PrintWriter fout =  new PrintWriter ("products.txt");
		for (Product Pro: products) 
			if (Pro.getId() ==  ((Product)p).getId())
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
				new CheckProduct();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Product File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
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
					Product pro = new Product(name,id,catagory,size,price,picture,quant,desc);
					printToFile(pro);
					JOptionPane.showMessageDialog(null, "Product List Updated" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new CheckProduct();
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}