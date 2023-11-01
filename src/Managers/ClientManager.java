package Managers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import Models.Client.Address;
import Models.Client.Client;
import Models.Client.ClientPF;
import Models.Client.ClientPJ;

public class ClientManager {
    private static ClientManager instance = null;
    private Vector<ClientPF> clientsPf;
    private Vector<ClientPJ> clientsPj;

    private ClientManager() {
        FileManager fileManager = new FileManager();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Vector<String[]> clientsData = fileManager.readRows("baseDados/clients.txt");

        for (String[] clientData : clientsData) {
            try {
                if (clientData[0] == "PJ") {
                    Address address = new Address(clientData[6], clientData[7], clientData[8], clientData[9],
                            clientData[10], clientData[11]);
                    clientsPj.add(new ClientPJ(clientData[1], clientData[2], Integer.parseInt(clientData[3]),
                            clientData[4], format.parse(clientData[5]), address));
                } else {
                    Address Address = new Address(clientData[5], clientData[6], clientData[7], clientData[8],
                            clientData[9], clientData[10]);
                    clientsPf.add(new ClientPF(clientData[1], clientData[2], Integer.parseInt(,
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

    public void create() {
        System.out.println("ClientManager.create()");
    }

    public void read() {
        System.out.println("ClientManager.read()");
    }

    public void update() {
        System.out.println("ClientManager.update()");
    }

    public void delete() {
        System.out.println("ClientManager.delete()");
    }
}
