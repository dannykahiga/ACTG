package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * @author loyalone
 */
public class Lecturer_Registration_Controller implements Initializable {

    @FXML
    private ImageView imageview_photo;
    @FXML
    private JFXButton button_take_photo;
    @FXML
    private JFXButton button_upload_photo;
    @FXML
    private JFXButton button_choose_avator;
    @FXML
    private JFXTextField textfield_full_name;
    @FXML
    private JFXTextField textfiled_email;
    @FXML
    private JFXTextField textfield_reg_number;
    @FXML
    private JFXTextField textfield_username;
    @FXML
    private JFXPasswordField password_field_choose;
    @FXML
    private JFXPasswordField password_field_confirm;
    @FXML
    private JFXTextField textfield_password_hint;
    @FXML
    private JFXButton button_sign_up;

    private FileChooser fileChooser;
    private File file;
    private Desktop desktop = Desktop.getDesktop();
    private Image image;
    private FileInputStream fileInputStream;

    private PreparedStatement preparedStatement = Database_Handler.preparedStatement;
    private Connection connection = Database_Handler.connection;

    Database_Handler database_Handler;

    PopOver popOver_avatar_chooser = new PopOver();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        database_Handler = Database_Handler.getInstance();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
    }

    @FXML
    private void take_photo(ActionEvent event) {
    }

    @FXML
    private void upload_photo(ActionEvent event) {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = new Image(file.toURI().toString(), 100, 150, true, true);
            imageview_photo.setImage(image);
        }
    }

    @FXML
    private void choose_avator(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/Avatar_Holder.fxml"));
        popOver_avatar_chooser.setContentNode(root);
        popOver_avatar_chooser.setAnimated(true);
        popOver_avatar_chooser.setDetachable(false);
        popOver_avatar_chooser.setFadeInDuration(Duration.seconds(1.0));
        popOver_avatar_chooser.setFadeOutDuration(Duration.seconds(1.0));
        popOver_avatar_chooser.setCornerRadius(10);
        popOver_avatar_chooser.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        popOver_avatar_chooser.show(button_choose_avator);
        //Retrieving image from the database

//        String query = "SELECT * FROM Lecturer_Account";
//        ResultSet rs = database_Handler.execQuery(query);
//        while (rs.next()) {
//            InputStream inputStream = rs.getBinaryStream("Photo_Upload");
//            OutputStream outputStream = new FileOutputStream(new File("photo.jpg"));
//            byte[] content = new byte[1024];
//            int size = 0;
//            while ((size = inputStream.read(content)) != -1) {
//                outputStream.write(content, 0, size);
//            }
//            inputStream.close();
//            outputStream.close();
//            image = new Image("file:photo.jpg", 100, 150, true, true);
//            imageview_photo.setImage(image);
//        }
    }

    @FXML
    private void sign_up(ActionEvent event) {

        try {
            String full_name = textfield_full_name.getText();
            String email = textfiled_email.getText();
            String reg_no = textfield_reg_number.getText();
            String user_name = textfield_username.getText();
            String password_choose = password_field_choose.getText();
            String password_hint = textfield_password_hint.getText();
            String account_type = "Lecturer";

            String query = "insert into ACTGdb.User_Accounts(Reg_Number, Full_Name, Username, Email, Password, Password_Hint, Account_Type, Photo_Upload) values (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, reg_no);
            preparedStatement.setString(2, full_name);
            preparedStatement.setString(3, user_name);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password_choose);
            preparedStatement.setString(6, password_hint);
            preparedStatement.setString(7, account_type);

            fileInputStream = new FileInputStream(file);
            preparedStatement.setBinaryStream(8, (InputStream) fileInputStream, (int) file.length());

            preparedStatement.executeUpdate();

            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Data Saved Successfully...");
            notification.showAndDismiss(Duration.seconds(1.5));

        } catch (SQLException ex) {

            Logger.getLogger(Lecturer_Registration_Controller.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lecturer_Registration_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
