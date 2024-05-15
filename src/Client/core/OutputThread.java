package Client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class OutputThread extends Thread {
    private PrintWriter out;
    private BufferedReader consoleInput;

    public OutputThread(OutputStream outputStream, BufferedReader consoleInput) {
        this.out = new PrintWriter(outputStream, true);
        this.consoleInput = consoleInput;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = consoleInput.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Erro ao enviar mensagem para o servidor: " + e.getMessage());
        }
    }
}
