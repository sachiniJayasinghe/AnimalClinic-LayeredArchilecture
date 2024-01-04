package lk.ijse.animal_clinic.controller;


import javafx.scene.control.Alert;
import lk.ijse.animal_clinic.dto.AppointmentDto;
import lk.ijse.animal_clinic.dto.DoctorDto;
import lk.ijse.animal_clinic.dto.customerDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailForAppointment {
    public SendMailForAppointment(DoctorDto doctorDto, AppointmentDto appointmentDto, customerDto customerDto) {
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
            message.setFrom(new InternetAddress(customerDto.getEmail()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(customerDto.getEmail(),false)
            );
            message.setSubject("Hi "+customerDto.getName()+" Thank you for coming !");
            message.setText("Your Appointment ID  : "+appointmentDto.getApp_id()+"  \n Your Number is :"+appointmentDto.getNumber()+" \n Doctor Name is Dr: "+doctorDto.getName()+" \n Time to come: "+appointmentDto.getTime()+" \n Date : "+appointmentDto.getDate()+"\n Doctor Email : "+doctorDto.getEmail()+" \n Doctor Address : "+doctorDto.getAddress()+" \n Doctor Come in Time : "+doctorDto.getComeInTime()+" \n Doctor Come Out Time : "+doctorDto.getOutTime()+" \n Doctor ID : "+doctorDto.getId()+"\n Doctor Tel : "+doctorDto.getTel()+"\n Thank you for making an appointment with us. We look forward to seeing you soon.");

            Transport.send(message);
            new Alert(Alert.AlertType.CONFIRMATION, "The email was successfully sent!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
