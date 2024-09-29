/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employee;
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
public class DAOEmployee extends DBConnection {

    public int insertEmployee(Employee other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]\n"
                + "           ([LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Photo]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES\n"
                + "           ('" + other.getLastName() + "' ,'" + other.getFirstName() + "' ,'" + other.getTitle() + "','" + other.getTitle() + "' ,'" + other.getBirthDate() + "' ,"
                + "'" + other.getHireDate() + "' ,'" + other.getAddress() + "' ,'" + other.getCity() + "' ,'" + other.getRegion() + "' ,'" + other.getPostalCode() + "' ,"
                + "'" + other.getCountry() + "','" + other.getHomePhone() + "' ,'" + other.getExtension() + "' ,'" + other.getPhoto() + "' ,"
                + "'" + other.getNotes() + "' ," + other.getReportsTo() + " ,'" + other.getPhotoPath() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addEmployee(Employee other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]\n"
                + "           ([LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);

            prestate.setString(1, other.getLastName());
            prestate.setString(2, other.getFirstName());
            prestate.setString(3, other.getTitle());
            prestate.setString(4, other.getTitleOfCourtesy());
            prestate.setString(5, other.getBirthDate());
            prestate.setString(6, other.getHireDate());
            prestate.setString(7, other.getAddress());
            prestate.setString(8, other.getCity());
            prestate.setString(9, other.getRegion());
            prestate.setString(10, other.getPostalCode());
            prestate.setString(11, other.getCountry());
            prestate.setString(12, other.getHomePhone());
            prestate.setString(13, other.getExtension());

            prestate.setString(14, other.getNotes());
            prestate.setInt(15, other.getReportsTo());
            prestate.setString(16, other.getPhotoPath());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteEmployee(int employeeID) {
        int n = 0;
        String sql = "Delete from Employees where EmployeeID =" + employeeID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateEmployee(Employee other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [LastName] =?\n"
                + "      ,[FirstName] = ?\n"
                + "      ,[Title] = ?\n"
                + "      ,[TitleOfCourtesy] = ?\n"
                + "      ,[BirthDate] = ?\n"
                + "      ,[HireDate] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[HomePhone] = ?\n"
                + "      ,[Extension] = ?\n"
                + "      ,[Photo] = ?\n"
                + "      ,[Notes] = ?\n"
                + "      ,[ReportsTo] = ?\n"
                + "      ,[PhotoPath] = ?\n"
                + " WHERE [EmployeeID] = ?";

        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getLastName());
            prestate.setString(2, other.getFirstName());
            prestate.setString(3, other.getTitle());
            prestate.setString(4, other.getTitleOfCourtesy());
            prestate.setString(5, other.getBirthDate());
            prestate.setString(6, other.getHireDate());
            prestate.setString(7, other.getAddress());
            prestate.setString(8, other.getCity());
            prestate.setString(9, other.getRegion());
            prestate.setString(10, other.getPostalCode());
            prestate.setString(11, other.getCountry());
            prestate.setString(12, other.getHomePhone());
            prestate.setString(13, other.getExtension());
            prestate.setString(14, other.getPhoto());
            prestate.setString(15, other.getNotes());
            prestate.setInt(16, other.getReportsTo());
            prestate.setString(17, other.getPhotoPath());
            prestate.setInt(18, other.getEmployeeID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Employee> getEmployee(String sql) {
        Vector<Employee> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName"),
                        FirstName = rs.getString("FirstName"),
                        Title = rs.getString("Title"),
                        TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                String BirthDate = rs.getString("BirthDate"),
                        HireDate = rs.getString("HireDate");
                String Address = rs.getString("Address"),
                        City = rs.getString("City"),
                        Region = rs.getString("Region"),
                        PostalCode = rs.getString("PostalCode"),
                        Country = rs.getString("Country"),
                        HomePhone = rs.getString("HomePhone"),
                        Extension = rs.getString("Extension");
                String Photo = rs.getString("Photo");
                String Notes = rs.getString("Notes");
                int ReportsTo = rs.getInt("ReportsTo");
                String PhotoPath = rs.getString("PhotoPath");
                
                Employee newEmployee = new Employee(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath);
                vector.add(newEmployee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public static void main(String[] args) {
        DAOEmployee daoemp = new DAOEmployee();
        Vector<Employee> vector = daoemp.getEmployee("Select * from Employees");
        for (Employee employee : vector) {
            System.out.println(employee.toString());
        }
    }
}
