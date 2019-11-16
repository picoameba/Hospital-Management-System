package project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//class of the login frame

class LogIn extends JFrame implements ActionListener {

	private JLabel userLabel = new JLabel("Enter User: ", SwingConstants.CENTER);
	private JLabel passLabel = new JLabel("Enter Password: ", SwingConstants.CENTER);
	private JTextField id = new JTextField();
	private JTextField pass = new JTextField();
	private JButton admin = new JButton("Log In as Admin");
	private JButton employee = new JButton("Log In as Employee");
	//creates elements needed for frame
	
	//***************************************************************
	//constructor
	
	public LogIn() {
		super("Log In");

		setLayout(new GridLayout(3, 2));

		add(userLabel);		add(id);
		add(passLabel);		add(pass);
		add(admin);			add(employee);

		setSize(450, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLUE);

		admin.addActionListener(this);
		employee.addActionListener(this);
	}
	//***************************************************************
	//action listener
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == admin) {
			try {
				POS.authenticate("Admin", id.getText(), pass.getText());
				dispose();
				//authenticates if the user is an admin, then closes this frame
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == employee) {
			try {
				POS.authenticate("employee", id.getText(), pass.getText());
				dispose();
				//authenticates if the user is a employee, then closes this frame
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
}
