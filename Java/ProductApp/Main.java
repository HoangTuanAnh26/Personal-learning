import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        ProductManager manager = new ProductManager();

        while (true) {
            displayMenu();
            System.out.print("CHỌN: ");
            String choice = scanner.nextLine().trim();

            if (choice.isEmpty()) {
                System.out.println("Vui lòng nhập một lựa chọn!");
                continue;
            }

            try {
                switch (choice) {
                    case "1":
                        createProduct(manager, scanner);
                        break;
                    case "2":
                        listProducts(manager, scanner);
                        break;
                    case "3":
                        findProduct(manager, scanner);
                        break;
                    case "4":
                        deleteProduct(manager, scanner);
                        break;
                    case "5":
                        System.out.println("Thoát chương trình.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Quản lý sản phẩm ===");
        System.out.println("1. Tạo sản phẩm");
        System.out.println("2. Liệt kê sản phẩm");
        System.out.println("3. Tìm sản phẩm theo ID");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Thoát");
    }

    private static void createProduct(ProductManager manager, Scanner scanner) {
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Tên sản phẩm không được để trống!");
            return;
        }

        Float price = null;
        while (price == null) {
            System.out.print("Nhập giá sản phẩm: ");
            try {
                price = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Giá phải là một số hợp lệ! Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập đường dẫn tệp hình ảnh (để trống nếu không có): ");
        String filePath = scanner.nextLine().trim();

        System.out.print("Nhập mô tả (để trống nếu không có): ");
        String description = scanner.nextLine().trim();

        Long categoryId = null;
        while (categoryId == null) {
            System.out.print("Nhập mã danh mục: ");
            try {
                categoryId = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mã danh mục phải là một số hợp lệ! Vui lòng nhập lại.");
            }
        }

        String result = manager.createProduct(name, price, filePath, description, categoryId);
        System.out.println(result);
    }

    private static void listProducts(ProductManager manager, Scanner scanner) {
        Integer page = null;
        while (page == null) {
            System.out.print("Nhập số trang: ");
            try {
                page = Integer.parseInt(scanner.nextLine());
                if (page < 1) {
                    System.out.println("Số trang phải lớn hơn 0! Vui lòng nhập lại.");
                    page = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Số trang phải là một số hợp lệ! Vui lòng nhập lại.");
            }
        }

        Integer limit = null;
        while (limit == null) {
            System.out.print("Nhập số sản phẩm mỗi trang: ");
            try {
                limit = Integer.parseInt(scanner.nextLine());
                if (limit < 1) {
                    System.out.println("Số sản phẩm mỗi trang phải lớn hơn 0! Vui lòng nhập lại.");
                    limit = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Số sản phẩm mỗi trang phải là một số hợp lệ! Vui lòng nhập lại.");
            }
        }

        System.out.println(manager.getProducts(page, limit));
    }

    private static void findProduct(ProductManager manager, Scanner scanner) {
        Long id = null;
        while (id == null) {
            System.out.print("Nhập ID sản phẩm: ");
            try {
                id = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID phải là một số hợp lệ! Vui lòng nhập lại.");
            }
        }
        System.out.println(manager.getProductById(id));
    }

    private static void deleteProduct(ProductManager manager, Scanner scanner) {
        Long id = null;
        while (id == null) {
            System.out.print("Nhập ID sản phẩm: ");
            try {
                id = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID phải là một số hợp lệ! Vui lòng nhập lại.");
            }
        }
        System.out.println(manager.deleteProduct(id));
    }
}