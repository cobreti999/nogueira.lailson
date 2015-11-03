package edu.luc.webservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Order;

public class OrderDAO {
	public static Order findOrderByCustomerName(String customerName) {
		Customer customer = CustomerDAO.findByName(customerName);
		String query = "SELECT * FROM orders where customer_fk = '" + customer.getCustomerId() + "' ORDER BY Customer_ID DESC LIMIT 1;";
		try {
			ResultSet rs = TestHelper.makeQuery(query);
			Date orderDate = null;
			String orderShippingMethod = "";
			String orderEstimateDelivery = "";
			String orderStatus = "";
			int orderFinalPrice = 0;
			
			while (rs.next()){
				orderDate =  rs.getDate("Order_Date");
				orderEstimateDelivery = rs.getString("Order_EstimateDelivery");
				orderFinalPrice = rs.getInt("Order_FinalPrice");
				orderShippingMethod = rs.getString("Order_ShippingMethod");
				orderStatus = rs.getString("Order_Status");
			}		
			CustomerPayment customerPayment = CustomerPaymentDAO.findCustomerPaymentByCustomerName(customerName);
			Order order = new Order(customer, customerPayment, orderDate, orderFinalPrice, orderShippingMethod, orderEstimateDelivery, orderStatus);
			
			rs.close();
			TestHelper.stmt.close();
			TestHelper.conn.close();
			return order;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
	
	public static Order findOrderById(Short orderID, String customerName){
		String query = "SELECT * FROM orders where order_ID = '" + orderID + ";";
		try {
			ResultSet rs = TestHelper.makeQuery(query);
			Date orderDate = null;
			String orderShippingMethod = "";
			String orderEstimateDelivery = "";
			String orderStatus = "";
			int orderFinalPrice = 0;
			
			while (rs.next()){
				orderDate =  rs.getDate("Order_Date");
				orderEstimateDelivery = rs.getString("Order_EstimateDelivery");
				orderFinalPrice = rs.getInt("Order_FinalPrice");
				orderShippingMethod = rs.getString("Order_ShippingMethod");
				orderStatus = rs.getString("Order_Status");
			}		
			CustomerPayment customerPayment = CustomerPaymentDAO.findCustomerPaymentByCustomerName(customerName);
			Order order = new Order(customerPayment.getCustomer(), customerPayment, orderDate, orderFinalPrice, orderShippingMethod, orderEstimateDelivery, orderStatus);
			
			rs.close();
			TestHelper.stmt.close();
			TestHelper.conn.close();
			return order;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
}
