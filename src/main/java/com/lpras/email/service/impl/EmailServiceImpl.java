package com.lpras.email.service.impl;

import com.lpras.email.exception.EmailDataException;
import com.lpras.email.service.EmailService;
import com.lpras.lombok.model.Email;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
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

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    @Autowired
    private Email email;

    @Autowired
    private Configuration freemarkerConfig;

    /**
     * This method will send compose and send the message
     */
    public void testSendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    /**
     *  Used for sending email,
     *  e.g
     *  To abc@gmail.com
     *  From bcd@gmail.com
     *  Sir, "toName",
     *      "bodyContent"
     *  Thanks,
     *  "fromSignature"
     *  "location","phone"
     * @param to to which email will be sent, only gmail accepted untill now
     * @param from sender email, only gmail accepted untill now
     * @param subject Subjet line of email
     * @param bodyContent content of the email
     * @param toName In salution this name will be used
     * @param fromLocation In salutation this location will be used
     * @param fromSignaure In salutation this signature will be used
     * @throws MessagingException
     */

    public void sendMail (String to, String from, String subject, String bodyContent, String toName, String fromLocation, String fromSignaure, String phone) {

        if(Strings.isBlank(from) || Strings.isBlank(to))
            throw new EmailDataException("to or from must not be null");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", toName);
        model.put("location", fromLocation);
        model.put("signature", fromSignaure);
        model.put("bodyContent", bodyContent);
        model.put("phone", phone);
        email.setModel(model);

        String html = Strings.EMPTY;
        Template template = null;
        MimeMessageHelper helper = null;
        try{
            template = freemarkerConfig.getTemplate("basic-email-template.ftl");
        }catch(TemplateNotFoundException | MalformedTemplateNameException | ParseException e){
            e.printStackTrace();
        } catch(IOException e4){
            e4.printStackTrace();
        }
        try{
            html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }catch(TemplateException | IOException e){
            e.printStackTrace();
        }
        if(Strings.isBlank(html))
            html = "Please contact customer support";

        MimeMessage message = mailSender.createMimeMessage();
        try{
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
            helper.setTo(to);
            helper.setText(html, true);

            if(Strings.isBlank(subject))
                helper.setSubject(Strings.EMPTY);
            else
                helper.setSubject(subject);

            helper.setFrom(to);
        }catch(MessagingException me){
            me.printStackTrace();
            throw new EmailDataException(me.getMessage());
        }

        try{
            mailSender.send(message);
        }catch(MailException me){
            me.printStackTrace();
        }
    }

    /**
     * This method will send a pre-configured message
     */
    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
