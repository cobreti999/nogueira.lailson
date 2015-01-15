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
	
	
	public DesGui(){
		this.setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuFile.add("Browse");
		menuFile.add("Exit");
		//setar fechamento do programa
		//menuFile.getItem(1)
		menuBar.add(menuFile);
		
		menuAbout = new JMenu("About");
		menuAbout.add("About");
		menuBar.add(menuAbout);
		
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
        
        processFilePanel = new JPanel();
        processFilePanel.setLayout(new FlowLayout());
        processFilePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Encrypt/Decrypt"),
                BorderFactory.createEmptyBorder(0,10,10,10)));
		processFileLabel = new JLabel("<html>Press the \"Encrypt\" or the \"Decrypt\" button to choose whether you want to encrypt<br> or decrypt the file.</html>");
		processFileLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        
		
		processFilePanel.add(processFileLabel);
		
		encryptButton = new JButton("Encrypt");
		encryptButton.setEnabled(false);
		decryptButton = new JButton("Decrypt");
		decryptButton.setEnabled(false);
		processFilePanel.add(encryptButton);
		processFilePanel.add(decryptButton);
		
		this.add(processFilePanel, BorderLayout.CENTER);
        
		
		
		statusLabel = new JLabel("Choose a valid file (.txt or .cipher) to encrypt/decrypt.");
		statusLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		statusLabel.setBorder(new EmptyBorder(4,4,4,4));
		statusLabel.setHorizontalAlignment(JLabel.LEADING);
		this.add(statusLabel, BorderLayout.SOUTH);
		
		
		
		ButtonHandler handler = new ButtonHandler();
		browseButton.addActionListener(handler);
		
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser j1= new JFileChooser();           
		    File f1;
		    j1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    j1.setVisible(true);
		    if(j1.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
		    	f1 = new File(j1.getSelectedFile().getAbsolutePath());
		    	if (!f1.getAbsolutePath().endsWith(".txt") && !f1.getAbsolutePath().endsWith(".ciphertext")){
		    		statusLabel.setText("Invalid File!");
		    	}
		    	else{
			    	textField.setText(f1.getAbsolutePath());
		    		if (f1.getAbsolutePath().endsWith(".txt")){
		    			statusLabel.setText("Valid text file! Click on \"Encrypt\" or button.");
			    		encryptButton.setEnabled(true);
			    		DES des = new DES();
			    		
		    		}
		    		else if (f1.getAbsolutePath().endsWith(".ciphertext")){
		    			statusLabel.setText("Valid ciphertext file! Click on \"Decrypt\" button.");
			    		decryptButton.setEnabled(true);
		    		}
		    	}
		    }
		}
		
	}
	
	public static void main(String args[]){
		DesGui desGui = new DesGui();
		desGui.setSize(500, 250);
		desGui.setResizable(false);
		desGui.setVisible(true);
		desGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

  