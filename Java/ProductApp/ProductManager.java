import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    private long nextId = 1;
    private static final String UPLOAD_DIR = "uploads";
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final Pattern IMAGE_CONTENT_TYPE = Pattern.compile("image/.*");

    public ProductManager() {
        try {
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
        } catch (IOException e) {
            System.err.println("Không thể tạo thư mục uploads: " + e.getMessage());
        }
    }

    public List<String> validateProduct(String name, float price, String filePath, Long categoryId) {
        List<String> errors = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            errors.add("Tên sản phẩm là bắt buộc");
        } else if (name.length() < 3 || name.length() > 200) {
            errors.add("Tên sản phẩm phải từ 3 đến 200 ký tự");
        }
        if (price < 0 || price > 10000000) {
            errors.add("Giá phải từ 0 đến 10,000,000");
        }
        if (filePath != null && !filePath.isEmpty()) {
            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    errors.add("Tệp không tồn tại");
                } else if (file.length() > MAX_FILE_SIZE) {
                    errors.add("Tệp quá lớn! Kích thước tối đa là 10MB");
                } else {
                    String contentType = Files.probeContentType(file.toPath());
                    if (contentType == null || !IMAGE_CONTENT_TYPE.matcher(contentType).matches()) {
                        String extension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
                        if (!Set.of("jpg", "jpeg", "png", "gif").contains(extension)) {
                            errors.add("Tệp phải là hình ảnh");
                        }
                    }
                }
            } catch (IOException e) {
                errors.add("Lỗi khi kiểm tra tệp: " + e.getMessage());
            }
        }
        return errors;
    }

    public String storeFile(String filePath) throws IOException {
        File file = new File(filePath);
        String filename = file.getName();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        Path destination = Paths.get(UPLOAD_DIR, uniqueFilename);
        Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    public String createProduct(String name, float price, String filePath, String description, Long categoryId) {
        List<String> errors = validateProduct(name, price, filePath, categoryId);
        if (!errors.isEmpty()) {
            return "Lỗi: " + String.join(", ", errors);
        }
        String thumbnail = null;
        if (filePath != null && !filePath.isEmpty()) {
            try {
                thumbnail = storeFile(filePath);
            } catch (IOException e) {
                return "Lỗi khi lưu tệp: " + e.getMessage();
            }
        }
        Product product = new Product(name, price, thumbnail, description, categoryId, nextId++);
        products.add(product);
        return "Tạo sản phẩm thành công: " + product;
    }

    public String getProducts(int page, int limit) {
        int start = (page - 1) * limit;
        if (start >= products.size()) {
            return "Không có sản phẩm nào ở trang này";
        }
        List<Product> pagedProducts = products.subList(start, Math.min(start + limit, products.size()));
        return pagedProducts.stream().map(Product::toString).collect(Collectors.joining("\n"));
    }

    public String getProductById(long id) {
        Optional<Product> product = products.stream().filter(p -> p.getId() == id).findFirst();
        return product.map(Product::toString).orElse("Không tìm thấy sản phẩm với id: " + id);
    }

    public String deleteProduct(long id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        return removed ? "Xóa sản phẩm với id = " + id + " thành công" : "Không tìm thấy sản phẩm với id: " + id;
    }
}