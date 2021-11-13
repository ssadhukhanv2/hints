package com.ssadhukhanv2.hints.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    /*
     * This way of configuring emails is copied from here
     * https://www.baeldung.com/spring-email
     *
     * However, Google DOESN'T"T ALLOW emails to be sent from less secure apps.
     * Please set Allow less secure apps: ON using the below link for the email account you're using
     * https://myaccount.google.com/lesssecureapps?pli=1&rapt=AEjHL4NJNDWiCsvVxi6CDNrQMVrofl85RiCj6qppKaUDDDWqB8OGjeVv-J1Yk-2yb97c5PyTMSteCsCnU54rXJHasJhbcTRcvQ
     * */

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("authoremail@gmail.com");
        mailSender.setPassword("authoremailpasswd");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
