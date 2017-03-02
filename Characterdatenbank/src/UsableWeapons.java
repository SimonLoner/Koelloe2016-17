
public class UsableWeapons {
	private int classID;
	private int weaponID;
	
	public UsableWeapons(int classid, int weaponid){
		this.classID = classid;
		this.weaponID = weaponid;
	}
	
	public void save(){
		Manager.getManager().saveUsableWeapons(this);
	}
	
	public void delete(){
		Manager.getManager().deleteUsableWeapons(this);
	}

	public int getClassID() {
		return classID;
	}

	public int getWeaponID() {
		return weaponID;
	}
	
	public String toString(){
		return this.classID+"\t|"+this.weaponID+"\t|";
	}

}
