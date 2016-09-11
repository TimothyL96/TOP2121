
abstract public class Jobs{
	
	protected String department;
	protected String position;
	
	Jobs(){
		
		department = "No Department";
		position = "No position";			
	}
	
	void changeDepartment(String d)
	{
		department = d;
	}
	
	void changeposition(String p)
	{
		position= p;
	}
	
	void changeboth(String d, String p){
		department = d;
		position = p;
	}
	
	String seedepartment(){
		return department;
	}
	
	String seeposition(){
		return position;
	}
	

	
	
	
	

}
