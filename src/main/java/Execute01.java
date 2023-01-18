

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.adim : Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.adim :Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Nefise1234");

        //3. adim: Statement olustur
        Statement st = con.createStatement();

       // System.out.println("Connection Success");

        // 4. adim :query calistir

        /*
        execude() methodu DDL(creat,alter table)ve DQL (select)icin kullanilabilir.
        1) eger bu execude methodu DDL icin kullanilirsa 'false'rutern yapar.
        2) eger execude methodu DQL icin kullanilirsa RESULTSET alindiginda 'true' aksi halde 'false' verecek.
        */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
      boolean sql1=  st.execute("CREATE TABLE workers (worker_id varchar(20) ,worker_name varchar(20),worker_salary int);");
        System.out.println("sql1 = " + sql1);// false rutern eder cunki data cagirmiyoruz.

     //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.

        String sql2="alter table workers add worker_address varchar(80)";
       boolean sql2b= st.execute(sql2);
        System.out.println(sql2b);

        //3.Örnek: Drop workers table

        String sql3="drop table workers";
        boolean sql3b= st.execute(sql3);
        System.out.println("sql3b = " + sql3b);

        // 5. adim : baglanti ve statement'i kapat.
        
        con.close();
        st.close();
        
        
        

    }
}
