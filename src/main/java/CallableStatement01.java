import java.lang.reflect.Type;
import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.adim : Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.adim :Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Nefise1234");

        //3. adim: Statement olustur
        Statement st = con.createStatement();

        /*
        Java da method lar return type sahibi olsa mda olmasada method olarak adrlandirilir
        SQL de ise datya return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandirilir
         */

        //CallableStatement ile function cagirmayi parametrelendirecegiz
        //1.adim : Function kodunu yaz.
        String sql1 = "create or replace function toplamaF(x numeric,y numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return x+y;\n" +
                "\n" +
                "end\n" +
                "$$";
        //2.adim: function calistir.

        st.execute(sql1);
        // 3.adiim : function cagir

        CallableStatement cst1 = con.prepareCall("{?=call toplamaF(?,?)}");
        //4. adim: return icin registerOurParameter() methodunu'parametreler icin ise set()...methotlarini uygula
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        //5. adim execude () methodu ile CallableStatement'i calistir.
        cst1.execute();
        //6.adim : soNucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        //1.adim
        String sql2 = "create or replace function konininHacmiF(r NUmeric,h numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return 3.14*r*r*h/3;\n" +
                "\n" +
                "end\n" +
                "$$";
        //2.adim: function calistir.

        st.execute(sql2);
        // 3.adiim : function cagir

        CallableStatement cst2 = con.prepareCall("{?=call konininHacmiF(?,?)}");
        //4. adim: return icin registerOurParameter() methodunu'parametreler icin ise set()...methotlarini uygula
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);//r parametresi
        cst2.setInt(3, 6);// h parametresi

        //5. adim execude () methodu ile CallableStatement'i calistir.
        cst2.execute();
        //6.adim : soNucu cagirmak icin return data type tipine gore
        System.out.printf("%.2f",cst2.getBigDecimal(1));
    }
}