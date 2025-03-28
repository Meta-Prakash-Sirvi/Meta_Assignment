import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class DeleteProduct {
    private static final String url = "jdbc:mysql://localhost:3306/storefront";
    private static final String username = "root";
    private static final String password = "root";


    public static void main(String[] args) throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username, password);
            Statement statement = connection.createStatement();
            String query = "Delete from product where product_id NOT IN(select product_id from orders where order_date >= NOW() - INTERVAL 1 year)";

            int DeleteProduct = statement.executeUpdate(query);
            System.out.println("delete :" + DeleteProduct);          

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}