/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerURL"})
public class CustomerController extends HttpServlet {

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
        DAOCustomers dao = new DAOCustomers();
        String sql = "Select * from Customers";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if(service == null){
                service = "listAllCustomer";
            }
            if(service.equals("insertCustomer")){
                String CustomerID = request.getParameter("CustomerID");
                String CompanyName = request.getParameter("CompanyName");
                String ContactName = request.getParameter("ContactName");
                String ContactTitle = request.getParameter("ContactTitle");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String Phone = request.getParameter("Phone");
                String Fax = request.getParameter("Fax");
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                int n = dao.addCustomer(cus);
                response.sendRedirect("CustomerURL?service=listAllCustomer");
            }
            
            else if(service.equals("listAllCustomer")){
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(" <form action=\"CustomerURL\" method=\"get\">\n"
                    + "        <p>Search name : <input type=\"text\" name = \"cusname\" id =\"\">\n"
                    + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                    + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                    + "        </p>\n"
                    + "    </form>");
            out.print(" <p><a href=\"HTML/insertCustomer.html\">insert customer</a></p>");
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "Select * from Customers";
            } else {
                String cusname = request.getParameter("cusname");
                sql = "SELECT * FROM Customers where CustomerID like '%" + cusname + "%' ";
            }
            Vector<Customer> vector = dao.getCustomer(sql);
            out.println("<table>\n"
                    + "        <tr>\n"
                    + "            <th>CustomerID</th>\n"
                    + "            <th>CompanyName</th>\n"
                    + "            <th>ContactName</th>\n"
                    + "            <th>ContactTitle</th>\n"
                    + "            <th>Address</th>\n"
                    + "            <th>City</th>\n"
                    + "            <th>Region</th>\n"
                    + "            <th>PostalCode</th>\n"
                    + "            <th>Country</th>\n"
                    + "            <th>Phone</th>\n"
                    + "            <th>Fax</th>\n"
                    + "            <th>update</th>\n"
                    + "            <th>delete</th>\n"
                    + "        </tr>");
            for (Customer customer : vector) {
                out.println("<tr>\n"
                        + "            <td>"+customer.getCustomerID()+"</td>\n"
                        + "            <td>"+customer.getCompanyName()+"</td>\n"
                        + "            <td>"+customer.getContactName()+"</td>\n"
                        + "            <td>"+customer.getContactTitle()+"</td>\n"
                       + "            <td>"+customer.getAddress()+"</td>\n"
                        + "            <td>"+customer.getCity()+"</td>\n"
                        + "            <td>"+customer.getRegion()+"</td>\n"
                        + "            <td>"+customer.getPostalCode()+"</td>\n"
                        + "            <td>"+customer.getCountry()+"</td>\n"
                        + "            <td>"+customer.getPhone()+"</td>\n"
                        + "            <td>"+customer.getFax()+"</td>\n"
                        + "            <td></td>\n"
                        + "            <td></td>\n"
                        + "\n"
                        + "        </tr>");

            }
            out.println("</table>");
            out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
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
