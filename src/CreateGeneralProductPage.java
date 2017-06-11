import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by Alfonso on 5/31/2017.
 */
@WebServlet(name = "CreateGeneralProductPage")
public class CreateGeneralProductPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = Connect.getInstance();

        PrintWriter out = response.getWriter();

        RequestDispatcher rd = request.getRequestDispatcher("nav_bar.html");
        rd.include(request, response);

        if(connection == null){
            out.println("<h1> Could Not Connect To DB </h1>");
            return;
        }

        request.setAttribute("typeOfShoes", "general");
        rd = request.getRequestDispatcher("CreateProductGridServlet");
        rd.include(request, response);

        response.getWriter().println("<div class=\"row\">\n" +
                "        <div class=\"col-12\">\n" +
                "            <h1>Most Recent Viewed Products</h1>\n" +
                "        </div>\n" +
                "    </div>");

        rd = request.getRequestDispatcher("History");
        rd.include(request, response);

        rd = request.getRequestDispatcher("footer.html");
        rd.include(request, response);
    }
}
