/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class CustomerCustomerDemo {
        private String CustomerID;
	private String CustomerTypeID;

    public CustomerCustomerDemo(String CustomerID, String CustomerTypeID) {
        this.CustomerID = CustomerID;
        this.CustomerTypeID = CustomerTypeID;
    }

    public CustomerCustomerDemo() {
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getCustomerTypeID() {
        return CustomerTypeID;
    }

    @Override
    public String toString() {
        return "CustomerCustomerDemo{" + "CustomerID=" + CustomerID + ", CustomerTypeID=" + CustomerTypeID + '}';
    }
        
        
}
