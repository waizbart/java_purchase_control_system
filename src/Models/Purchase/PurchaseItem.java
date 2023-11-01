package Models.Purchase;

import Models.Product.Product;

public class PurchaseItem {
    private int quantity;
    private Product product;
    private double price;

    public PurchaseItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.price = product.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }
}
