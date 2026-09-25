package Messaging;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Properties;

public class Send_Emails {
    private static ObservableList<String> observableList_emails = FXCollections.observableArrayList("dannykamau.dk@gmail.com", "kagumbajustus@gmail.com", "d.kahiga@yahoo.com");

    public static void send_multiple_emails() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        String sender_email = "actg8822@gmail.com";
        String subject = "Timetable Released";
        String message_composed = "Hello ";
        String password = "actg@2018";

        ArrayList recipientsArray = new ArrayList(observableList_emails);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(sender_email);
            message.setFrom(addressFrom);
            int sizeTo = recipientsArray.size();
            InternetAddress[] addressTo = new InternetAddress[sizeTo];
            for (int i = 0; i < sizeTo; i++) {
                addressTo[i] = new InternetAddress(recipientsArray.get(i).toString());
            }
            message.setRecipients(Message.RecipientType.BCC, addressTo);
            message.setSubject(subject);
            message.setText(message_composed);
            Transport.send(message);
            System.out.println("EMAIL MESSAGE SENT SUCCESSFULLY");

        } catch (Exception e) {
            System.out.println("EMAIL MESSAGE SENT SUCCESSFULLY");
        }
    }
}
