package AllegroWebApiClient.WebApi;

import pl.allegro.webapi.service_php.*;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;

import javax.xml.rpc.ServiceException;

public class App 
{

    boolean doLogin(String userLogin, String userPassword, int countryCode, long localVersion, String webApiKey) {
    	 
        ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator(); 
        
		try {
			ServicePort allegro= allegroWebApiService.getservicePort();
			 DoLoginRequest doLoginRequest = new DoLoginRequest();
		        doLoginRequest.setUserLogin(userLogin);
		        doLoginRequest.setUserPassword(userPassword);
		        doLoginRequest.setCountryCode(countryCode);
		        doLoginRequest.setLocalVersion(localVersion);
		        doLoginRequest.setWebapiKey(webApiKey);
		     
		        DoLoginResponse doLoginResponse =  allegro.doLogin(doLoginRequest);
		        if (doLoginResponse.getSessionHandlePart() != "") {
		     
		            // Identyfikator sesji dla użytkownika jest trzymany w: doLoginResponse.getSessionHandlePart()
		            System.out.println(doLoginResponse.getSessionHandlePart() +"\n"+ doLoginResponse.getUserId());
		        	return true;
		        } else {
		            return false;
		        }
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(RemoteException e){
			e.printStackTrace();
		}
		return false;
     
       
    }
    void doGetItemsList(String webapiKey, FilterOptionsType[] filterOptions, SortOptionsType sortOptions, int resultSize, int resultOffset, int resultScope){
    	 
        ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator(); 
       
        try {
			ServicePort allegro= allegroWebApiService.getservicePort();
			DoGetItemsListRequest doGetitemsList= new DoGetItemsListRequest();
			doGetitemsList.setCountryId(1);
			doGetitemsList.setFilterOptions(filterOptions);
			doGetitemsList.setResultOffset(resultOffset);
			doGetitemsList.setResultScope(resultScope);
			doGetitemsList.setSortOptions(sortOptions);
			doGetitemsList.setWebapiKey(webapiKey);
			
			DoGetItemsListResponse doGetitemsListResponse = allegro.doGetItemsList(doGetitemsList);
			CategoriesListType itemList= new CategoriesListType();
			itemList=doGetitemsListResponse.getCategoriesList();
			for(int i =0; i<itemList.getCategoriesTree().length;i++){
			System.out.println(i+1+". "+itemList.getCategoriesTree()[i].getCategoryName()+" "+itemList.getCategoriesTree()[i].getCategoryItemsCount()+" "+itemList.getCategoriesTree()[i].getCategoryId());
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
