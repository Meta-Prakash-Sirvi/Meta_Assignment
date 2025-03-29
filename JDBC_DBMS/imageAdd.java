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
           
            
            statement.addBatch("INSERT INTO image(image_id , product_id, isiamge) VALUES (201, 11111 ,'y')");
            statement.addBatch("INSERT INTO image(image_id , product_id, isiamge) VALUES (202, 12324 ,'n')");
            statement.addBatch("INSERT INTO image(image_id , product_id, isiamge) VALUES (203, 12483 ,'y')");
           statement.addBatch("INSERT INTO image(image_id , product_id, isiamge) VALUES (204, 10325 ,'y')");
           statement.addBatch("INSERT INTO image(image_id , product_id, isiamge) VALUES (205, 12508 ,'n')");

            
            int []result = statement.executeBatch(); 

            
            System.out.println("batch insert successfull " +result.length);
           
                     

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}