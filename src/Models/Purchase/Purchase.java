package Models.Purchase;

import java.util.Date;

public class Purchase {
    private int id;
    private Date date;
    private int idClient;
    private PurchaseItem[] items;
    private double totalValue;
    private double totalPaid;

    public Purchase(int id, Date date, int idClient, PurchaseItem[] items) {
        this.id = id;
        this.date = date;
        this.idClient = idClient;
        this.items = items;
        this.totalValue = 0;
        this.totalPaid = 0;
        for (PurchaseItem item : items) {
            this.totalValue += item.getPrice();
        }
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getIdClient() {
        return idClient;
    }

    public PurchaseItem[] getItems() {
        return items;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getRemainingValue() {
        return totalValue - totalPaid;
    }
}
