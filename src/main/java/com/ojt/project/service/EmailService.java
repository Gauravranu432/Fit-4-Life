package com.ojt.project.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String body, String to) {
        boolean isSent = false;

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");

            helper.setFrom("fit4life201301@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, false); // Set to true for HTML content

            mailSender.send(message);
            isSent = true;

            logger.info("Email sent successfully to: {}", to);

        } catch (MessagingException e) {
            logger.error("MessagingException while sending email: {}", e.getMessage());
        } catch (MailException e) {
            logger.error("MailException while sending email: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while sending email: {}", e.getMessage());
        }

        return isSent;
    }
}