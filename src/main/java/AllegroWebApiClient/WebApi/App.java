package AllegroWebApiClient.WebApi;

import pl.allegro.webapi.service_php.*;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.rpc.ServiceException;

public class App {

	boolean doLogin(String userLogin, String userPassword, int countryCode,
			long localVersion, String webApiKey) {

		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();

		try {
			ServicePort allegro = allegroWebApiService.getservicePort();
			DoLoginRequest doLoginRequest = new DoLoginRequest();
			doLoginRequest.setUserLogin(userLogin);
			doLoginRequest.setUserPassword(userPassword);
			doLoginRequest.setCountryCode(countryCode);
			doLoginRequest.setLocalVersion(localVersion);
			doLoginRequest.setWebapiKey(webApiKey);

			DoLoginResponse doLoginResponse = allegro.doLogin(doLoginRequest);
			if (doLoginResponse.getSessionHandlePart() != "") {

				// Identyfikator sesji dla użytkownika jest trzymany w:
				// doLoginResponse.getSessionHandlePart()
				System.out.println(doLoginResponse.getSessionHandlePart()
						+ "\n" + doLoginResponse.getUserId());
				return true;
			} else {
				return false;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}

	public ArrayList<ArrayList<String>> doGetItemsList(String ValueItemId, int CategoryParentId) {

		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();
		ArrayList<ArrayList<String>> ItemListResp = new ArrayList<ArrayList<String>>();
		ArrayList<String> CategoryName = new ArrayList<String>();
		ArrayList<String> CategoryItemsCount = new ArrayList<String>();
		ArrayList<String> CategoryId = new ArrayList<String>();

		FilterOptionsType[] FOT = new FilterOptionsType[1];
		FOT[0] = new FilterOptionsType();
		String FilterValueId[] = new String[1];
		FilterValueId[0] = ValueItemId;
		FOT[0].setFilterValueId(FilterValueId);

		FOT[0].setFilterValueRange(new RangeValueType());
		FOT[0].setFilterId("category");
		try {
			ServicePort allegro = allegroWebApiService.getservicePort();
			DoGetItemsListRequest doGetitemsList = new DoGetItemsListRequest();
			doGetitemsList.setCountryId(1);
			doGetitemsList.setFilterOptions(FOT);
			doGetitemsList.setResultOffset(0);
			doGetitemsList.setResultScope(1);
			doGetitemsList.setSortOptions(null);
			doGetitemsList.setWebapiKey("569a5dcc");

			DoGetItemsListResponse doGetitemsListResponse = allegro
					.doGetItemsList(doGetitemsList);
			CategoriesListType catList = new CategoriesListType();
			catList = doGetitemsListResponse.getCategoriesList();

			for (int i = 0; i < catList.getCategoriesTree().length; i++) {
				if (catList.getCategoriesTree()[i].getCategoryParentId() == CategoryParentId) {
					/*System.out.println(i
							+ 1
							+ ". "
							+ catList.getCategoriesTree()[i].getCategoryName()
							+ " "
							+ catList.getCategoriesTree()[i]
									.getCategoryItemsCount() + " "
							+ catList.getCategoriesTree()[i].getCategoryId());*/
					CategoryName.add(catList.getCategoriesTree()[i]
							.getCategoryName());
					CategoryItemsCount.add(Integer.toString(catList
							.getCategoriesTree()[i].getCategoryItemsCount()));
					CategoryId
							.add(Integer.toString(catList.getCategoriesTree()[i]
									.getCategoryId()));
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ItemListResp.add(CategoryName);
		ItemListResp.add(CategoryItemsCount);
		ItemListResp.add(CategoryId);
		return ItemListResp;
	}
}
