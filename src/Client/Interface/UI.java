package Client.Interface;

import java.io.IOException;
import Client.Interface.Controllers.FXMLControllers;
import Client.core.ClientService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UI extends Application {

    private FXMLControllers interfaceController;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Carrega a interface gráfica
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Client/Interface/Window/window.fxml"));
        Parent root = loader.load();
    
        // Obtém o controlador da interface gráfica
        interfaceController = loader.getController();
        
        // Cria e inicia o serviço do cliente
        ClientService clientService = new ClientService(interfaceController);
        clientService.start();
    
        // Cria a cena e define a janela principal
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
