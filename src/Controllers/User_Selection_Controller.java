package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User_Selection_Controller implements Initializable {

    @FXML
    private JFXButton button_student;
    @FXML
    private JFXButton button_admin;
    @FXML
    private JFXButton button_lecturer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void show_student_login(ActionEvent event) {
        Stage stage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Student_Login.fxml"));
            JFXDecorator decorator = new JFXDecorator(stage, root, false, false, false);
            decorator.setCustomMaximize(true);
            Scene scene = new Scene(decorator);
            scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
            stage.setTitle("ACTG");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_Selection_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show_admin_login(ActionEvent event) {
        Stage stage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Admin_Login.fxml"));
            JFXDecorator decorator = new JFXDecorator(stage, root, false, false, false);
            decorator.setCustomMaximize(true);
            Scene scene = new Scene(decorator);
            scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
            stage.setTitle("ACTG");
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_Selection_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show_lecturer_login(ActionEvent event) {
        Stage stage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Lecturer_Login.fxml"));
            JFXDecorator decorator = new JFXDecorator(stage, root, false, false, false);
            decorator.setCustomMaximize(true);
            Scene scene = new Scene(decorator);
            scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
            stage.setTitle("ACTG");
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_Selection_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
