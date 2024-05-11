import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345); // Conexão ao servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(in.readLine());
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        String username = consoleInput.readLine();
        System.out.println("client "+username);
        out.println(username);
        
        String message;

        while ((message = in.readLine()) != null) {
            System.out.println("While");
            System.out.println("Servidor diz: " + message); // Recebe resposta do servidor
            
        }

        out.close();
        in.close();
        socket.close();
    }
}
