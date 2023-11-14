package Managers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Models.Product.Product;
import Models.Purchase.Purchase;
import Models.Purchase.PurchaseItem;

public class PurchaseManager {
    private static PurchaseManager instance = null;
    private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

    private ProductManager productManager = ProductManager.getInstance();

    private PurchaseManager() {
        FileManager fileManager = new FileManager();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Vector<String[]> purchasesData = fileManager.readRows("baseDados/purchases.txt");

        for (String[] purchaseData : purchasesData) {
            String itemRegex = "\\[(\\d+)-(\\w\\d+)\\]";

            Pattern pattern = Pattern.compile(itemRegex);
            Matcher matcher = pattern.matcher(purchaseData[3]);

            ArrayList<PurchaseItem> items = new ArrayList<PurchaseItem>();

            while (matcher.find()) {
                Product product = productManager.getProduct(matcher.group(2));

                items.add(new PurchaseItem(Integer.parseInt(matcher.group(1)), product));
            }

            try {
                Purchase purchase = new Purchase(
                        Integer.parseInt(purchaseData[0]),
                        format.parse(purchaseData[1]),
                        purchaseData[2],
                        items.toArray(new PurchaseItem[items.size()]));

                purchases.add(purchase);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Erro ao criar compra");
            }
        }
    }

    public static PurchaseManager getInstance() {
        if (instance == null) {
            instance = new PurchaseManager();
        }
        return instance;
    }

    public void create(
            Purchase purchase) {
        purchases.add(purchase);
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void updatePaidValue(int id, float value) throws Error {
        for (Purchase purchase : purchases) {
            if (purchase.getId() == id) {
                purchase.setTotalPaid(purchase.getTotalValue());
                return;
            }
        }

        throw new Error("Compra não encontrada");
    }
}
