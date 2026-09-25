package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Add_Course_Controller implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField textfield_id;

    @FXML
    private JFXTextField textfield_name;

    public static JFXTextField xTextField_name;

    @FXML
    private JFXTextField textfield_code;

    @FXML
    private JFXTextField textfield_nos;

    @FXML
    private JFXTextField textfield_nou;

    @FXML
    private JFXButton button_add_units;

    @FXML
    private JFXComboBox jfxcombobox_semester;

    @FXML
    private JFXComboBox jfxcombox_department;

    @FXML
    private JFXComboBox jfxcombobox_Yr_of_study;

    @FXML
    private JFXComboBox jfxcombobox_academic_Yr;

    @FXML
    private JFXButton button_save;

    @FXML
    private JFXButton button_cancel;

    ObservableList ol;
    List list;

    JFXSnackbar jFXSnackbarSuccess;
    JFXSnackbar jFXSnackbarError;
    JFXSnackbar jFXSnackbarWarning;

    ObservableList departments;

    ObservableList academic_year;
    ObservableList year_of_study;
    ObservableList semester;

    ResultSet resultSet;
    ResultSet resultSet1;

    Database_Handler database_handler;
    @FXML
    private BorderPane mainpane;

    public static class HboxCell extends HBox {

        Pane root2;

        HboxCell(String text) throws IOException {
            super();
            this.root2 = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Unit.fxml"));
            this.getChildren().addAll(root2);
        }
    }

    static JFXDialog dialog = new JFXDialog();
    JFXDialogLayout layout = new JFXDialogLayout();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        xTextField_name = textfield_name;

        database_handler = Database_Handler.getInstance();

        jFXSnackbarError = new JFXSnackbar(stackPane);
        jFXSnackbarSuccess = new JFXSnackbar(stackPane);
        jFXSnackbarWarning = new JFXSnackbar(stackPane);

        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        jFXSnackbarError.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());
        jFXSnackbarSuccess.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());

        stackPane.getStylesheets().add(getClass().getResource("/CSS/snackbarError.css").toExternalForm());

        try {
            departments = FXCollections.observableArrayList();
            String sql = "SELECT * FROM Department";
            resultSet = database_handler.execQuery(sql);
            while (resultSet.next()) {
                String department = resultSet.getString("Name");
                departments.add(department);
            }

            academic_year = FXCollections.observableArrayList("1", "2", "3", "4");
            year_of_study = FXCollections.observableArrayList();
            semester = FXCollections.observableArrayList("One", "Two");

            String sql2 = "SELECT * FROM Tuition_Details";
            resultSet1 = database_handler.execQuery(sql2);
            while (resultSet1.next()) {
//                String academic_yr = resultSet1.getString("Academic_Year");
                String yr_of_study = resultSet1.getString("Year_Of_Study");
//                String Semester = resultSet1.getString("Semester");

//                academic_year.add(academic_yr);
                year_of_study.add(yr_of_study);
//                semester.add(Semester);

            }

            jfxcombox_department.setItems(departments);
            jfxcombobox_academic_Yr.setItems(academic_year);
            jfxcombobox_Yr_of_study.setItems(year_of_study);
            jfxcombobox_semester.setItems(semester);
        } catch (SQLException ex) {

        }

    }

    @FXML
    void Add_Units(ActionEvent event) throws IOException {
        int i;
        int NOU = Integer.valueOf(textfield_nou.getText());
        for (i = 1; i <= NOU; i++) {
            System.out.println(i);
        }

        dialog.setTransitionType(JFXDialog.DialogTransition.RIGHT);
        layout.setStyle("-fx-background-color: #009688");
        dialog.setDialogContainer(stackPane);
        Label labelHeading = new Label("COURSE UNITS");
        labelHeading.setStyle("-fx-text-fill: #FFF");
        layout.setHeading(labelHeading);
        double width = stackPane.getWidth();
        double height = stackPane.getHeight();
        dialog.setPrefHeight(height);
        dialog.setPrefWidth(width);
        dialog.setContent(layout);
        Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/Unit.fxml"));

        //ADDING UNITS
        list = new ArrayList<>();
        for (int j = 1; j < i; j++) {
            list.add(new HboxCell("" + j));
        }

        ol = FXCollections.observableArrayList(list);
        Unit_Controller.listView.setItems(ol);

        layout.setBody(root);
        dialog.show();
    }

    @FXML
    void Cancel(ActionEvent event) {
        Add_DetailsController.dialogAdd_Courses.close();
    }

    @FXML
    void Save_Course(ActionEvent event) {

        String id = textfield_id.getText();
        String code = textfield_code.getText();
        String name = textfield_name.getText();
        String number_of_students = textfield_nos.getText();
       // String number_of_units = textfield_nou.getText();
        String department = jfxcombox_department.getValue().toString().toString();
        String semester = jfxcombobox_semester.getValue().toString().toString();
        String yr_of_study = jfxcombobox_Yr_of_study.getValue().toString().toString();
        String academic_yr = jfxcombobox_academic_Yr.getValue().toString().toString();

        Boolean flag = id.isEmpty() || code.isEmpty() || name.isEmpty() || number_of_students.isEmpty()|| department.isEmpty() || semester.isEmpty() || academic_yr.isEmpty() || yr_of_study.isEmpty();
        if (flag) {
            jFXSnackbarWarning.show("Please provide all information required", 3000);
            mainpane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        Boolean flag2 = id.startsWith("CRS-");

        if (!flag2) {
            jFXSnackbarWarning.show("All Lecturer ID'S should start with prefix 'CRS-'", 5000);
            mainpane.getStylesheets().add(getClass().getResource("/CSS/snackBarWarning.css").toExternalForm());
            return;
        }
        String query = "INSERT INTO Course VALUES("
                + "'" + id + "',"
                + "'" + code + "',"
                + "'" + name + "',"
                + "'" + number_of_students + "',"
                + "'" + department + "',"
                + "'" + semester + "',"
                + "'" + yr_of_study + "',"
                + "'" + academic_yr + "'"
                + ")";
        System.out.println(query);
        if (database_handler.executeAction(query)) {
            jFXSnackbarSuccess.show("Course " + name.toUpperCase() + " added successfully", 3000);
            mainpane.getStylesheets().add(getClass().getResource("/CSS/snackBarSuccess.css").toExternalForm());
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage("Failed Saving Data...");
            notification.showAndDismiss(Duration.seconds(1.5));
        }
    }
}
