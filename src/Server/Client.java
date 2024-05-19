package Server;

import java.io.IOException;
import java.util.List;

public class Client {
    private ConnectionHandler connectionHandler;
    private String username;

    public Client(ConnectionHandler connectionHandler, String username) {
        this.connectionHandler = connectionHandler;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ConnectionHandler getClientConnection() {
        return connectionHandler;
    }

    public void sendMessage(String message) {
        connectionHandler.sendMessage(message);
    }

    public void sendClientList(List<String> clients) throws IOException {
        connectionHandler.sendClientList(clients);
    }

    public String receiveMessage() {
        return connectionHandler.receiveMessage();
    }
    
    public void closeConnection() {
        connectionHandler.closeConnection();
    }
    
}
