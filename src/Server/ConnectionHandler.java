package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

public class ConnectionHandler {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static ObjectOutputStream outObject;
    
    public ConnectionHandler(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            outObject = new ObjectOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String messsage) {
        out.println(messsage);
    }

    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendClientList(List<String> clients) throws IOException {
        ArrayList<String> clientsList = new ArrayList<>(clients);
        System.out.println("Enviando lista de clientes"+ clientsList);
        outObject.writeObject(clientsList);
        outObject.flush();
    }
    

    public Socket getSocket() {
        return socket;
    }

    public void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
