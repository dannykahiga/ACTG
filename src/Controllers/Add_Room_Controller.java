package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.util.ResourceBundle;

public class Add_Room_Controller implements Initializable {
    @FXML
    private JFXTextField jfxtextfield_id;

    @FXML
    private JFXTextField jfxtextfield_number;

    @FXML
    private JFXTextField jfxtextfield_capacity;

    @FXML
    private JFXButton button_save;

    @FXML
    private JFXButton button_cancel;

    @FXML
    private BorderPane mainPane;

    Database_Handler database_handler;

    JFXSnackbar jFXSnackbarSuccess;
    JFXSnackbar jFXSnackbarError;
    JFXSnackbar jFXSnackbarWarning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        database_handler = Database_Handler.getInstance();
        jFXSnackbarError = new JFXSnackbar(mainPane);
        jFXSnackbarSuccess = new JFXSnackbar(mainPane);
        jFXSnackbarWarning = new JFXSnackbar(mainPane);

        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        jFXSnackbarError.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
    }

    @FXML
    void Cancel(ActionEvent event) {
        Add_DetailsController.dialogAdd_Room.close();
    }

    @FXML
    void Save_Room_Details(ActionEvent event) {
        String id = jfxtextfield_id.getText();
        String number = jfxtextfield_number.getText();
        int capacity = Integer.parseInt(jfxtextfield_capacity.getText());

        Boolean flag = id.isEmpty() || number.isEmpty();
        Boolean flag2 = id.startsWith("RM-");
        Boolean flag3 = number.startsWith("LH-");

        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        if (!flag2) {
            jFXSnackbarWarning.show("All Rooms ID'S should start with prefix 'RM-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }

        if (!flag3) {
            jFXSnackbarWarning.show("All Rooms Numbers should start with prefix 'LH-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }        String query = "INSERT INTO Room VALUES("
                + "'" + id + "',"
                + "'" + number + "',"
                + "'" + capacity + "'"
                + ")";
        System.out.println(query);
        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show("Room "+number.toUpperCase()+ " added successfully", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage("Failed Saving Data...");
            notification.showAndDismiss(Duration.seconds(1.5));
        }
    }
}