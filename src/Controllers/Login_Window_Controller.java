package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author loyalone
 */
public class Login_Window_Controller implements Initializable {

    @FXML
    private JFXButton button_admin;
    @FXML
    private JFXButton button_lecturer;
    @FXML
    private JFXButton button_student;
    @FXML
    private BorderPane borderpane_main;
    @FXML
    private Pane pane_admin;
    @FXML
    private Pane pane_lecturer;
    @FXML
    private Pane pane_student;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void admin_login_page(ActionEvent event) throws IOException {
        pane_admin.setVisible(true);
        pane_lecturer.setVisible(false);
        pane_student.setVisible(false);
        Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Admin_Login.fxml"));
        borderpane_main.setCenter(root);
    }

    @FXML
    private void lecturer_login_page(ActionEvent event) throws IOException {
        pane_admin.setVisible(false);
        pane_lecturer.setVisible(true);
        pane_student.setVisible(false);
        Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Lecturer_Login.fxml"));
        borderpane_main.setCenter(root);
    }

    @FXML
    private void student_login_page(ActionEvent event) throws IOException {
        pane_admin.setVisible(false);
        pane_lecturer.setVisible(false);
        pane_student.setVisible(true);
        Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Student_Login.fxml"));
        borderpane_main.setCenter(root);
    }

}
