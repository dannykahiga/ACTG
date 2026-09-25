package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * @author loyalone
 */
public class Add_Tuition_Details_Controller implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXButton button_save;
    @FXML
    private JFXButton button_cancel;
    @FXML
    private JFXTextField jfxtextfield_id;
    @FXML
    private JFXTextField jfxtextfield_academic_yr;
    @FXML
    private JFXTextField jfxtextfield_semester;
    @FXML
    private JFXTextField jfxtextfield_yr_of_study;

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
    private void Save_Tuition_Details(ActionEvent event) {

        String id = jfxtextfield_id.getText();
        String academic_yr = jfxtextfield_academic_yr.getText();
        String year_of_study = jfxtextfield_yr_of_study.getText();
        String semester = jfxtextfield_semester.getText();

        Boolean flag = id.isEmpty() || academic_yr.isEmpty() || year_of_study.isEmpty() || semester.isEmpty();
        Boolean flag2 = id.startsWith("TUT-");

        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        if (!flag2) {
            jFXSnackbarWarning.show("All Tuition Details ID'S should start with prefix 'TUT-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        String query = "INSERT INTO Tuition_Details VALUES("
                + "'" + id + "',"
                + "'" + academic_yr + "',"
                + "'" + year_of_study + "',"
                + "'" + semester + "'"
                + ")";
        System.out.println(query);
        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show(academic_yr.toUpperCase() + " year details" + " added successfully", 3000);
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
        Add_DetailsController.dialogAdd_Tuition_Details.close();
    }

}
