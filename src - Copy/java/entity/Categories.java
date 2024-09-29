/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Categories {
        private int CategoryID;
	private String CategoryName;
	private String Description;
	private String Picture;

    public Categories(int CategoryID, String CategoryName, String Description, String Picture) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Description = Description;
        this.Picture = Picture;
    }

    public Categories(String CategoryName, String Description, String Picture) {
        this.CategoryName = CategoryName;
        this.Description = Description;
        this.Picture = Picture;
    }

    public Categories(int CategoryID, String CategoryName, String Description) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Description = Description;
    }

    public Categories(String CategoryName, String Description) {
        this.CategoryName = CategoryName;
        this.Description = Description;
    }

    public Categories() {
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getDescription() {
        return Description;
    }

    public String getPicture() {
        return Picture;
    }

    @Override
    public String toString() {
        return "Categories{" + "CategoryID=" + CategoryID + ", CategoryName=" + CategoryName + ", Description=" + Description + ", Picture=" + Picture + '}';
    }
        
        
}
