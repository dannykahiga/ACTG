package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 * @author loyalone
 */
public class Student_Login_Controller implements Initializable {

    @FXML
    private JFXButton button_add_student;
    @FXML
    private JFXButton button_login;

    PopOver popOver_new_user = new PopOver();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void add_student(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/Student_Registration.fxml"));
            popOver_new_user.setContentNode(root);
            popOver_new_user.setAnimated(true);
            popOver_new_user.setDetachable(false);
            popOver_new_user.setFadeInDuration(Duration.seconds(1.0));
            popOver_new_user.setFadeOutDuration(Duration.seconds(1.0));
            popOver_new_user.setCornerRadius(10);
            popOver_new_user.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
            popOver_new_user.show(button_add_student);
        } catch (IOException ex) {
            Logger.getLogger(Student_Login_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void student_login(ActionEvent event) {
    }

}
