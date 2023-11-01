package Models.Client;
import java.util.Date;

public class ClientPJ extends Client {
    String cnpj;
    String socialReason;
    int maxDaysForPayment;
    
    public ClientPJ(String cnpj, String socialReason, int maxDaysForPayment, String name, Date entryDate, Address address) {
        super(name, entryDate, address);
        this.cnpj = cnpj;
        this.socialReason = socialReason;
        this.maxDaysForPayment = maxDaysForPayment;
    }

    public String toString() {
        return super.toString() + " | CNPJ: " + cnpj + " | Social Reason: " + socialReason + " | Max Days For Payment: " + maxDaysForPayment;
    }
}