package order.utils;

public class OracleQueries {

	
	public static final String CREATEORDER = "INSERT INTO ORDER_TABLE "
			+ "(QUANTITY, ITEM_ID) "
			+ "VALUES(?, ?) ";
	
	public static final String GETALLITEMS = "SELECT * FROM ITEM_TABLE "
			+ "WHERE ITEM_QUANTITY_IN_STOCK > 0 ";
	
	public static final String UPDATEQUANTITYINSTOCK = "UPDATE ITEM_TABLE "
			+ "SET ITEM_QUANTITY_IN_STOCK = ? "
			+ "WHERE ITEM_ID = ?";

}
