import java.util.Date;

public class Product {
    String code;
    String name;
    String description;
    double price;
    Date dueDate;

    public Product(String code, String name, String description, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String code, String name, String description, double price, Date dueDate) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dueDate = dueDate;
    }

    public boolean isExpired(){
        Date today = new Date();
        return today.after(dueDate);
    }
}
