/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Territories {

    private String TerritoryID, TerritoryDescription;
    private int RegionID;

    public Territories(String TerritoryID, String TerritoryDescription, int RegionID) {
        this.TerritoryID = TerritoryID;
        this.TerritoryDescription = TerritoryDescription;
        this.RegionID = RegionID;
    }
    
    public Territories() {
    }

    public String getTerritoryID() {
        return TerritoryID;
    }

    public String getTerritoryDescription() {
        return TerritoryDescription;
    }

    public int getRegionID() {
        return RegionID;
    }
    
    @Override
    public String toString() {
        return "Territories{" + "TerritoryID=" + TerritoryID + ", TerritoryDescription=" + TerritoryDescription + ", RegionID=" + RegionID + '}';
    }

}
