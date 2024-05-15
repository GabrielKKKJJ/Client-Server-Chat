package Client.Interface.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FXMLControllers {

    @FXML
    private ImageView sendBtn;

    @FXML
    private TextField userMessage;

    @FXML
    private TextFlow msgFlow;

    @FXML
    void Send_Message(ActionEvent event) {
        String message = userMessage.getText();
        if (!message.isEmpty()) {   
                
            addMessage(message);
            userMessage.clear();
            System.out.println(message);
        }
    }

    @FXML
    void addMessage(String message) {

        Text text = new Text(message);
        text.setStyle("-fx-font-family: Ubunto; -fx-text-fill: white; -fx-fill: white; -fx-font-size: 20px;");

        msgFlow.getChildren().add(text);
        msgFlow.getChildren().add(new Text("\n"));
    }

}
