package Managers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import Models.Product.Product;

public class ProductManager {
    private static ProductManager instance = null;
    private ArrayList<Product> products = new ArrayList<Product>();

    private ProductManager() {
        FileManager fileManager = new FileManager();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Vector<String[]> productsData = fileManager.readRows("baseDados/products.txt");

        for (String[] productData : productsData) {
            try {
                products.add(
                        new Product(
                                productData[0],
                                productData[1],
                                productData[2],
                                Double.parseDouble(productData[3]),
                                format.parse(productData[4])));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public void create(Product product) {
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
