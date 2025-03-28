import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class DisplayTopCategories {
    private static final String url = "jdbc:mysql://localhost:3306/storefront";
    private static final String username = "root";
    private static final String password = "root";


    public static void main(String[] args) throws Exception{
       
        try {
            Connection connection = DriverManager.getConnection(url,username, password);
            Statement statement = connection.createStatement();
            String query = "Select c1.catgorie_name AS top_parent_categories , count(c2.categorie_id) AS childs " +
            "from  catgories c1 "+
            "LEFT Join catgories c2 ON c1.categorie_id = c2.praent_id " +
            "WHERE c1.praent_id IS NULL " +
            "group by c1.categorie_id " +
            "order by c1.catgorie_name ASC ";

            ResultSet resultset = statement.executeQuery(query);            

            while(resultset.next()) {
                System.out.println("TiTtle : " + resultset.getString("top_parent_categories") +" count "+ resultset.getInt("childs"));
        
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}