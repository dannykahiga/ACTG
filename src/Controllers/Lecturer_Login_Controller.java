package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 * @author loyalone
 */
public class Lecturer_Login_Controller implements Initializable {

    @FXML
    private JFXButton button_login;
    @FXML
    private JFXButton button_add_lec;

    PopOver popOver_new_user = new PopOver();
    @FXML
    private StackPane stackpane_parent;

    public static JFXDialog dialog_add_user = new JFXDialog();
    public JFXDialogLayout layout_add_user = new JFXDialogLayout();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            dialog_add_user.setDialogContainer(stackpane_parent);
//            dialog_add_user.setOverlayClose(true);
//            dialog_add_user.setTransitionType(JFXDialog.DialogTransition.LEFT);
//            layout_add_user.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
//            Pane root_add_user = FXMLLoader.load(getClass().getResource("/User_Interface/Lecturer_Registration.fxml"));
//            layout_add_user.setBody(root_add_user);
//            dialog_add_user.setContent(layout_add_user);
//        } catch (IOException ex) {
//            Logger.getLogger(Lecturer_Registration_Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void lec_login(ActionEvent event) {
    }
    
    PopOver popOver_Registration = new PopOver();
    @FXML
    private void add_lec(ActionEvent event) throws IOException {
//        dialog_add_user.show();
        Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/Lecturer_Registration.fxml"));
        popOver_Registration.setContentNode(root);
        popOver_Registration.setAnimated(true);
        popOver_Registration.setDetachable(false);
        popOver_Registration.setDetached(true);
        popOver_Registration.setTitle("Account Details");
        popOver_Registration.setFadeInDuration(Duration.seconds(1.0));
        popOver_Registration.setFadeOutDuration(Duration.seconds(1.0));
        popOver_Registration.setCornerRadius(10);
        popOver_Registration.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
        popOver_Registration.show(button_add_lec);
    }

}
