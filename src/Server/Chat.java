package Server;

public class Chat implements Runnable {
    private Client client;

    public Chat(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = client.receiveMessage()) != null) {
                Server.broadcastMessage(message, client);
                System.out.println(client.getUsername() + ": " + message);
                
            }
            System.out.println("Conexão encerrada");
        } finally {
            System.out.println(client.getUsername() + " se desconectou");

            synchronized (Server.clients) {
                Server.clients.remove(client);
                Server.broadcastMessage(client.getUsername() + " se desconectou", client);
            }
            client.closeConnection();
        }
    }
}
