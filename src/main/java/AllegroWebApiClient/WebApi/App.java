package AllegroWebApiClient.WebApi;

import pl.allegro.webapi.service_php.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.xml.rpc.ServiceException;

import AllegroWebApi.H2Database.H2Connection;
import AllegroWebApi.H2Database.ItemAttribsJDBCImpl;
import AllegroWebApi.H2Database.ItemInfoJDBCImpl;

public class App {



	public ArrayList<ArrayList<String>> doGetCatsData() {

		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();
		ArrayList<ArrayList<String>> doGetCatsDataResp = new ArrayList<ArrayList<String>>();
		ArrayList<String> CategoryName = new ArrayList<String>();
		ArrayList<String> CategoryId = new ArrayList<String>();
		ArrayList<String> CategoryParentId = new ArrayList<String>();


		try {
			ServicePort allegro = allegroWebApiService.getservicePort();
			DoGetCatsDataRequest doGetCatsData = new DoGetCatsDataRequest();
			doGetCatsData.setWebapiKey("569a5dcc");
			doGetCatsData.setCountryId(1);

			DoGetCatsDataResponse doGetCatsDataResponse = allegro
					.doGetCatsData(doGetCatsData);

			for (int i = 0; i < doGetCatsDataResponse.getCatsList().length; i++) {
		
					CategoryName.add(doGetCatsDataResponse.getCatsList()[i].getCatName());
					CategoryId.add((Integer.toString(doGetCatsDataResponse.getCatsList()[i].getCatId())));
					CategoryParentId.add((Integer.toString(doGetCatsDataResponse.getCatsList()[i].getCatParent())));
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGetCatsDataResp.add(CategoryName);
		doGetCatsDataResp.add(CategoryId);
		doGetCatsDataResp.add(CategoryParentId);
		return doGetCatsDataResp;
	}
	
	public long doGetLocalVersionValue() {

		long localVersion=0;
		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();
		try {
			ServicePort allegro = allegroWebApiService.getservicePort();
			DoGetCatsDataRequest doGetCatsData = new DoGetCatsDataRequest();
			doGetCatsData.setWebapiKey("569a5dcc");
			doGetCatsData.setCountryId(1);

			DoGetCatsDataResponse doGetCatsDataResponse = allegro
					.doGetCatsData(doGetCatsData);
			localVersion=doGetCatsDataResponse.getVerKey();
			System.out.println(localVersion);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localVersion;
	}
	public String doLogin(String userLogin, String userPassword){
		String sessionHandlePart=null;
		
		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();

		try {
			ServicePort allegro = allegroWebApiService.getservicePort();
			DoLoginRequest doLoginRequest = new DoLoginRequest();
			doLoginRequest.setUserLogin(userLogin);
			doLoginRequest.setUserPassword(userPassword);
			doLoginRequest.setCountryCode(1);
			doLoginRequest.setLocalVersion(doGetLocalVersionValue());
			doLoginRequest.setWebapiKey("569a5dcc");

			DoLoginResponse doLoginResponse = allegro.doLogin(doLoginRequest);
			sessionHandlePart=doLoginResponse.getSessionHandlePart();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		return sessionHandlePart;
		
	}
	public ArrayList<Long> doGetItemsList(String CategoryId) {

		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();
		ArrayList<Long> ItemListResp = new ArrayList<Long>();

		FilterOptionsType[] FOT = new FilterOptionsType[1];
		FOT[0] = new FilterOptionsType();
		String FilterValueId[] = new String[1];
		FilterValueId[0]=CategoryId;
		FOT[0].setFilterValueId(FilterValueId);
		FOT[0].setFilterId("category");
		try {
			ServicePort allegro = allegroWebApiService.getservicePort();
			DoGetItemsListRequest doGetitemsList = new DoGetItemsListRequest();
			doGetitemsList.setCountryId(1);
			doGetitemsList.setFilterOptions(FOT);
			
			doGetitemsList.setResultOffset(0);
			doGetitemsList.setResultScope(3);
			doGetitemsList.setSortOptions(null);
			doGetitemsList.setWebapiKey("569a5dcc");
			doGetitemsList.setResultSize(1000);
			DoGetItemsListResponse doGetitemsListResponse = allegro
					.doGetItemsList(doGetitemsList);
			
			for(int i =0;i<doGetitemsListResponse.getItemsList().length;i++){
				ItemListResp.add(doGetitemsListResponse.getItemsList()[i].getItemId());
				try {
					writeFile(doGetitemsListResponse.getItemsList()[i].getItemId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			int requestIteration=0;
			
			while(ItemListResp.size()<doGetitemsListResponse.getItemsCount()){
				
				requestIteration++;
				
				if(doGetitemsListResponse.getItemsCount()<requestIteration*1000+1000){
					doGetitemsList.setResultSize(doGetitemsListResponse.getItemsCount()-requestIteration*1000);
					doGetitemsList.setResultOffset(requestIteration*1000);
					System.out.println(doGetitemsListResponse.getItemsCount()-requestIteration*1000+" if "+requestIteration);
				}
				else{
					doGetitemsList.setResultOffset(requestIteration*1000);
					System.out.println("else");
				}
				
				doGetitemsListResponse = allegro
						.doGetItemsList(doGetitemsList);
				
				for(int i =0;i<doGetitemsList.getResultSize();i++){
					
					ItemListResp.add(doGetitemsListResponse.getItemsList()[i].getItemId());
					try {
						writeFile(doGetitemsListResponse.getItemsList()[i].getItemId());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			System.out.println("Pobrano: "+ItemListResp.size()+" elementów");	
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ItemListResp;
	}
	public void itemListUpdate(ArrayList<Long> itemsIdList, String sessionHandle){

		ServiceServiceLocator allegroWebApiService = new ServiceServiceLocator();
		ArrayList<Long> tempItemList = new ArrayList<Long>();
		new H2Connection();
		ItemInfoJDBCImpl itemInfoCreation = new ItemInfoJDBCImpl();
		ItemAttribsJDBCImpl iteamAttribsCreation = new ItemAttribsJDBCImpl();
		int i = 0;
		
		while(i<itemsIdList.size()){
			tempItemList.add(itemsIdList.get(i));
			i++;
			if(tempItemList.size()==25){
				
				try {
					ServicePort allegro = allegroWebApiService.getservicePort();
					DoGetItemsInfoRequest itemsInfoRequest= new DoGetItemsInfoRequest();
					
					long[] tempItemList2=new long[25];
					for(int k=0;k<tempItemList.size();k++){
						tempItemList2[k]=tempItemList.get(k);
					}
					
					itemsInfoRequest.setItemsIdArray(tempItemList2);
					itemsInfoRequest.setSessionHandle(sessionHandle);
					itemsInfoRequest.setGetDesc(1);
					itemsInfoRequest.setGetImageUrl(1);
					itemsInfoRequest.setGetAttribs(1);
					itemsInfoRequest.setGetPostageOptions(0);
					itemsInfoRequest.setGetCompanyInfo(0);
					
					DoGetItemsInfoResponse itemsInfoResponse= allegro.doGetItemsInfo(itemsInfoRequest);
					ItemInfoStruct[] itemsInfoStruct= itemsInfoResponse.getArrayItemListInfo();
					ItemInfo itemInfo;
					AttribStruct[] itemAttribs;
					
					for(int l=0;l<itemsInfoStruct.length;l++){
						itemInfo=itemsInfoResponse.getArrayItemListInfo()[l].getItemInfo();
						itemAttribs=itemsInfoResponse.getArrayItemListInfo()[l].getItemAttribs();
						itemInfoCreation.createItemInfo(itemInfo.getItId(),itemInfo.getItCountry(), itemInfo.getItName(), itemInfo.getItPrice(),
								itemInfo.getItBidCount(),new Timestamp(itemInfo.getItEndingTime()*1000), itemInfo.getItSellerId(), itemInfo.getItSellerLogin(),
								itemInfo.getItStartingPrice(), itemInfo.getItQuantity(), itemInfo.getItReservePrice(), itemInfo.getItLocation()
								, itemInfo.getItBuyNowPrice(), itemInfo.getItDescription(), itemInfo.getItState(), itemInfo.getItHitCount(),
								itemInfo.getItStartingQuantity(), itemInfo.getItEndingInfo(), itemInfo.getItIsNewUsed());
				
						for (int m=0;m<itemAttribs.length;m++){
							for(int n=0;n<itemAttribs[m].getAttribValues().length;n++){
								iteamAttribsCreation.createItemAttribs(itemInfo.getItId(), itemAttribs[m].getAttribName(), itemAttribs[m].getAttribValues()[n], null);
							}
							
							
						}
						

					
					
					
				
					
					}
					
					
					
					
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				
				tempItemList.clear();
			}
		}
		
		
		
		
	}
	public void writeFile(long input) throws IOException {
		PrintWriter printWriter = null;
		  printWriter = new PrintWriter(new FileOutputStream("out.txt", true));
		  printWriter.write(Long.toString(input));
		  printWriter.println();
		
	 
		  printWriter.close();
	}
	
}
