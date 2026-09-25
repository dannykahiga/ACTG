package Controllers;

import Algorithm_Camp.Helper_Classes.Driver;
import com.jfoenix.controls.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author loyal
 */
public class Timetable_OptionsController implements Initializable {

    Driver driver = new Driver();
    @FXML
    private JFXButton button_generate_timetable;
    @FXML
    private JFXButton button_view_timetable;
    @FXML
    private HBox hbox_view_timetable;
    @FXML
    private StackPane stackpane_parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXButton button_entire_timetable = new JFXButton("Entire Timetable");
        button_entire_timetable.setButtonType(JFXButton.ButtonType.RAISED);
        button_entire_timetable.setPrefSize(238, 80);
        button_entire_timetable.setWrapText(true);
        button_entire_timetable.setStyle("-fx-background-color: #3f51b5;"
                + " -fx-text-fill: white;" + ""
                + "-fx-border-width: 3px;"
                + "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 10px;"
                + "-fx-border-color: white;"
                + "-fx-border-radius: 10px;"
                + "-fx-background-radius: 15px;");
        JFXButton button_filtered_timetable = new JFXButton("Filtered Timetable");
        button_filtered_timetable.setButtonType(JFXButton.ButtonType.RAISED);
        button_filtered_timetable.setPrefSize(238, 80);
        button_filtered_timetable.setWrapText(true);
        button_filtered_timetable.setStyle("-fx-background-color: #3f51b5;"
                + " -fx-text-fill: white;" + ""
                + "-fx-border-width: 3px;"
                + "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 10px;"
                + "-fx-border-color: white;"
                + "-fx-border-radius: 10px;"
                + "-fx-background-radius: 15px;");
        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(button_view_timetable);
        nodesList.addAnimatedNode(button_entire_timetable);
        nodesList.addAnimatedNode(button_filtered_timetable);
        hbox_view_timetable.getChildren().addAll(nodesList);

        //SETTING ACTIONS TO VIEW TIMETABLE
        button_entire_timetable.setOnAction(event -> {

            try {
                // ((Node) event.getSource()).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Entire_Timetable.fxml"));
                Stage stage = new Stage();
                JFXDecorator decorator = new JFXDecorator(stage, root, false, true, true);
                decorator.setCustomMaximize(true);
                Scene scene = new Scene(decorator);
                scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        button_filtered_timetable.setOnAction(event -> {
            try {
                // ((Node) event.getSource()).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Filter_Timetable.fxml"));
                Stage stage = new Stage();
                JFXDecorator decorator = new JFXDecorator(stage, root, false, false, true);
                decorator.setCustomMaximize(true);
                Scene scene = new Scene(decorator);
                scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void Generate_Timetable(ActionEvent event) throws SQLException {
        Main_PageController.dialogConfirm_generate_tt.show();
    }

}
