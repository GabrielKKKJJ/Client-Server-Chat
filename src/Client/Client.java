package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import Client.core.ClientConnection;
import Client.core.InputThread;
import Client.core.OutputThread;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        ClientConnection clientConnection = new ClientConnection(socket);

        String loginMsg = clientConnection.receiveMessage();
        System.out.println(loginMsg);

        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        String username = consoleInput.readLine();
        System.out.println("client " + username);
        clientConnection.sendMessage(username);

        // Cria e inicia as threads de entrada e saída
        System.out.println("here");
        InputThread inputThread = new InputThread(socket.getInputStream());
        OutputThread outputThread = new OutputThread(socket.getOutputStream(), consoleInput);

        inputThread.start();
        outputThread.start();

        // Aguarda as threads terminarem
        try {
            inputThread.join();
            outputThread.join();
        } catch (InterruptedException e) {
            System.err.println("Erro ao aguardar término das threads: " + e.getMessage());
        }

        // Fecha recursos
        socket.close();
    }
}
