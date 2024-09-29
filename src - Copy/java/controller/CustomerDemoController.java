/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CustomerCustomerDemo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomerCustomerDemo;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerDemoController", urlPatterns = {"/CustomerDemoURL"})
public class CustomerDemoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if(service == null ){
                service = "listAllCustomerDemo";
            }
            if(service.equals("insertcustomerdemo")){
                String CustomerID = request.getParameter("CustomerID");
                String CustomerTypeID = request.getParameter("CustomerTypeID");
                
                CustomerCustomerDemo cusDemo = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                int n = dao.addCustomerDemo(cusDemo);
                response.sendRedirect("CustomerDemoURL?service=listAllCustomerDemo");
            }
            if(service.equals("listAllCustomerDemo")){
                String sql = "SELECT * FROM [dbo].[CustomerCustomerDemo]";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerDemoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("""
                        <p><a href="HTML/insertcustomerdemo.html">Insert CustomerDemo</a></p>""");
            out.println("""
                        <form action="CustomerDemoURL" method="get">
                                <p>Search Name: <input type="text" name="cdname"></p>
                                <input type="submit" value="search" name="submit">
                                <input type="reset" value="clear">
                            </form>""");
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "Select * from CustomerCustomerDemo";
            } else {
                String cdname = request.getParameter("cdname");
                sql = "SELECT *  FROM CustomerCustomerDemo where CustomerID like '%" + cdname + "%'";
            }
            Vector<CustomerCustomerDemo> vector = dao.getCustomers(sql);
            out.println("""
                        <table>
                                <tr>
                                    <th>CustomerID</th>
                                    <th>CustomerTypeID</th>
                                  
                                   
                                </tr>""");
            
            for (CustomerCustomerDemo customerCustomerDemo : vector) {
                out.print("<tr>\n"
                        + "            <td>"+customerCustomerDemo.getCustomerID()+"</td>\n"
                        + "            <td>"+customerCustomerDemo.getCustomerTypeID()+"</td>\n"
                        + "        </tr>    ");
            }
            out.print(" </table>");
            out.println("<h1>Servlet CustomerDemoController at " + request.getContextPath() + "</h1>");
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
