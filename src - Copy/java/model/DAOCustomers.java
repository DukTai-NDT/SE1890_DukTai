/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.Product;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class DAOCustomers extends DBConnection {

    public int insertCustomers(Customer other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES\n"
                + "           ('" + other.getCustomerID() + "','" + other.getCompanyName() + "' ,'" + other.getContactName() + "' ,"
                + "'" + other.getContactTitle() + "' ,'" + other.getAddress() + "' ,'" + other.getCity() + "' ,"
                + "'" + other.getRegion() + "' ,'" + other.getPostalCode() + "' ,'" + other.getCountry() + "','" + other.getPhone() + "' ,'" + other.getFax() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCustomer(Customer other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES\n"
                + "           ( ?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCustomerID());
            prestate.setString(2, other.getCompanyName());
            prestate.setString(3, other.getContactName());
            prestate.setString(4, other.getContactTitle());
            prestate.setString(5, other.getAddress());
            prestate.setString(6, other.getCity());
            prestate.setString(7, other.getRegion());
            prestate.setString(8, other.getPostalCode());
            prestate.setString(9, other.getCountry());
            prestate.setString(10, other.getPhone());
            prestate.setString(11, other.getFax());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

public int deleteCustomer(String customerID){
    int n = 0;
    String sql = "Delete from Customers where CustomerID = "+ customerID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return n;
}
public int updateCustomer(Customer other) {
    int n = 0;
    String sql = "UPDATE [dbo].[Customers]\n"
            + "   SET [CompanyName] = ?, [ContactName] = ?, [ContactTitle] = ?, [Address] = ?, [City] = ?, "
            + "[Region] = ?, [PostalCode] = ?, [Country] = ?, [Phone] = ?, [Fax] = ?\n"
            + " WHERE [CustomerID] = ?"; 

    try {
        PreparedStatement prestate = conn.prepareStatement(sql);
       
        prestate.setString(1, other.getCompanyName());
        prestate.setString(2, other.getContactName());
        prestate.setString(3, other.getContactTitle());
        prestate.setString(4, other.getAddress());
        prestate.setString(5, other.getCity());
        prestate.setString(6, other.getRegion());
        prestate.setString(7, other.getPostalCode());
        prestate.setString(8, other.getCountry());
        prestate.setString(9, other.getPhone());
        prestate.setString(10, other.getFax());
        prestate.setString(11, other.getCustomerID()); 

        n = prestate.executeUpdate(); 
    } catch (SQLException ex) {
        Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex); // Ghi log lỗi
    }

    return n;
}
    public Vector<Customer> getCustomer(String sql){
        Vector<Customer> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                String CustomerID = rs.getString("CustomerID"),
                        CompanyName = rs.getString("CompanyName"),
                        ContactName = rs.getString("ContactName"),
                        ContactTitle = rs.getString("ContactTitle"),
                        Address = rs.getString("Address"),
                        City = rs.getString("City"),
                        Region = rs.getString("Region"),
                        PostalCode = rs.getString("PostalCode"),
                        Country = rs.getString("Country"),
                        Phone = rs.getString("Phone"),
                        Fax = rs.getString("Fax");
                
                Customer newCustomer = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                vector.add(newCustomer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOCustomers daocus = new DAOCustomers();
        
        Vector<Customer> vector = daocus.getCustomer("Select * from Customers");
        for (Customer customer : vector) {
            System.out.println(customer.toString());
        }
        
    }
}
