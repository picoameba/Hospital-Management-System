package project;
//class of the frame that will show the product's picture and details and asks the employee for the quantity

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

class BookAppointment extends JFrame implements ActionListener, PrintToFile {

	//declare the JFrame elements
	private JLabel confirmLabel =  new JLabel("Confirm the product");
	private JLabel quantity =  new JLabel("Choose the quantity of the product");
	private JLabel image;
	private JTextArea details = new JTextArea();
	private JTextField quant = new JTextField();
	private JButton cancel = new JButton ("Cancel");
	private JButton confirm = new JButton ("Confirm");
	private JPanel p1 = new JPanel (new GridLayout(2,2));
	private JPanel p2 = new JPanel (new GridLayout(1,2));
	private Patient selectedProduct = new Patient();

	//**********************************************************************************************
	//constructor

	public BookAppointment(Patient P) { // a frame which takes products as a parameter
		super("Product details"); //the title of the frame and the layout is the default Border Layout
		selectedProduct.makeCopy(P); //Make a copy of the parameter product to use it in the actionPerformed method
		details.setEditable(false); //disable the edit text for the product details
		image = new JLabel("No Image Available");
		
		
		//adding the JPanel elements which will be in the center of the Border Layout
		p1.add(image);
		p1.add(details);
		p1.add(quantity);
		p1.add(quant);
		p2.add(cancel);
		p2.add(confirm);

		//Print the product information to check if it is the correct product
		details.append("Name:"+selectedProduct.getName()+"\nID:"+selectedProduct.getId()+"\nCatagory:"+selectedProduct.getCatagory()+"\nSize:"+selectedProduct.getSize()
		+"\nPrice:"+selectedProduct.getPrice()+"\nQuantity:"+selectedProduct.getQuantity()+"\nDescription: "+selectedProduct.getDescription());

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
		
		try {
			BufferedImage img = null;
		    img = ImageIO.read(new File(P.getPicture()+".jpg"));
		    Image ScaledImg = img.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
			image.setIcon(new ImageIcon(ScaledImg));// = new JLabel(); //import the image with format jpg
			image.setText("");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Image File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	}

	//**********************************************************************************************
	//method that check if quantity is positive or negative and 
	//checks if the quantity that the employee asks for is more than the product quantity or not

	public boolean checkQuant(int quantity, Patient p) {
		if (quantity >= 0)
			return p.getQuantity()-quantity<0?false:true;
		else
			return false;
	}


	//**********************************************************************************************
	//method that checks if the product id exists

	public boolean checkExists (int i) throws FileNotFoundException {
		Scanner fin =  new Scanner (new FileReader("Temp.txt"));
		int id;

		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			cin.next();
			id =  cin.nextInt();
			cin.close();
			if (id ==  i)
				return true;
		}
		fin.close();
		return false;
	}

	//**********************************************************************************************
	//prints the details of a purchase to a temporary file, for use when creating the bill

	public void printToTemp(String name, int id, String size, double price, int quant , int selectedQuant) throws FileNotFoundException {
		PrintWriter fout = new PrintWriter(new FileOutputStream(new File("Temp.txt"),true));
		fout.println(name+" "+id+" "+size+" "+price+" "+quant+" "+selectedQuant);
		fout.close();
	}

	//**********************************************************************************************
	//method that updates the file ("products.txt") with the new quantity after the purchase done

	public void printToFile(Object p) throws FileNotFoundException{
		Scanner fin =  new Scanner (new FileReader("products.txt"));
		ArrayList<Patient> products = new ArrayList<>();
		String description;

		//Add all products from file to arrayList
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
			products.add(new Patient (name,id,catagory,size,price,picture,quant,description));
			cin.close();
		}
		fin.close();

		//Print out the updated file
		PrintWriter fout =  new PrintWriter ("products.txt");
		for (Patient Pro: products) 
			if (Pro.getId() == ((Patient)p).getId())
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
				JOptionPane.showMessageDialog(null, "Products File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else //the actions for Confirm JButton which will confirm the product details and added to the bill
		{
			try {
				int quantity =  Integer.parseInt(quant.getText());
				if (checkQuant(quantity,selectedProduct) && quantity != 0) {
					if (!checkExists(selectedProduct.getId()))
						printToTemp(selectedProduct.getName(),selectedProduct.getId(),
								selectedProduct.getSize(),selectedProduct.getPrice(),selectedProduct.getQuantity(),quantity);
					else
						JOptionPane.showMessageDialog(null, "You have this item already!!" , "Item Exists" , JOptionPane.ERROR_MESSAGE);
					new CheckOut();
					dispose();
				} 
				else
					JOptionPane.showMessageDialog(null, "Enter a vaild quantity" , "Invaild Quantity" , JOptionPane.ERROR_MESSAGE);
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
	}
}