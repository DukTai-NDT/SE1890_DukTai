/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Shippers {

    private int ShipperID;
    private String CompanyName, Phone;

    public Shippers(int ShipperID, String CompanyName, String Phone) {
        this.ShipperID = ShipperID;
        this.CompanyName = CompanyName;
        this.Phone = Phone;
    }

    public Shippers() {
    }

    public Shippers(String CompanyName, String Phone) {
        this.CompanyName = CompanyName;
        this.Phone = Phone;
    }

    public int getShipperID() {
        return ShipperID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getPhone() {
        return Phone;
    }
    
    @Override
    public String toString() {
        return "Shippers{" + "ShipperID=" + ShipperID + ", CompanyName=" + CompanyName + ", Phone=" + Phone + '}';
    }

}
