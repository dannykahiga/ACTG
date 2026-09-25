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

public class Admin_Login_Controller implements Initializable {

    @FXML
    private JFXButton button_login;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void admin_login(ActionEvent event) {
                Stage stage = new Stage();
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Main_Page.fxml"));
            JFXDecorator decorator = new JFXDecorator(stage, root, true, true, true);
            decorator.setCustomMaximize(true);
            Scene scene = new Scene(decorator);
            scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
            stage.setTitle("ACTG");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_Selection_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
