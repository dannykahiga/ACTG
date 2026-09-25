package MainClass;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author loyal
 */
public class MainClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new Thread(() -> {
            Database_Handler.getInstance();
        }).start();

        Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Login_Window.fxml"));
        JFXDecorator decorator = new JFXDecorator(stage, root, true, true, true);
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
        stage.setTitle("ACTG");
        stage.setScene(scene);
        stage.show();
    }

}
