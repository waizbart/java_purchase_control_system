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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        if (dueDate != null) {
            return String.format(
                    "Código: %s | Nome: %s | Descrição: %s | Valor: R$%.2f | Data de validade: %s",
                    code,
                    name,
                    description,
                    price,
                    format.format(dueDate));
        } else {
            return String.format(
                    "Código: %s | Nome: %s | Descrição: %s | Valor: R$%.2f",
                    code,
                    name,
                    description,
                    price);
        }
    }
}
