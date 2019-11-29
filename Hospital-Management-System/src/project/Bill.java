package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;


//frame that will show the employee billing details, and provagee the options to add or remove patient, change the quantity,
//and proceed to purchase.

class Bill extends JFrame implements ActionListener{

	private Scanner fin;
	private String employeeName;
	//declare an array to use it in the JLabel   
	private String [] labelNames =  {"Name", "age", "TypeOfVisit", "Price", "Drugs Purchased", "Selected Quantity", "Total Price"};
	//Declare the JFrame elements
	private JLabel jLabels[] = new JLabel[7];   
	private JLabel price = new JLabel("Total Price");   
	private JLabel vat = new JLabel("VAT");
	private JLabel priceVat = new JLabel("Total Price with VAT");
	private JTextArea tprice; //field to show the total price
	private JTextArea tvat; //field to show the vat
	private JTextArea tpriceVat; //field to show the total price with the vat
	private JTextArea [] a =  new JTextArea[7];
	private JScrollPane [] sp = new JScrollPane[7]; //JScrollPane to add it to the JTextArea
	private JButton add = new JButton ("Add new patient's bill");
	// private JButton remove =  new JButton ("Remove patient bill");
//	private JButton update =  new JButton("Change Quantity");
	private JButton purchase =  new JButton("Generate bill");
	private JButton cancel =  new JButton("Cancel");
	private JPanel p1 =  new JPanel(new GridLayout(1,7));
	private JPanel p2 =  new JPanel(new GridLayout(1,7));
	private JPanel p3 =  new JPanel(new GridLayout(1,5));
	private JPanel p4 =  new JPanel(new GridLayout(3,2));
	private JPanel p5 =  new JPanel(new GridLayout(2,1));
	private double totalprice;

	//**********************************************************************************************
	//constructor

