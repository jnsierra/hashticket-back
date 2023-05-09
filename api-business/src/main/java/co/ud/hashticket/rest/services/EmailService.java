package co.ud.hashticket.rest.services;

public interface EmailService {
    Boolean sendHtmlMessage(String to, String subject, String htmlBody);
}
