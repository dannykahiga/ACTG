package Controllers;

import Database_Controller.Database_Handler;
import static Database_Controller.Database_Handler.database_handler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Add_Unit_Controller implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private JFXTextField unitIDtext;

    @FXML
    private JFXTextField unitNameID;

    @FXML
    private JFXTextField unitCode;

    @FXML
    private JFXComboBox unitLecturer;

    @FXML
    private JFXTextField numberOfStudents;

    @FXML
    private TextField lecturerID;

    @FXML
    private TextField textLecturer;

    @FXML
    private JFXButton buttonAddUnit;

    @FXML
    private Label label_unit_number;
    public static Label l_unit_number;

    private ObservableList lecturer;
    private ObservableList departmentList;
    private ObservableList lec_and_departmenList;

    Database_Handler database_Handler;

    private ResultSet resultSet;

    JFXSnackbar jFXSnackbarSuccess;
    JFXSnackbar jFXSnackbarError;
    JFXSnackbar jFXSnackbarWarning;
    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        database_handler = Database_Handler.getInstance();

        jFXSnackbarError = new JFXSnackbar(stackPane);
        jFXSnackbarSuccess = new JFXSnackbar(stackPane);
        jFXSnackbarWarning = new JFXSnackbar(stackPane);

        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        jFXSnackbarError.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());

        stackPane.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());

        l_unit_number = label_unit_number;

        try {

            database_Handler = Database_Handler.getInstance();

            lecturer = FXCollections.observableArrayList();
            departmentList = FXCollections.observableArrayList();
            lec_and_departmenList = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Lecturer";
            resultSet = database_handler.execQuery(sql);
            while (resultSet.next()) {
                String lecturer_name = resultSet.getString("Lecturer_Name");
                String department = resultSet.getString("Department");
                lecturer.add(lecturer_name);
                departmentList.add(department);

                String lec_and_dept = lecturer_name.concat(" (" + department + ")");
                lec_and_departmenList.add(lec_and_dept);
            }
            unitLecturer.setItems(lec_and_departmenList);
        } catch (SQLException ex) {
            Logger.getLogger(Add_Unit_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void addLecturer(ActionEvent event) {

    }

    @FXML
    void addUnit(ActionEvent event) {
        String id = unitIDtext.getText();
        String code = unitCode.getText();
        String name = unitNameID.getText();
        String number_of_students = numberOfStudents.getText();
        String course_name = Add_Course_Controller.xTextField_name.getText();
        String lecturer = unitLecturer.getValue().toString().toString();

        Boolean flag = id.isEmpty() || code.isEmpty() || name.isEmpty() || number_of_students.isEmpty() || lecturer.isEmpty();
        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        Boolean flag2 = id.startsWith("UNT-");

        if (!flag2) {
            jFXSnackbarWarning.show("All Unit ID'S should start with prefix 'UNT-'", 5000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());

        }
            String query = "INSERT INTO Unit VALUES("
                + "'" + id + "',"
                + "'" + code + "',"
                + "'" + name + "',"
                + "'" + number_of_students + "',"
                + "'" + course_name + "',"
                + "'" + lecturer + "'"
                + ")";
        System.out.println(query);
        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show("Unit " + name.toUpperCase() + " added successfully", 3000);
            mainPane.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage("Failed Saving Data...");
            notification.showAndDismiss(Duration.seconds(1.5));
        }
    }
}
