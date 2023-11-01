package Models.Client;

import java.util.Date;

public class Client {
    String name;
    Date entryDate;
    Address address;

    public Client(String name, Date entryDate, Address address) {
        this.name = name;
        this.entryDate = new Date();
        this.address = address;
    }

    public String toString() {
        return "Client: " + name + " | Entry Date: " + entryDate + " | Address: " + address;
    }
}
