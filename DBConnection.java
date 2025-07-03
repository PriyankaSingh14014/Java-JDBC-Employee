import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            System.out.println("🔌 Connecting to MySQL...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/employee_db?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", // ✅ your MySQL username
                    "9457771051" // ✅ your MySQL password
            );
        } catch (Exception e) {
            System.out.println("❌ DB Connection Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
