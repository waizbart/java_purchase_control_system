package Models.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    String name;
    Date entryDate;
    Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Client(String name, Date entryDate, Address address) {
        this.name = name;
        this.entryDate = new Date();
        this.address = address;
    }

    public String toString() {
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(entryDate);

        return "Cliente: " + name + " | Data de entrada: " + formattedDate;
    }
    
}
