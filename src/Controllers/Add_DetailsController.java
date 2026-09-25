package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author loyal
 */
public class Add_DetailsController implements Initializable {

    //DIALOGS
    public static JFXDialog dialogAdd_Department = new JFXDialog();
    public static JFXDialog dialogAdd_Lecturer = new JFXDialog();
    public static JFXDialog dialogAdd_Courses = new JFXDialog();
    public static JFXDialog dialogAdd_Room = new JFXDialog();
    public static JFXDialog dialogAdd_Time = new JFXDialog();
    public static JFXDialog dialogAdd_Tuition_Details = new JFXDialog();

    //DIALOG LAYOUTS
    public JFXDialogLayout layout_add_department;
    public JFXDialogLayout layout_add_lecturer;
    public JFXDialogLayout layout_add_time;
    public JFXDialogLayout layout_add_courses;
    public JFXDialogLayout layout_add_room;
    public JFXDialogLayout layout_add_tuition_details;

    @FXML
    private JFXButton button_add_tuition_basics;
    @FXML
    private JFXButton button_add_department;
    @FXML
    private JFXButton button_add_lecturer;
    @FXML
    private JFXButton button_add_class;
    @FXML
    private JFXButton button_add_time;
    @FXML
    private JFXButton add_subjects;
    @FXML
    private StackPane stackpane_parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        layout_add_department = new JFXDialogLayout();
        layout_add_lecturer = new JFXDialogLayout();
        layout_add_room = new JFXDialogLayout();
        layout_add_time = new JFXDialogLayout();
        layout_add_courses = new JFXDialogLayout();
        layout_add_tuition_details = new JFXDialogLayout();

        try {

            //DIALOG SHOWING ADDING DEPARTMENT CONSOLE
            dialogAdd_Department.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogAdd_Department.setOverlayClose(false);
            dialogAdd_Department.setDialogContainer(stackpane_parent);
            Label labelHeading = new Label("Add Department");
            labelHeading.setStyle("-fx-text-fill: #FFF;");
            layout_add_department.setHeading(labelHeading);
            layout_add_department.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Department.fxml"));
            layout_add_department.setBody(root);
            dialogAdd_Department.setContent(layout_add_department);

            //DIALOG SHOWING ADDING LECTURER CONSOLE
            dialogAdd_Lecturer.setContent(layout_add_lecturer);
            dialogAdd_Lecturer.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogAdd_Lecturer.setOverlayClose(false);
            dialogAdd_Lecturer.setDialogContainer(stackpane_parent);
            Label label_heading_lecturer = new Label("Add Lecturer");
            label_heading_lecturer.setStyle("-fx-text-fill: #FFF;");
            layout_add_lecturer.setHeading(label_heading_lecturer);
            layout_add_lecturer.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane pane_lecturer = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Lecturer.fxml"));
            layout_add_lecturer.setBody(pane_lecturer);

            //DIALOG SHOWING ADDING LECTURE HALL CONSOLE
            dialogAdd_Room.setContent(layout_add_room);
            dialogAdd_Room.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogAdd_Room.setOverlayClose(false);
            dialogAdd_Room.setDialogContainer(stackpane_parent);
            Label label_heading_room = new Label("Add Lecture Hall");
            label_heading_room.setStyle("-fx-text-fill: #FFF;");
            layout_add_room.setHeading(label_heading_room);
            layout_add_room.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane pane_room = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Room.fxml"));
            layout_add_room.setBody(pane_room);

            //DIALOG SHOWING ADDING TIME CONSOLE
            dialogAdd_Time.setContent(layout_add_time);
            dialogAdd_Time.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogAdd_Time.setOverlayClose(false);
            dialogAdd_Time.setDialogContainer(stackpane_parent);
            Label label_heading_time = new Label("Add Meeting Time");
            label_heading_time.setStyle("-fx-text-fill: #FFF;");
            layout_add_time.setHeading(label_heading_time);
            layout_add_time.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane pane_time = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Time.fxml"));
            layout_add_time.setBody(pane_time);

            //DIALOG SHOWING ADDING COURSE CONSOLE
            dialogAdd_Courses.setContent(layout_add_courses);
            dialogAdd_Courses.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogAdd_Courses.setOverlayClose(false);
            dialogAdd_Courses.setDialogContainer(stackpane_parent);
            Label label_heading_courses = new Label("Add Course");
            label_heading_courses.setStyle("-fx-text-fill: #FFF;");
            layout_add_courses.setHeading(label_heading_courses);
            layout_add_courses.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane pane_courses = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Course.fxml"));
            layout_add_courses.setBody(pane_courses);

            //DIALOG SHOWING ADDING TUITION DETAILS CONSOLE
            dialogAdd_Tuition_Details.setContent(layout_add_tuition_details);
            dialogAdd_Tuition_Details.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogAdd_Tuition_Details.setOverlayClose(false);
            dialogAdd_Tuition_Details.setDialogContainer(stackpane_parent);
            Label label_heading_tuition_details = new Label("Add Tuition Details");
            label_heading_tuition_details.setStyle("-fx-text-fill: #FFF;");
            layout_add_tuition_details.setHeading(label_heading_tuition_details);
            layout_add_tuition_details.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane pane_tuition_details = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Tuition_Details.fxml"));
            layout_add_tuition_details.setBody(pane_tuition_details);

        } catch (IOException ex) {
            Logger.getLogger(Add_DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void add_courses(ActionEvent event) {
        dialogAdd_Courses.show();
    }

    @FXML
    void add_department(ActionEvent event) {
        dialogAdd_Department.show();
    }

    @FXML
    void add_lecture_hall(ActionEvent event) {
        dialogAdd_Room.show();
    }

    @FXML
    void add_lecturer(ActionEvent event) {
        dialogAdd_Lecturer.show();
    }

    @FXML
    void add_time(ActionEvent event) {
        dialogAdd_Time.show();
    }

    @FXML
    void add_tuition_basics(ActionEvent event) {
        dialogAdd_Tuition_Details.show();
    }
}
