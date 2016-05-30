package objComp.util;

public class Second {
	
//	public Second(){
//  		//MyLogger.printToStdout(2,"Second Constructor is called");
//	}

	int IntValue = 0;
	double DoubleValue = 0;

	
	 public void setIntValue(int IntValue) {
		 	this.IntValue = IntValue;
	 }
	 public void setDoubleValue(double DoubleValue) {
			this.DoubleValue  =  DoubleValue;
	 }


	@Override
	public boolean equals(Object obj) {
		System.out.println("In equl second");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Second other = (Second) obj;
		if (Double.doubleToLongBits(DoubleValue) != Double
				.doubleToLongBits(other.DoubleValue))
			return false;
		if (IntValue != other.IntValue)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(DoubleValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + IntValue;
		return result;
	}
}
