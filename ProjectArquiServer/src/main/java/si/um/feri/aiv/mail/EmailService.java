package si.um.feri.aiv.mail;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Patient;

@Stateless
public class EmailService {

    @Resource(lookup = "java:jboss/mail/mymail")  // Matches what you set in WildFly
    private Session mailSession;

    public void sendConfirmationEmails(Doctor doctor, Patient patient) {
        sendEmail(doctor.getEmail(), "New Patient Assigned",
                "Patient " + patient.getEmail() + " has been assigned to you.");
        sendEmail(patient.getEmail(), "Doctor Selection Confirmed",
                "You have been assigned to Dr. " + doctor.getDoctorName());
    }

    public void sendFailureEmail(Patient patient, Doctor doctor) {
        sendEmail(patient.getEmail(), "Doctor Unavailable",
                "Dr. " + doctor.getDoctorName() + " cannot accept more patients.");
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent to: " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
