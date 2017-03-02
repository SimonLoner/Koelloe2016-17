import java.sql.*;
import java.util.ArrayList;

public class Database {

	private Connection c = null;
	private ArrayList<Usage> stromVerbrauch = new ArrayList<Usage>();
	private ArrayList<Usage> wasserVerbrauch = new ArrayList<Usage>();

	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:verbrauch.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public ArrayList<Usage> selectWasserverbrauch(){
		String sql = "SELECT * FROM wasser_verbrauch ORDER BY datum ASC;";
		try {
			Statement stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
		      while ( rs.next() ) {
		         long datum = rs.getLong("datum");
		         double  value = rs.getDouble("value");
		         wasserVerbrauch.add(new Usage(datum, value));
		      }
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wasserVerbrauch;
	}
	public ArrayList<Usage> selectStromverbrauch(){
		String sql = "SELECT * FROM strom_verbrauch ORDER BY datum ASC;";
		try {
			Statement stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
		      while ( rs.next() ) {
		         long datum = rs.getLong("datum");
		         double  value = rs.getDouble("value");
		         stromVerbrauch.add(new Usage(datum,value));
		      }
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stromVerbrauch;
	}
}
