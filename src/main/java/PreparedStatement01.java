import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.adim : Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.adim :Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Nefise1234");

        //3. adim: Statement olustur
        Statement st = con.createStatement();
        /*
        PreparedStatement interface birden cok calistirilabilen onceden derlenmis bir sql kodunu temsil eder
        Parametrelendirilmis sql sorgulari (query) ile calisir Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz

         /*
        PreparedStatement interface birden cok calistirilabilen onceden derlenmis bir sql kodunu temsil eder
        Parametrelendirilmis sql sorgulari (query) ile calisir Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz
*/
        //1. Örnek : Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1. Adim : PreparedStatement query sini olustur
        String sql1 = "update companies set number_of_employees = ? where company = ?";

        //2. Adim : PreparedStatement objesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Adim : setInt(), setString(),... methodlarini kullanarak soru isareti yerlerine deger gir.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4. Adim : Query i calistir
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "Select * from companies";
        ResultSet rs1 = st.executeQuery(sql2);
        while (rs1.next()) {
            System.out.println(rs1.getInt(1) + "--" + rs1.getString(2) + "--" + rs1.getInt(3));


        }
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");
        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet rs2 = st.executeQuery(sql2);


        while (rs2.next()) {
            System.out.println(rs2.getInt(1) + "--" + rs2.getString(2) + "--" + rs2.getInt(3));
        }
        con.close();
        st.close();
        rs1.close();
        rs2.close();
    }
}
