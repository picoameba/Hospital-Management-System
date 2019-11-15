//class used by the admin to update the details of a product 

package project;

import java.io.*;
import java.util.*;
import javax.swing.*;

import kuGrocery.Product;
import kuGrocery.UpdateProduct;

import java.awt.event.*;


class AddProduct extends UpdateProduct implements ActionListener{
	
	//constructor
	public AddProduct() {
		super(new Product());
		setTitle("Add Product");
		update.setText("Add");
		tid.setEditable(true);
	}

	//**********************************************************************************************
	//method that adds a product to the file

	public void printToFile(Object p) throws IOException{
		PrintWriter fout =  new PrintWriter (new FileWriter("products.txt", true));//appends to the file
		fout.println(p);
		fout.close();
	}
	
	//**********************************************************************************************
	//method that checks if a product ID exists

	public boolean checkID(int i) throws FileNotFoundException{
		Scanner fin =  new Scanner (new FileReader("products.txt"));
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int id =  cin.nextInt();
			cin.close();
			if (id == i)
				return true;
		}
		fin.close();
		return false;
	}

	//**********************************************************************************************
	//actionListener

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) { //actions for the add (update) Button
			int id =  Integer.parseInt(tid.getText());
			try {
				if (!checkID(id) && id > 0) {
					super.actionPerformed(e);
				}
				else 
					JOptionPane.showMessageDialog(null, "Please enter a positive and new ID" , "Invaild ID" , JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Products File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else { //action for the cancel Button
			super.actionPerformed(e);
		}
	}
}
