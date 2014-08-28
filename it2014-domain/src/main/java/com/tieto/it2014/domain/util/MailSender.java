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

    private static String username;
    private static String token;
    private static String email;
    private static String html;

    private static final String IP_ADDRESS = "http://192.168.16.7";
    private static final String PORT = "8081";

    private MailSender() {
    }

    public static void send(String email, String subject, String username, String token) {
        MailSender.username = username;
        MailSender.token = token;
        MailSender.email = email;
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
            html = "<html>"
                    + "<body>"
                    + "<div style=\"background-color: #fff; border: 1px solid"
                    + "transparent; border-radius: 4px; box-shadow: 0 1px 1px "
                    + "rgba(0, 0, 0, 0.05); margin-bottom: 20px; border-color: #bce8f1;\">"
                    + "<div style=\"border-bottom: 1px solid transparent;"
                    + " border-top-left-radius: 3px; border-top-right-radius: 3px; padding: 10px 15px; background-color: #d9edf7; border-color: #bce8f1; color: #31708f;\">Confirm your registration</div>"
                    + "<div style=\"padding: 15px;\">"
                    + "<p>Hello, " + username + "</p>"
                    + "<br/>"
                    + "<p>Thank you for registering at IRun, to confirm your "
                    + "registration please click this <a href=\""
                    + IP_ADDRESS + ":"
                    + PORT
                    + "/IRun/activate/" + email + "/" + token + "\">link</a>.</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            msg.setContent(html, "text/html; charset=utf-8");
            msg.setSentDate(new Date());

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

            t.connect("smtp.gmail.com", "mantas.jonytis@tietocamp.eu", "mantas12345");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();

        } catch (Exception e) {
        }
    }
}
