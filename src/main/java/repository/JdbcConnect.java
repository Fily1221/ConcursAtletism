package repository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnect {
    public static Connection conn;
    public JdbcConnect() {

    }

    public static void connect() {
        Properties serverProps = new Properties(System.getProperties());

        try {
            serverProps.load(new FileReader("connection"));
            System.setProperties(serverProps);
        } catch (IOException var2) {
            System.out.println("Eroare " + var2);
        }

        String tasksFile = System.getProperty("tasksFile");
    }

    private static Connection getNewConnection() {
        connect();
        String driver = System.getProperty("jdbc.driver");
        String url = System.getProperty("jdbc.url");
        String user = System.getProperty("jdbc.user");
        String pass = System.getProperty("jdbc.pass");
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException var6) {
            System.out.println("Eroare incarcare driver " + var6);
        } catch (SQLException var7) {
            System.out.println("Eroare stabilire conexiune " + var7);
        }

        return con;
    }

    public static void openConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = getNewConnection();
                System.out.println("OPENED A NEW CONNECTION");
            }
        } catch (SQLException var1) {
            var1.printStackTrace();
        }

    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException var1) {
            System.out.println("Conexiunea n-a putut sa se inchida " + var1);
        }

    }
}
