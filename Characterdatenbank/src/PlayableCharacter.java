
public class PlayableCharacter {
	private int characterID;
	private int classID;
	private String name;
	private int strength;
	private int magic;
	private int skill;
	private int speed;	
	private int defense;
	private int resistance;
	
	public PlayableCharacter(int characterid, int classid, String name, int strength, int magic, int skill, int speed, int defense, int resistance){  
		this.characterID = characterid;
		this.classID = classid;
		this.name = name;
		this.strength = strength;
		this.magic = magic;
		this.skill = skill;
		this.speed = speed;
		this.defense = defense;
		this.resistance = resistance;
	}
	
	public void save(){
		Manager.getManager().saveCharacter(this);
	}
	
	public void delete(){
		Manager.getManager().deleteCharacter(this);
	}
	
	public int getCharacterID() {
		return characterID;
	}

	public int getClassID() {
		return classID;
	}

	public String getName() {
		return name;
	}

	public int getStrength() {
		return strength;
	}

	public int getMagic() {
		return magic;
	}

	public int getSkill() {
		return skill;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDefense() {
		return defense;
	}

	public int getResistance() {
		return resistance;
	}
	
	public String toString(){
		return this.characterID+"\t|"+this.classID+"\t|"+this.name+"\t|"+this.strength+"\t|"+this.magic+"\t|"+this.skill+"\t|"+this.speed+"\t|"+this.defense+"\t|"+this.resistance+"\t|";
	}
}
