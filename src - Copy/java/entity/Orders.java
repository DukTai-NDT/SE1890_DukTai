/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Orders {

    private int OrderID;
    private String CustomerID;
    private int EmployeeID;
    private String OrderDate, RequiredDate, ShippedDate;
    private int ShipVia;
    private double Freight;
    private String ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry;

    public Orders(int OrderID, String CustomerID, int EmployeeID, String OrderDate, String RequiredDate, String ShippedDate, int ShipVia, double Freight, String ShipName, String ShipAddress, String ShipCity, String ShipRegion, String ShipPostalCode, String ShipCountry) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.ShipVia = ShipVia;
        this.Freight = Freight;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCity = ShipCity;
        this.ShipRegion = ShipRegion;
        this.ShipPostalCode = ShipPostalCode;
        this.ShipCountry = ShipCountry;
    }

    public Orders(String CustomerID, int EmployeeID, String OrderDate, String RequiredDate, String ShippedDate, int ShipVia, double Freight, String ShipName, String ShipAddress, String ShipCity, String ShipRegion, String ShipPostalCode, String ShipCountry) {
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.ShipVia = ShipVia;
        this.Freight = Freight;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCity = ShipCity;
        this.ShipRegion = ShipRegion;
        this.ShipPostalCode = ShipPostalCode;
        this.ShipCountry = ShipCountry;
    }
    
    public Orders() {
    }

    public int getOrderID() {
        return OrderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getRequiredDate() {
        return RequiredDate;
    }

    public String getShippedDate() {
        return ShippedDate;
    }

    public int getShipVia() {
        return ShipVia;
    }

    public double getFreight() {
        return Freight;
    }

    public String getShipName() {
        return ShipName;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public String getShipCity() {
        return ShipCity;
    }

    public String getShipRegion() {
        return ShipRegion;
    }

    public String getShipPostalCode() {
        return ShipPostalCode;
    }

    public String getShipCountry() {
        return ShipCountry;
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + OrderID + ", CustomerID=" + CustomerID + ", EmployeeID=" + EmployeeID + ", OrderDate=" + OrderDate + ", RequiredDate=" + RequiredDate + ", ShippedDate=" + ShippedDate + ", ShipVia=" + ShipVia + ", Freight=" + Freight + ", ShipName=" + ShipName + ", ShipAddress=" + ShipAddress + ", ShipCity=" + ShipCity + ", ShipRegion=" + ShipRegion + ", ShipPostalCode=" + ShipPostalCode + ", ShipCountry=" + ShipCountry + '}';
    }

}
