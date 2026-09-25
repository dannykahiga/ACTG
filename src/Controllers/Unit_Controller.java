package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Unit_Controller implements Initializable {

    public ListView<?> listviewUnits;
    static ListView listView;

    @FXML
    public TextField departmentText;
    static TextField textFieldDepartment;

    @FXML
    public TextField textFieldCourse;
    static TextField textFieldCourseField;
    @FXML
    public TextField textNumberOfStudents;
    static TextField textFieldNOS;

    @FXML
    private JFXButton completeAddingUnits;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView = listviewUnits;
        textFieldDepartment = departmentText;
        textFieldCourseField = textFieldCourse;
        textFieldNOS = textNumberOfStudents;
    }
    @FXML
    void goBackToAddingCourse(ActionEvent event) {
        Add_Course_Controller.dialog.close();
    }
}
