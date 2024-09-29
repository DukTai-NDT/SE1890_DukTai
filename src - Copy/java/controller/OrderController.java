/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOOrders;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderURL"})
public class OrderController extends HttpServlet {

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
        DAOOrders dao = new DAOOrders();
        String sql = "Select * from [Orders]";

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("insertOrder")) {

                String CustomerID = request.getParameter("CustomerID");
                String EmployeeID = request.getParameter("EmployeeID");
                String OrderDate = request.getParameter("OrderDate"),
                        RequiredDate = request.getParameter("RequiredDate"),
                        ShippedDate = request.getParameter("ShippedDate");
                String ShipVia = request.getParameter("ShipVia");
                String Freight = request.getParameter("Freight");
                String ShipName = request.getParameter("ShipName"),
                        ShipAddress = request.getParameter("ShipAddress"),
                        ShipCity = request.getParameter("ShipCity"),
                        ShipRegion = request.getParameter("ShipRegion"),
                        ShipPostalCode = request.getParameter("ShipPostalCode"),
                        ShipCountry = request.getParameter("ShipCountry");
                
                int EmployeeId = Integer.parseInt(EmployeeID);
                int ShipViA = Integer.parseInt(ShipVia);
                double FreighT = Double.parseDouble(Freight);
                
                int n = dao.addOrder(new Orders(CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry));
                response.sendRedirect("OrderURL?service=listAllOrder");

            }
            if (service.equals("listAllOrder")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OrderController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"OrderURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"oname\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        <input type=\"hidden\" value=\"listAllOrder\" name=\"service\">\n"
                        + "        </p>\n"
                        + "    </form>");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "Select * from [Orders]";
                } else {
                    String oname = request.getParameter("oname");
                    sql = "Select * from [Orders] where CustomerID like '%" + oname + "%'";
                }
                Vector<Orders> vector = dao.getOrder(sql);
                out.print("<p><a href=\"HTML/insertOrder.html\">insert Order</a></p>");
                out.print("<table>\n"
                        + "        <tr>\n"
                        + "            <th>OrderID</th>\n"
                        + "            <th>CustomerID</th>\n"
                        + "            <th>EmployeeID</th>\n"
                        + "            <th>OrderDate</th>\n"
                        + "            <th>RequiredDate</th>\n"
                        + "            <th>ShippedDate</th>\n"
                        + "            <th>ShipVia</th>\n"
                        + "            <th>Freight</th>\n"
                        + "            <th>ShipName</th>\n"
                        + "            <th>ShipAddress</th>\n"
                        + "            <th>ShipCity</th>\n"
                        + "            <th>ShipRegion</th>\n"
                        + "            <th>ShipPostalCode</th>\n"
                        + "            <th>ShipCountry</th>\n"
                        + "            <th>update</th>\n"
                        + "            <th>delete</th>\n"
                        + "\n"
                        + "\n"
                        + "        </tr>");
                for (Orders orders : vector) {
                    out.print("<tr>\n"
                            + "            <td>" + orders.getOrderID() + "</td>\n"
                            + "            <td>" + orders.getCustomerID() + "</td>\n"
                            + "            <td>" + orders.getEmployeeID() + "</td>\n"
                            + "            <td>" + orders.getOrderDate() + "</td>\n"
                            + "            <td>" + orders.getRequiredDate() + "</td>\n"
                            + "            <td>" + orders.getShippedDate() + "</td>\n"
                            + "            <td>" + orders.getShipVia() + "</td>\n"
                            + "            <td>" + orders.getFreight() + "</td>\n"
                            + "            <td>" + orders.getShipName() + "</td>\n"
                            + "            <td>" + orders.getShipAddress() + "</td>\n"
                            + "            <td>" + orders.getShipCity() + "</td>\n"
                            + "            <td>" + orders.getShipRegion() + "</td>\n"
                            + "            <td>" + orders.getShipPostalCode() + "</td>\n"
                            + "            <td>" + orders.getShipCountry() + "</td>\n"
                            + "            <td>" + "update" + "</td>\n"
                            + "            <td>" + "delete" + "</td>\n"
                            + "\n"
                            + "        </tr>");
                }
                out.print("</table>");
                out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
