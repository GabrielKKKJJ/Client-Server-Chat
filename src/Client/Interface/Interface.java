package Client.Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Client/Interface/Window/window.fxml"));
        Parent root = loader.load();

        // Configurar o palco
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
