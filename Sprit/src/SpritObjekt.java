import java.util.Date;

public class SpritObjekt {
	
	private Date zeit;
	private int tanke;
	private double wert;
	
	public SpritObjekt(Date zeit, int tanke, double wert) {
		super();
		this.zeit = zeit;
		this.tanke = tanke;
		this.wert = wert;
	}
	
	public Date getZeit() {
		return zeit;
	}

	public void setZeit(Date zeit) {
		this.zeit = zeit;
	}
	public int getTanke() {
		return tanke;
	}
	public void setTanke(int tanke) {
		this.tanke = tanke;
	}
	public double getWert() {
		return wert;
	}
	public void setWert(double wert) {
		this.wert = wert;
	}

}
