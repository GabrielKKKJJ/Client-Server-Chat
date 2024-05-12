package Server;
import java.io.IOException;
import java.net.Socket;

public class Login implements Runnable{
    private Socket clienSocket;
    private ClientConnection clientConnection;

    public Login(ClientConnection clientConnection) {
        this.clienSocket = clientConnection.getSocket();
        this.clientConnection = clientConnection;
    }

    public void run() {
        try{
                
            clientConnection.sendMenssage("Informe seu nome");

            String username = clientConnection.receiveMessage();
            System.out.println(username+" Se conectou");

            Chat chat = new Chat(clienSocket, username);
            Thread chatThread = new Thread(chat);
            chatThread.start();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
