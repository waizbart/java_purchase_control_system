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
                if (productData[4].equals("null")) {
                    products.add(
                            new Product(
                                    productData[0],
                                    productData[1],
                                    productData[2],
                                    Double.parseDouble(productData[3])));

                } else {
                    products.add(
                            new Product(
                                    productData[0],
                                    productData[1],
                                    productData[2],
                                    Double.parseDouble(productData[3]),
                                    format.parse(productData[4])));
                }
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

    public Product getProduct(String code) throws Error {
        for (Product product : products) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }

        throw new Error("Produto não encontrado");
    }

    public void closeAndSave() {
        FileManager fileManager = new FileManager();
        Vector<String[]> productsData = new Vector<String[]>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Product product : products) {
            String[] productData = new String[5];

            productData[0] = product.getCode();
            productData[1] = product.getName();
            productData[2] = product.getDescription();
            productData[3] = String.valueOf(product.getPrice());
            if (product.getDueDate() != null) {
                productData[4] = sdf.format(product.getDueDate());
            }

            productsData.add(productData);
        }

        fileManager.writeRows("baseDados/products.txt", productsData);
    }
}
