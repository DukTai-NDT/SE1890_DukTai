/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class CustomerDemographics {
        private String CustomerTypeID;
	private String CustomerDesc;

    public CustomerDemographics(String CustomerTypeID, String CustomerDesc) {
        this.CustomerTypeID = CustomerTypeID;
        this.CustomerDesc = CustomerDesc;
    }

    public CustomerDemographics() {
    }

    public String getCustomerTypeID() {
        return CustomerTypeID;
    }

    public String getCustomerDesc() {
        return CustomerDesc;
    }

    @Override
    public String toString() {
        return "CustomerDemographics{" + "CustomerTypeID=" + CustomerTypeID + ", CustomerDesc=" + CustomerDesc + '}';
    }
        
        
}
