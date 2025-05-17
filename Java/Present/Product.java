import java.util.Set;
public class Product {
    private String name;
    private float price;
    private String thumbnail;
    private String description;
    private Long categoryId;
    private long id;

    public Product(String name, float price, String thumbnail,String description, long cetegoryId, long id) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.categoryId = cetegoryId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public long getCategory() {
        return categoryId;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sản phẩm {id=" + id + ", tên='" + name + "', giá=" + price +
                ", ảnh đại diện='" + thumbnail + "', mô tả='" + description +
                "', mã danh mục=" + categoryId + "}";
    }
}
