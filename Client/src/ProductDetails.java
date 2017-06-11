import sun.misc.Request;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Vector;

/**
 * Created by Alfonso on 5/29/2017.
 */
@WebServlet(name = "ProductDetails")
public class ProductDetails extends HttpServlet {

    private static String AddToCart = "AddToCart";  // Servlet

    private void saveProductToHistory(HttpServletRequest request, String shoeId){
    // Create a session object if it is already not  created.
        HttpSession session = request.getSession(true);

        String lastFiveShoesViewedKey = "lastFiveShoesViewed";
        Vector<String> lastFiveShoesViewed = new Vector<>(6);

        // Check if this is new comer
        if (session.isNew() || session.getAttribute("lastFiveShoesViewed") == null){
            lastFiveShoesViewed.add(shoeId);
            session.setAttribute(lastFiveShoesViewedKey, lastFiveShoesViewed);
        } else {
            lastFiveShoesViewed = (Vector<String>) session.getAttribute("lastFiveShoesViewed");

            // Do not allow repeat products to be saved in the history
            if(lastFiveShoesViewed.contains(shoeId))
                return;

            lastFiveShoesViewed.add(shoeId);
            if(lastFiveShoesViewed.size() > 5){
                lastFiveShoesViewed.remove(0);  // remove the first and last element in the vector
            }
            session.setAttribute(lastFiveShoesViewedKey, lastFiveShoesViewed);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String shoeId = request.getParameter("shoeId");

        saveProductToHistory(request, shoeId);

        ResultSet rs;
        String url = "NULL";
        String description = "NULL";
        String name = "NULL";
        String cost = "NULL";
        try{
            Statement statement = Connect.getInstance().createStatement();
            rs = statement.executeQuery ("SELECT * FROM men,images WHERE men_id=images_id AND men_id="+shoeId);
            if(rs.next()) {
                url = rs.getString("url");
                description = rs.getString("description");
                name = rs.getString("name");
                cost = rs.getString("cost");
            }
        } catch (SQLException sql){
            out.println(sql.getMessage());
            return;
        }

        String itemInCart = request.getParameter("inCart");

        if( itemInCart!= null && !itemInCart.equals(""))
            request.setAttribute("itemInCart", "Item Has Been Added To The Cart!");

        request.setAttribute("shoeId", shoeId);
        request.setAttribute("name", name);
        request.setAttribute("url", url);
        request.setAttribute("description", description);
        request.setAttribute("cost", cost);

        RequestDispatcher rd = request.getRequestDispatcher("product_details.jsp");
        rd.forward(request, response);
    }
}
