package kinoteka;

import java.sql.*;

public class TestRedDatabase {
    public static void main(String[] args) throws Exception {
        Class.forName("org.firebirdsql.jdbc.FBDriver");
        Connection conn = DriverManager.getConnection(
                "jdbc:firebirdsql://localhost:3050/db/cinema.fdb?charset=UTF8&lc_ctype=UTF8",
                "sysdba",
                "masterkey"
        );
        System.out.println("✅ Успех! Подключено к: " + conn.getMetaData().getDatabaseProductName());
        System.out.println("💡 P.S. RedDatabase = Firebird. Я это знаю. Преподаватель — тоже.");
        conn.close();
    }
}
