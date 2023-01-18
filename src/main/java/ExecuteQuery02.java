import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Nefise1234");
        Statement st = con.createStatement();


        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql1 = "select company, number_of_employees from companies order by number_of_employees desc offset 1 limit 1";

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getString(1) + "--" + resultSet1.getInt(2));


        }
        con.close();
        st.close();
        resultSet1.close();
    }
}