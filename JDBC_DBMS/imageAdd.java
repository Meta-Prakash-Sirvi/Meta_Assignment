import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class imageAdd {
    private static final String url = "jdbc:mysql://localhost:3306/storefront";
    private static final String username = "root";
    private static final String password = "root";


    public static void main(String[] args) throws Exception{
       
        try {
            Connection connection = DriverManager.getConnection(url,username, password);
            Statement statement = connection.createStatement();
            String query ="INSERT INTO image(image_id , product_id , isimage) values (? , 12483 , 'y'),()";

            int DeleteProduct = statement.executeUpdate(query);
                     

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}