package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

                    ClientConnection clientConnection = new ClientConnection(cliente);

                    Login login = new Login(clientConnection);
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

    public static void addClient(Client client) {
        clients.add(client);
    }

    public static void removeClient(Client client) {
        clients.remove(client);
    }
}