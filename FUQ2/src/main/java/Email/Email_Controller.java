/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Martynas
 */
@Stateless
@Named
public class Email_Controller {

    final private String username = "fuq.psk@gmail.com";
    final private String password = "pskFuturisticQual";
    private Properties props;
    private Session session;
    private MimeMessage generateMailMessage;

    public void setProperties() {

        props = new Properties();
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        session = Session.getDefaultInstance(props, null);
        

    }
    
    public void generateMessage() throws MessagingException {
        
        generateMailMessage = new MimeMessage(session);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("martynasbagdonas93@gmail.com"));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("gupijazaure@gmail.com"));
        generateMailMessage.setSubject("TEMA");
        String emailBody = "Testinis emailas " + "<br><br> Pagarbiai Futuristic Quality komanda <br> Martynas";
        generateMailMessage.setContent(emailBody, "text/html");
    }
    
    public void sendEmail() throws NoSuchProviderException, MessagingException{
        
        setProperties();
        generateMessage();
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "fuq.psk@gmail.com", "pskFuturisticQual");
	transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
	transport.close();
    }
}
