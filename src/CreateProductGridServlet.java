import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.*;

/**
 * Created by Alfonso on 5/29/2017.
 */
@WebServlet(name = "CreateProductGridServlet")
public class CreateProductGridServlet extends HttpServlet {

    public static StringBuilder create_general_product_grid(ResultSet rs) throws SQLException{
        StringBuilder data = new StringBuilder("");
        String productDetails = "ProductDetails";

        int i = 1;
        data.append("<div class='row'>\n");

        while (rs.next()) {

            String men_id = rs.getString("men_id");
            String cost = rs.getString("cost");
            String name =  rs.getString("name");
            String url = rs.getString("url");

            data.append("<div class='col-3'>\n");
            data.append("<a id="+men_id+" href="+productDetails+"?shoeId="+men_id+">\n");
            data.append("<img class='grid_image' src="+url+" alt='Shoes'>\n");
            data.append("</a>\n");

            data.append("<div class='row'>\n");
            data.append("<div class='col-12'>"+name+"</div>\n");
            data.append("</div>\n");
            data.append("<div class='row'>\n");
            data.append("<div class='col-12'>$"+cost+" </div>\n");
            data.append("</div>\n");

            data.append("</div>\n");  // col-3

            if( (i % 4) == 0) {
                data.append("</div>\n");
                data.append("<div class='row'>\n");
            }
            ++i;
        }
        return data;
    }

    public static StringBuilder create_small_product_grid(ResultSet rs) throws SQLException{
        StringBuilder data = new StringBuilder("");
        String productDetails = "ProductDetails";

        int i = 1;
        data.append("<div class='row'>\n");

        while (rs.next()) {

            String men_id = rs.getString("men_id");
            String cost = rs.getString("cost");
            String name =  rs.getString("name");
            String url = rs.getString("url");

            data.append("<div class='col-4'>\n");
            data.append("<a id="+men_id+" href="+productDetails+"?shoeId="+men_id+">\n");
            data.append("<img class='grid_image' src="+url+" alt='Shoes'>\n");
            data.append("</a>\n");

            data.append("<div class='row'>\n");
            data.append("<div class='col-12'>"+name+"</div>\n");
            data.append("</div>\n");
            data.append("<div class='row'>\n");
            data.append("<div class='col-12'>$"+cost+" </div>\n");
            data.append("</div>\n");

            data.append("</div>\n");  // col-3

            if( (i % 3) == 0) {
                data.append("</div>\n");
                data.append("<div class='row'>\n");
            }
            ++i;
        }

        return data;
    }

    private StringBuilder get_general_data() throws ClassNotFoundException, SQLException{
        Connection connection = Connect.getInstance();

        if(connection == null)
            return new StringBuilder("<h1> Could Not Connect To DB </h1>");

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery ("SELECT * FROM men,images WHERE men_id=images_id");
        return create_general_product_grid(rs);
    }

    private StringBuilder get_dress_shoes_data() throws ClassNotFoundException, SQLException{
        Connection connection = Connect.getInstance();

        if(connection == null)
            return new StringBuilder("<h1> Could Not Connect To DB </h1>");

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery ("SELECT * FROM men,images WHERE men_id=images_id AND men.dress=TRUE");
        return create_small_product_grid(rs);
    }

    private StringBuilder get_casual_shoes_data() throws ClassNotFoundException, SQLException{
        Connection connection = Connect.getInstance();

        if(connection == null)
            return new StringBuilder("<h1> Could Not Connect To DB </h1>");

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery ("SELECT * FROM men,images WHERE men_id=images_id AND men.dress=FALSE");
        return create_small_product_grid(rs);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Post does should not get called
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String typeOfShoes = (String) request.getAttribute("typeOfShoes");

        PrintWriter out = response.getWriter();
        StringBuilder data = new StringBuilder("");

        try{
            if(typeOfShoes.equals("general"))
                data.append(get_general_data());
            else if(typeOfShoes.equals("dress"))
                data.append(get_dress_shoes_data());
            else if(typeOfShoes.equals("casual"))
                data.append(get_casual_shoes_data());
            else {
                out.println("<h1>No Data Found<h1>");
                return;
            }
        } catch (SQLException|ClassNotFoundException e){
            out.println(e.getMessage());
        }

        out.println(data);
    }
}
