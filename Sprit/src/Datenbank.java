import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Datenbank {
	
	private Connection c = null;
	private ArrayList<SpritObjekt> spritdata = new ArrayList<SpritObjekt>();

	public Datenbank() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sprit.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public ArrayList<SpritObjekt> getJahresdurschnitProTag(int tankenr, String fromdate, String todate){
		String sql = "select avg(value),tankenr,date(datum,'unixepoch') as ddate from sprit_data where tankenr = ? and ddate > ? and ddate < ? group by ddate";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, tankenr);
			stmt.setString(2, fromdate);
			stmt.setString(3, todate);
			ResultSet rs =stmt.executeQuery();
			
		      while ( rs.next() ){
		         Date datum = (Date) formatter.parse(rs.getString("ddate"));
		         int tanke = rs.getInt("tankenr");
		         double  value = rs.getDouble("avg(value)");
		         spritdata.add(new SpritObjekt(datum, tanke, value));
		      }
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return spritdata;
	}
	
	public ArrayList<SpritObjekt> getBilligsteTanke(String fromdate, String todate){
		String sql = "select min(value),tankenr,strftime('%Y-%m-%d %H',datum,'unixepoch') as ddate from sprit_data where ddate >= ? and ddate <= ? group by ddate";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, fromdate);
			stmt.setString(2, todate);
			ResultSet rs = stmt.executeQuery();
			
		      while ( rs.next() ){
		    	  Date datum = (Date) formatter.parse(rs.getString("ddate"));
		         int tanke = rs.getInt("tankenr");
		         double  value = rs.getDouble("min(value)");
		         spritdata.add(new SpritObjekt(datum, tanke, value));
		      }
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return spritdata;
	}
	
	

}
