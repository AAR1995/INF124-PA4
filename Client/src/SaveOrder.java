import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by Alfonso on 5/30/2017.
 */
@WebServlet(name = "SaveOrder")
public class SaveOrder extends HttpServlet {

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String first=  (request.getParameter("firstname").equals("")) ? "Guest" : request.getParameter("firstname");
        String last=  (request.getParameter("lastname").equals("")) ? "Guest" : request.getParameter("lastname");
        String phone=  request.getParameter("phonenumber");
        String email=  request.getParameter("email");
        String total_cost = request.getParameter("totalCost");
        String city=  request.getParameter("city");
        String state=  request.getParameter("state");
        String address=  request.getParameter("address");

        String insertTableSQL = "INSERT INTO orders_submitted"
                + "(shoe_id, quantity, total_cost, customer_name, city, state, address, date_time) VALUES"
                + "(?,?,?,?,?,?,?,?)";

        HttpSession session = request.getSession(true);
        Vector<String> cart_items = (Vector<String>) session.getAttribute("cart_items");

        if(cart_items.isEmpty()){
            response.sendRedirect("CreateGeneralProductPage");
            return;
        }

        int confirmation_id;
        int affectedRows;
        ResultSet generatedKeys;
        try{
            PreparedStatement preparedStatement = Connect.getInstance().prepareStatement(insertTableSQL,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 1);
            preparedStatement.setDouble(3, 000.00);
            preparedStatement.setString(4, first + " " + last);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, state);
            preparedStatement.setString(7, address);
            preparedStatement.setTimestamp(8, getCurrentTimeStamp());
            // execute insert SQL statement
            affectedRows = preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            if (generatedKeys.next()) {
                confirmation_id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Reset Items in Cart (Remove all items in cart)
        cart_items = new Vector<>();
        session.setAttribute("cart_items", cart_items);

        // Set Attributes in request for order_details.jsp to access them
        request.setAttribute("confirmation_id", confirmation_id);
        request.setAttribute("first_name", first);
        request.setAttribute("last_name", last);
        request.setAttribute("cost", total_cost);
        request.setAttribute("city", city);
        request.setAttribute("state", state);
        request.setAttribute("address", address);
        request.setAttribute("time_submitted", getCurrentTimeStamp());
        RequestDispatcher rd =  request.getRequestDispatcher("order_details.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
