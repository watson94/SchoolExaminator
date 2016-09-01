package com.db.schooolexaminator.server.mailsender;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendEmail {

    final String senderEmailID = "watson9494@gmail.com";
    final String senderPassword = "";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmailID = "maxim.usmanov23";
    static String emailSubject = "Test Mail";
    static String emailBody = ":)";
    public SendEmail(String receiverEmailID, String emailSubject, String emailBody)
    {
        this.receiverEmailID=receiverEmailID;
        this.emailSubject=emailSubject;
        this.emailBody=emailBody;
        Properties props = new Properties();
        props.put("mail.smtp.user",senderEmailID);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        SecurityManager security = System.getSecurityManager();
        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmailID));
            Transport.send(msg);
            System.out.println("Message send Successfully:)");
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
    }
    public class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }
    public static void main(String[] args) {
        SendEmail mailSender;
        mailSender = new SendEmail("maxim.usmanov23@gmail.com","Testing Code 2 example","Testing Code Body yess");

    }

}