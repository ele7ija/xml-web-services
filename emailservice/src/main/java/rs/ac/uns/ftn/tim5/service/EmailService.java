package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.config.EmailConfiguration;
import rs.ac.uns.ftn.tim5.dto.EmailDTO;


import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    @Autowired
    private EmailConfiguration emailConfiguration;

    @Value("${EMAIL_SENDER}")
    private String emailSender;


    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(emailConfiguration.getHost());
        mailSenderImpl.setPort(emailConfiguration.getPort());
        mailSenderImpl.setUsername(emailConfiguration.getUsername());
        mailSenderImpl.setPassword(emailConfiguration.getPassword());

        Properties props = mailSenderImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSenderImpl;
    }

    @Async
    public void noAttach(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.emailSender);
        message.setTo(emailDTO.getRecipient());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getMessage());
        getJavaMailSender().send(message);
    }

    @Async
    public void withAttach(EmailDTO emailDTO) {
        MimeMessage message = getJavaMailSender().createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(this.emailSender);
            helper.setTo(emailDTO.getRecipient());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getMessage());

            helper.addAttachment("obavestenje.pdf", new ByteArrayResource(emailDTO.getAttachmentPdf()));
            helper.addAttachment("obavestenje.html", new ByteArrayResource(emailDTO.getAttachmentHtml()));
            getJavaMailSender().send(message);
        } catch (Exception e) {
            System.out.println("Neuspesno poslat mejl.");
            e.printStackTrace();
            return;
        }

        System.out.println("Uspesno poslat mejl.");
    }

}
