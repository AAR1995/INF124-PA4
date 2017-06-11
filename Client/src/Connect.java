import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connect {

    private static Connection connection;

    private static String hostname = "<YOUR HOST NAME>";
    private static String user = "<YOUR USER NAME>";
    private static String password = "<YOUR PASSWORD>";
    private static String dbName = "<YOUR DB NAME>";

    public static Connection getInstance(){
        if(connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://"+hostname+"/"+dbName,
                        user,
                        password);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return connection;
    }
}
