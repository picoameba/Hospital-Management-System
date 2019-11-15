package kuGrocery;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//class of the frame that provides the options

class Options extends JFrame implements ActionListener {

	private JPanel operations = new JPanel();
	private JTextArea info = new JTextArea();
	private JButton[] buttons = new JButton[6];
	private String[] buttonNames = {"Log In", "Log Out", 
			"Check Cashiers", "Check Products", 
			"Select Product", "Purchase Menu"};
	//initializes variables needed for the frame

	private Admin activeAccount;
	//declares variables needed for the actions

	//**********************************************************************************************
	//constructor 

	public Options(boolean userFound) {
		super("KU Grocery");

		activeAccount = POS.getActiveAccount(); //sets the active account as it is in the POS

		operations.setLayout(new GridLayout(3, 2));//
		setLayout(new GridLayout(2, 1));//
		setSize(600, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLUE);
		//sets necessary sizes and operations for the frame and panel

		for (int i = 0; i < 6; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			operations.add(buttons[i]);
			buttons[i].addActionListener(this);
			buttons[i].setOpaque(true);
		}//adds all the buttons to the panel
		info.setBackground(Color.WHITE);

		add(info);
		add(operations);
		//adds text area and panel to frame

		info.setEditable(false);
		if(activeAccount != null) {	//user logged in successfully
			info.setText("Welcome " + activeAccount.getName() + "!\n");
			buttons[0].setEnabled(false);
			buttons[0].setBackground(Color.LIGHT_GRAY);
			if(activeAccount instanceof Cashier) {
				buttons[2].setEnabled(false);
				buttons[3].setEnabled(false);
				buttons[2].setBackground(Color.LIGHT_GRAY);
				buttons[3].setBackground(Color.LIGHT_GRAY);
			}
			else if(activeAccount instanceof Admin) {
				buttons[4].setEnabled(false);
				buttons[5].setEnabled(false);
				buttons[4].setBackground(Color.LIGHT_GRAY);
				buttons[5].setBackground(Color.LIGHT_GRAY);
			}
		}
		else {	//user failed to log in
			for(int i = 1; i < 6; i++) {
				buttons[i].setEnabled(false);
				buttons[i].setBackground(Color.LIGHT_GRAY);
			}
			if(userFound) {	//wrong password
				info.setText("Error: Wrong Password.");
			}
			else {	//user not found
				info.setText("Error: User Not Found");
			}
		}

	}

	//**********************************************************************************************
	//action listener

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttons[0]) {		//log in
			new LogIn();
			dispose();
			//creates a log in frame, then closes this frame
		}
		else if(e.getSource() == buttons[1]) {	//log out
			for(int i = 1; i < 6; i++) {
				buttons[i].setEnabled(false);
				buttons[i].setBackground(Color.LIGHT_GRAY);
			}
			buttons[0].setEnabled(true);
			buttons[0].setBackground(Color.WHITE);
			activeAccount = null;
			info.append("Logged out successfully!");
			//closes all buttons besides log in and removes the active account
		}
		else if(e.getSource() == buttons[2]) {	//register cashier (Admin)
			try {
				new RegisterCashier();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Cashier List Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			info.append("Registering new cashier.\n");
		}
		else if(e.getSource() == buttons[3]) {	//check products (Admin)
			try {
				new CheckProduct();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Product List Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			info.append("Checking products.\n");
		}
		else if(e.getSource() == buttons[4]) {	//select product (Cashier)
			try {
				new SelectProduct();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Product List Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			info.append("Selecting product.\n");
		}
		else if(e.getSource() == buttons[5]) {	//purchase (Cashier)
			try {
				new CheckOut();
				info.append("Purchase complete!\n");
			}
			catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Bill not found" , "404" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
}