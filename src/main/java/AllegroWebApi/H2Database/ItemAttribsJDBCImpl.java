package AllegroWebApi.H2Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	
}
