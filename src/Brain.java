import java.util.Date;
import java.util.Hashtable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Calendar;
import java.io.IOException;
import java.io.BufferedReader;
import javax.swing.JOptionPane;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Brain extends Employee{
	
	//setting up all kind of variable
	private boolean approved = false;
	private boolean dateisLogic = true;
	private String status = "Pending";
	private String duration;
	private String day;
	private String month;
	private String year;
	private String reason;
	private Calendar currentTime = Calendar.getInstance();
	private Date now;
	File x = new File("save.txt");
	Hashtable< String, String> ht = new Hashtable();

	public void changeApprove(){
		if(approved)
			status = "Approved";
		else
			status = "Not approved";
	}
	
	Brain(){
		
	}
	
	//CONSTRUCTOR TO SAVE THE FILE
	Brain(String n,String id, String d, String p, String day, String month, String year, String duration, String r){
		name = n;
		staff_ID = id;
		department = d;
		position = p;
		this.duration = duration;
		this.day = day;
		this.month = month;
		this.year = year;
		reason = r;
		now = currentTime.getTime();
		dateisLogic = checkdate();
		if(dateisLogic)
			savetofile();
		else
			JOptionPane.showMessageDialog(null, "Your date is not valid, please resubmit another application", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void leavedate(){
		
	}
	//save to file for the applied form
	public void savetofile(){
		try{
		if(x.exists()){
			String information = staff_ID + "    " + name + "    " + department + "    " + position + "    " + day + "-" + month + "-" + year + "    " + duration + " days    " + reason + "    " + status + "    " + now + "\n";
			FileWriter aWriter = new FileWriter(x.getAbsoluteFile(),true);
			BufferedWriter anotherWriter = new BufferedWriter(aWriter);
			anotherWriter.append(information);
			anotherWriter.newLine();
			anotherWriter.close();
			System.out.println("Done");
			JOptionPane.showMessageDialog(null, "Leave form submitted", "Success Message", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			System.out.println("File is not exists, please create it");
		}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	//check either the date inputted by the user logic or not
	public boolean checkdate(){
		int years = Integer.parseInt(this.year);
		int months = Integer.parseInt(this.month);
		int dates = Integer.parseInt(this.day);
		if((currentTime.get(Calendar.YEAR) == years)
		    &&
		   (currentTime.get(Calendar.MONTH) == months)
		    &&
		   (currentTime.get(Calendar.DATE) > dates))
		   	return false;
		else if(currentTime.get(Calendar.YEAR) > years)
				return false;
		else if((currentTime.get(Calendar.YEAR)== years) && (currentTime.get(Calendar.MONTH) > months))
			return false;
		
		return true;
		}
	
	//THIS FUNCTION WILL RUN IF ADMIN LOG IN SUCCES
	public void display(){
		System.out.println("Log in Success");
		String[] information= new String[100];
		int count=0;
		int index=0;
		String separator = "Pending";
		String test = "";
		String data;
		try{
			FileReader aReader = new FileReader("save.txt");
			Scanner aScanner = new Scanner(x);
			BufferedReader aBReader = new BufferedReader(aReader);
			boolean notdonereadfile = true;
			System.out.println("file exists =3");
			String compare = "Pending";
			while(aScanner.hasNextLine()){
				data = aScanner.nextLine();
				information[count] = data;
				count++;
				//System.out.println(data);
			}
			DisplayTable aTable = new DisplayTable(information, count);
		}catch(IOException abc){
			System.out.println("YOU HAVE AN ERROR");
		}
	}
	
	//THIS FUNCTION WILL RUN FOR EMPLOYEE TO CHECK THEIR LEAVE, String s is the ID
	public void display(String s){
		String information;
		String[] toDisplay = new String[10];
		int count = 0;
		boolean resultavailable = false;
		DisplayTable aTable;
		try{
		FileReader a = new FileReader("save.txt");
		Scanner aScanner = new Scanner(x);
		System.out.println(s);
		while(aScanner.hasNextLine()){	//IF THE FILE HAS NEXT LINE THEN IT WILL LOOP
			String comparison = aScanner.next(); //COMAPRE THE STRING WITH THE ID
			System.out.println(comparison);
			if(comparison.equals(s)){	//IF THE ID IS FOUND IN THE ID, RUN THE FUNCTION BELLOW
				information = aScanner.nextLine(); // EXTRACT THE INFORMATION
				System.out.println(information);
				toDisplay[count] = s + information;  //PASS TO AN ARRAY
				count++;	//INCREASE THE COUNT SO THAT IT WON'T OVERWROTE THE VARIABLE
				resultavailable = true; //TO CHECK EITHER GOT RESULT OR NOT
			}
			else
				aScanner.nextLine(); //GO TO THE NEXT LINE
		}
		a.close(); //CLOSE READER
		aScanner.close(); //CLOSE SCANNER
		
		}catch(IOException a){
			System.out.println("File does not exists");
		}
		if(!resultavailable)
			JOptionPane.showMessageDialog(null, "No result found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
		else
			aTable = new DisplayTable(toDisplay); // VIEW DISPLAYTABLE FUNCTION

	}
}
	

	
	
	




