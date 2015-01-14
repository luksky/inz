package AllegroWebApi.H2Database;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemAttribsJDBCImpl {

	public DataModelItemAttribs createItemAttribs(long itId, String attribName,
			String attribValuesStart, String attribValuesEnd) throws SQLException{
		
		Connection conn = DriverManager.
			      getConnection("jdbc:h2:~/AllegroWebApi", "sa", "");
	
		PreparedStatement ps = 
			     conn.prepareStatement( "insert into \"itemAttribs\" values (?,?,?,?);" );
		ps.setLong(1, itId);
		ps.setString(2, attribName);
		ps.setString(3, attribValuesStart);
		ps.setString(4, attribValuesEnd);
		
		ps.executeUpdate();
		return new DataModelItemAttribs(itId, attribName, attribValuesStart, attribValuesEnd);
		
	}
	
	public void clearDatabase() throws SQLException{
		 
		Connection conn = DriverManager.
			      getConnection("jdbc:h2:~/AllegroWebApi", "sa", "");
		
		String deleteAttribs="delete from \"itemAttribs\"";
		String deleteItems="delete from \"ItemInfo\"";
		Statement ps = 
			     conn.createStatement();
		
		ps.executeUpdate(deleteAttribs);
		ps.executeUpdate(deleteItems);
		
	}

	
}
