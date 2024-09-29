/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOSupplier;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SupplierController", urlPatterns = {"/SupplierURL"})
public class SupplierController extends HttpServlet {

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
        DAOSupplier dao = new DAOSupplier();
        String sql = "Select * from Suppliers";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("insertSupplier")) {
                String CompanyName = request.getParameter("CompanyName"),
                        ContactName = request.getParameter("ContactName"),
                        ContactTitle = request.getParameter("ContactTitle"),
                        Address = request.getParameter("Address"),
                        City = request.getParameter("City"),
                        Region = request.getParameter("Region"),
                        PostalCode = request.getParameter("PostalCode"),
                        Country = request.getParameter("Country"),
                        Phone = request.getParameter("Phone"),
                        Fax = request.getParameter("Fax");
                String HomePage = request.getParameter("HomePage");
                
                int n = dao.addSupplier(new Suppliers(CompanyName,
                        ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage));
                response.sendRedirect("SupplierURL?service=listAllSupplier");
            }
            if (service.equals("listAllSupplier")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SupplierController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"SupplierURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"slname\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        <input type=\"hidden\" value=\"listAllSupplier\" name=\"service\">\n"
                        + "        </p>\n"
                        + "    </form>");
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String slname = request.getParameter("slname");
                    sql = "Select * from Suppliers where CompanyName like '%"+slname+"%' ";
                } else {
                    sql = "Select * from Suppliers";
                }
                Vector<Suppliers> vector = dao.getSuppliers(sql);
                out.print("<p><a href=\"HTML/insertSupplier.html\">insert supplier</a></p>");
                out.print(" <table>\n"
                        + "        <tr>\n"
                        + "            <th>SupplierID</th>\n"
                        + "            <th>CompanyName</th>\n"
                        + "            <th>ContactName</th>\n"
                        + "            <th>QuantityPerUnit</th>\n"
                        + "            <th>ContactTitle</th>\n"
                        + "            <th>Address</th>\n"
                        + "            <th>City</th>\n"
                        + "            <th>Region</th>\n"
                        + "            <th>PostalCode</th>\n"
                        + "            <th>Country</th>\n"
                        + "            <th>ReorderLevel</th>\n"
                        + "            <th>Phone</th>\n"
                        + "            <th>Fax</th>\n"
                        + "            <th>HomePage</th>\n"
                        + "            <th>update</th>\n"
                        + "            <th>delete</th>\n"
                        + "\n"
                        + "\n"
                        + "        </tr>");
                for (Suppliers suppliers : vector) {
                    out.print("<tr>\n"
                            + "            <td>" + suppliers.getSupplierID() + "</td>\n"
                            + "            <td>" + suppliers.getCompanyName() + "</td>\n"
                            + "            <td>" + suppliers.getContactName() + "</td>\n"
                            + "            <td>" + suppliers.getContactTitle() + "</td>\n"
                            + "            <td>" + suppliers.getAddress() + "</td>\n"
                            + "            <td>" + suppliers.getCity() + "</td>\n"
                            + "            <td>" + suppliers.getRegion() + "</td>\n"
                            + "            <td>" + suppliers.getPostalCode() + "</td>\n"
                            + "            <td>" + suppliers.getCountry() + "</td>\n"
                            + "            <td>" + suppliers.getPhone() + "</td>\n"
                            + "            <td>" + suppliers.getFax() + "</td>\n"
                            + "            <td>" + suppliers.getHomePage() + "</td>\n"
                            + "            <td></td>\n"
                            + "            <td></td>\n"
                            + "\n"
                            + "        </tr>");
                }
                out.print("</table>");
                out.println("<h1>Servlet SupplierController at " + request.getContextPath() + "</h1>");
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
