package Client.Interface.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import Client.core.ConnectionHandler;
import java.util.ArrayList;

public class InterfaceController {

    @FXML
    private ImageView sendBtn;

    @FXML
    private TextField userMessage;

    @FXML
    private TextFlow msgFlow;

    @FXML
    private Label userLabel;

    @FXML
    private ListView<String> userList;

    private ConnectionHandler connectionHandler;

    private int messageCount = 0;

    public void setUserLabel(String userLabel) {
        this.userLabel.setText(userLabel);
    }

    public void setClientConnection(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void updateUsersList(ArrayList<String> users) {
        if(users != null) {
            ObservableList<String> userList = FXCollections.observableArrayList(users);
            System.out.println(userList);
            this.userList.setItems(userList);
        }
    }

    @FXML
    void Send_Message(ActionEvent event) {
        String message = userMessage.getText();

        // Primeira msg de login
        if (messageCount == 0 && !message.isEmpty()) {
            
            userMessage.clear();
            messateToServer(message);
            addMessage("Nome definido com sucesso!", false);
            messageCount++;
            connectionHandler.setUsername(message);
            setUserLabel(message);
        }
        else if (!message.isEmpty()) {   
            userMessage.clear();
            messateToServer(message);
            addMessage(connectionHandler.getUsername() + ": " + message, true);
        }
    }

    private void messateToServer(String message) {
        connectionHandler.sendMessage(message);
    }

    public void ui_sendMessage(String message) {
        Platform.runLater(() -> addMessage(message));
    }


    void addMessage(String message) {
        addMessage(message, false);
    }

    @FXML
    void addMessage(String message, Boolean isMine) {
        try {
            Text text = new Text(message);

            if (isMine) {
                text.setStyle("-fx-font-family: Ubunto; -fx-text-fill: green; -fx-fill: green; -fx-font-size: 20px;");
            } else {
                text.setStyle("-fx-font-family: Ubunto; -fx-text-fill: blue; -fx-fill: blue; -fx-font-size: 20px;");
            }
    
            msgFlow.getChildren().add(text);
            msgFlow.getChildren().add(new Text("\n"));
        } catch (Exception e) {
            e.printStackTrace();    
        }
    }


}
