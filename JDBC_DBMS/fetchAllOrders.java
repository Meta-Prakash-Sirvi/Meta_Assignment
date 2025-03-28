import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class fetchAllOrders {
    private static final String url = "jdbc:mysql://localhost:3306/storefront";
    private static final String username = "root";
    private static final String password = "root";


    public static void main(String[] args) throws Exception{
    

        try {
            Connection connection = DriverManager.getConnection(url,username, password);
            Statement statement = connection.createStatement();
            String query = "Select o.order_id , o.order_date , o.amount "+
            "FROM orders o "+
            "INNER JOIN shipping s ON o.order_id=s.order_id "+
            "WHERE o.user_id =103 AND s.status = 'shipped' "+
            "ORDER BY o.order_date ASC ";

            ResultSet resultset = statement.executeQuery(query);            

            while(resultset.next()) {
                System.out.println("Order ID : " + resultset.getInt("order_id") +" Date : "+ resultset.getDate("order_date")+" Total :"+resultset.getInt("amount"));
        
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}