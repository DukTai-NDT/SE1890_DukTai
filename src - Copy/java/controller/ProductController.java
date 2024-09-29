package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author Admin
 */
@WebServlet(urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

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
        //Display all product
        DAOProduct dao = new DAOProduct();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteProduct")) {
                dao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAllProducts");

            }
            if (service.equals("insertProduct")) {
                //getdata
                String ProductName = request.getParameter("ProductName");
                String SupplierID = request.getParameter("SupplierID");
                String CategoryID = request.getParameter("CategoryID");
                String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                String UnitPrice = request.getParameter("UnitPrice");
                String UnitsInStock = request.getParameter("UnitsInStock");
                String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                String ReorderLevel = request.getParameter("ReorderLevel");
                String Discontinued = request.getParameter("Discontinued");
                //check data
                if (ProductName.equals("")) {
                    out.print("Product name is not empty");
                }
                //convert

                int SupplierId = Integer.parseInt(SupplierID),
                        CategoryId = Integer.parseInt(CategoryID);
                double UnitPricE = Double.parseDouble(UnitPrice);
                int UnitsInStocK = Integer.parseInt(UnitsInStock),
                        UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder),
                        ReorderLeveL = Integer.parseInt(ReorderLevel);
                boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                Product pro = new Product(ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                int n = dao.addProduct(pro);
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            if (service.equals("listAllProducts")) {

                String sql = "select * from Products";

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductController</title>");
                out.println("</head>");
                out.print("<body>");
                out.print("<form action=\"ProductURL\" method=\"get\">\n"
                        + "        <p>Search name : <input type=\"text\" name = \"pname\" id =\"\">\n"
                        + "        <input type=\"submit\" value=\"Search\" name=\"submit\">\n"
                        + "        <input type=\"reset\" value=\"reset\" name=\"clean\">\n"
                        + "        </p>\n"
                        + "        <input type=\"hidden\" value=\"listAllProducts\" name=\"service\">\n"
                        + "  </form>");
                out.print("    <p><a href=\"HTML/insertProduct.html\">insert product</a></p>");

                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "select * from Products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select *\n"
                            + "from Products\n"
                            + "where ProductName like '%" + pname + "%'";
                }
                Vector<Product> vector = dao.getProducts(sql);
                out.println("<table>\n"
                        + "        <tr>\n"
                        + "            <th>ProductName</th> "
                        + "            <th>SupplierID</th> "
                        + "            <th>CategoryID</th> "
                        + "            <th>QuantityPerUnit</th> "
                        + "            <th>UnitPrice</th> "
                        + "            <th>UnitsInStock</th> "
                        + "            <th>UnitsOnOrder</th> "
                        + "            <th>ReorderLevel</th> "
                        + "            <th>Discontinued</th> "
                        + "            <th>update</th> "
                        + "            <th>delete</th> "
                        + " "
                        + "\n"
                        + "        </tr>");
                for (Product product : vector) {
                    out.print("<tr>\n"
                            + "            <td>" + product.getProductName() + "</td>\n"
                            + "            <td>" + product.getSupplierID() + "</td>\n"
                            + "            <td>" + product.getCategoryID() + "</td>\n"
                            + "            <td>" + product.getQuantityPerUnit() + "</td>\n"
                            + "            <td>" + product.getUnitPrice() + "</td>\n"
                            + "            <td>" + product.getUnitsInStock() + "</td>\n"
                            + "            <td>" + product.getUnitsOnOrder() + "</td>\n"
                            + "            <td>" + product.getReorderLevel() + "</td>\n"
                            + "            <td>" + product.isDiscontinued() + "</td>\n"
                            + "            <td> </td>\n"
                            + "            <td> <p><a href=\"ProductURL?service=deleteProduct&pid=" + product.getProductID() + "\">Delete </a> </td>\n"
                            + "\n"
                            + "        </tr>     ");
                }
                out.print("</table>");
                out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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
