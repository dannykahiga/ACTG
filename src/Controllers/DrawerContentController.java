package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author loyal
 */
public class DrawerContentController implements Initializable {

    public static Pane p_add_details;
    public static Pane p_timetable_optns;
    public static Pane p_database;
    public static AnchorPane pane_add_dtls;
    public static AnchorPane pane_timetable_options;
    public static AnchorPane pane_db;
    @FXML
    private JFXButton button_add_details;
    @FXML
    private JFXButton button_timetable_optns;
    @FXML
    private Pane pane_add_details;
    @FXML
    private Pane pane_timetable_optns;
    @FXML
    private Pane pane_database;
    @FXML
    private JFXButton button_database;
    @FXML
    private JFXButton button_exit_app;
    @FXML
    private Pane pane_quit_app;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p_add_details = pane_add_details;
        p_timetable_optns = pane_timetable_optns;
        p_database = pane_database;
    }

    @FXML
    private void addDetails(ActionEvent event) throws IOException {
        pane_add_details.setVisible(true);
        pane_timetable_optns.setVisible(false);
        pane_database.setVisible(false);
        pane_add_dtls = FXMLLoader.load(getClass().getResource("/User_Interface/Add_Details.fxml"));
        Main_PageController.bp_main_page.setCenter(pane_add_dtls);
        Main_PageController.l_spacer.setVisible(true);
        Main_PageController.l_service_type.setText("Add Details");
    }

    @FXML
    private void timetableOptions(ActionEvent event) throws IOException {
        pane_add_details.setVisible(false);
        pane_timetable_optns.setVisible(true);
        pane_database.setVisible(false);
        pane_timetable_options = FXMLLoader.load(getClass().getResource("/User_Interface/Timetable_Options.fxml"));
        Main_PageController.bp_main_page.setCenter(pane_timetable_options);
        Main_PageController.l_spacer.setVisible(true);
        Main_PageController.l_service_type.setText("Timetable Options");
    }

    @FXML
    private void go_to_database(ActionEvent event) throws IOException {
        pane_add_details.setVisible(false);
        pane_timetable_optns.setVisible(false);
        pane_database.setVisible(true);
        pane_db = FXMLLoader.load(getClass().getResource("/User_Interface/Database.fxml"));
        Main_PageController.bp_main_page.setCenter(pane_db);
        Main_PageController.l_spacer.setVisible(true);
        Main_PageController.l_service_type.setText("Database");
    }

    @FXML
    void quit_app(ActionEvent event) {
        Confirmation_Notification_Window_Controller.label_info.setText("Are you sure you want to quit?");
        Main_PageController.dialogConfirm.show();
    }

}
