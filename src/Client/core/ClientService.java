package Client.core;

import java.net.Socket;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import Client.Interface.Controllers.FXMLControllers;

public class ClientService extends Service<Void> {

    private FXMLControllers interfaceController;

    public ClientService(FXMLControllers interfaceController) {
        this.interfaceController = interfaceController;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                connectToServer();
                return null;
            }
        };
    }

    private void connectToServer() {
        try {
            // Cria o socket
            Socket socket = new Socket("localhost", 12345);
            ConnectionHandler connectionHandler = new ConnectionHandler(socket);
            interfaceController.setClientConnection(connectionHandler);

            // Envia a mensagem de login
            String loginMsg = connectionHandler.receiveMessage();
            interfaceController.ui_sendMessage(loginMsg);

            //interfaceController.updateUsersList(connectionHandler.receiveClientList());

            // Cria e inicia as threads de entrada
            InputThread inputThread = new InputThread(connectionHandler.getSocket().getInputStream(), interfaceController);

            inputThread.start();

            // Aguarda as threads terminarem
            try {
                inputThread.join();
            } catch (InterruptedException e) {
                System.err.println("Erro ao aguardar término das threads: " + e.getMessage());
            }

            // Fecha recursos
            socket.close();
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
}
