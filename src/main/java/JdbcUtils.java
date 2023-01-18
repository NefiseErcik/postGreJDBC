import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;
    //1.adim : Driver a kaydol
    //2.adim :Database e baglan

    public static Connection connectToDataBase(String hostName, String dbName, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName + "", username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            System.out.println("Connection Succes");
        } else {
            System.out.println("Connection Fail");
        }
        return connection;
    }

    //3. adim: Statement olustur
    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
        // 4. adim :query calistir
    }

    // 4. adim :query calistir
    public static boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    // 5. adim : baglanti ve statement'i kapat.
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        try {
            if (connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and statement closed");
            }else {
                System.out.println("Connection and statement NOT closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //table olusturan method
    public static void createTable(String tableName,String ...columnName_dataType){
        StringBuilder columnName_dataValue=new StringBuilder("");
        for (String w:columnName_dataType) {
            columnName_dataValue.append(w).append(",");
        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);
        String sql="create table "+tableName+"("+columnName_dataValue+")";
        try {
            statement.execute(sql);
            System.out.println("Table "+tableName+" successfully created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}