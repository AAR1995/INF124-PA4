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
import java.util.Vector;

/**
 * Created by Alfonso on 5/29/2017.
 */
@WebServlet(name = "History")
public class History extends HttpServlet {

    private StringBuilder createHistoryGrid(ResultSet rs)  throws SQLException {
        StringBuilder data = new StringBuilder("");
        String productDetails = "ProductDetails";

        while (rs.next()) {

            String men_id = rs.getString("men_id");
            String cost = rs.getString("cost");
            String name =  rs.getString("name");
            String url = rs.getString("url");

            data.append("<div class='col-2'>\n");
            data.append("<a id="+men_id+" href="+productDetails+"?shoeId="+men_id+">\n");
            data.append("<img class='recent_viewed_image' src="+url+" alt='Shoes'>\n");
            data.append("</a>\n");

            data.append("<div class='row'>\n");
            data.append("<div class='col-12'>"+name+"</div>\n");
            data.append("</div>\n");
            data.append("<div class='row'>\n");
            data.append("<div class='col-12'>$"+cost+" </div>\n");
            data.append("</div>\n");

            data.append("</div>\n");  // col-1
        }

        return data;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);

        Vector<String> lastFiveShoesViewed = (Vector<String>) session.getAttribute("lastFiveShoesViewed");

        ResultSet rs;
        StringBuilder data = new StringBuilder("");
        if(lastFiveShoesViewed == null || lastFiveShoesViewed.isEmpty()){
            response.setContentType("text/html");
            out.print("<h1>No History to Display.</h1>");
            return;
        }

        data.append("<div class='row'>\n");

        // For Extra Padding
        data.append("<div class='col-1'>\n");
        data.append("</div>\n");

        for(int i=0; i < lastFiveShoesViewed.size(); ++i){
            String shoeId = lastFiveShoesViewed.get(i);
            try{
                Statement statement = Connect.getInstance().createStatement();
                rs = statement.executeQuery ("SELECT * FROM men,images WHERE men_id=images_id AND men_id="+shoeId);
                data.append(createHistoryGrid(rs));
            } catch (SQLException sql){
                out.println(sql.getMessage());
            }
        }

        // For Extra Padding
        data.append("<div class='col-1'>\n");
        data.append("</div>\n");    // col-1

        data.append("</div>\n");  // row

        out.println(data);
    }
}
