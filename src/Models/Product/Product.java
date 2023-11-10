package Models.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private String code;
    private String name;
    private String description;
    private double price;
    private Date dueDate;

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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isExpired() {
        Date today = new Date();
        return today.after(dueDate);
    }

    public String toString() {
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(dueDate);

        return "Nome: " + name + " | Código: " + code + " | Descrição: " + description + " | Preço: " + price + " | Data de validade: " + formattedDate;
    }
}
