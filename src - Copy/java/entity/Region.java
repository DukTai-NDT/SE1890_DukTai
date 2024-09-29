package entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Region {

    private int RegionID;
    private String RegionDescription;

    public Region(int RegionID, String RegionDescription) {
        this.RegionID = RegionID;
        this.RegionDescription = RegionDescription;
    }

    public Region() {
    }

    public int getRegionID() {
        return RegionID;
    }

    public String getRegionDescription() {
        return RegionDescription;
    }
    
    @Override
    public String toString() {
        return "Region{" + "RegionID=" + RegionID + ", RegionDescription=" + RegionDescription + '}';
    }

}
