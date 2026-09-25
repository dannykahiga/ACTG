package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author loyal
 */
public class DatabaseController implements Initializable {

    public static JFXDialog dialog_current_departments = new JFXDialog();
    public static JFXDialog dialog_current_classrooms = new JFXDialog();
    public static JFXDialog dialog_current_lecturers = new JFXDialog();
    public JFXDialogLayout layout_current_deparments = new JFXDialogLayout();
    public JFXDialogLayout layout_current_classrooms = new JFXDialogLayout();
    public JFXDialogLayout layout_current_lecturers = new JFXDialogLayout();
    @FXML
    private StackPane stackpane_parent;
    @FXML
    private JFXButton button_departments;
    @FXML
    private JFXButton button_classrooms;
    @FXML
    private JFXButton button_lecturers;
    @FXML
    private JFXButton button_courses_units;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dialog_current_departments.setDialogContainer(stackpane_parent);
            dialog_current_departments.setOverlayClose(true);
            dialog_current_departments.setTransitionType(JFXDialog.DialogTransition.TOP);
            layout_current_deparments.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane root_current_departments = FXMLLoader.load(getClass().getResource("/User_Interface/Current_Departments.fxml"));
            layout_current_deparments.setBody(root_current_departments);
            dialog_current_departments.setContent(layout_current_deparments);

            dialog_current_classrooms.setDialogContainer(stackpane_parent);
            dialog_current_classrooms.setOverlayClose(true);
            dialog_current_classrooms.setTransitionType(JFXDialog.DialogTransition.TOP);
            layout_current_classrooms.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane root_current_classrooms = FXMLLoader.load(getClass().getResource("/User_Interface/Current_Classrooms.fxml"));
            layout_current_classrooms.setBody(root_current_classrooms);
            dialog_current_classrooms.setContent(layout_current_classrooms);

            dialog_current_lecturers.setDialogContainer(stackpane_parent);
            dialog_current_lecturers.setOverlayClose(true);
            dialog_current_lecturers.setTransitionType(JFXDialog.DialogTransition.TOP);
            layout_current_lecturers.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane root_current_lecturers = FXMLLoader.load(getClass().getResource("/User_Interface/Current_Lecturers.fxml"));
            layout_current_lecturers.setBody(root_current_lecturers);
            dialog_current_lecturers.setContent(layout_current_lecturers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void show_current_classrooms(ActionEvent event) {
        Main_PageController.label_service_type_extension.setText("Current Classrooms");
        dialog_current_classrooms.show();
    }

    @FXML
    void show_current_courses_and_units(ActionEvent event) {

    }

    @FXML
    void show_current_departments(ActionEvent event) {
        Main_PageController.label_service_type_extension.setText("Current Departments");
        dialog_current_departments.show();
    }

    @FXML
    void show_current_lecturers(ActionEvent event) {
        Main_PageController.label_service_type_extension.setText("Current Lecturers");
        dialog_current_lecturers.show();
    }

}
