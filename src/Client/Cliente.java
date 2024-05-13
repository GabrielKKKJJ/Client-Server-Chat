package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(in.readLine());
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        String username = consoleInput.readLine();
        System.out.println("client "+username);
        out.println(username);
        
        String message;

        while ((message = consoleInput.readLine()) != null) {
            out.println(message);
            System.out.println(in.readLine());
        }
        System.out.println("Conexão encerrada");

        out.close();
        in.close();
        socket.close();
    }

}
