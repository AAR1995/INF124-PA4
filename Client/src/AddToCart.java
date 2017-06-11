import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Alfonso on 5/29/2017.
 */
@WebServlet(name = "AddToCart")
public class AddToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shoeId = request.getParameter("shoeId");
        HttpSession session = request.getSession(true);

        Vector<String> cart_items;

        // Check if this is new session or if cart is empty
        if (session.isNew() || session.getAttribute("cart_items") == null)
            cart_items = new Vector<>();
        else
            cart_items = (Vector<String>) session.getAttribute("cart_items");

        cart_items.add(shoeId);

        session.setAttribute("cart_items", cart_items);

        response.sendRedirect("ProductDetails?inCart=true&shoeId="+shoeId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);

        Vector<String> cart_items = (Vector<String>) session.getAttribute("cart_items");

        // Check if this is new comer on your web page.
        if (session.isNew() || cart_items == null || cart_items.isEmpty()){
            RequestDispatcher rd = request.getRequestDispatcher("nav_bar.html");
            rd.include(request, response);
            out.println("<h1>Your Cart Is Empty</h1>");
            return;
        }

        Double totalCost = 0.00;

        Vector<Map<String, String>> cartList = new Vector<Map<String, String>>();

        for(int i=0; i < cart_items.size();++i){
            Map<String, String> item = new HashMap<String, String>();
            String shoeId = cart_items.get(i);
            item.put("shoeId", shoeId);
            try{
                Statement statement = Connect.getInstance().createStatement();
                ResultSet rs = statement.executeQuery ("SELECT * FROM men,images WHERE men_id=images_id AND men_id="+shoeId);
                if(rs.next()) {
                    item.put("id", rs.getString("men_id"));
                    item.put("url", rs.getString("url"));
                    item.put("name", rs.getString("name"));
                    item.put("cost", rs.getString("cost"));
                    totalCost += Double.parseDouble(rs.getString("cost"));
                }
            } catch (SQLException sql){
                out.println(sql.getMessage());
                return;
            }
            cartList.add(item);
        }
        DecimalFormat dollar_format = new DecimalFormat("0.00");
        totalCost = Double.parseDouble(dollar_format.format(totalCost));


        request.setAttribute("cartList", cartList); // Now it's available by ${cartList}
        request.setAttribute("totalCost", totalCost); // Now it's available by ${cartList}

        RequestDispatcher rd = request.getRequestDispatcher("check_out.jsp");
        rd.forward(request, response);
    }
}
