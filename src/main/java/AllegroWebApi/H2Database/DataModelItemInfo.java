package AllegroWebApi.H2Database;

import java.sql.Timestamp;

public class DataModelItemInfo {

	long itId;
	long CountryId;
	String itName;
	float itPrice;
	long itBidCount;
	java.util.Date itEndingTime;
	long itSellerId;
	String itSellerLogin;
	float itStartingPrice;
	long itQuantity;
	float itReservePrice;
	String itLocation;
	float itBuyNowPrice;
	String itDescription;
	long itState;
	long itHitCount;
	long itStartingQuantity;
	long itEndingInfo;
	long itIsNewUsed;

	public DataModelItemInfo(long itId, long CountryId, String itName,
			float itPrice, long itBidCount, java.util.Date itEndingTime,
			long itSellerId, String itSellerLogin, float itStartingPrice,
			long itQuantity, float itReservePrice, String itLocation,
			float itBuyNowPrice, String itDescription, long itState,
			long itHitCount, long itStartingQuantity, long itEndingInfo,
			long itIsNewUsed) {
		this.itId = itId;
		this.CountryId = CountryId;
		this.itName = itName;
		this.itPrice = itPrice;
		this.itBidCount = itBidCount;
		this.itEndingTime = itEndingTime;
		this.itSellerId = itSellerId;
		this.itSellerLogin = itSellerLogin;
		this.itStartingPrice = itStartingPrice;
		this.itQuantity = itQuantity;
		this.itReservePrice = itReservePrice;
		this.itLocation = itLocation;
		this.itBuyNowPrice = itBuyNowPrice;
		this.itDescription = itDescription;
		this.itState = itState;
		this.itHitCount = itHitCount;
		this.itStartingQuantity = itStartingQuantity;
		this.itEndingInfo = itEndingInfo;
		this.itIsNewUsed = itIsNewUsed;

	}

	public long getItId() {
		return itId;
	}

	public void setItId(long itId) {
		this.itId = itId;
	}

	public long getCountryId() {
		return CountryId;
	}

	public void setCountryId(long countryId) {
		CountryId = countryId;
	}

	public String getItName() {
		return itName;
	}

	public void setItName(String itName) {
		this.itName = itName;
	}

	public float getItPrice() {
		return itPrice;
	}

	public void setItPrice(float itPrice) {
		this.itPrice = itPrice;
	}

	public long getItBidCount() {
		return itBidCount;
	}

	public void setItBidCount(long itBidCount) {
		this.itBidCount = itBidCount;
	}

	public java.util.Date getItEndingTime() {
		return itEndingTime;
	}

	public void setItEndingTime(Timestamp itEndingTime) {
		this.itEndingTime = itEndingTime;
	}

	public long getItSellerId() {
		return itSellerId;
	}

	public void setItSellerId(long itSellerId) {
		this.itSellerId = itSellerId;
	}

	public String getItSellerLogin() {
		return itSellerLogin;
	}

	public void setItSellerLogin(String itSellerLogin) {
		this.itSellerLogin = itSellerLogin;
	}

	public float getItStartingPrice() {
		return itStartingPrice;
	}

	public void setItStartingPrice(float itStartingPrice) {
		this.itStartingPrice = itStartingPrice;
	}

	public long getItQuantity() {
		return itQuantity;
	}

	public void setItQuantity(long itQuantity) {
		this.itQuantity = itQuantity;
	}

	public float getItReservePrice() {
		return itReservePrice;
	}

	public void setItReservePrice(float itReservePrice) {
		this.itReservePrice = itReservePrice;
	}

	public String getItLocation() {
		return itLocation;
	}

	public void setItLocation(String itLocation) {
		this.itLocation = itLocation;
	}

	public float getItBuyNowPrice() {
		return itBuyNowPrice;
	}

	public void setItBuyNowPrice(float itBuyNowPrice) {
		this.itBuyNowPrice = itBuyNowPrice;
	}

	public String getItDescription() {
		return itDescription;
	}

	public void setItDescription(String itDescription) {
		this.itDescription = itDescription;
	}

	public long getItState() {
		return itState;
	}

	public void setItState(long itState) {
		this.itState = itState;
	}

	public long getItHitCount() {
		return itHitCount;
	}

	public void setItHitCount(long itHitCount) {
		this.itHitCount = itHitCount;
	}

	public long getItStartingQuantity() {
		return itStartingQuantity;
	}

	public void setItStartingQuantity(long itStartingQuantity) {
		this.itStartingQuantity = itStartingQuantity;
	}

	public long getItEndingInfo() {
		return itEndingInfo;
	}

	public void setItEndingInfo(long itEndingInfo) {
		this.itEndingInfo = itEndingInfo;
	}

	public long getItIsNewUsed() {
		return itIsNewUsed;
	}

	public void setItIsNewUsed(long itIsNewUsed) {
		this.itIsNewUsed = itIsNewUsed;
	}

}
