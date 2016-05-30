package objComp.driver;
import objComp.fileOperations.FileProcessor;
import objComp.util.populateObjects;
import objComp.util.MyLogger;

public class Driver {
	public static void main(String args[]){
		try{
		long startTime = System.currentTimeMillis();
		MyLogger d  = MyLogger.getInstance();
			if(args.length < 3) {
				System.err.println("Less than Required length input, please enter three parameters for input file, logger level,DEBUG VALUE\n");
				System.exit(1);
			}
			if(Integer.parseInt(args[2])>3 ||Integer.parseInt(args[2])<0)
			{
				System.err.println("Invalid Value");
				System.exit(1);
			}
			d.set(Integer.parseInt(args[2]));
			
			int N = Integer.parseInt(args[1]);
			populateObjects po = null;
			for(int i =0;i<N;i++){
				FileProcessor fp = new FileProcessor(args[0]);
				po = new populateObjects(fp);
				po.deserObjects();
			}
			
		long endTime   = System.currentTimeMillis();
		long totalTime = (endTime- startTime)/N;
		
		//po.printoutput();
		//System.out.printf("Total time: %3f", (float)totalTime/1e3);
		}
		catch(NumberFormatException e){
			System.out.println(e);
		}
		
	}
}