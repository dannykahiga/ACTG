package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author loyal
 */
public class Add_Lecturer_Controller implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXButton button_save;
    @FXML
    private JFXButton button_cancel;
    @FXML
    private JFXTextField textfield_id;

    @FXML
    private JFXTextField textfield_name;

    @FXML
    private JFXTextField textfield_postal_address;

    @FXML
    private JFXTextField textfield_email;

    @FXML
    private JFXTextField textfield_phone_number;

    @FXML
    private JFXComboBox combobox_department;

    Database_Handler database_handler;

    JFXSnackbar jFXSnackbarSuccess;
    JFXSnackbar jFXSnackbarError;
    JFXSnackbar jFXSnackbarWarning;

    ObservableList departments;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        database_handler = Database_Handler.getInstance();
        jFXSnackbarError = new JFXSnackbar(mainPane);
        jFXSnackbarSuccess = new JFXSnackbar(mainPane);
        jFXSnackbarWarning = new JFXSnackbar(mainPane);

        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        jFXSnackbarError.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());

        try {
            departments = FXCollections.observableArrayList();
            String sql = "select * from Department";
            ResultSet rs = database_handler.execQuery(sql);
            while (rs.next()) {
                departments.add(rs.getString("Name"));
            }
            combobox_department.setItems(departments);

        } catch (SQLException ex) {
            System.out.println("DEBUG ME IMMEDIATELY!!");
        }
    }

    @FXML
    private void Save_Details(ActionEvent event) {
        String id = textfield_id.getText();
        String name = textfield_name.getText();
        String address = textfield_postal_address.getText();
        String mobile = textfield_phone_number.getText();
        String email = textfield_email.getText();
        String department = combobox_department.getValue().toString().toString();

        Boolean flag = id.isEmpty() || name.isEmpty() || address.isEmpty() || mobile.isEmpty() || email.isEmpty() || department.isEmpty();
        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        Boolean flag2 = id.startsWith("LEC-");
        if (!flag2) {
            jFXSnackbarWarning.show("All Lecturer ID'S should start with prefix 'LEC-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        String query = "INSERT INTO Lecturer VALUES("
                + "'" + id + "',"
                + "'" + name + "',"
                + "'" + address + "',"
                + "'" + mobile + "',"
                + "'" + email + "',"
                + "'" + department + "'"
                + ")";
        System.out.println(query);
        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show("Lecturer " + name.toUpperCase() + " added successfully", 3000);
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
        Add_DetailsController.dialogAdd_Lecturer.close();
    }

}
