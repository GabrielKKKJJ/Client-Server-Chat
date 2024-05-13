package Server;

public class Login implements Runnable{
    private ClientConnection clientConnection;

    public Login(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }   

    public void run() {
        try{
                
            clientConnection.sendMessage("Informe seu nome");

            String username = clientConnection.receiveMessage();
            System.out.println(username+" Se conectou");

            Client client = new Client(clientConnection, username);
            synchronized (Server.clients) {
                Server.clients.add(client);
            }

            Chat chat = new Chat(client);
            Thread chatThread = new Thread(chat);
            chatThread.start();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
