import java.util.Scanner;

public class CategoryProgram {
    
    public static void updateCategory(String idInput) {
        // Kiểm tra và lấy ID
        if (idInput == null || idInput.trim().isEmpty()) {
            System.out.println("Missing category ID");
            return;
        }

        // Chuyển đổi ID thành Long
        Long id;
        try {
            id = Long.parseLong(idInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format");
            return;
        }

        // In kết quả
        System.out.println("This is updateCategory with id = " + id);
    }

    public static void main(String[] args) {
        // Sử dụng Scanner để nhận input từ người dùng
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category ID: ");
        String idInput = scanner.nextLine();

        // Gọi phương thức updateCategory
        updateCategory(idInput);

        // Đóng scanner
        scanner.close();
    }
}