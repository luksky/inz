package AllegroWebApiClient.WebApi;

import java.rmi.RemoteException;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;

import pl.allegro.webapi.service_php.FilterOptionsType;
import pl.allegro.webapi.service_php.RangeValueType;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		App AllegroFunctionality= new App();
		FilterOptionsType[] FOT = new FilterOptionsType[1];
		FOT[0]=new FilterOptionsType();
		String FilterValueId[] = new String[1];
		FilterValueId[0] = "1";
		FOT[0].setFilterValueId(FilterValueId);
		
		FOT[0].setFilterValueRange(new RangeValueType());
		FOT[0].setFilterId("category");
		//System.out.println(AllegroFunctionality.doLogin("luksky92","kanalia665",1, 1415799229,"569a5dcc"));
		AllegroFunctionality.doGetItemsList("569a5dcc",  FOT , null, 100, 0, 1);
		
		
	}

}
