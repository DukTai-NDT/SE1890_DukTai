/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Shippers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOShipper;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShipperController", urlPatterns = {"/ShipperURL"})
public class ShipperController extends HttpServlet {

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
        DAOShipper dao = new DAOShipper();
        String sql = "Select * from Shippers";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if(service.equals("insertShipper")){
                String CompanyName = request.getParameter("CompanyName");
                String Phone = request.getParameter("Phone");
                
                int n = dao.addShipper(new Shippers(CompanyName, Phone));
                response.sendRedirect("ShipperURL?service=listAllShipper");
            }
            if(service.equals("listAllShipper")){
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShipperController</title>");
            out.println("</head>");
            out.println("<body>");
            out.print("<form action=\"ShipperURL\" method=\"get\">\n"
                    + "        <p>Search name : <input type=\"text\" name = \"sname\" id =\"\">\n"
                    + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                    + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                    + "        <input type=\"hidden\" value=\"listAllShipper\" name=\"service\">\n"
                    + "        </p>\n"
                    + "    </form>");
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "Select * from Shippers";
                
            } else {
                String sname = request.getParameter("sname");
                sql = "Select * from Shippers where CompanyName like '%"+sname+"%' ";
            }
            Vector<Shippers> vector = dao.getShippers(sql);
            out.print("<p><a href=\"HTML/insertShipper.html\">insert shipper</a></p>");
            out.print("<table>\n"
                    + "        <tr>\n"
                    + "            <th>ShipperID</th>\n"
                    + "            <th>CompanyName</th>\n"
                    + "            <th>Phone</th>\n"
                    + "            <th>update</th>\n"
                    + "            <th>delete</th>\n"
                    + "\n"
                    + "\n"
                    + "        </tr>");
            for (Shippers shippers : vector) {
                out.print("<tr>\n"
                        + "            <td>"+shippers.getShipperID()+"</td>\n"
                        + "            <td>"+shippers.getCompanyName()+"</td>\n"
                        + "            <td>"+shippers.getPhone()+"</td>\n"
                        + "            <td></td>\n"
                        + "            <td></td>\n"
                        + "\n"
                        + "        </tr> ");
            }
            out.print("</table>");
            out.println("<h1>Servlet ShipperController at " + request.getContextPath() + "</h1>");
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
