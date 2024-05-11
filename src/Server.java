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

                Login login = new Login(cliente);
                Thread loginThread = new Thread(login);
                loginThread.start();
                
                try {
                    loginThread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                String username = login.getUsername();
                System.out.println("Server "+username);

                // adicionar thread de chat
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}