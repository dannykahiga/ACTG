package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Confirmation_Notification_Window_Controller implements Initializable {
    public static Label label_info;
    public static JFXButton fXButtonCancel;
    public static JFXButton fXButtonConfirm;
    @FXML
    private Label info;
    @FXML
    private JFXButton buttonCancel;
    @FXML
    private JFXButton buttonConfirm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_info = info;
        fXButtonCancel = buttonCancel;
        fXButtonConfirm = buttonConfirm;
    }

    @FXML
    void cancelOperation(ActionEvent event) {
        Main_PageController.dialogConfirm.close();
    }

    @FXML
    void confirmOperation(ActionEvent event) {
        System.exit(0);
    }
}
