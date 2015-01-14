package AllegroWebApi.iReportGenerate;

public class RaportQuery {
	String Query;
	String fromQuery;
	String whereQuery;
	public RaportQuery() {
			fromQuery="from \"ItemInfo\" II join \"itemAttribs\" IA on II.\"itId\" = IA.\"itId\" " +
				"join \"itemAttribs\" IA2 on II.\"itId\" = IA2.\"itId\" " +
				"join \"itemAttribs\" IA3 on II.\"itId\" = IA3.\"itId\" ";
		
		whereQuery="where IA.\"attribName\"='Rok produkcji' and IA2.\"attribName\"='Rodzaj paliwa' " +
				"and (IA2.\"attribValuesStart\"='Diesel' or  IA2.\"attribValuesStart\"='Benzyna') " +
				"and IA3.\"attribName\"='Uszkodzony' and IA3.\"attribValuesStart\"='Nie' ";
		
		Query = 
				"Select cast(IA.\"attribValuesStart\" as int) as rok, II.\"itBuyNowPrice\"as cena,  IA2.\"attribValuesStart\" as rodzaj_paliwa " +
				 "\n"+fromQuery +"\n"+ whereQuery+
						"group by IA.\"attribValuesStart\", IA2.\"attribValuesStart\", cena " +
				"order by IA.\"attribValuesStart\", IA2.\"attribValuesStart\"";
		

		System.out.println(Query);
	}
}
