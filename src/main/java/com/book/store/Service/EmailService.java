package com.book.store.Service;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    public void sendRegistrationEmail(String username, String firstName) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("name", "Anirudh");

        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = configuration.getTemplate("registration.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setText(html, true);
            //helper.addInline("logo.png",new ClassPathResource("/templates/images/logo.png"));
            helper.setTo(username);
            String name = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
            helper.setSubject("Welcome "+ name);
            helper.setFrom("books&co.site@gmail.com");
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);

    }
}
