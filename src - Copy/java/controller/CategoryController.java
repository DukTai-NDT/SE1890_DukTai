/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;

/**
 *
 * @author quang
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryURL"})
public class CategoryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOCategories dao = new DAOCategories();
           
            String service = request.getParameter("service");
            if(service == null){
                service = "listAllCategory";
            }
            if(service.equals("insertCategory")){
                
	  String CategoryName =request.getParameter("CategoryName");
	  String Description = request.getParameter("Description");
	  String Picture = request.getParameter("Picture");
          
          int n = dao.addCategories(new Categories(CategoryName, Description, Picture));
          response.sendRedirect("CategoryURL?service=listAllCategory");
            }
            if(service.equals("listAllCategory")){
             String sql = "select * from Categories";    
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("""
                        <p><a href="HTML/insertCategory.html">Insert Category</a></p>""");
            out.println("""
                        <form action="CategoryURL" method="get">
                                <p>Search Name: <input type="text" name="cname"></p>
                                <input type="submit" value="search" name="submit">
                                <input type="reset" value="clear">
                            </form>""");
            
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "select * from Categories";
            } else {
                String cname = request.getParameter("cname");
                sql = "select *\n"
                        + "from Categories\n"
                        + "where CategoryName like '%"+cname+"%'";
            }
            Vector<Categories> vector = dao.getCategory(sql);
            out.println("""
                        <table>
                                <tr>
                                    <th>CategoryID</th>
                                    <th>CategoryName</th>
                                    <th>Description</th>
                                    <th>Picture</th>
                                   
                                </tr>""");

            for (Categories categories : vector) {
                out.print("<tr>\n"
+ "            <td>" + categories.getCategoryID() + "</td>\n"
                        + "            <td>" + categories.getCategoryName() + "</td>\n"
                        + "            <td>" + categories.getDescription() + "</td>\n"
                        + "            <td>" + categories.getPicture() + "</td>\n"
                        + "            <td></td>\n"
                        + "            <td></td>\n"
                        + "        </tr>");
            }
            out.println("</table>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}