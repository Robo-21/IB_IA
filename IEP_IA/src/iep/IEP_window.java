package iep;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.*;

public class IEP_window {
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel studentEntryPanel;
	private JPanel section1Panel;
	private JPanel section2Panel;
	private JPanel section3Panel;
	private JPanel dataPanel;
	private JButton enterButton;
	public String enteredStudent;  //holds the current selected student profile
	private JTextField dataTextField;
	private JLabel s1q1;
	private JTextField s1q1TextField;
	private JLabel s2q1;
	private JTextField s2q1TextField;
	private JLabel s3q1;
	private JTextField s3q1TextField;
	public String s1q1A;
	public String s2q1A;
	public String s3q1A;
	private JButton addStudentButton;
	private JButton section1_Button;
	private JButton section2_Button;
	private JButton section3_Button;
	private JButton viewStudentDataButton;
	private JButton saveButton;
	private JLabel addStudentText;
	private JLabel studentDisplay;
	private JLabel dataDisplay;
	public ArrayList<String> studentAnswers = new ArrayList<String>(); //An arraylist that holds the string values of what is entered in the Jtextfield
	private boolean dataCreated = false;
	private Panel currentPanel = Panel.MAIN;
	
	public static void main(String[] args) { //create the jframe window

		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					IEP_window window = new IEP_window();
					window.frame.setVisible(true);
					window.frame.setTitle("IEP Entry");
					window.frame.setSize(new Dimension(530, 400));
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	enum Panel { //panels to switch to
		MAIN, // What the program starts on
		STUDENTENTRY, // Asks the user to enter in the students name
		SECTION1, // Holds the first sections input data
		SECTION2, // Holds the second sections input data
		SECTION3, // Holds the third sections input data
		DATA // Displays the data in its own panel
	}
	
	
	public void switchToMainPanel() {
		if(currentPanel != Panel.MAIN) { //Clears the jframe and adds the main panel
			frame.remove(frame.getContentPane().getComponent(0));
			if(this.mainPanel != null) {
			mainPanel.removeAll();
			}
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			if(enteredStudent != null) {
			mainPanel.add(studentDisplay);
			}
			if(enteredStudent == null) {
			mainPanel.add(addStudentButton);
			}
			mainPanel.add(section1_Button);
			mainPanel.add(section2_Button);
			mainPanel.add(section3_Button);
			if(dataCreated == true) {
			mainPanel.add(viewStudentDataButton);
			}
			frame.add(mainPanel);
			frame.invalidate();
			frame.validate();
			currentPanel = Panel.MAIN;
		}
	}
	public void switchToStudentEntryPanel() { //Clears the jframe and adds the student entry panel
		if(currentPanel != Panel.STUDENTENTRY) {
			frame.remove(frame.getContentPane().getComponent(0));
			if(this.studentEntryPanel != null) {
			studentEntryPanel.removeAll();
			}
			JPanel studentEntryPanel = new JPanel();
			studentEntryPanel.setLayout(new GridBagLayout());
			studentEntryPanel.add(addStudentText);
			studentEntryPanel.add(dataTextField);
			studentEntryPanel.add(enterButton);
			frame.add(studentEntryPanel);
			frame.invalidate();
			frame.validate();
			currentPanel = Panel.STUDENTENTRY;
		}
	}
	public void switchToSection1Panel() {
		if(currentPanel != Panel.SECTION1) {
			frame.remove(frame.getContentPane().getComponent(0));
			if(this.section1Panel != null) {
			section1Panel.removeAll();
			}
			JPanel section1Panel = new JPanel();
			section1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			if(enteredStudent != null) {
			section1Panel.add(studentDisplay);
			}
			if(enteredStudent == null) {
			section1Panel.add(addStudentButton);
			}
			section1Panel.add(section1_Button);
			section1Panel.add(section2_Button);
			section1Panel.add(section3_Button);
			if(dataCreated == true) {
			section1Panel.add(viewStudentDataButton);
			}
			section1Panel.add(s1q1);
			section1Panel.add(s1q1TextField);
			section1Panel.add(saveButton);
			
			frame.add(section1Panel);
			frame.invalidate();
			frame.validate();
			currentPanel = Panel.SECTION1;
		}
	}
	public void switchToSection2Panel() { //Called when the "Section 2" button is pressed
		if(currentPanel != Panel.SECTION2) { //Checks if the panel is already displaying section 2, and if not, displays it
			frame.remove(frame.getContentPane().getComponent(0));
			if(this.section1Panel != null) {
			section1Panel.removeAll();
			}
			JPanel section2Panel = new JPanel();
			section2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			if(enteredStudent != null) {
			section2Panel.add(studentDisplay);
			}
			if(enteredStudent == null) {
			section2Panel.add(addStudentButton);
			}
			section2Panel.add(section1_Button);
			section2Panel.add(section2_Button); //I then add the JComponents to the panel
			section2Panel.add(section3_Button);
			if(dataCreated == true) {
			section2Panel.add(viewStudentDataButton);
			}
			section2Panel.add(s2q1);
			section2Panel.add(s2q1TextField);
			section2Panel.add(saveButton);
			
			frame.add(section2Panel); //Finally I add the panel to the opened Jframe, using the invalidate and validate commands to update the screen
			frame.invalidate();
			frame.validate();
			currentPanel = Panel.SECTION2;
		}
	}
	public void switchToSection3Panel() {
		if(currentPanel != Panel.SECTION3) {
			frame.remove(frame.getContentPane().getComponent(0));
			if(this.section3Panel != null) {
			section3Panel.removeAll();
			}
			JPanel section3Panel = new JPanel();
			section3Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			if(enteredStudent != null) {
			section3Panel.add(studentDisplay);
			}
			if(enteredStudent == null) {
			section3Panel.add(addStudentButton);
			}
			section3Panel.add(section1_Button);
			section3Panel.add(section2_Button);
			section3Panel.add(section3_Button);
			if(dataCreated == true) {
			section3Panel.add(viewStudentDataButton);
			}
			section3Panel.add(s3q1);
			section3Panel.add(s3q1TextField);
			section3Panel.add(saveButton);
			
			frame.add(section3Panel);
			frame.invalidate();
			frame.validate();
			currentPanel = Panel.SECTION3;
		}
	}
	public void switchToDataPanel() throws FileNotFoundException {
		if(currentPanel != Panel.DATA) {
			frame.remove(frame.getContentPane().getComponent(0));
			if(this.dataPanel != null) {
			dataPanel.removeAll();
			}
			JPanel dataPanel = new JPanel();
			dataPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			if(enteredStudent == null) {
			dataPanel.add(addStudentButton);
			}
			dataPanel.add(section1_Button);
			dataPanel.add(section2_Button);
			dataPanel.add(section3_Button);
			File studentDataFile = new File("res/documents/studentData.txt"); // creates the file object to be written to
			Scanner dataScanner = new Scanner(studentDataFile); // scanner object to be used
			for(int i = 0; i < 4; i++) {
				studentAnswers.add(i, dataScanner.nextLine() + "<br/>"); /* adds the data of the text file  with the index i, 
				setting each index to each respective line, adding a break inbetween each*/
			}
			
			dataDisplay.setText("<html>" + studentAnswers.get(0) + studentAnswers.get(1) + studentAnswers.get(2) + studentAnswers.get(3) + "</html>");
			dataPanel.add(viewStudentDataButton);
			dataPanel.add(dataDisplay);
		
			frame.add(dataPanel);
			frame.invalidate();
			frame.validate();
			currentPanel = Panel.DATA;
		}
	}

	public IEP_window() {
		initialize();
	}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		addStudentText = new JLabel("Student Name: ");
		
		studentDisplay = new JLabel();

		studentDisplay.setSize(new Dimension (20, 10));
		
		addStudentButton = new JButton("Add Student");
		addStudentButton.setBounds(new Rectangle(0, 0, 20, 10));
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToStudentEntryPanel();
			}
		});
		
		saveButton = new JButton("Save");
		saveButton.setBounds(new Rectangle(0, 0, 20, 10));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //When the save button is clicked, the Answers and student name are all recorded in the text file
				s1q1A = s1q1TextField.getText();
				s2q1A = s2q1TextField.getText();
				s3q1A = s3q1TextField.getText();
			    try { // writes the answers to the file
			        dataCreated = true;
			        FileWriter myWriter = new FileWriter("res/documents/studentData.txt");
			        myWriter.write("Student Name: " + enteredStudent);
			        myWriter.write("\nSection 1 Question 1: " + s1q1A);
			        myWriter.write("\nSection 2 Question 1: " + s2q1A);
			        myWriter.write("\nSection 3 Question 1: " + s3q1A);
			        myWriter.close();
			        System.out.println("Successfully wrote to the file");
			      } catch (IOException j) {
			        System.out.println("An error occurred.");
			        j.printStackTrace();
			      }
			}
		});
		
		s1q1 = new JLabel("<html>What is the students primary disability?: </html>");
		
		s1q1TextField = new JTextField(40);
		s1q1TextField.setHorizontalAlignment(SwingConstants.LEFT);
		s1q1TextField.setBounds(new Rectangle(0, 0, 400, 100));
		
		s2q1 = new JLabel("What are the students academic goals?: ");
		
		s2q1TextField = new JTextField(40);
		s2q1TextField.setHorizontalAlignment(SwingConstants.LEFT);
		s2q1TextField.setBounds(new Rectangle(0, 0, 400, 100));
		
		s3q1 = new JLabel("What are the students social goals?: ");
		
		s3q1TextField = new JTextField(40);
		s3q1TextField.setHorizontalAlignment(SwingConstants.LEFT);
		s3q1TextField.setBounds(new Rectangle(0, 0, 400, 100));
		
		viewStudentDataButton = new JButton("View Data");
		viewStudentDataButton.setBounds(new Rectangle(0, 0, 20, 10));
		viewStudentDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switchToDataPanel();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		section1_Button = new JButton("Section 1");
		section1_Button.setBounds(new Rectangle(0, 0, 20, 10));
		section1_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToSection1Panel();
			}
		});
		section2_Button = new JButton("Section 2");
		section2_Button.setBounds(new Rectangle(0, 0, 20, 10));
		section2_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToSection2Panel();
			}
		});
		section3_Button = new JButton("Section 3");
		section3_Button.setBounds(new Rectangle(0, 0, 20, 10));
		section3_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToSection3Panel();
			}
		});

		dataTextField = new JTextField(16);
		dataTextField.setHorizontalAlignment(SwingConstants.LEFT);
		dataTextField.setToolTipText("Enter Student Name:");
		dataTextField.setBounds(new Rectangle(0, 0, 20, 10));
		
		dataDisplay = new JLabel();
		dataDisplay.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		
		enterButton = new JButton("Submit");
		enterButton.setBounds(new Rectangle(0, 0, 20, 10));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enteredStudent = dataTextField.getText();
				studentDisplay.setText(enteredStudent);
				dataTextField.setText("");
				switchToMainPanel();
			}
		});
		
		mainPanel.add(addStudentButton);
		mainPanel.add(section1_Button);
		mainPanel.add(section2_Button);
		mainPanel.add(section3_Button);
		frame.add(mainPanel);

	}
}
