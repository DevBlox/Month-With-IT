package com.tieto.it2014.domain.util;

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by mantas on 28/08/14.
 */
public class MailSender {

    private MailSender() {
    }

    public static void send(String email, String subject, String message) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        try {
            msg.setFrom(new InternetAddress("no-reply@tietocamp.eu"));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

            msg.setSubject(subject);
            msg.setText(message, "utf-8");
            msg.setSentDate(new Date());

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

            t.connect("smtp.gmail.com", "mantas.jonytis@tietocamp.eu", "mantas12345");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();

        } catch (Exception e) {

        }
    }
}
