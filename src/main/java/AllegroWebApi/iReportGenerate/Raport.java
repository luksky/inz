package AllegroWebApi.iReportGenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Raport {
	
	public Raport(){
		try{
			JasperDesign jasperDesign = JRXmlLoader.load("Raport.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			  Connection conn = null;
				try {
					Class.forName("org.h2.Driver");
					  conn = DriverManager.
					      getConnection("jdbc:h2:~/AllegroWebApi", "sa", "");
					  if(conn!=null)
					  System.out.println("Connection works");
				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
			JasperViewer.viewReport(jasperPrint);
			conn.close();
			}catch(Exception ex) {
			String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
			System.out.println(connectMsg);
			}
		
	}
}

