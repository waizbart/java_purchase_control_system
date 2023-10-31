public class Address {
    String street;
    String number;
    String neighborhood;
    String cep;
    String cidade;
    String state;

    public Address(String street, String number, String neighborhood, String cep, String cidade, String state) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.cidade = cidade;
        this.state = state;
    }

    public String toString() {
        return "Address: " + street + " | Number: " + number + " | Neighborhood: " + neighborhood + " | CEP: " + cep
                + " | City: " + cidade + " | State: " + state;
    }
}
