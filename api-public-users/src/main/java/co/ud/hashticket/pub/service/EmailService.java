package co.ud.hashticket.pub.service;

public interface EmailService {
    Boolean sendSimpleMessage(String to, String subject, String text);
}
