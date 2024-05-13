package Server;

public class Client {
    private ClientConnection clientConnection;
    private String username;

    public Client(ClientConnection clientConnection, String username) {
        this.clientConnection = clientConnection;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void sendMessage(String message) {
        clientConnection.sendMessage(message);
    }

    public String receiveMessage() {
        return clientConnection.receiveMessage();
    }
    
    public void closeConnection() {
        clientConnection.closeConnection();
    }
    
}
