package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class Receptionist_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField Tfname;
	private JTextField Tlname;
	private JTextField Tage;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist_GUI frame = new Receptionist_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Receptionist_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogOut.setBounds(387, 0, 91, 23);
		contentPane.add(btnLogOut);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setSelectedIndex(1);
		tabbedPane.setBounds(10, 11, 468, 343);
		contentPane.add(tabbedPane);
		
		JPanel addPatientPanel = new JPanel();
		tabbedPane.addTab("Add Patient", null, addPatientPanel, null);
		addPatientPanel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 42, 58, 14);
		addPatientPanel.add(lblFirstName);
		
		Tfname = new JTextField();
		Tfname.setBounds(78, 39, 106, 20);
		addPatientPanel.add(Tfname);
		Tfname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(194, 42, 57, 14);
		addPatientPanel.add(lblLastName);
		
		Tlname = new JTextField();
		Tlname.setBounds(261, 39, 106, 20);
		addPatientPanel.add(Tlname);
		Tlname.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(10, 67, 23, 14);
		addPatientPanel.add(lblAge);
		
		Tage = new JTextField();
		Tage.setBounds(43, 64, 58, 20);
		addPatientPanel.add(Tage);
		Tage.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(111, 67, 40, 14);
		addPatientPanel.add(lblGender);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(153, 63, 58, 22);
		addPatientPanel.add(comboBox);
		
		JLabel lblBloodType = new JLabel("Blood Type:");
		lblBloodType.setBounds(221, 67, 66, 14);
		addPatientPanel.add(lblBloodType);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"A-", "A+", "B-", "B+", "O-", "O+", "AB-", "AB+"}));
		comboBox_1.setBounds(297, 63, 48, 22);
		addPatientPanel.add(comboBox_1);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(10, 93, 40, 14);
		addPatientPanel.add(lblHeight);
		
		textField = new JTextField();
		textField.setBounds(53, 90, 48, 20);
		addPatientPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblM = new JLabel("m");
		lblM.setBounds(103, 92, 23, 14);
		addPatientPanel.add(lblM);
		
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(136, 93, 40, 14);
		addPatientPanel.add(lblWeight);
		
		textField_1 = new JTextField();
		textField_1.setBounds(186, 90, 48, 20);
		addPatientPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblKg = new JLabel("Kg");
		lblKg.setBounds(239, 92, 48, 14);
		addPatientPanel.add(lblKg);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 137, 31, 14);
		addPatientPanel.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(45, 134, 322, 20);
		addPatientPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number:");
		lblMobileNumber.setBounds(10, 162, 79, 14);
		addPatientPanel.add(lblMobileNumber);
		
		textField_3 = new JTextField();
		textField_3.setBounds(99, 159, 131, 20);
		addPatientPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setBounds(239, 162, 58, 14);
		addPatientPanel.add(lblTelephone);
		
		textField_4 = new JTextField();
		textField_4.setBounds(304, 159, 106, 20);
		addPatientPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblInsurance = new JLabel("Insurance:");
		lblInsurance.setBounds(10, 187, 58, 14);
		addPatientPanel.add(lblInsurance);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(70, 187, 164, 22);
		addPatientPanel.add(comboBox_2);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if() {
					
				}
			}
		});
		btnRegister.setBounds(186, 235, 91, 23);
		addPatientPanel.add(btnRegister);
		
		JPanel updatePatientPanel = new JPanel();
		tabbedPane.addTab("Update Patient", null, updatePatientPanel, null);
		updatePatientPanel.setLayout(null);
		
		JLabel label = new JLabel("First Name:");
		label.setBounds(10, 42, 58, 14);
		updatePatientPanel.add(label);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(78, 39, 106, 20);
		updatePatientPanel.add(textField_5);
		
		JLabel label_1 = new JLabel("Last Name:");
		label_1.setBounds(194, 42, 57, 14);
		updatePatientPanel.add(label_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(261, 39, 106, 20);
		updatePatientPanel.add(textField_6);
		
		JLabel label_2 = new JLabel("Age:");
		label_2.setBounds(10, 67, 23, 14);
		updatePatientPanel.add(label_2);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(43, 64, 58, 20);
		updatePatientPanel.add(textField_7);
		
		JLabel label_3 = new JLabel("Gender:");
		label_3.setBounds(111, 67, 40, 14);
		updatePatientPanel.add(label_3);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(153, 63, 58, 22);
		updatePatientPanel.add(comboBox_3);
		
		JLabel label_4 = new JLabel("Blood Type:");
		label_4.setBounds(221, 67, 66, 14);
		updatePatientPanel.add(label_4);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(297, 63, 48, 22);
		updatePatientPanel.add(comboBox_4);
		
		JLabel label_5 = new JLabel("Height:");
		label_5.setBounds(10, 93, 40, 14);
		updatePatientPanel.add(label_5);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(53, 90, 48, 20);
		updatePatientPanel.add(textField_8);
		
		JLabel label_6 = new JLabel("m");
		label_6.setBounds(103, 92, 23, 14);
		updatePatientPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Weight:");
		label_7.setBounds(136, 93, 40, 14);
		updatePatientPanel.add(label_7);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(186, 90, 48, 20);
		updatePatientPanel.add(textField_9);
		
		JLabel label_8 = new JLabel("Kg");
		label_8.setBounds(239, 92, 48, 14);
		updatePatientPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Email:");
		label_9.setBounds(10, 137, 31, 14);
		updatePatientPanel.add(label_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(45, 134, 322, 20);
		updatePatientPanel.add(textField_10);
		
		JLabel label_10 = new JLabel("Mobile Number:");
		label_10.setBounds(10, 162, 79, 14);
		updatePatientPanel.add(label_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(99, 159, 131, 20);
		updatePatientPanel.add(textField_11);
		
		JLabel label_11 = new JLabel("Telephone:");
		label_11.setBounds(239, 162, 58, 14);
		updatePatientPanel.add(label_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(304, 159, 106, 20);
		updatePatientPanel.add(textField_12);
		
		JLabel label_12 = new JLabel("Insurance:");
		label_12.setBounds(10, 187, 58, 14);
		updatePatientPanel.add(label_12);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(70, 187, 164, 22);
		updatePatientPanel.add(comboBox_5);
		
		JButton button = new JButton("Register");
		button.setBounds(186, 235, 91, 23);
		updatePatientPanel.add(button);
		
		JPanel bookAppointmentPanel = new JPanel();
		tabbedPane.addTab("Book Appointment", null, bookAppointmentPanel, null);
		bookAppointmentPanel.setLayout(null);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(10, 61, 60, 14);
		bookAppointmentPanel.add(lblPatientId);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(80, 57, 130, 22);
		bookAppointmentPanel.add(comboBox_6);
		
		JButton btnSeeNameAnd = new JButton("See name and Phone");
		btnSeeNameAnd.setBounds(220, 57, 170, 23);
		bookAppointmentPanel.add(btnSeeNameAnd);
		
		JLabel lblDoctorId = new JLabel("Doctor ID:");
		lblDoctorId.setBounds(10, 86, 60, 14);
		bookAppointmentPanel.add(lblDoctorId);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(80, 82, 130, 22);
		bookAppointmentPanel.add(comboBox_7);
		
		JButton btnSeeNameAnd_1 = new JButton("See Name and Specialty");
		btnSeeNameAnd_1.setBounds(220, 82, 170, 23);
		bookAppointmentPanel.add(btnSeeNameAnd_1);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(230, 116, 48, 14);
		bookAppointmentPanel.add(lblTime);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(90, 116, 48, 14);
		bookAppointmentPanel.add(lblDate);
		
		JButton btnBookAppointment = new JButton("Book appointment");
		btnBookAppointment.setBounds(173, 242, 130, 23);
		bookAppointmentPanel.add(btnBookAppointment);
		
		DateTimePicker dateTimePicker = new DateTimePicker();
		dateTimePicker.getDatePicker().getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateTimePicker.setBounds(82, 142, 221, 23);
		bookAppointmentPanel.add(dateTimePicker);
		
		JPanel paymentPanel = new JPanel();
		tabbedPane.addTab("Payment", null, paymentPanel, null);
		paymentPanel.setLayout(null);
	}
}
