import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Login implements Runnable{
    private Socket clienSocket;
    private String username;

    public Login(Socket socket){
        this.clienSocket = socket;
    }

    public void run() {
        try{
            PrintWriter out = new PrintWriter(clienSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
            
            out.println("Insert your username:");

            String username = in.readLine();
            System.out.println(username+" Se conectou");

            this.username = username;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public String getUsername() {
        return username;
    }
    
}