	public Bill() throws FileNotFoundException {
		super ("Bill"); 
		
		fin =  new Scanner (new FileReader("Temp.txt")); //read from temp.txt file to show the patient information
		employeeName  = Authenticate.getActiveAccount().getName();
		
		for (int i = 0; i<labelNames.length; i++) { //add the text areas and labels to the correct place
			jLabels[i] = new JLabel(labelNames[i]);
			p1.add(jLabels[i]);
			a[i] = new JTextArea();
			a[i].setEditable(false);
			sp[i] = new JScrollPane(a[i]);
			p2.add(sp[i]);
		}
		totalprice =  0;
		
		while (fin.hasNext()) {	//adds the data from the file to the appropriate place
			String name = fin.next();
			a[0].append(name +"\n");
			int age =  fin.nextInt();
			a[1].append(age +"\n");
			String TypeOfVisit =  fin.next();
			a[2].append(TypeOfVisit +"\n");
			double price = fin.nextDouble();
			a[3].append(price +"\n");
			int quan =  fin.nextInt();
			a[4].append(quan+"\n");
			int quantity =  fin.nextInt();
			totalprice +=  price * quantity;
			a[5].append(quantity +"\n");
			a[6].append(price*quantity+"\n");
		}
		
		if (totalprice == 0) {	//presents the price values
			tprice =  new JTextArea();
			tvat = new JTextArea();
			tpriceVat = new JTextArea();
		}
		else {
			tprice =  new JTextArea("" + totalprice);
			tvat = new JTextArea(String.format("%.2f", totalprice * 0.05));
			tpriceVat = new JTextArea(String.format("%.2f", totalprice +=  totalprice * 0.05));
		}

		p4.add(price);
		p4.add(tprice);
		p4.add(vat);
		p4.add(tvat);
		p4.add(priceVat);
		p4.add(tpriceVat);
		
		tprice.setEnabled(false);
		tvat.setEnabled(false);
		tpriceVat.setEnabled(false);
		
		p3.add(add);
	//	p3.add(remove);
	//	p3.add(update);
		p3.add(purchase);
		p3.add(cancel);
		p5.add(p4);
		p5.add(p3);
		
		add.addActionListener(this);
	//	remove.addActionListener(this);
	//	update.addActionListener(this);
		purchase.addActionListener(this);
		cancel.addActionListener(this);
		
		add(p1, "North");
		add(p2);
		add(p5,"South");
		
		setVisible(true);
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.RED);
	}

	//**********************************************************************************************
	//method to add patient information to the temp file, to be used when creating the bill

	public void printToTemp(String name, int age, String TypeOfVisit, double price, int quant , int selectedQuant) throws FileNotFoundException {
		PrintWriter fout = new PrintWriter(new FileOutputStream(new File("Temp.txt."),true));
		fout.println(name+" "+age+" "+TypeOfVisit+" "+price+" "+quant+" "+selectedQuant);
		fout.close();
	}

	//**********************************************************************************************
	//method to update the patient file

	public void printToFile(int i, int quantity) throws FileNotFoundException{
		Scanner fin =  new Scanner (new FileReader("patients.txt"));
		ArrayList<Patient> patients = new ArrayList<>();
		String description;
		
		while (fin.hasNextLine()) {//copies the file data to the arrayList
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int age =  cin.nextInt();
			String sex = cin.next();
			String TypeOfVisit = cin.next();
			double price = cin.nextDouble();
			String picture = cin.next();
			String bloodType = cin.next();
			int quant = cin.nextInt();
			if (cin.hasNext()) 
				description =  cin.nextLine();
	/*		else
				description = ""; 
			patients.add(new Patient (name,age,sex,bloodType)); */
			cin.close();
		}
		
		fin.close();
		PrintWriter fout =  new PrintWriter ("patient.txt");
	}
	/*	for (Patient Pro: patients) //prints arrayList data, correcting the quantity where needed
			if (Pro.getAge() == i) {
				Pro.setQuant(Pro.getQuantity()-quantity);
				fout.println(Pro);
			}
			else
				fout.println(Pro);
		fout.close();
	}
	*/
	//**********************************************************************************************
	//actionListener

	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == add) { //the actions for the Add Button
			try {
				new SelectPatient();
				dispose();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "patient File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}

		}
	/*	else if (e.getSource() == update){ //the actions for the Update Button
			String s =  JOptionPane.showInputDialog(null, "Enter The patient age", "Search patient by age", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);

				boolean check = false;

				Scanner rage = new Scanner(a[1].getText());
				while (rage.hasNext()) { //check if the age exists or not
					int age = rage.nextInt();
					if (age == i)
						check = true;
				}
				if (check) {

					String q =  JOptionPane.showInputDialog(null, "Enter The Quantity", "Edit Quantity", JOptionPane.QUESTION_MESSAGE);
					int newquant =  Integer.parseInt(q);

					PrintWriter fout =  new PrintWriter ("Temp.txt");
					Scanner rname = new Scanner(a[0].getText());
					rage = new Scanner(a[1].getText());
					Scanner rTypeOfVisit = new Scanner(a[2].getText());
					Scanner rprice = new Scanner(a[3].getText());
					Scanner rquant1 = new Scanner(a[4].getText());
					Scanner rquant2 = new Scanner(a[5].getText());

					while (rname.hasNext()) {
						String name = rname.next();
						int age = rage.nextInt();
						String TypeOfVisit = rTypeOfVisit.next();
						double price = rprice.nextDouble();
						int quant1 = rquant1.nextInt();
						int quant2 = rquant2.nextInt();

						if (age == i) {
							if (newquant > 0 && newquant <= quant1 && newquant != 0) {
								fout.println(name+" "+age+" "+TypeOfVisit+" "+price+" "+quant1+" "+newquant);
							}
							else {
								JOptionPane.showMessageDialog(null, "The Quantity is not correct!!" , "Invaild Quantity" , JOptionPane.ERROR_MESSAGE);
								fout.println(name+" "+age+" "+TypeOfVisit+" "+price+" "+quant1+" "+quant2);
							}
						}
						else {
							fout.println(name+" "+age+" "+TypeOfVisit+" "+price+" "+quant1+" "+quant2);
						}
					}
					rname.close();
					rage.close();
					rTypeOfVisit.close();
					rprice.close();
					rquant1.close();
					rquant2.close();
					fout.close();
					dispose();
					new CheckOut();
				}
				else
					JOptionPane.showMessageDialog(null, "The age is not correct!!" , "Invaild age" , JOptionPane.ERROR_MESSAGE);

			} catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}


		} 
		*/
	/*	else if (e.getSource() == remove){ //the actions for the remove Button
			String s =  JOptionPane.showInputDialog(null, "Enter The patient age", "Search patient by age", JOptionPane.QUESTION_MESSAGE);
			try {
				int i = Integer.parseInt(s);
				PrintWriter fout =  new PrintWriter ("Temp.txt");
				boolean check = false;
				
				Scanner rname = new Scanner(a[0].getText());
				Scanner rage = new Scanner(a[1].getText());
				Scanner rTypeOfVisit = new Scanner(a[2].getText());
				Scanner rprice = new Scanner(a[3].getText());
				Scanner rquant1 = new Scanner(a[4].getText());
				Scanner rquant2 = new Scanner(a[5].getText());

				while (rname.hasNext()) { //check if the age entered is correct or not, if yes it will print all the elements except the entered age
					String name = rname.next();
					int age = rage.nextInt();
					String TypeOfVisit = rTypeOfVisit.next();
					double price = rprice.nextDouble();
					int quant1 = rquant1.nextInt();
					int quant2 = rquant2.nextInt();
					if (age == i)
						check = true;
					else
						fout.println(name+" "+age+" "+TypeOfVisit+" "+price+" "+quant1+" "+quant2);
				}
				
				rname.close();
				rage.close();
				rTypeOfVisit.close();
				rprice.close();
				rquant1.close();
				rquant2.close();
				fout.close();

				if (check) {
					JOptionPane.showMessageDialog(null, "The item was removed seccussfully" , "Successful" , JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new CheckOut();
				}
				else
					JOptionPane.showMessageDialog(null, "The age is not correct!!" , "Invaild age" , JOptionPane.ERROR_MESSAGE);

			}
			catch(IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		} stop comment
		*/
		else if (e.getSource() == purchase) { //the actions for the purchase Button
			PrintWriter fout;
			try {
				fout = new PrintWriter ("Bill.txt");
				fout.println("Purchased patients:");
				
				Scanner rname = new Scanner(a[0].getText());
				Scanner rage = new Scanner(a[1].getText());
				Scanner rTypeOfVisit = new Scanner(a[2].getText());
				Scanner rprice = new Scanner(a[3].getText());
				Scanner rquant1 = new Scanner(a[4].getText());
				Scanner rquant2 = new Scanner(a[5].getText());
				Scanner rtotalprice = new Scanner(a[6].getText());
				
				while (rname.hasNext()) { //check if the age entered is correct or not, if yes it will print all the elements except the entered age
					String name = rname.next();
					int age = rage.nextInt();
					String TypeOfVisit = rTypeOfVisit.next();
					double price = rprice.nextDouble();
					int quant1 = rquant1.nextInt();
					int quant2 = rquant2.nextInt();
					double totalprice = rtotalprice.nextDouble();
					printToFile(age,quant2);
					fout.println("Name: "+name+", age: "+age+", TypeOfVisit: "+TypeOfVisit+", Quantity: "+quant2+", Total Price: "+totalprice);				
				}

				DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				fout.println("\nTotal Amount: " + tprice.getText());
				fout.printf("VAT: %s %n", tvat.getText());
				fout.printf("Total Amount with the VAT: %s %n", tpriceVat.getText());
				fout.println("\nBill Date and Time: " + dtf.format(now));
				fout.println("\nemployee Name: " + employeeName);
				fout.println("\nThank You");


				rname.close();
				rage.close();
				rTypeOfVisit.close();
				rprice.close();
				rquant1.close();
				rquant2.close();
				rtotalprice.close();
				fout.close();

				new PrintWriter("Temp.txt").close();//clear the Temp file

				JOptionPane.showMessageDialog(null, "The Bill was generated seccessfully" , "Successfull!!" , JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			catch(IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild Input" , "Error" , JOptionPane.ERROR_MESSAGE);
			}


		}
		else { //actions for cancel button
			try {
				new PrintWriter("Temp.txt").close(); //clear the Temp file
				dispose();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invaild" , "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
