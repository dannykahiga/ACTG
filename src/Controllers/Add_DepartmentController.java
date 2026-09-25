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

/**
 * @author loyal
 */
public class Add_DepartmentController implements Initializable {

    @FXML
    private JFXButton button_save;
    @FXML
    private JFXButton button_cancel;
    @FXML
    private JFXTextField jfxtextfield_id;

    @FXML
    private JFXTextField jfxtextfield_name;
    @FXML
    private BorderPane mainPane;

    Database_Handler database_handler;

    JFXSnackbar jFXSnackbarSuccess;
    JFXSnackbar jFXSnackbarError;
    JFXSnackbar jFXSnackbarWarning;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        database_handler = Database_Handler.getInstance();
        jFXSnackbarError = new JFXSnackbar(mainPane);
        jFXSnackbarSuccess = new JFXSnackbar(mainPane);
        jFXSnackbarWarning = new JFXSnackbar(mainPane);

        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        jFXSnackbarError.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
    }

    @FXML
    private void Save_Department_Details(ActionEvent event) {
        String id = jfxtextfield_id.getText();
        String name = jfxtextfield_name.getText();

        Boolean flag = id.isEmpty() || name.isEmpty();
        Boolean flag2 = id.startsWith("DEP-");

        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        if (!flag2) {
            jFXSnackbarWarning.show("All Department ID'S should start with prefix 'DEP-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        String query = "INSERT INTO Department VALUES("
                + "'" + id + "',"
                + "'" + name + "'"
                + ")";
        System.out.println(query);
        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show(name.toUpperCase() + " department" + " added successfully", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage("Failed Saving Data...");
            notification.showAndDismiss(Duration.seconds(1.5));
        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        jfxtextfield_id.setText(null);
        jfxtextfield_name.setText(null);
        Add_DetailsController.dialogAdd_Department.close();
    }

}
