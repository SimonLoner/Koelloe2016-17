
public class Weapon {
	private int weaponID;
	private String name;
	private String type;
	private int might;
	private String wrange;
	private int crit;
	private int accuracy;
	
	public Weapon(int weaponid, String name, String type, int might, String range,int crit, int accuracy){
		this.weaponID = weaponid;
		this.name = name;
		this.type = type;
		this.might = might;
		this.wrange = range;
		this.crit = crit;
		this.accuracy = accuracy;
	}
	
	public void save(){
		Manager.getManager().saveWeapon(this);
	}
	
	public void delete(){
		Manager.getManager().deleteWeapon(this);
	}

	public int getWeaponID() {
		return weaponID;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getMight() {
		return might;
	}

	public String getWrange() {
		return wrange;
	}
	
	public int getCrit() {
		return crit;
	}

	public int getAccuracy() {
		return accuracy;
	}
	
	public String toString(){
		return this.weaponID+"\t|"+this.name+"\t|"+this.type+"\t|"+this.might+"\t|"+this.wrange+"\t|"+this.crit+"\t|"+this.accuracy+"\t|";
	}
}
