package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Add_Time_Controller implements Initializable {

    @FXML
    private JFXTextField jfxtextfield_id;

    @FXML
    private HBox timepicker_to;

    @FXML
    private JFXComboBox jfxcombo_box_day;

    @FXML
    private JFXButton button_save;

    @FXML
    private JFXButton button_cancel;

    @FXML
    private BorderPane mainPane;

    Database_Handler database_handler;
    private ResultSet resultSet;

    JFXSnackbar jFXSnackbarSuccess;
    JFXSnackbar jFXSnackbarError;
    JFXSnackbar jFXSnackbarWarning;

    private ObservableList listDays = FXCollections.observableArrayList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY");
    @FXML
    private JFXDatePicker time_picker_from;
    @FXML
    private JFXDatePicker time_picker_to;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jfxcombo_box_day.setItems(listDays);

        database_handler = Database_Handler.getInstance();

        jFXSnackbarError = new JFXSnackbar(mainPane);
        jFXSnackbarSuccess = new JFXSnackbar(mainPane);
        jFXSnackbarWarning = new JFXSnackbar(mainPane);

        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        jFXSnackbarError.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());

        mainPane.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
    }

    @FXML
    void Cancel(ActionEvent event) {
        Add_DetailsController.dialogAdd_Time.close();
    }

    @FXML
    void Save_Time_Details(ActionEvent event) throws SQLException {

        String id = jfxtextfield_id.getText();
        String time_from = time_picker_from.getTime().toString();
        String day = jfxcombo_box_day.getValue().toString();
        String time_to = time_picker_to.getTime().toString();
        String time = day + " (" + time_from + " - " + time_to + ")";

        Boolean flag = id.isEmpty() || time.isEmpty();

        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        Boolean flag2 = id.startsWith("TM-");

        if (!flag2) {
            jFXSnackbarWarning.show("All Meeting_Time ID'S should start with prefix 'TM-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }

        System.out.println(time);
        String query = "INSERT INTO Meeting_Time VALUES("
                + "'" + id + "',"
                + "'" + time + "'"
                + ")";

        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show("Time " + time.toUpperCase() + " added successfully", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage("Failed Saving Data...");
            notification.showAndDismiss(Duration.seconds(1.5));
        }

    }
}
