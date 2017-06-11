import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Alfonso on 5/30/2017.
 */
@WebServlet(name = "DeleteItemFromCart")
public class DeleteItemFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shoeId = request.getParameter("shoeId");

        HttpSession session = request.getSession(true);
        Vector<String> cart_items;

        if (session.isNew() || session.getAttribute("cart_items") == null)
            return; // Nothing to delete
        else
            cart_items = (Vector<String>) session.getAttribute("cart_items");

        if(cart_items.contains(shoeId))
            cart_items.remove(shoeId);

        // Save cart_items back into session
        session.setAttribute("cart_items", cart_items);

        RequestDispatcher rd = request.getRequestDispatcher("AddToCart");
        rd.forward(request, response);
    }
}
