
public class CharacterClass {
	private int classID;
	private String name;
	private String skill1;
	private String skill2;
	private int move;
	
	public CharacterClass(int classid, String name, String skill1, String skill2, int move){
		this.classID = classid;
		this.name = name;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.move = move;
	}
	
	public void save(){
		Manager.getManager().saveClass(this);
	}
	
	public void delete(){
		Manager.getManager().deleteClass(this);
	}

	public int getClassID() {
		return classID;
	}

	public String getName() {
		return name;
	}

	public String getSkill1() {
		return skill1;
	}

	public String getSkill2() {
		return skill2;
	}

	public int getMove() {
		return move;
	}
	
	public String toString(){
		return this.classID+"\t|"+this.name+"\t\t|"+this.skill1+"\t|"+this.skill2+"\t|"+this.move+"\t|";
	}
}
