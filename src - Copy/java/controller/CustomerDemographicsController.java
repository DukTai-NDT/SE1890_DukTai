/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CustomerDemographics;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomerDemographics;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerDemographicsController", urlPatterns = {"/CustomerDemographicsURL"})
public class CustomerDemographicsController extends HttpServlet {

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
        DAOCustomerDemographics dao = new DAOCustomerDemographics();
        String sql = "Select * from CustomerDemographic";
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllCustomerDemoGraphics";
            }
            if(service.equals("insertCustomerDemographics")){
                String CustomerTypeID = request.getParameter("CustomerTypeID");
                String CustomerDesc = request.getParameter("CustomerDesc");
                
                if(CustomerTypeID == null){
                    out.println("CustomerTypeID not null");
            }
                CustomerDemographics cusDemoGraphic = new CustomerDemographics(CustomerTypeID, CustomerDesc);
                int n = dao.insertCusDemoGrap(cusDemoGraphic);
                response.sendRedirect("CustomerDemographicsURL?service=listAllCustomerDemoGraphics");
            }    
            
            else if (service.equals("listAllCustomerDemoGraphics")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet CustomerDemographicsController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"CustomerDemographicsURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"cdgname\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        </p>\n"
                        + "    </form>");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "Select * CustomerDemographic";
                } else {
                    String cdgname = request.getParameter("cdgname");
                    sql = "SELECT *  FROM CustomerDemographics where CustomerTypeID like '%" + cdgname + "%'";
                }
                Vector<CustomerDemographics> vector = dao.getCustomers(sql);
                out.println("<table>\n"
                        + "        <tr>\n"
                        + "            <th>CustomerTypeID</th>\n"
                        + "            <th>CustomerDesc</th>\n"
                        + "            <th>update</th>\n"
                        + "            <th>delete</th>\n"
                        + "\n"
                        + "\n"
                        + "        </tr>");

                for (CustomerDemographics customerDemographics : vector) {
                    out.print("<tr>\n"
                            + "            <td>" + customerDemographics.getCustomerTypeID() + "</td>\n"
                            + "            <td>" + customerDemographics.getCustomerDesc() + "</td>\n"
                            + "\n"
                            + "        </tr>");
                }
                out.print("</table>");
                out.print("<p><a href=\"HTML/insertCustomerDemographics.html\">insert CustomerDemoGraphics</a></p>");
                out.println("<h1>Servlet CustomerDemographicsController at " + request.getContextPath() + "</h1>");
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
