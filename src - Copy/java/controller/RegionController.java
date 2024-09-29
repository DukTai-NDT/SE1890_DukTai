/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Region;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAORegion;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegionController", urlPatterns = {"/RegionURL"})
public class RegionController extends HttpServlet {

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
        DAORegion dao = new DAORegion();
        String sql = "Select * from Region";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if(service.equals("insertRegion")){
                String RegionID = request.getParameter("RegionID");
                String RegionDescription = request.getParameter("RegionDescription");
                
                int RegionId = Integer.parseInt(RegionID);
                int n = dao.addRegion(new Region(RegionId, RegionDescription));
                response.sendRedirect("RegionURL?service=listAllRegion");
            }
            if(service.equals("listAllRegion")){
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.print("<form action=\"RegionURL\" method=\"get\">\n"
                    + "        <p>Search name : <input type=\"text\" name = \"rname\" id =\"\">\n"
                    + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                    + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                    + "        <input type=\"hidden\" value=\"listAllRegion\" name=\"service\">\n"
                    + "        </p>\n"
                    + "    </form>");
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "Select * from Region";
            } else {
                String rname = request.getParameter("rname");
                sql = "Select * from Region where RegionID = " + rname;
            }
            Vector<Region> vector = dao.getRegion(sql);
            out.print("<p><a href=\"HTML/insertRegion.html\">insert region</a></p>");
            out.print("<table>\n"
                    + "        <tr>\n"
                    + "            <th>RegionID</th>\n"
                    + "            <th>RegionDescription</th>\n"
                    + "            <th>update</th>\n"
                    + "            <th>delete</th>\n"
                    + "\n"
                    + "\n"
                    + "        </tr>");
            for (Region region : vector) {
                out.print("<tr>\n"
                        + "            <td>"+region.getRegionID()+"</td>\n"
                        + "            <td>"+region.getRegionDescription()+"</td>\n"
                        + "            <td></td>\n"
                        + "            <td></td>\n"
                        + "\n"
                        + "        </tr>");
            }
            out.println("<h1>Servlet RegionController at " + request.getContextPath() + "</h1>");
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
