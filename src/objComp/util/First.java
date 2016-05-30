package objComp.util;

public class First {
//
//	public First(){
//  		//MyLogger.printToStdout(2,"First Constructor is called");
//	}

	int IntValue=0;
	String StringValue = null;
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("In equl");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		First other = (First) obj;
		if (IntValue != other.IntValue)
			return false;
		if (StringValue == null) {
			if (other.StringValue != null)
				return false;
		} else if (!StringValue.equals(other.StringValue))
			return false;
		return true;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IntValue;
		result = prime * result
				+ ((StringValue == null) ? 0 : StringValue.hashCode());
		return result;
	}

	
	public void setIntValue(int Intvalue) {
		this.IntValue = Intvalue;
	}
	
	public void setStringValue(String StringValue) {
		this.StringValue = StringValue;
	 }
	
}