package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat implements Runnable{
    private Socket clienSocket;
    private String username;

    public Chat(Socket socket, String username){
        this.clienSocket = socket;
        this.username = username;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clienSocket.getOutputStream(), true);
            
            while (true) {
                String message = in.readLine();
                System.out.println(username+": "+message);
                out.println(username+": "+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
