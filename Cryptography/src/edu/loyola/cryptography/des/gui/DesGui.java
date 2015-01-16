/*
 * 
 * Data Encryption Standard (DES) implementation using Java 7.
 * Graphical User Interface Class
 * @author: Lailson Lima Nogueira - Graduate Student at Loyola University Chicago
 * Course: COMP 447 - Intrusion Detection and Network Security Fall 2014
 * Professor: Corby Schmitz
 * 
 */

package edu.loyola.cryptography.des.gui;

import edu.loyola.cryptography.des.core.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DesGui extends JFrame{
	//Declares all the gui components
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuAbout;
	private JPanel selectFilePanel;
	private JLabel selectFileLabel;
	private JLabel labelPath;
	private JLabel statusLabel;
	private JTextField textField;
	private JButton browseButton;
	
	private JPanel processFilePanel;
	private JLabel processFileLabel;
	private JButton encryptButton;
	private JButton decryptButton;
	
	private File selectedFile;
	private JDialog aboutDialog;
	
	
	public DesGui(){
		super("Data Encryption Standard - Java Implementation by Lailson Nogueira");
		this.setLayout(new BorderLayout());
		//instantiates and set all the gui components inside the frame
		//creates a menubar
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		//creates a the Fille menu
		menuFile = new JMenu("File");
		menuFile.add("Browse");
		menuFile.add("Exit");
		//If the user clicks on "Exit" the program quits
		menuFile.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(menuFile);
		//Show the about Dialog when the user clicks on the menu
		menuAbout = new JMenu("About");
		menuAbout.add("About");
		menuAbout.getItem(0).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				showAboutDialog();
			}
		});
		menuBar.add(menuAbout);
		
		//Create the first panel and formats it
		selectFilePanel = new JPanel();
		selectFilePanel.setLayout(new FlowLayout());
		selectFilePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Select File"),
                BorderFactory.createEmptyBorder(0,10,20,10)));
		selectFileLabel = new JLabel("Select the file you want to encrypt/decypt (.txt or .cipher) using" + 
		        " the \"Browse\" button.");
		selectFileLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		labelPath = new JLabel("File path:");
		labelPath.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setColumns(20);
		textField.setEditable(false);
		
		browseButton = new JButton("Browse");
		
		selectFilePanel.add(selectFileLabel);
		selectFilePanel.add(labelPath);
		selectFilePanel.add(textField);
		selectFilePanel.add(browseButton);
		
        this.add(selectFilePanel, BorderLayout.NORTH);
        //Creates the second panel and formats it
        processFilePanel = new JPanel();
        processFilePanel.setLayout(new FlowLayout());
        processFilePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Encrypt/Decrypt"),
                BorderFactory.createEmptyBorder(0,10,10,10)));
		processFileLabel = new JLabel("<html>Press the \"Encrypt\" or the \"Decrypt\" button to "
				+ "choose whether you want to encrypt<br> or decrypt the file.</html>");
		processFileLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        
		processFilePanel.add(processFileLabel);
		
		//Create the buttons
		encryptButton = new JButton("Encrypt");
		encryptButton.setEnabled(false);
		decryptButton = new JButton("Decrypt");
		decryptButton.setEnabled(false);
		processFilePanel.add(encryptButton);
		processFilePanel.add(decryptButton);
		
		this.add(processFilePanel, BorderLayout.CENTER);
        
		statusLabel = new JLabel("Choose a valid file (.txt or .cipher) to encrypt/decrypt.");
		statusLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		statusLabel.setBorder(new EmptyBorder(4,4,4,4));
		statusLabel.setHorizontalAlignment(JLabel.LEADING);
		this.add(statusLabel, BorderLayout.SOUTH);
		
		//Add listeners to the buttons
		BrowseButtonHandler browseHandler = new BrowseButtonHandler();
		browseButton.addActionListener(browseHandler);
		menuFile.getItem(0).addActionListener(browseHandler);
		
		EncryptButtonHandler encryptHandler = new EncryptButtonHandler();
		encryptButton.addActionListener(encryptHandler);
		
		DecryptButtonHandler decryptHandler = new DecryptButtonHandler();
		decryptButton.addActionListener(decryptHandler);
	}
	
	/*
	 * 
	 * The class BrowseButtonHandler handles the events regarding the browse button
	 * 
	 */
	
	private class BrowseButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Opens a JFileChooser
			JFileChooser j1= new JFileChooser();           
		    j1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    j1.setVisible(true);
		    if(j1.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
		    	selectedFile = new File(j1.getSelectedFile().getAbsolutePath());
		    	//Check to see if the input file is valid
		    	if (!selectedFile.getAbsolutePath().endsWith(".txt") && 
		    			!selectedFile.getAbsolutePath().endsWith(".cipher")){
		    		statusLabel.setText("Invalid File!");
		    	}
		    	else{
			    	textField.setText(selectedFile.getAbsolutePath());
			    	//If the input file is a simple text file, only the encrypt button is available
		    		if (selectedFile.getAbsolutePath().endsWith(".txt")){
		    			statusLabel.setText("Valid text file! Click on \"Encrypt\" or button.");
		    			decryptButton.setEnabled(false);
			    		encryptButton.setEnabled(true);			    		
		    		}
		    		//If the input file is a cipher text, only the decrypt button is available
		    		else if (selectedFile.getAbsolutePath().endsWith(".cipher")){
		    			statusLabel.setText("Valid ciphertext file! Click on \"Decrypt\" button.");
			    		decryptButton.setEnabled(true);
			    		encryptButton.setEnabled(false);
		    		}
		    	}
		    }
		}
	}
	
	/*
	 * 
	 * The class EncryptButtonHandler handles the events regarding the encrypt button
	 * 
	 */
	
	private class EncryptButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			statusLabel.setText("Processing...Please Wait.");
			//Calls the method processInputData from the DES core class
    		DES.processInputData(selectedFile, "encrypt");
			statusLabel.setText("Finished! Encrypted file saved at " + selectedFile.getParent());
		}
		
	}
	
	/*
	 * 
	 * The class DecryptButtonHandler handles the events regarding the encrypt button
	 * 
	 */
	
	private class DecryptButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File cipherFile = new File(textField.getText());
			statusLabel.setText("Processing...Please Wait.");
			//Calls the method processInputData from the DES core class
			DES.processInputData(cipherFile, "decrypt");
			statusLabel.setText("Finished! Decrypted file saved at " + cipherFile.getParent());
		}
		
	}
	
	/*
	 * 
	 * showAboutDialog() creates and shows a JDialog with information about this implementation
	 * 
	 */
	
	public void showAboutDialog(){
		aboutDialog = new JDialog();	
		aboutDialog.setTitle("Data Encryption Standard - Java Implementation");		
		ImageIcon image = new ImageIcon("loyola.jpg");
		JLabel label1 = new JLabel(image);
		String s = "<html>Author: Lailson Lima Nogueira<br>Course: Intrusion Detection<br>"
				+ "Professor: Corby Schmitz<br>Loyola University Chicago<br>Fall 2014</html>";
		JLabel label2 = new JLabel(s);		
		aboutDialog.setLayout(null);
		int height = image.getIconHeight();
		int width = image.getIconWidth();
		label1.setBounds(0,0,width,height);
		label2.setBounds(10,height-45,200,200);
		aboutDialog.add(label1);
		aboutDialog.add(label2);
		aboutDialog.setBounds(50, 50, width, 400);
		aboutDialog.setVisible(true); 
		aboutDialog.setResizable(false);
	}
	
	/*
	 * 
	 * main(String args[]) creates and shows the DES Graphical User Interface 
	 * 
	 */
	
	
	public static void main(String args[]){
		DesGui desGui = new DesGui();
		desGui.setSize(550, 250);
		desGui.setResizable(false);
		desGui.setVisible(true);
		desGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

  