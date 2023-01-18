import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {

        // 1. adim: Driver 'a kaydol
        //2. adim : Datbase 'e baglan
        Connection connection = JdbcUtils.connectToDataBase("localhost","techproed","postgres","Nefise1234");

        //3 adim : statement olustur.
        Statement statement = JdbcUtils.createStatement();

        // 4. adim :query calistir
        // JdbcUtils.execute("Create table students(name varchar(20),id int,adres varchar(80))");

        JdbcUtils.createTable("School","classes Varchar (20)","teacher_name Varchar(20)","id INT");


        // 5. adim : baglanti ve statement'i kapat.
        JdbcUtils.closeConnectionAndStatement();



    }
}
