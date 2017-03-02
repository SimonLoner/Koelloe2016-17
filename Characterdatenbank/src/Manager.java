import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	private static Manager instance;
	private Connection c;
	private File f = null;
	private Scanner scan = null;
	
	private Manager() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		 c =DriverManager.getConnection("jdbc:mysql://localhost/CharacterDatenbank","root","");
	}
	
	public static Manager getManager(){
		if(instance==null)
			try {
				instance=new Manager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return instance;
	}
	
	private ResultSet checkCharacterID(PlayableCharacter character){
		ResultSet rs = null;
		java.sql.PreparedStatement checkpstmt;
		String checkid ="Select * FROM playablecharacter WHERE characterid=?";
		try {
			checkpstmt=c.prepareStatement(checkid);
			checkpstmt.setInt(1, character.getCharacterID());
			rs = checkpstmt.executeQuery();
			//checkpstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void saveCharacter(PlayableCharacter character) {
		ResultSet rs;

		try {
			rs = checkCharacterID(character);

			if(!rs.next()){
				insertCharacter(character);
			}else{
				updateCharacter(character);
				}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertCharacter(PlayableCharacter character){
		java.sql.PreparedStatement pstmt;
		String preparedInsertString="INSERT INTO playablecharacter VALUES(?,?,?,?,?,?,?,?,?);";
		
		try {
			pstmt=c.prepareStatement(preparedInsertString);
			pstmt.setInt(1,character.getCharacterID());
			pstmt.setInt(2, character.getClassID());
			pstmt.setString(3, character.getName());
			pstmt.setInt(4, character.getStrength());
			pstmt.setInt(5, character.getMagic());
			pstmt.setInt(6, character.getSkill());
			pstmt.setInt(7, character.getSpeed());
			pstmt.setInt(8, character.getDefense());
			pstmt.setInt(9, character.getResistance());
		
			pstmt.executeUpdate();
			System.out.println("Successfully inserted character\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateCharacter(PlayableCharacter character){
		java.sql.PreparedStatement pstmt;
		String preparedUpdateString ="UPDATE playablecharacter SET classid=?,name=?,strength=?,magic=?,skill=?,speed=?,defense=?,resistance=? WHERE characterid=?;";
		
		try {
			pstmt=c.prepareStatement(preparedUpdateString);
			pstmt.setInt(1, character.getClassID());
			pstmt.setString(2, character.getName());
			pstmt.setInt(3, character.getStrength());
			pstmt.setInt(4, character.getMagic());
			pstmt.setInt(5, character.getSkill());
			pstmt.setInt(6, character.getSpeed());
			pstmt.setInt(7, character.getDefense());
			pstmt.setInt(8, character.getResistance());
			pstmt.setInt(9,character.getCharacterID());
			
			pstmt.executeUpdate();
			System.out.println("Successfully updated character\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCharacter(PlayableCharacter character){
		java.sql.PreparedStatement pstmt;
		String preparedDeleteStatement="DELETE FROM playablecharacter WHERE characterid=?";
		
		try {
			if(checkCharacterID(character).next()){
				pstmt = c.prepareStatement(preparedDeleteStatement);
				pstmt.setInt(1, character.getCharacterID());
				
				pstmt.executeUpdate();
				System.out.println("Succesfully deleted Character\n");
			}else{System.out.println("Character does not exist\n");}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	public ArrayList<PlayableCharacter> readAllCharacters(){
		java.sql.PreparedStatement pstmt;
		String prepearedSelectString ="Select * FROM playablecharacter";
		ArrayList<PlayableCharacter> characters = new ArrayList<PlayableCharacter>();

		try {
			pstmt = c.prepareStatement(prepearedSelectString);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int characterID = rs.getInt("characterid");
				int classID = rs.getInt("classid");
				String name = rs.getString("name");
				int strength = rs.getInt("strength");
				int magic = rs.getInt("magic");
				int skill = rs.getInt("skill");
				int speed = rs.getInt("speed");	
				int defense = rs.getInt("defense");
				int resistance = rs.getInt("resistance");
				characters.add(new PlayableCharacter(characterID, classID, name, strength, magic, skill, speed, defense, resistance));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully read all characters\n");
		return characters;
	}
	
	private ResultSet checkClassID(CharacterClass cclass){
		ResultSet rs = null;
		java.sql.PreparedStatement checkpstmt;
		String checkid ="Select * FROM CharacterClass WHERE classid=?";
		try {
			checkpstmt=c.prepareStatement(checkid);
			checkpstmt.setInt(1, cclass.getClassID());
			rs = checkpstmt.executeQuery();
			//checkpstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void saveClass(CharacterClass cclass){
		ResultSet rs;

		try {
			rs = checkClassID(cclass);

			if(!rs.next()){
				insertClass(cclass);
			}else{
				updateClass(cclass);
				}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertClass(CharacterClass cclass){
		java.sql.PreparedStatement pstmt;
		String preparedInsertString="INSERT INTO characterclass VALUES(?,?,?,?,?);";
		
		try {
			pstmt=c.prepareStatement(preparedInsertString);
			pstmt.setInt(1,cclass.getClassID());
			pstmt.setString(2, cclass.getName());
			pstmt.setString(3, cclass.getSkill1());
			pstmt.setString(4, cclass.getSkill2());
			pstmt.setInt(5,cclass.getMove());
		
			pstmt.executeUpdate();
			System.out.println("Successfully inserted class\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateClass(CharacterClass cclass){
		java.sql.PreparedStatement pstmt;
		String preparedUpdateString ="UPDATE characterclass SET name=?,skill1=?,skill2=?,move=? WHERE classid=?;";
		
		try {
			pstmt=c.prepareStatement(preparedUpdateString);
			pstmt.setString(1, cclass.getName());
			pstmt.setString(2, cclass.getSkill1());
			pstmt.setString(3, cclass.getSkill2());
			pstmt.setInt(4,cclass.getMove());
			pstmt.setInt(5,cclass.getClassID());
			
			pstmt.executeUpdate();
			System.out.println("Successfully updated class\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteClass(CharacterClass cclass){
		java.sql.PreparedStatement pstmt;
		String preparedDeleteStatement="DELETE FROM characterclass WHERE classid=?";
		
		try {
			if(checkClassID(cclass).next()){
				pstmt = c.prepareStatement(preparedDeleteStatement);
				pstmt.setInt(1, cclass.getClassID());
				
				pstmt.executeUpdate();
				System.out.println("Succesfully deleted Class\n");
			}else{System.out.println("Class does not exist\n");}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<CharacterClass> readAllClasses(){
		java.sql.PreparedStatement pstmt;
		String prepearedSelectString ="Select * FROM characterclass";
		ArrayList<CharacterClass> classes = new ArrayList<CharacterClass>();

		try {
			pstmt = c.prepareStatement(prepearedSelectString);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int classID = rs.getInt("classid");
				String name = rs.getString("name");
				String skill1 = rs.getString("skill1");
				String skill2 = rs.getString("skill2");
				int move = rs.getInt("move");
				classes.add(new CharacterClass(classID, name, skill1, skill2, move));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully read all classes\n");
		return classes;
	}
	
	private ResultSet checkWeaponID(Weapon weapon){
		ResultSet rs = null;
		java.sql.PreparedStatement checkpstmt;
		String checkid ="Select * FROM Weapon WHERE weaponid=?";
		try {
			checkpstmt=c.prepareStatement(checkid);
			checkpstmt.setInt(1, weapon.getWeaponID());
			rs = checkpstmt.executeQuery();
			//checkpstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void saveWeapon(Weapon weapon){
		ResultSet rs;

		try {
			rs = checkWeaponID(weapon);
			
			if(!rs.next()){
				insertWeapon(weapon);
			}else{
				updateWeapon(weapon);
				}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertWeapon(Weapon weapon){
		java.sql.PreparedStatement pstmt;
		String preparedInsertString="INSERT INTO weapon VALUES(?,?,?,?,?,?,?);";
		
		try {
			pstmt=c.prepareStatement(preparedInsertString);
			pstmt.setInt(1,weapon.getWeaponID());
			pstmt.setString(2, weapon.getName());
			pstmt.setString(3, weapon.getType());
			pstmt.setInt(4, weapon.getMight());
			pstmt.setString(5, weapon.getWrange());
			pstmt.setInt(6,weapon.getCrit());
			pstmt.setInt(7,weapon.getAccuracy());
		
			pstmt.executeUpdate();
			System.out.println("Successfully inserted weapon\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateWeapon(Weapon weapon){
		java.sql.PreparedStatement pstmt;
		String preparedUpdateString ="UPDATE weapon SET name=?,type=?,might=?,wrange=?,crit=?,accuracy=? WHERE weaponid=?;";
		
		try {
			pstmt=c.prepareStatement(preparedUpdateString);
			pstmt.setString(1, weapon.getName());
			pstmt.setString(2, weapon.getType());
			pstmt.setInt(3, weapon.getMight());
			pstmt.setString(4, weapon.getWrange());
			pstmt.setInt(5,weapon.getCrit());
			pstmt.setInt(6,weapon.getAccuracy());
			pstmt.setInt(7,weapon.getWeaponID());
			
			pstmt.executeUpdate();
			System.out.println("Successfully updated weapon\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteWeapon(Weapon weapon){
		java.sql.PreparedStatement pstmt;
		String preparedDeleteStatement="DELETE FROM weapon WHERE weaponid=?";
		
		try {
			if(checkWeaponID(weapon).next()){
				pstmt = c.prepareStatement(preparedDeleteStatement);
				pstmt.setInt(1, weapon.getWeaponID());
				
				pstmt.executeUpdate();
				System.out.println("Succesfully deleted Weapon\n");
			}else{System.out.println("Weapon does not exist\n");}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Weapon> readAllWeapons(){
		java.sql.PreparedStatement pstmt;
		String prepearedSelectString ="SELECT * FROM weapon";
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();

		try {
			pstmt = c.prepareStatement(prepearedSelectString);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int weaponID = rs.getInt("weaponid");
				String name = rs.getString("name");
				String type = rs.getString("type");
				int might = rs.getInt("might");
				String wrange = rs.getString("wrange");
				int crit = rs.getInt("crit");
				int accuracy = rs.getInt("accuracy");
				weapons.add(new Weapon(weaponID, name, type, might, wrange, crit, accuracy));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully read all weapons\n");
		return weapons;
	}
	
	private ResultSet checkUsableWeaponsID(UsableWeapons usableweapon){
		ResultSet rs = null;
		java.sql.PreparedStatement checkpstmt;
		String checkid ="Select * FROM usableweapons WHERE classid=? AND weaponid=?";
		try {
			checkpstmt=c.prepareStatement(checkid);
			checkpstmt.setInt(1, usableweapon.getClassID());
			checkpstmt.setInt(2, usableweapon.getWeaponID());
			rs = checkpstmt.executeQuery();
			//checkpstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void saveUsableWeapons(UsableWeapons usableWeapon){
		ResultSet rs;

		try {
			rs = checkUsableWeaponsID(usableWeapon);
			
			if(!rs.next()){
				insertUsableWeapons(usableWeapon);
			}else{
				updateUsableWeapons(usableWeapon);
				}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertUsableWeapons(UsableWeapons usableweapon){
		java.sql.PreparedStatement pstmt;
		String preparedInsertString="INSERT INTO usableweapons VALUES(?,?);";
		
		try {
			pstmt=c.prepareStatement(preparedInsertString);
			pstmt.setInt(1,usableweapon.getClassID());
			pstmt.setInt(2, usableweapon.getWeaponID());

			pstmt.executeUpdate();
			System.out.println("Successfully inserted usable weapon\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateUsableWeapons(UsableWeapons usableweapon){
		java.sql.PreparedStatement pstmt;
		String preparedUpdateString ="UPDATE usableweapons SET classid=?, weaponid=? WHERE classid=? AND weaponid=?;";
		
		try {
			pstmt=c.prepareStatement(preparedUpdateString);
			pstmt.setInt(1, usableweapon.getClassID());
			pstmt.setInt(2, usableweapon.getWeaponID());
			pstmt.setInt(3, usableweapon.getClassID());
			pstmt.setInt(4, usableweapon.getWeaponID());
			
			pstmt.executeUpdate();
			System.out.println("Successfully updated usable weapon\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUsableWeapons(UsableWeapons usableweapon){
		java.sql.PreparedStatement pstmt;
		String preparedDeleteStatement="DELETE FROM usableweapons WHERE classid=? AND weaponid=?";
		
		try {
			if(checkUsableWeaponsID(usableweapon).next()){
				pstmt = c.prepareStatement(preparedDeleteStatement);
				pstmt.setInt(1, usableweapon.getClassID());
				pstmt.setInt(2, usableweapon.getWeaponID());
				
				pstmt.executeUpdate();
				System.out.println("Succesfully deleted Weapon\n");
			}else{System.out.println("Usable weapon does not exist\n");}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UsableWeapons> readAllUsableWeapons(){
		java.sql.PreparedStatement pstmt;
		String prepearedSelectString ="SELECT * FROM usableweapons";
		ArrayList<UsableWeapons> usableweapons = new ArrayList<UsableWeapons>();

		try {
			pstmt = c.prepareStatement(prepearedSelectString);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int classID = rs.getInt("classid");
				int weaponID = rs.getInt("weaponid");
				usableweapons.add(new UsableWeapons(classID, weaponID));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully read all usable weapons\n");
		return usableweapons;
	}
	
	public void fillTables(){
		fillClasses();
		fillCharacters();
		fillWeapons();
		fillUsableWeapons();
	}

	private void fillClasses(){
		f=new File("C:/Users/Simon/OneDrive/HTL/INFI-DBP Köllö/Characterdatenbank/Classes.csv");
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()){		
		String[] classAttributes= scan.nextLine().split(",");
		CharacterClass c = new CharacterClass((Integer.parseInt((classAttributes[0]))), classAttributes[1], classAttributes[2], classAttributes[3],  Integer.parseInt((classAttributes[4])));
		saveClass(c);
		}
	}
	
	private void fillCharacters(){
		f = new File("C:/Users/Simon/OneDrive/HTL/INFI-DBP Köllö/Characterdatenbank/Characters.csv");
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()){		
		String[] characterAttributes= scan.nextLine().split(",");
		PlayableCharacter c = new PlayableCharacter((Integer.parseInt((characterAttributes[0]))), Integer.parseInt(characterAttributes[1]), characterAttributes[2], Integer.parseInt(characterAttributes[3]),  Integer.parseInt(characterAttributes[4]), Integer.parseInt(characterAttributes[5]), Integer.parseInt(characterAttributes[6]),Integer.parseInt(characterAttributes[7]),Integer.parseInt(characterAttributes[8]));
		saveCharacter(c);
		}
	}
	
	private void fillWeapons(){
		f = new File("C:/Users/Simon/OneDrive/HTL/INFI-DBP Köllö/Characterdatenbank/Weapons.csv");
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()){		
		String[] weaponAttributes= scan.nextLine().split(",");
		Weapon c = new Weapon((Integer.parseInt((weaponAttributes[0]))), weaponAttributes[1], weaponAttributes[2], Integer.parseInt(weaponAttributes[3]), weaponAttributes[4], Integer.parseInt(weaponAttributes[5]), Integer.parseInt(weaponAttributes[6]));
		saveWeapon(c);
		}
	}

	private void fillUsableWeapons(){
		f=new File("C:/Users/Simon/OneDrive/HTL/INFI-DBP Köllö/Characterdatenbank/UsableWeapons.csv");
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()){		
		String[] usableWeaponsAttributes= scan.nextLine().split(",");
		UsableWeapons c = new UsableWeapons((Integer.parseInt((usableWeaponsAttributes[0]))), Integer.parseInt(usableWeaponsAttributes[1]));
		saveUsableWeapons(c);
		}
	}

	public void joinTables(){
		java.sql.Statement stmt;
		String joinString ="SELECT classid, characterclass.name, weapon.name FROM usableweapons JOIN characterclass USING(classid) JOIN weapon USING(weaponid)";

		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(joinString);
			while(rs.next()){
				String classID = rs.getString("classid");
				String classname = rs.getString("characterclass.name");
				String weaponname = rs.getString("weapon.name");
				System.out.println("|"+classID+"|\t"+classname+"\t|\t"+weaponname+"|");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

