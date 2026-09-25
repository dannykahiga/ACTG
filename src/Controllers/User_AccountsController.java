package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author loyal
 */
public class User_AccountsController implements Initializable {

    @FXML
    private Label label_account_name;
    @FXML
    private JFXButton button_logout;
    @FXML
    private ImageView user_imageview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Log_Out(ActionEvent event) {
    }

}
