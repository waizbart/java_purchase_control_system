package Managers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import Models.Client.Address;
import Models.Client.Client;
import Models.Client.ClientPF;
import Models.Client.ClientPJ;

public class ClientManager {
    private static ClientManager instance = null;
    private ArrayList<ClientPF> clientsPf = new ArrayList<ClientPF>();
    private ArrayList<ClientPJ> clientsPj = new ArrayList<ClientPJ>();

    private ClientManager() {
        FileManager fileManager = new FileManager();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Vector<String[]> clientsData = fileManager.readRows("baseDados/clients.txt");

        for (String[] clientData : clientsData) {

            try {
                if (clientData[0].equals("PJ")) {
                    Address address = new Address(clientData[6], clientData[7], clientData[8], clientData[9],
                            clientData[10], clientData[11]);
                    clientsPj.add(new ClientPJ(clientData[1], clientData[2], Integer.parseInt(clientData[3]),
                            clientData[4], format.parse(clientData[5]), address));
                } else {
                    Address Address = new Address(clientData[5], clientData[6], clientData[7], clientData[8],
                            clientData[9], clientData[10]);
                    clientsPf.add(new ClientPF(clientData[1], Integer.parseInt(clientData[2]), clientData[3],
                            format.parse(clientData[4]), Address));
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Erro ao criar cliente");
            }
        }

        System.out.println("ClientManager()");
    }

    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    public void create(
            Client client,
            String type) {
        if (type.equals("PF")) {
            clientsPf.add((ClientPF) client);
        } else {
            clientsPj.add((ClientPJ) client);
        }
    }

    public ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.addAll(clientsPf);
        clients.addAll(clientsPj);

        clients.sort(
                (Client client1, Client client2) -> {
                    return client1.getEntryDate().compareTo(client2.getEntryDate());
                });

        return clients;
    }

    public void deleteByCpf(String cpf) throws Error {
        cpf = cpf.replaceAll("[^\\d]", "");

        for (ClientPF client : clientsPf) {
            String clientCpf = client.getCpf().replaceAll("[^\\d]", "");

            if (clientCpf.equals(cpf)) {
                clientsPf.remove(client);
                return;
            }
        }

        throw new Error("Cliente não encontrado");
    }

    public void deleteByCnpj(String cnpj) throws Error {
        cnpj = cnpj.replaceAll("[^\\d]", "");

        for (ClientPJ client : clientsPj) {
            String clientCnpj = client.getCnpj().replaceAll("[^\\d]", "");

            if (clientCnpj.equals(cnpj)) {
                clientsPj.remove(client);
                return;
            }
        }

        throw new Error("Cliente não encontrado");
    }

    public void deleteByName(String name) throws Error {
        for (ClientPF client : clientsPf) {
            if (client.getName().equals(name)) {
                clientsPf.remove(client);
                return;
            }
        }

        for (ClientPJ client : clientsPj) {
            if (client.getName().equals(name)) {
                clientsPj.remove(client);
                return;
            }
        }

        throw new Error("Cliente não encontrado");
    }
}
