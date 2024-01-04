package lk.ijse.animal_clinic.controller;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class sendMail {
    public sendMail(String name, String appId, LocalDate now, LocalTime now1, double total, String email) {
        final String username = "akuressaanimalclinic@gmail.com";

        final String password = "rxbk rjax gfpp nugn";



        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email,false)
            );
            message.setSubject("Hi "+name+" Thank you for coming !");
            message.setText("Your Appointment ID  : "+appId+"  \n Time :"+now1+" \n Date : "+now+" \nTotal RS: "+total+" \ncome back Thank You !");

            Transport.send(message);
            new Alert(Alert.AlertType.CONFIRMATION, "The email was successfully sent!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
