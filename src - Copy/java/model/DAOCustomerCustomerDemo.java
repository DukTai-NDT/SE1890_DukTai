/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
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
public class DAOCustomerCustomerDemo extends DBConnection {

    public int insertCustomerDemo(CustomerCustomerDemo other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES "
                + "           ('" + other.getCustomerID() + "' ," + other.getCustomerTypeID() + ")";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCustomerDemo(CustomerCustomerDemo other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES "
                + "           (?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCustomerID());
            prestate.setString(2, other.getCustomerTypeID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateCustomerDemo(CustomerCustomerDemo other) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerCustomerDemo]\n"
                + "   SET \n"
                + "      [CustomerTypeID] = ?\n"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCustomerTypeID());
            prestate.setString(2, other.getCustomerID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
public int deleteCustomerDemo(String cusid){
        int n = 0;
        String sql = "DELETE FROM CustomerCustomerDemo WHERE CustomerID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cusid);  // Use setString to handle the CustomerID (since it's nvarchar)
            n = pre.executeUpdate();  // Execute the delete statement
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
     public Vector<CustomerCustomerDemo> getCustomers (String sql){
        Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        try {
            //note in case login must be used PreparedStatement;
            Statement state =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE
                    ,ResultSet.CONCUR_UPDATABLE);
            ResultSet re = state.executeQuery(sql);
            while(re.next()){
                String CustomerID = re.getString("CustomerID");
                String CustomerTypeID = re.getString("CustomerTypeID");

                CustomerCustomerDemo cus = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                
                
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOCustomerCustomerDemo dcd = new DAOCustomerCustomerDemo();
//        int x = dcd.addCustomerDemo(new CustomerCustomerDemo("ALFKI", "abc"));
//        if (x > 0) {
//            System.out.println("Add success");
//        }
        int x = dcd.updateCustomerDemo(new CustomerCustomerDemo("ANATR", "abc"));
        if (x > 0) {
            System.out.println("update success");
        }
    }
}
