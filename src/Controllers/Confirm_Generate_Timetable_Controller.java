package Controllers;

import Algorithm_Camp.Helper_Classes.Driver;
import Messaging.Send_Emails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Confirm_Generate_Timetable_Controller implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private Label info;

    @FXML
    private JFXButton buttonCancel;

    @FXML
    private JFXButton buttonConfirm;

    public static JFXDialog dialogSuccess = new JFXDialog();
    public JFXDialogLayout layoutSuccess = new JFXDialogLayout();

    public static void showSuccessNotification() {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                dialogSuccess.show();
                Thread.sleep(5000);
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                dialogSuccess.close();
            }

        };
        new Thread(task).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Pane rootSuccess = null;
        try {
            dialogSuccess.setContent(layoutSuccess);
            dialogSuccess.setOverlayClose(false);
            dialogSuccess.setTransitionType(JFXDialog.DialogTransition.CENTER);
            layoutSuccess.setStyle("-fx-background-color: #2b8a2f");
            dialogSuccess.setDialogContainer(stackPane);
            layoutSuccess.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            rootSuccess = FXMLLoader.load(getClass().getResource("/User_Interface/Success_Notification.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        layoutSuccess.setBody(rootSuccess);
    }
    @FXML
    void cancelOperation(ActionEvent event) {
        Main_PageController.dialogConfirm_generate_tt.close();
    }

    @FXML
    void confirmOperation(ActionEvent event) {
        System.out.println("Generate");
       
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Driver.Generate_Timetable();

                Main_PageController.masker_pane.setVisible(true);
                return null;
            }
            @Override
            protected void succeeded() {
                super.succeeded();
                Main_PageController.closeGenerationNotification();
                Main_PageController.masker_pane.setVisible(false);
                Success_Notification_Controller.label_info.setText("Timetable Generated Successfully");
                showSuccessNotification();
                Send_Emails.send_multiple_emails();
            }
        };
        new Thread(task).start();
    }
}
