import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaTest {
    /*
   Given
     User connects to the database//Kullanıcı veritabanına bağlanır
     (Host name: medunna.com, Database name: medunna_db, Usename: medunna_user, Password: medunna_pass_987))
     //Ana bilgisayar adı: medunna.com, Veritabanı adı: medunna_db, Kullanıcı adı: medunna_user, Şifre: medunna_pass_987))
   When
     User sends the query to get the names of created_by column from "room" table
     //Kullanıcı, "room" tablosundan Created_by sütununun adlarını almak için sorguyu gönderir.
   Then
     Assert that there are some rooms created by "john_doe".
     //"john_doe" tarafından oluşturulmuş bazı odalar olduğunu iddia edin.
   And
     User closes the connection//Kullanıcı bağlantıyı kapatır

  */

    @Test
    public  void medunnaTest() throws SQLException {
       // User connects to the database//Kullanıcı veritabanına bağlanır
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
       Statement statement= JdbcUtils.createStatement();
       // User sends the query to get the names of created_by column from "room" table
        //Kullanıcı, "room" tablosundan Created_by sütununun adlarını almak için sorguyu gönderir.
        String sql="select Created_by from room";
        ResultSet resultSet1=statement.executeQuery(sql);
        List<String> created_byList=new ArrayList<>();
        while (resultSet1.next()){
            created_byList.add(resultSet1.getString(1));

        }
        System.out.println("created_byList = " + created_byList);

        //Assert that there are some rooms created by "john_doe".
        //"john_doe" tarafından oluşturulmuş bazı odalar olduğunu iddia edin.
        Assert.assertTrue(created_byList.contains("john_doe"));
        created_byList.contains("john_doe");

      //  User closes the connection//Kullanıcı bağlantıyı kapatır


        JdbcUtils.closeConnectionAndStatement();



    }
}




