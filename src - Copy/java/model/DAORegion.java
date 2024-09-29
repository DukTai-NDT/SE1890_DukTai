/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Region;
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
public class DAORegion extends DBConnection {

    public int insertRegion(Region other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Region]\n"
                + "           ([RegionID]\n"
                + "           ,[RegionDescription])\n"
                + "     VALUES\n"
                + "           (" + other.getRegionID() + " ,'" + other.getRegionDescription() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public int addRegion(Region other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Region]\n"
                + "           ([RegionID]\n"
                + "           ,[RegionDescription])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setInt(1, other.getRegionID());
            prestate.setString(2, other.getRegionDescription());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteRegion(int regionID) {
        int n = 0;
        String sql = "Delete from Region where RegionID =" + regionID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateRegion(Region other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Region]\n"
                + "   SET [RegionDescription] =?\n"
                + " WHERE RegionID = ?";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getRegionDescription());
            prestate.setInt(2, other.getRegionID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Region> getRegion(String sql) {
        Vector<Region> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int RegionID = rs.getInt("RegionID");
                String RegionDescription = rs.getString("RegionDescription");
                Region newRegion = new Region(RegionID, RegionDescription);
                vector.add(newRegion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public static void main(String[] args) {
        DAORegion dr = new DAORegion();
        Vector<Region> vector = dr.getRegion("Select * from Region");
        for (Region region : vector) {
            System.out.println(region.toString());
        }
    }
}
