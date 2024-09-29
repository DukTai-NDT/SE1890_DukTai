/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOEmployee;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeURL"})
public class EmployeeController extends HttpServlet {

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
        DAOEmployee dao = new DAOEmployee();
        String sql;
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if(service.equals("insertEmployee")){
                 
	  String LastName =request.getParameter("LastName"),
                  FirstName=request.getParameter("FirstName"),
                  Title=request.getParameter("Title"),
                  TitleOfCourtesy=request.getParameter("TitleOfCourtesy");
	  String BirthDate=request.getParameter("BirthDate"),
                  HireDate=request.getParameter("HireDate");
	  String Address=request.getParameter("Address"),
                  City=request.getParameter("City"),
                  Region=request.getParameter("Region"),
                  PostalCode=request.getParameter("PostalCode"),
                  Country=request.getParameter("Country"),
                  HomePhone=request.getParameter("HomePhone"),
                  Extension=request.getParameter("Extension");
	  String Notes=request.getParameter("Notes");
	  String ReportsTo=request.getParameter("ReportsTo");
	  String PhotoPath=request.getParameter("PhotoPath");
          
          int ReportsTO = Integer.parseInt(ReportsTo);
          Employee emp = new Employee(LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Notes, ReportsTO, PhotoPath);
          int n = dao.addEmployee(emp);
          response.sendRedirect("EmployeeURL?service=listAllEmployee");
            }
            if (service.equals("listAllEmployee")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EmployeeController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"EmployeeURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"ename\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        <input type=\"hidden\" value=\"listAllEmployee\" name=\"service\">\n"
                        + "        </p>\n"
                        + "    </form>");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT *  FROM  Employees  ";
                } else {
                    String ename = request.getParameter("ename");
                    sql = "SELECT *  FROM  Employees where EmployeeID like '%" + ename + "%'";
                }
                Vector<Employee> vector = dao.getEmployee(sql);
                out.print("<p><a href=\"HTML/insertEmployee.html\">insert employee</a></p>");
                out.print("<table>\n"
                        + "        <tr>\n"
                        + "            <th>EmployeeID</th>\n"
                        + "            <th>LastName</th>\n"
                        + "            <th>FirstName</th>\n"
                        + "            <th>Title</th>\n"
                        + "            <th>TitleOfCourtesy</th>\n"
                        + "            <th>BirthDate</th>\n"
                        + "            <th>HireDate</th>\n"
                        + "            <th>Address</th>\n"
                        + "            <th>City</th>\n"
                        + "            <th>Region</th>\n"
                        + "            <th>PostalCode</th>\n"
                        + "            <th>Country</th>\n"
                        + "            <th>HomePhone</th>\n"
                        + "            <th>Extension</th>\n"
                        + "            <th>Notes</th>\n"
                        + "            <th>ReportsTo</th>\n"
                        + "            <th>PhotoPath</th>\n"
                        + "            <th>update</th>\n"
                        + "            <th>delete</th>\n"
                        + "\n"
                        + "\n"
                        + "        </tr>");
                for (Employee employee : vector) {
                    out.print(" <tr>\n"
                            + "            <td>" + employee.getEmployeeID() + "</td>\n"
                            + "            <td>" + employee.getLastName() + "</td>\n"
                            + "            <td>" + employee.getFirstName() + "</td>\n"
                            + "            <td>" + employee.getTitle() + "</td>\n"
                            + "            <td>" + employee.getTitleOfCourtesy() + "</td>\n"
                            + "            <td>" + employee.getBirthDate() + "</td>\n"
                            + "            <td>" + employee.getHireDate() + "</td>\n"
                            + "            <td>" + employee.getAddress() + "</td>\n"
                            + "            <td>" + employee.getCity() + "</td>\n"
                            + "            <td>" + employee.getRegion() + "</td>\n"
                            + "            <td>" + employee.getPostalCode() + "</td>\n"
                            + "            <td>" + employee.getCountry() + "</td>\n"
                            + "            <td>" + employee.getHomePhone() + "</td>\n"
                            + "            <td>" + employee.getExtension() + "</td>\n"
                            + "            <td>" + employee.getNotes() + "</td>\n"
                            + "            <td>" + employee.getReportsTo() + "</td>\n"
                            + "            <td>" + employee.getPhoto() + "</td>\n"
                            + "            <td>" + "update" + "</td>\n"
                            + "            <td>" + "delete" + "</td>\n"
                            + "\n"
                            + "        </tr>");
                }
                out.print("</table>");
                out.println("<h1>Servlet EmployeeController at " + request.getContextPath() + "</h1>");
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
