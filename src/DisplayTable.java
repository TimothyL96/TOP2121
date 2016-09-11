import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DisplayTable extends JFrame{
	
	//THIS IS THE FUNCTION REQUIRE YOUR HELP, ADMIN FUNCTION, TO APPROVE A LEAVE OR NOT, THEN SAVE TO FILE
	JFrame a;
	String data[];
	int size;
	JComboBox[] sadcase;
	DisplayTable(String [] s, int size)
	{
		data = s;
		this.size = size;
		GridLayout aLayout = new GridLayout(0,2);
		a = new JFrame("Application pending approval");
		JPanel aPanel = new JPanel();
		String[] changetoleave = { "Approved", "Not Approved", "Pending"};
		sadcase = new JComboBox[size];
		SpringLayout aGodLayout = new SpringLayout();
		JLabel[] result = new JLabel[size];
		String[] goodbye = new String[size]; 
		JButton submit = new JButton("Submit");
		submit.setBounds(1000, 600, 140, 50);
		approval approve = new approval();
		submit.addActionListener(approve);
		a.getRootPane().setDefaultButton(submit);
		//aGodLayout.putConstraint(SpringLayout.EAST, aPanel, 5, SpringLayout.EAST, sadcase);
		//aGodLayout.putConstraint(SpringLayout.SOUTH, aPanel, 5, SpringLayout.SOUTH, sadcase);
		
		for (int i = 0; i < size; i++)
		{
			if(s[i]!=null)
			{
				result[i] = new JLabel(s[i]);
				aPanel.add(result[i]);
				
				sadcase[i] = new JComboBox<String>(changetoleave);
				
				result[i].setLabelFor(sadcase[i]);
				if (s[i].indexOf("Not Approved") != -1)
					sadcase[i].setSelectedIndex(1);
				else if (s[i].indexOf("Approved") != -1)
					sadcase[i].setSelectedIndex(0);
				else if (s[i].indexOf("Pending") != -1)
					sadcase[i].setSelectedIndex(2);
				aPanel.add(sadcase[i]);
				System.out.println(s[i]);
			}else
				continue;
		
		}
		//aPanel.setLayout(aGodLayout);
		a.add(submit);
		a.setSize(1200,700);
		a.add(aPanel);
		a.setVisible(true);		
	}
	
	//DISPLAY RESULT FROM THE SORTED;
	DisplayTable(String[] s)
	{
		JFrame a = new JFrame("Search Result");
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS));
		a.setSize(700, 500);
		JLabel[] text = new JLabel[10];
		for(int i = 0; i < 10; i++){
			if(s[i] == null)
				break;
			else{
				text[i]= new JLabel(s[i]);
				System.out.println(s[i]);
			}
			
		}
		for(int i = 0; i < 10; i++)
		{
			if(s[i] == null)
				break;
			else
				aPanel.add(text[i]);
			aPanel.add(Box.createRigidArea(new Dimension(0,20)));
		}
		a.add(aPanel);
		a.setVisible(true);
		
	}
	
	public class approval implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			File save = new File("save.txt");
			if (save.exists())
			{
				for (int i = 0; i < size; i++)
				{
					if (data[i].indexOf("Not Approved") != -1)
						data[i] = data[i].replace("Not Approved", sadcase[i].getSelectedItem().toString());
					else if (data[i].indexOf("Approved") != -1)
						data[i] = data[i].replace("Approved", sadcase[i].getSelectedItem().toString());
					else if (data[i].indexOf("Pending") != -1)
						data[i]= data[i].replace("Pending", sadcase[i].getSelectedItem().toString());
					else
						System.out.println("Error, none found!");
				}
				System.out.println(size);
				try {
					FileWriter writefile = new FileWriter(save.getAbsolutePath(), false);
					BufferedWriter bufferfile = new BufferedWriter(writefile);
					for (int i = 0; i < size; i++)
					{
						bufferfile.write(data[i]);
						bufferfile.newLine();
					}
					bufferfile.close();
					writefile.close();
				}catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "File Error Occured!", "Fatal Error", JOptionPane.WARNING_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "All your staff leaves have been updated successfully!", "Updated", JOptionPane.INFORMATION_MESSAGE);
				a.dispose();
			}else
			{
				JOptionPane.showMessageDialog(null, "File Error Occured!", "Fatal Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
	
}
