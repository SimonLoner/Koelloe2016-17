
public class Usage {
	
	private long timestamp;
	private double value;
	
	public Usage(long timstamp, double value){
		this.timestamp = timstamp;
		this.value = value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
