/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Territories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOTerritorie;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TerritoryController", urlPatterns = {"/TerritoryURL"})
public class TerritoryController extends HttpServlet {

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
        DAOTerritorie dao = new DAOTerritorie();
        String sql = "Select * from Territories";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("insertTerritory")) {
                String TerritoryID = request.getParameter("TerritoryID"),
                       TerritoryDescription = request.getParameter("TerritoryDescription");
                String RegionID = request.getParameter("RegionID");
                
                int RegionId = Integer.parseInt(RegionID);
                int n = dao.addTerritorie(new Territories(TerritoryID, TerritoryDescription, RegionId));
                response.sendRedirect("TerritoryURL?service=listAllTerritory");
            }
            if (service.equals("listAllTerritory")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TerritoryController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"TerritoryURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"tname\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        <input type=\"hidden\" value=\"listAllTerritory\" name=\"service\">\n"
                        + "        </p>\n"
                        + "    </form>");
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String tname = request.getParameter("tname");
                    sql = "Select * from Territories where TerritoryID like '%" + tname + "%'";
                } else {
                    sql = "Select * from Territories";
                }
                Vector<Territories> vector = dao.getTerritories(sql);
                out.print("<p><a href=\"HTML/insertTerritory.html\">insert Territory</a></p>");
                out.print("<table>\n"
                        + "        <tr>\n"
                        + "            <th>TerritoryID</th>\n"
                        + "            <th>TerritoryDescription</th>\n"
                        + "            <th>RegionID</th>\n"
                        + "            <th>update</th>\n"
                        + "            <th>delete</th>\n"
                        + "\n"
                        + "\n"
                        + "        </tr>");
                for (Territories territories : vector) {
                    out.print("<tr>\n"
                            + "            <td>" + territories.getTerritoryID() + "</td>\n"
                            + "            <td>" + territories.getTerritoryDescription() + "</td>\n"
                            + "            <td>" + territories.getRegionID() + "</td>\n"
                            + "            <td></td>\n"
                            + "            <td></td>\n"
                            + "\n"
                            + "        </tr>  ");
                }
                out.print("</table>");
                out.println("<h1>Servlet TerritoryController at " + request.getContextPath() + "</h1>");
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
