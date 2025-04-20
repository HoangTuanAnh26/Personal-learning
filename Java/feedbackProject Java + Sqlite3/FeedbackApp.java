import java.sql.*;
import java.util.Scanner;

public class FeedbackApp {
    private static final String DATABASE_URL = "jdbc:C:\\SQlite\\Database\\データベース論.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            // Khởi tạo cơ sở dữ liệu
            initializeDatabase(connection);
            // Hiển thị danh sách feedback
            displayFeedbacks(connection);
            // Thêm feedback mới
            addFeedback(connection);
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Khởi tạo bảng và dữ liệu mẫu
    private static void initializeDatabase(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS feedback (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "body TEXT," +
                "date TEXT" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);

            // Chèn dữ liệu mẫu (nếu bảng trống)
            String checkEmptySQL = "SELECT COUNT(*) FROM feedback";
            ResultSet rs = statement.executeQuery(checkEmptySQL);
            if (rs.getInt(1) == 0) {
                String insertSQL = "INSERT INTO feedback (name, email, body, date) VALUES " +
                        "('John', 'john@gmail.com', 'I like it', datetime('now'))," +
                        "('Tony', 'tony12@gmail.com', 'Please add more videos', datetime('now'))," +
                        "('Hoang', 'hoang@gmail.com', 'Let do Laravel project', datetime('now'))";
                statement.execute(insertSQL);
            }
        }
    }

    // Hiển thị danh sách feedback
    private static void displayFeedbacks(Connection connection) throws SQLException {
        String sql = "SELECT name, email, body FROM feedback";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("\nDanh sách feedback:");
            System.out.println("-------------------");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String body = resultSet.getString("body");
                System.out.println("Tên: " + name);
                System.out.println("Email: " + email);
                System.out.println("Nội dung: " + body);
                System.out.println("-------------------");
            }
        }
    }

    // Thêm feedback mới
    private static void addFeedback(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nNhập feedback của bạn:");

        System.out.print("Tên của bạn là gì? ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Tên không được để trống!");
            return;
        }

        System.out.print("Nhập email của bạn: ");
        String email = scanner.nextLine();
        if (email.isEmpty()) {
            System.out.println("Email không được để trống!");
            return;
        }

        System.out.print("Nhập nội dung feedback: ");
        String body = scanner.nextLine();
        if (body.isEmpty()) {
            System.out.println("Nội dung không được để trống!");
            return;
        }

        String sql = "INSERT INTO feedback (name, email, body, date) VALUES (?, ?, ?, datetime('now'))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, body);
            statement.executeUpdate();
            System.out.println("Feedback đã được thêm thành công!");
        }
    }
}