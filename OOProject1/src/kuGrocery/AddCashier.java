package kuGrocery;

import java.io.*;
import java.util.*;
import javax.swing.*;

import kuGrocery.Cashier;
import kuGrocery.UpdateCashier;

import java.awt.event.*;


class AddCashier extends UpdateCashier {

	//constructor
	public AddCashier(ArrayList<Cashier> cashiers) {
		super(new Cashier());
		setTitle("Add Cashier");
		update.setText("Add");
		int lastCashier = cashiers.size() - 1;
		int newId = cashiers.get(lastCashier).getId() + 1;
		tid.setText(Integer.toString(newId));
	}

	//**********************************************************************************************
	//method that adds a product to the file

	public void printToFile(Object c) throws IOException{
		PrintWriter fout =  new PrintWriter (new FileWriter("cashier.txt", true));//appends to the file
		fout.println(c);
		fout.close();
	}

	//**********************************************************************************************
	//actionListener

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) { //actions for the add (update) Button
			String user =  tname.getText();
			try {
				if (!checkUserExists(user) && !user.equals("")) {
					super.actionPerformed(e);
				}
				else 
					JOptionPane.showMessageDialog(null, "Please enter a new name" , "Invaild Name" , JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Cashier File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else { //action for the cancel Button
			super.actionPerformed(e);
		}
	}
}
