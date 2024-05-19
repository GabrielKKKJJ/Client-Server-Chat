package Server;

public class Login implements Runnable{
    private ConnectionHandler connectionHandler;

    public Login(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }   

    public void run() {
        try{
                
            connectionHandler.sendMessage("Informe seu nome");

            String username = connectionHandler.receiveMessage();
            System.out.println(username+" Se conectou");

            Client client = new Client(connectionHandler, username);
            synchronized (Server.clients) {
                Server.clients.add(client);
                Server.sendUpdatedClientList();
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
