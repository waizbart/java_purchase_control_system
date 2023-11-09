package Models.Client;
import java.util.Date;

public class ClientPF extends Client {
    String cpf;
    int maxInstallments;
    
    public ClientPF(String cpf, int maxInstallments, String name, Date entryDate, Address address) {
        super(name, entryDate, address);
        this.cpf = cpf;
        this.maxInstallments = maxInstallments;
    }

    public String toString() {
        return super.toString() + " | CPF: " + cpf + " | Max. Parcelas: " + maxInstallments;
    }

    public String getCpf() {
        return cpf;
    }
}
