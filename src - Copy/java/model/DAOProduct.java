/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
public class DAOProduct extends DBConnection {

    public int insertProduct(Product other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           ('" + other.getProductName() + "' ," + other.getSupplierID() + "," + other.getCategoryID() + ""
                + ",'" + other.getQuantityPerUnit() + "' ," + other.getUnitPrice() + " ," + other.getUnitsInStock() + " "
                + "," + other.getUnitsOnOrder() + "  ," + other.getReorderLevel() + " ," + (other.isDiscontinued() == true ? 1 : 0) + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProduct(Product other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           ( ?,?,?,?,?,?,?,?,?)";
        try {
            // ---> field , index start tai 1 (kh phai tai 0)
            //dau ? dau tien dai dien productName
            PreparedStatement prestate = conn.prepareStatement(sql);
            //set value for ? 
            prestate.setString(1, other.getProductName());
            prestate.setInt(2, other.getSupplierID());
            prestate.setInt(3, other.getCategoryID());
            prestate.setString(4, other.getQuantityPerUnit());
            prestate.setDouble(5, other.getUnitPrice());
            prestate.setInt(6, other.getUnitsInStock());
            prestate.setInt(7, other.getUnitsOnOrder());
            prestate.setInt(8, other.getReorderLevel());
            prestate.setInt(9, other.isDiscontinued() == true ? 1 : 0);
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return n;
    }

    public void changeDiscontinued(int pid, int newValue) {
        String sql = "  update Products set Discontinued = "+newValue+" where ProductID = " + pid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int deleteProduct(int pid) {
        int n = 0;
//        cach xu ly khi khong xoa duoc do khoa ngoai
//                case1: Xoa tai foreignt key truoc roi moi xoa o primarykey;
//                case2: select foreign key --> IsExist ---> disable primary key
        String sqlCheck = "Select * from [Order Details] where ProductID = " + pid;
        ResultSet rs = getData(sqlCheck);
        try {
            if (rs.next()) {//co khoa ngoai--> khong xoa, Doi Status (discontined)
                changeDiscontinued(pid, 0);
                return 0;
            }
            String sql = "delete from Products where ProductID =" + pid;

            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ? ,[SupplierID] = ? ,[CategoryID] = ? ,[QuantityPerUnit] = ?  ,[UnitPrice] = ? ,"
                + "[UnitsInStock] = ? ,[UnitsOnOrder] = ? ,[ReorderLevel] = ? ,[Discontinued] = ?\n"
                + " WHERE ProductID = ?";
        try {
            // ---> field , index start tai 1 (kh phai tai 0)
            //dau ? dau tien dai dien productName
            PreparedStatement prestate = conn.prepareStatement(sql);
            //set value for ? 
            prestate.setString(1, other.getProductName());
            prestate.setInt(2, other.getSupplierID());
            prestate.setInt(3, other.getCategoryID());
            prestate.setString(4, other.getQuantityPerUnit());
            prestate.setDouble(5, other.getUnitPrice());
            prestate.setInt(6, other.getUnitsInStock());
            prestate.setInt(7, other.getUnitsOnOrder());
            prestate.setInt(8, other.getReorderLevel());
            prestate.setInt(9, other.isDiscontinued() == true ? 1 : 0);
            prestate.setInt(10, other.getProductID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return n;
    }

    public Vector<Product> getProducts(String sql) {
        Vector<Product> vector = new Vector<Product>();
        try {
//            note: in case login must be used PreparedStatement;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt("SupplierID"),
                        CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock"),
                        UnitsOnOrder = rs.getInt("UnitsOnOrder"),
                        ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = (rs.getInt("Discontinued") == 1 ? true : false);

                Product pro = new Product(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                vector.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();

        Vector<Product> vector = dao.getProducts("select * from Products");
        for (Product product : vector) {
            System.out.println(product.toString());
        }

    }
}
