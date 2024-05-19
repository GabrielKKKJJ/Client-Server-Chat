package Client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import Client.Interface.Controllers.FXMLControllers;

public class InputThread extends Thread {
    private BufferedReader in;
    private FXMLControllers interfaceController;

    public InputThread(InputStream inputStream, FXMLControllers interfaceController) {
        this.in = new BufferedReader(new InputStreamReader(inputStream));
        this.interfaceController = interfaceController;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                interfaceController.ui_sendMessage(message);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler mensagem do servidor: " + e.getMessage());
        }
    }
}
