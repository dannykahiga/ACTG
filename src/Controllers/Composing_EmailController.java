package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Composing_EmailController implements Initializable {

    @FXML
    private JFXTextField textfield_senders_email;
    @FXML
    private JFXTextField textfield_reciver_email;
    @FXML
    private JFXTextField textfield_subject;
    @FXML
    private JFXTextArea text_area_message;
    @FXML
    private JFXButton button_send;
    @FXML
    private JFXPasswordField password_field;
    
    Hyperlink hyperlink =  new Hyperlink("https://www.google.com/settings/security/lesssecureapps");

    public String sendMail(String Email, String Password, String ToEmail, String Subject, String Text) {

        String Msg;

        final String username = Email;
        final String password = Password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Email));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ToEmail));//u will send to
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);
            Msg = "true";

            return Msg;
        } catch (Exception e) {
            return e.toString();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void send_mail(ActionEvent event) {
        String sender_email = textfield_senders_email.getText();
        String password = password_field.getText();
        String reciver_email = textfield_reciver_email.getText();
        String subject = textfield_subject.getText();
        String message = text_area_message.getText();

        String Data = new Composing_EmailController().sendMail(sender_email, password, reciver_email, subject, message);

        System.out.println(Data);

        if (Data.equals("true")) {

            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Email sent Successfully...");
            notification.showAndDismiss(Duration.seconds(1.5));

        } else {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage(hyperlink.toString());
            notification.showAndDismiss(Duration.seconds(1.5));
        }
    }

}
