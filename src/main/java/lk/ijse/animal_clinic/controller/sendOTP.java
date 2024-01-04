package lk.ijse.animal_clinic.controller;


import javafx.scene.control.Alert;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class sendOTP {

    public static OTPFormController otpFormController = new OTPFormController();
    public static void sendOtp(String name,String email){

        Random rand = new Random();

        int randNum = rand.nextInt(100001);

        otpFormController.randNum(randNum);


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
            message.setSubject("Hi "+name+" your OTP ");
            message.setText("OTP : "+randNum+"");

            Transport.send(message);
            new Alert(Alert.AlertType.CONFIRMATION, "OTP SENDED").show();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
