package order.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import order.dao.OracleConnection;
import order.model.Item;
import order.utils.OracleQueries;


public class ItemDAO {

	
	public static List<Item> getAllItems() throws SQLException {
		
		
		List<Item> item = new ArrayList<Item>();	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETALLITEMS);
			result = stmt.executeQuery();
			
			System.out.println("ITEMS IN STOCK");
			
			while(result != null && result.next() ) {
				
				
				System.out.print("Item ID : " + result.getInt(1) );
				System.out.print("  Item name: " + result.getString(2));
				System.out.print("  Item Quantity: " + result.getInt(3));
				System.out.println("  Item Price: $" + result.getDouble(4));
				
				Item local = new Item();
				local.setId(result.getInt(1));
				local.setName(result.getString(2));
				local.setQuantityInStock(result.getInt(3));
				local.setPrice(result.getDouble(4));
				
				item.add(local);
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			result.close();
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	
		
		return item;
	}
	
	
public static boolean updateQuantityInStock (int id, int quantity_in_stock) throws SQLException {
	Connection conn = null; 
	PreparedStatement stmt = null;
	int result =0;
	boolean success = false;
	try {
		conn = OracleConnection.getConnection();
		stmt = conn.prepareStatement(OracleQueries.UPDATEQUANTITYINSTOCK);
		stmt.setInt(1, quantity_in_stock);
		stmt.setInt(2, id);
		result = stmt.executeUpdate();
		
	} catch (ClassNotFoundException | IOException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}
	
	if(result != 0){
		success = true;
	}
	return success;
	
}
	
}
