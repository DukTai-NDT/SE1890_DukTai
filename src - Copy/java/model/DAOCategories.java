/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
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
public class DAOCategories extends DBConnection{
  public int insertCategories(Categories other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n" +
"           ([CategoryName]\n" +
"           ,[Description]\n" +
"           ,[Picture])\n" +
"     VALUES\n" +
"           ('"+other.getCategoryName()+"' ,'"+other.getDescription()+"' ,'"+other.getPicture()+"')";
       
      try {
          Statement state = conn.createStatement();
          n = state.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
      }
        return n;
    }
  public int addCategories(Categories other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n" +
"           ([CategoryName]\n" +
"           ,[Description]\n" +
 
"     VALUES\n" +
"           (?,?)";
       
      try {
          PreparedStatement prestate = conn.prepareStatement(sql);
          prestate.setString(1, other.getCategoryName());
          prestate.setString(2, other.getDescription());
          
          n = prestate.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
      }
        return n;
    }
  
  public int updateCategorie( Categories other){
      int n = 0;
      String sql = "UPDATE [dbo].[Categories]\n" +
"   SET [CategoryName] =?\n" +
"      ,[Description] = ?\n" +
"      ,[Picture] = ?\n" +
" WHERE <CategoryID = ?>";
       try {
            // ---> field , index start tai 1 (kh phai tai 0)
            //dau ? dau tien dai dien productName
            PreparedStatement prestate = conn.prepareStatement(sql);
            //set value for ? 
            prestate.setString(1, other.getCategoryName());
            prestate.setString(2, other.getDescription());
            prestate.setString(3, other.getPicture());
            prestate.setInt(4, other.getCategoryID());
            
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return n;
  }
  public int deleteCategory(int cid){
        int n = 0;
        String sql = "delete from Categories where CategoryID=" + cid;
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Categories> getCategory (String sql){
        Vector<Categories> vector = new Vector<Categories>();
        try {
            //note in case login must be used PreparedStatement;
            Statement state =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE
                    ,ResultSet.CONCUR_UPDATABLE);
            ResultSet re = state.executeQuery(sql);
            while(re.next()){
                int CategoryID = re.getInt("CategoryID");
                String CategoryName = re.getString("CategoryName");
                String Description = re.getString("Description");
                String Picture = re.getString("Picture");
                Categories cat = new Categories(CategoryID, CategoryName, Description,Picture);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOCategories dc = new DAOCategories();
         
        int x2 = dc.addCategories(new Categories("Name2","Des2" ));
        if(x2 > 0 ){
       
            System.out.println("Success");
        }
    }
}
