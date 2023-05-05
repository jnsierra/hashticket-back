package co.ud.hashticket.rest.services;

import javax.mail.MessagingException;

public interface EmailService {
    boolean sendHtmlMessage(String to, String subject, String htmlBody)throws MessagingException;
}
