package AllegroWebApi.H2Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

public H2Connection(){
	  Connection conn = null;
	try {
		Class.forName("org.h2.Driver");
		  conn = DriverManager.
		      getConnection("jdbc:h2:~/AllegroWebApi", "sa", "");
		  if(conn!=null)
		  System.out.println("Connection works");
		  else{
			  System.out.println("!!!!!!");
		  }
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      // add application code here
      try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	      
	  
}
