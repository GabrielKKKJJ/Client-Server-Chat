package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;

public class Server {
    private static final int PORT = 12345;
    public static List<Client> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try{
            try (ServerSocket server = new ServerSocket(PORT)) {
                System.out.println("Server started on port "+PORT);

                while (true) {
                    Socket cliente = server.accept();
                    System.out.println("Cliente conected: "+ cliente.getInetAddress().getHostAddress());

                    ConnectionHandler connectionHandler = new ConnectionHandler(cliente);

                    Login login = new Login(connectionHandler);
                    Thread loginThread = new Thread(login);
                    loginThread.start();

                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void broadcastMessage(String message, Client sender) {
        for (Client client : clients) {
            if (client != sender) {
                client.sendMessage(sender.getUsername() + ": " + message);
            }
        }
    }

    public static void addClient(Client client) throws IOException {
        clients.add(client);
        sendUpdatedClientList();
    }

    public static void removeClient(Client client) throws IOException {
        clients.remove(client);
        sendUpdatedClientList();
    }

    public static void sendUpdatedClientList() throws IOException {
        List<String> usernameList = FXCollections.observableArrayList();

        for (Client client : clients) {
            usernameList.add(client.getUsername());
        }
        for (Client client : clients) {
            client.sendClientList(usernameList);
        }
    }
}