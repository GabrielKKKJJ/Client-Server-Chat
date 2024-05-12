package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        try{
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started on port "+PORT);

            while (true) {
                Socket cliente = server.accept();
                System.out.println("Cliente conected:"+ cliente.getInetAddress().getHostAddress());

                ClientConnection clientConnection = new ClientConnection(cliente);

                Login login = new Login(clientConnection);
                Thread loginThread = new Thread(login);
                loginThread.start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}