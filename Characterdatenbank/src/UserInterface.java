import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	static Manager cm = Manager.getManager();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]){
		cm.fillTables();
		boolean goon = true;
		System.out.println("Welcome to the Databse");
		while(goon){
			Interface();
			System.out.println("Do you want to continue? (true/false)");
			goon = scan.nextBoolean();
		}

	}
	
	public static void Interface(){
		
		System.out.println("add/change Character: \t a");
		System.out.println("remove Character: \t b");
		System.out.println("show Characters: \t c");
		System.out.println("add/change Class: \t d");
		System.out.println("remove Class: \t\t e");
		System.out.println("show Classes: \t\t f");
		System.out.println("add/change Weapon: \t g");
		System.out.println("remove Weapon: \t\t h");
		System.out.println("show Weapon: \t\t i");
		System.out.println("add/change U.leWeapon: \t j");
		System.out.println("remove UsableWeapon: \t k");
		System.out.println("show UsableWeapons: \t l");
		System.out.println("show joined Tables: \t m");
		System.out.println("show print HTML: \t n");
		
		System.out.println("Please select how you want to continue:");
		char choice = Character.toLowerCase(scan.next().charAt(0));
		
		switch(choice){
		
		case 'a':
			addChangeCharacter();
		break;
		case 'b':
			removeCharacter();
		break;
		case 'c':
			showCharacters();
		break;
		case 'd':
			addChangeClass();
		break;
		case 'e':
			removeClass();
		break;
		case 'f':
			showClasses();
		break;
		case 'g':
			addChangeWeapon();
		break;
		case 'h':
			removeWeapon();
		break;
		case 'i':
			showWeapons();
		break;
		case 'j':
			addChangeUsableWeapons();
		break;
		case 'k':
			removeUsableWeapons();
		break;
		case 'l':
			showUsableWeapons();
		break;
		case 'm':
			cm.joinTables();
		break;
		case 'n':
			printHTML();
		break;
		default:
			System.out.println("Selected option not available");
		}
	}
	
	public static void addChangeCharacter(){
		System.out.println("Please enter the Information (CharacterID, ClassID, Name, Strength, Magic, Skill, Speed, Defense, Resistance):");
		int characterID = scan.nextInt();
		int classID = scan.nextInt();
		String name = scan.next();
		int strength = scan.nextInt();
		int magic = scan.nextInt();
		int skill = scan.nextInt();
		int speed = scan.nextInt();
		int defense = scan.nextInt();
		int resistance = scan.nextInt();
		PlayableCharacter c = new PlayableCharacter(characterID,classID,name,strength,magic,skill,speed,defense,resistance);
		cm.saveCharacter(c);
	}
	
	public static void removeCharacter(){
		System.out.println("Please enter the Information (CharacterID):");
		int characterID = scan.nextInt();
		PlayableCharacter c = new PlayableCharacter(characterID, 0, "", 0, 0, 0, 0, 0, 0);
		cm.deleteCharacter(c);
	}
	
	public static void showCharacters(){
		ArrayList<PlayableCharacter> characters = cm.readAllCharacters();		
		for(PlayableCharacter chars : characters){
			System.out.println(chars.toString());
		}
	}
	
	public static void addChangeClass(){
		System.out.println("Please enter the Information (ClassID, Name, Skill1, Skill2, Move):");
		int classID = scan.nextInt();
		String name = scan.next();
		String skill1 = scan.next();
		String skill2 = scan.next();
		int move = scan.nextInt();
		CharacterClass c = new CharacterClass(classID,name,skill1,skill2,move);
		cm.saveClass(c);
	}

	public static void removeClass(){
		System.out.println("Please enter the Information (ClassID):");
		int classID = scan.nextInt();
		CharacterClass c = new CharacterClass(classID, "", "", "", 0);
		cm.deleteClass(c);
	}
	
	public static void showClasses(){
		ArrayList<CharacterClass> classes = cm.readAllClasses();		
		for(CharacterClass clas : classes){
			System.out.println(clas.toString());
		}
	}
	
	public static void addChangeWeapon(){
		System.out.println("Please enter the Information (WeaponID, Name, Type, Might, Range, Crit, Accuracy):");
		int weaponID = scan.nextInt();
		String name = scan.next();
		String type = scan.next();
		int might = scan.nextInt();
		String range = scan.next();
		int crit = scan.nextInt();
		int accuracy = scan.nextInt();
		Weapon w = new Weapon(weaponID,name,type,might,range,crit,accuracy);
		cm.saveWeapon(w);
	}
	
	public static void removeWeapon(){
		System.out.println("Please enter the Information (WeaponID:");
		int weaponID = scan.nextInt();
		Weapon w = new Weapon(weaponID, "", "", 0, "", 0, 0);
		cm.deleteWeapon(w);
	}
	
	public static void showWeapons(){
		ArrayList<Weapon> weapons = cm.readAllWeapons();		
		for(Weapon weap : weapons){
			System.out.println(weap.toString());
		}
	}
	
	public static void addChangeUsableWeapons(){
		System.out.println("Please enter the Information (ClassID, WeaponID):");
		int classID = scan.nextInt();
		int weaponID = scan.nextInt();
		UsableWeapons uw = new UsableWeapons(classID, weaponID);
		cm.saveUsableWeapons(uw);
	}
	
	public static void showUsableWeapons(){
		ArrayList<UsableWeapons> usableweapons = cm.readAllUsableWeapons();		
		for(UsableWeapons useweap : usableweapons){
			System.out.println(useweap.toString());
		}
	}
	
	public static void removeUsableWeapons(){
		System.out.println("Please enter the Information (ClassId, WeaponID):");
		int classID = scan.nextInt();
		int weaponID = scan.nextInt();
		UsableWeapons uw = new UsableWeapons(classID, weaponID);
		cm.deleteUsableWeapons(uw);
	}
	
	public static void printHTML(){
		printCharacters();
		printClasses();
		printWeapons();
		printUsableWeapons();
	}
	
	private static void printCharacters(){
		File f = new File("characters.html");
		ArrayList<PlayableCharacter> characters = cm.readAllCharacters();		

		try {
			PrintWriter writer = new PrintWriter(f);
			writer.println("<html>");
			writer.println("<head>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<table width="+100+"% "+"border="+1+">");
			writer.println("<tr>");
			writer.println("<td>CharacterID</td>");
			writer.println("<td>ClassID</td>");
			writer.println("<td>Name</td>");
			writer.println("<td>Strength</td>");
			writer.println("<td>Magic</td>");
			writer.println("<td>Skill</td>");
			writer.println("<td>Speed</td>");
			writer.println("<td>Defense</td>");
			writer.println("<td>Resistance</td>");
			writer.println("</tr>");
			for(PlayableCharacter chars : characters){
				writer.println("<tr>");
				writer.println("<td>"+chars.getCharacterID()+"</td>");
				writer.println("<td>"+chars.getClassID()+"</td>");
				writer.println("<td>"+chars.getName()+"</td>");
				writer.println("<td>"+chars.getStrength()+"</td>");
				writer.println("<td>"+chars.getMagic()+"</td>");
				writer.println("<td>"+chars.getSkill()+"</td>");
				writer.println("<td>"+chars.getSpeed()+"</td>");
				writer.println("<td>"+chars.getDefense()+"</td>");
				writer.println("<td>"+chars.getResistance()+"</td>");
				writer.println("</tr>");
			}
			writer.println("</table>");
			writer.println("</body>");
			writer.println("</html>");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void printClasses(){
		File f = new File("classes.html");
		ArrayList<CharacterClass> classes = cm.readAllClasses();		

		try {
			PrintWriter writer = new PrintWriter(f);
			writer.println("<html>");
			writer.println("<head>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<table width="+100+"% "+"border="+1+">");
			writer.println("<tr>");
			writer.println("<td>ClassID</td>");
			writer.println("<td>Name</td>");
			writer.println("<td>Skill1</td>");
			writer.println("<td>Skill2</td>");
			writer.println("<td>Move</td>");
			writer.println("</tr>");
			for(CharacterClass clas : classes){
				writer.println("<tr>");
				writer.println("<td>"+clas.getClassID()+"</td>");
				writer.println("<td>"+clas.getName()+"</td>");
				writer.println("<td>"+clas.getSkill1()+"</td>");
				writer.println("<td>"+clas.getSkill2()+"</td>");
				writer.println("<td>"+clas.getMove()+"</td>");
				writer.println("</tr>");
			}
			writer.println("</table>");
			writer.println("</body>");
			writer.println("</html>");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void printWeapons(){
		File f = new File("weapons.html");
		ArrayList<Weapon> weapons = cm.readAllWeapons();		

		try {
			PrintWriter writer = new PrintWriter(f);
			writer.println("<html>");
			writer.println("<head>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<table width="+100+"% "+"border="+1+">");
			writer.println("<tr>");
			writer.println("<td>WeaponID</td>");
			writer.println("<td>Name</td>");
			writer.println("<td>Type</td>");
			writer.println("<td>Might</td>");
			writer.println("<td>Range</td>");
			writer.println("<td>Crit</td>");
			writer.println("<td>Accuracy</td>");
			writer.println("</tr>");
			for(Weapon weap : weapons){
				writer.println("<tr>");
				writer.println("<td>"+weap.getWeaponID()+"</td>");
				writer.println("<td>"+weap.getName()+"</td>");
				writer.println("<td>"+weap.getType()+"</td>");
				writer.println("<td>"+weap.getMight()+"</td>");
				writer.println("<td>"+weap.getWrange()+"</td>");
				writer.println("<td>"+weap.getCrit()+"</td>");
				writer.println("<td>"+weap.getAccuracy()+"</td>");
				writer.println("</tr>");
			}
			writer.println("</table>");
			writer.println("</body>");
			writer.println("</html>");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void printUsableWeapons(){
		File f = new File("usableweapons.html");
		ArrayList<UsableWeapons> usableweapons = cm.readAllUsableWeapons();

		try {
			PrintWriter writer = new PrintWriter(f);
			writer.println("<html>");
			writer.println("<head>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<table width="+100+"% "+"border="+1+">");
			writer.println("<tr>");
			writer.println("<td>ClassID</td>");
			writer.println("<td>WeaponID</td>");
			writer.println("</tr>");
			for(UsableWeapons usaweap : usableweapons){
				writer.println("<tr>");
				writer.println("<td>"+usaweap.getClassID()+"</td>");
				writer.println("<td>"+usaweap.getWeaponID()+"</td>");
				writer.println("</tr>");
			}
			writer.println("</table>");
			writer.println("</body>");
			writer.println("</html>");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

