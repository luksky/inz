package AllegroWebApi.H2Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ItemInfoJDBCImpl {

	public DataModelItemInfo createItemInfo(long itId, long CountryId,
			String itName, float itPrice, long itBidCount,
			java.util.Date itEndingTime, long itSellerId, String itSellerLogin,
			float itStartingPrice, long itQuantity, float itReservePrice,
			String itLocation, float itBuyNowPrice, String itDescription,
			long itState, long itHitCount, long itStartingQuantity,
			long itEndingInfo, long itIsNewUsed) throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:~/AllegroWebApi", "sa", "");

		PreparedStatement ps = conn
				.prepareStatement("merge into \"ItemInfo\" key(\"itId\") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		// PreparedStatement ps =
		// conn.prepareStatement(
		// "insert into \"ItemInfo\" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
		// );
		ps.setLong(1, itId);
		ps.setLong(2, CountryId);
		ps.setString(3, itName);
		ps.setFloat(4, itPrice);
		ps.setLong(5, itBidCount);
		ps.setTimestamp(6, (Timestamp) itEndingTime);
		ps.setLong(7, itSellerId);
		ps.setString(8, itSellerLogin);
		ps.setFloat(9, itStartingPrice);
		ps.setLong(10, itQuantity);
		ps.setFloat(11, itReservePrice);
		ps.setString(12, itLocation);
		ps.setFloat(13, itBuyNowPrice);
		ps.setString(14, itDescription);
		ps.setLong(15, itState);
		ps.setLong(16, itHitCount);
		ps.setLong(17, itStartingQuantity);
		ps.setLong(18, itEndingInfo);
		ps.setLong(19, itIsNewUsed);

		ps.executeUpdate();

		return new DataModelItemInfo(itId, CountryId, itName, itPrice,
				itBidCount, itEndingTime, itSellerId, itSellerLogin,
				itStartingPrice, itQuantity, itReservePrice, itLocation,
				itBuyNowPrice, itDescription, itState, itHitCount,
				itStartingQuantity, itEndingInfo, itIsNewUsed);

	}

}
