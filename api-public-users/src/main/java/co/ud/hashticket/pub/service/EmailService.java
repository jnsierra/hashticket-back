package co.ud.hashticket.pub.service;

public interface EmailService {
    boolean sendSimpleMessage(String to, String subject, String text);
}
