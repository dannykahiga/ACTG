package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Success_Notification_Controller implements Initializable {
    public static Label label_info;
    @FXML
    public Label info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_info = info;
    }
}
