package objComp.util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import objComp.fileOperations.FileProcessor;


public class populateObjects {
	FileProcessor fp=null;
	String readline = null;
  	Map<Object, Integer> Fhash = null; 	
  	Map<Object, Integer> Shash = null; 	

  	Map<String,Class<?>> builtInMap =null;
  	
  	public populateObjects(FileProcessor fp) {
  		MyLogger.printToStdout(2,"populateObjects Constructor is called");
  		Fhash = new HashMap<Object, Integer>();
  		Shash = new HashMap<Object, Integer>();
  		builtInMap  = new HashMap<String,Class<?>>();{
	 		   builtInMap.put("int", Integer.TYPE);
	 		   builtInMap.put("String", String.class );
	 		   builtInMap.put("double", Double.TYPE );
	 		   builtInMap.put("Integer", Integer.class);
	 	}
		// TODO Auto-generated constructor stub
		this.fp =  fp ;
	}
  	
  	
	
	public void deserObjects(){
		MyLogger.printToStdout(1,"deser method is called");
		String splitstr[]=null; 
		String nameofclass=null;
		String splitsecond[] =null;
		String valuesecondline =null;
		String varsecondline =null;
		String TypeOfValueSecond=null;
		String valuethirdline    =null;   
		String varthirdline        =null; 
		String TypeOfValueThird  =null   ;
		String splitthird[] = null;
		String methdNameSecond = null;
		String methdNameThird  = null;
		String fline=null;
		String sline=null;
		String thirdline =null;
		int counter=1;
		Class<?> signature[] = new Class[2];
		
		while((readline = fp.ReadfromFile())!=null){
			//try {
				if(readline.contains("fqn") || counter != 4){
					if(counter == 1){
						fline = readline;
					}
					if(counter == 2){
						sline = readline;
					}
					if(counter == 3){
						thirdline = readline;
						counter = -10;
					} 
					if(counter<=3){
						if(counter == -10){
							counter = 1;
						}
						else{
							counter++;
							continue;
						}	
					}								
				}
				
				//To Split first line and create new instance according to it
				splitstr = fline.split(":");
				nameofclass = splitstr[1];
				Class<?> javaclass =null;
				try {
					javaclass = Class.forName(nameofclass);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Class Not Found");
				}
				
				//To split second line
				splitsecond= sline.split(",");
				String SaveInfo[] = SplitLineFunction(splitsecond);
					splitsecond= sline.split(",");
					valuesecondline  = SaveInfo[2];
					varsecondline    = SaveInfo[1]; 
					TypeOfValueSecond= SaveInfo[0];

				//To split Third line
				splitthird = thirdline.split(",");
				SaveInfo = SplitLineFunction(splitthird);
					splitthird = thirdline.split(",");
					valuethirdline  =  SaveInfo[2];
					varthirdline    =  SaveInfo[1];
					TypeOfValueThird=  SaveInfo[0];
		
				
				//To get Boxed Type Primitive 
					if(builtInMap.containsKey(TypeOfValueSecond)){
						signature[0] = builtInMap.get(TypeOfValueSecond);
					}
					
					if(builtInMap.containsKey(TypeOfValueThird)){
						signature[1] = builtInMap.get(TypeOfValueThird);
					}
					
				 //To get Method Names
				  methdNameSecond = "set" + varsecondline; 	 	
				  methdNameThird  = "set" + varthirdline;
				  Method methSecond = null;
				  Method methThird = null;
				  
				  try {
					methSecond = javaclass.getMethod(methdNameSecond,signature[0]);
					methThird  = javaclass.getMethod(methdNameThird,signature[1]);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					System.out.println("No Such Method Found");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					System.out.println("There is security exception");
				}
				
				 
				 //To set Parameters
				 Object obj = null;
				 try {
					obj = javaclass.newInstance();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 Object[] params = new Object[2];
				
				 params[0] = handleparameters(methdNameSecond,valuesecondline);
				 params[1] = handleparameters(methdNameThird,valuethirdline);
		
				 //Invoke respective Method
				 Object result  = null;
				 Object result1  = null;
				 try {
					result = methSecond.invoke(obj, params[0]);
					result1 = methThird.invoke(obj,params[1]) ;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					System.out.println("Illegal Exception");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					System.out.println("Illegal Argument Exception");
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					System.out.println("Invocation Target Exception");
				} 
			add(obj);
		}		
	
}
	//To get Non Duplicates
	public int getnonDuplidateF(){
		MyLogger.printToStdout(1,"getnonDuplidateF method is called");
		return Fhash.size();
	}
	
	//
	public int getnonDuplidateS(){
		MyLogger.printToStdout(1,"getnonDuplidateS method is called");
		return Shash.size();
	}

	int fobjectsDUP= 0;
	int sobjectsDUP = 0;

	public int getTotalF(){
		MyLogger.printToStdout(1,"getTotalF method is called");
		for ( Object key : Fhash.keySet() ) {  // "for each key in the map's key set"
	    	 fobjectsDUP = fobjectsDUP + (Fhash.get(key));			 
		}
	return 	fobjectsDUP;
	}
	
	public int getTotalS(){
		MyLogger.printToStdout(1,"getTotals method is called");
		for ( Object key : Shash.keySet() ) {  // "for each key in the map's key set"
		   	 sobjectsDUP = sobjectsDUP + (Shash.get(key));			 
		}
		return sobjectsDUP;
	}
	
	//To Split the Line and get its values
	public String[] SplitLineFunction(String InputArray[])	{
		String returnArray[]=null;
		String temparray[] = new String[InputArray.length];
		for(int i = InputArray.length-1;i>=0;i--)
		{
			returnArray = InputArray[i].split("=");
			temparray[i] = returnArray[1]; 
		}
		return temparray;
	}
	
	//To handle parameters
	public Object handleparameters(String Methodname,String value){
		Object objct = null;
		 if(Methodname.contains("String")){
			 objct  = new String(value);
		 }	
		 else if(Methodname.contains("Int")){
			 objct  = new Integer(value);
		 }
		 else{
			 objct = new Double(value);
		 } 
		 return objct;
	}
	
	
	public void printoutput(){
		MyLogger.printToStdout(0,"printoutput method is called");
		System.out.println("Number of non-duplidate First objects :" + getnonDuplidateF());
		System.out.println("Total Number of First objects :" + getTotalF());
		System.out.println("Number of non-duplidate Second objects :" + getnonDuplidateS());
		System.out.println("Total Number of Second objects :" + getTotalS());
	}
	//TO add an Object
	private void add(Object objtoadd){
		if(objtoadd instanceof First){
				if(!Fhash.containsKey(objtoadd)){
					Fhash.put(objtoadd, 1);
				}
				else{
					Fhash.put(objtoadd, Fhash.get(objtoadd) + 1);
				}
		}
		else{
				if(!Shash.containsKey(objtoadd)){
					Shash.put(objtoadd, 1);
				}
				else{
					Shash.put(objtoadd, Shash.get(objtoadd) + 1);
				}
		}	
	}
}
