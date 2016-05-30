package objComp.fileOperations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {
	FileWriter fw = null;
	BufferedWriter bw =null;
	String textLine = "";
	BufferedReader br=null;
	FileReader reader=null;	
	String inputfilename;
	String outputfilename;
	File f = null;
	
	public FileProcessor(String ifilename)
	{
//		MyLogger.printToStdout(2,"FileProcessor Constructor is called");
		inputfilename = ifilename;
		f = new File(inputfilename);
		try {
			reader=new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(reader);
	}
	
/*
 * To read from file
 */
public final String ReadfromFile(){	
 	try{
			textLine = br.readLine();
		}
		catch(Exception E){
			E.printStackTrace();
			System.out.println("Exception: file operation");
			}
			return textLine;
	}
}
