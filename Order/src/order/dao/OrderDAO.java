package order.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import order.dao.OracleConnection;
import order.model.Order;
import order.utils.OracleQueries;


public class OrderDAO {

	
	public static boolean createOrder(Map<Integer,Integer> orders) throws SQLException {
		
		boolean orderCreated = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		int result=0;
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.CREATEORDER);
			for (Map.Entry<Integer, Integer> entry : orders.entrySet()) {
				stmt.setInt(1, entry.getValue());
				stmt.setInt(2, entry.getKey());
				result = stmt.executeUpdate();	
				
			}

			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		if(result != 0) {
			orderCreated = true;
		}
		return orderCreated;
	}
	
}
