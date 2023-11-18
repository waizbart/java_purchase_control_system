package Models.Purchase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {
    private int id;
    private Date date;
    private String idClient;
    private PurchaseItem[] items;
    private double totalValue;
    private double totalPaid;

    public Purchase(int id, Date date, String idClient, PurchaseItem[] items, double totalPaid) {
        this.id = id;
        this.date = date;
        this.idClient = idClient;
        this.items = items;
        this.totalValue = 0;
        this.totalPaid = totalPaid;
        for (PurchaseItem item : items) {
            this.totalValue += item.getPrice();
        }
    }

    public Purchase(int id, Date date, String idClient, PurchaseItem[] items) {
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

    public String getIdClient() {
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

    public boolean isPaid() {
        return totalPaid >= totalValue;
    }

    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return "ID: " + id + " | Data: " + format.format(date) + " | ID Cliente: " + idClient + " | Valor Total: " + totalValue
                + " | Valor Pago: R$ " + totalPaid + " | Valor Restante: R$ " + getRemainingValue();
    }
}
