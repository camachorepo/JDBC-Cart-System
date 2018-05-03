package order.mainentry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import order.dao.ItemDAO;
import order.dao.OrderDAO;
import order.model.Item;
import order.model.Order;

public class MainEntry {

	public static void main (String [] args) throws SQLException {
		
		
		Map<Integer, Integer> itemmap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> ordermap = new HashMap<Integer, Integer>();
		List<Item> items = new ArrayList<Item>();
		
		
		Scanner sc =  new Scanner(System.in);
		OrderDAO DAO = new OrderDAO();  
		ItemDAO iDAO = new ItemDAO();
		int option =0;
		int itemid =0; 
		int quantity =0;
		boolean orderCreated = false;
		
		
		System.out.println("Welcome to Pathmark Supermarket!");
		while(option != 5) {
			
			System.out.println("Please choose an option");
			System.out.println("1 Show inventory");
			System.out.println("2 Add items to cart"); 
			System.out.println("3 Checkout from the store");
			System.out.print("Option > ");
			option = sc.nextInt();
			switch (option) {
			
			case 1: 
				items = iDAO.getAllItems();
				for (Item i : items) {
					itemmap.put(i.getId(), i.getQuantityInStock());
				}
				break; 
			case 2: 
				items = iDAO.getAllItems();
				for (Item i : items) {
					itemmap.put(i.getId(), i.getQuantityInStock());
				}
				System.out.println("Please enter item number:");
				itemid = sc.nextInt();

				
				System.out.println("Please enter Item Quantity : ");
				quantity = sc.nextInt();
				if(itemmap.get(itemid) >= quantity) {
					ordermap.put(itemid, quantity);
					System.out.println("Added to cart");
					
				}
				break;
			case 3:	
			orderCreated =	OrderDAO.createOrder(ordermap);
			if(orderCreated) {
				ItemDAO.updateQuantityInStock(itemid, itemmap.get(itemid) - quantity);
				System.out.println("You have successfully place your order!");
			}
			
				break;
			default:	
				System.out.println("Invalid Input Please Try Again");
				break;
			
			}
			
			
		}
		
		
		
		

		
		

		
	}
	
	public static void addToCart() {
		
		
	}
	
}
