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
        return super.toString() + " | CNPJ: " + cnpj + " | Razão Social: " + socialReason + " | Max. dias para pagamento: " + maxDaysForPayment;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public int getMaxDaysForPayment() {
        return maxDaysForPayment;
    }
}