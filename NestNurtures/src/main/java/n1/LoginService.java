package n1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gecdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yugala@10";

    public boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    isAuthenticated = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }
}
