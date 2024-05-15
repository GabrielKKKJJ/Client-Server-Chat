package Client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputThread extends Thread {
    private BufferedReader in;

    public InputThread(InputStream inputStream) {
        this.in = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler mensagem do servidor: " + e.getMessage());
        }
    }
}
