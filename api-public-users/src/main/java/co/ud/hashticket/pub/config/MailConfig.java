package co.ud.hashticket.pub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${mail.configuration.user}")
    private String userEmail;
    @Value("${mail.configuration.password}")
    private String passwordEmail;
    @Bean
    public JavaMailSender getJavaMailSender() {
        System.out.println("Llego 02");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.zoho.com");
        mailSender.setPort(465);
        mailSender.setUsername(userEmail);
        mailSender.setPassword(passwordEmail);

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.smtp.host", "smtp.zoho.com");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug.auth", "true");
        properties.setProperty( "mail.pop3.socketFactory.fallback", "false");

        return mailSender;
    }
}
