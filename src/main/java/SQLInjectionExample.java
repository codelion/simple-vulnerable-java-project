import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) throws SQLException {
        String userInputA = args[1];

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");

        // Use a prepared statement to prevent SQL injection
        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, userInputA); // Set the input parameter
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }

        // Close resources
        rs.close();
        pstmt.close();
        con.close();
    }
}