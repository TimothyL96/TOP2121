import javax.swing.JFrame;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
public class Print_GUI {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		//this is the main function
		File myFile = new File("save.txt");
		//create a file if file DNE
		Formatter aCreator;
		if(myFile.exists()){
			System.out.println("file is there for you to use");
		}else{
			try{
			aCreator = new Formatter("save.txt");
			System.out.println("File is created");
			}catch(Exception e){
				System.out.println("You have an error...");
			}
		}
		
		//using Main Gui as the GUI for everything
		Main_Gui page1= new Main_Gui();
		page1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		page1.setSize(400,250);
		page1.setVisible(true);

	}

}
