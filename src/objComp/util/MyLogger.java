package objComp.util;

public class MyLogger {
	private volatile static MyLogger uniqueInstance;
	private static int loggerValue;

	private MyLogger() 
	{
	}
	
	public static MyLogger getInstance() {
		if(uniqueInstance == null){
			synchronized (MyLogger.class) 
			{
				if(uniqueInstance == null)
				{
					uniqueInstance = new MyLogger();
				}
			}
		}
		
		return uniqueInstance;
	}
	
	public  void set(int value){
		loggerValue= value;
	}
	
	public int get()	{
		return loggerValue;
	}
	
	public static void printToStdout(int level, String debugMessage){
		if(level == loggerValue)	{
				System.out.print(level);
				System.out.println(","+debugMessage);
		}	
	}
}
