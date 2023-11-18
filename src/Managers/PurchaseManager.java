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
                        items.toArray(new PurchaseItem[items.size()]),
                        purchaseData[4].equals("null") ? null : Double.parseDouble(purchaseData[4]));

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

    public void updatePaidValue(int id, double value) throws Error {
        for (Purchase purchase : purchases) {
            if (purchase.getId() == id) {
                purchase.setTotalPaid(value);
                return;
            }
        }

        throw new Error("Compra não encontrada");
    }

    public ArrayList<Purchase> getLastTenPaidPurchases() {
        ArrayList<Purchase> lastTenPaidPurchases = new ArrayList<Purchase>();

        for (int i = purchases.size() - 1; i >= 0; i--) {
            Purchase purchase = purchases.get(i);

            if (purchase.isPaid()) {
                lastTenPaidPurchases.add(purchase);
            }

            if (lastTenPaidPurchases.size() == 10) {
                break;
            }
        }

        return lastTenPaidPurchases;
    }

    public Purchase showMostExpensivePurchase() {
        Purchase mostExpensivePurchase = purchases.get(0);

        for (Purchase purchase : purchases) {
            if (purchase.getTotalValue() > mostExpensivePurchase.getTotalValue()) {
                mostExpensivePurchase = purchase;
            }
        }

        return mostExpensivePurchase;
    }

    public Purchase showMostCheapestPurchase() {
        Purchase mostCheapestPurchase = purchases.get(0);

        for (Purchase purchase : purchases) {
            if (purchase.getTotalValue() < mostCheapestPurchase.getTotalValue()) {
                mostCheapestPurchase = purchase;
            }
        }

        return mostCheapestPurchase;
    }

    // get total value by each month
    public double[] getTotalValueByMonth() {
        double[] totalValueByMonth = new double[12];

        for (Purchase purchase : purchases) {
            int month = purchase.getDate().getMonth();
            totalValueByMonth[month] += purchase.getTotalValue();
        }

        return totalValueByMonth;
    }

    public void closeAndSave() {
        FileManager fileManager = new FileManager();
        Vector<String[]> purchasesData = new Vector<String[]>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Purchase purchase : purchases) {
            String items = "";

            for (PurchaseItem item : purchase.getItems()) {
                items += "[" + item.getQuantity() + "-" + item.getProduct().getCode() + "]";
            }

            purchasesData.add(new String[] {
                    String.valueOf(purchase.getId()),
                    sdf.format(purchase.getDate()),
                    purchase.getIdClient(),
                    items,
                    String.valueOf(purchase.getTotalPaid())
            });

            System.out.println(purchasesData);
        }

        fileManager.writeRows("baseDados/purchases.txt", purchasesData);
    }

}
