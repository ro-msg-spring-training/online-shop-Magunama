package ro.msg.learning.shop.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${jmail.username}")
    private String username;

    @Value("${jmail.password}")
    private String password;

    @Value("${jmail.smtp.host}")
    private String smtpHost;

    @Value("${jmail.smtp.auth}")
    private String smtpAuth;

    @Value("${jmail.smtp.port}")
    private String smtpPort;

    @Value("${jmail.from}")
    private String from;

    @Value("${jmail.subject}")
    private String subject;

    @Value("${jmail.body.text}")
    private String bodyText;

    @Value("${jmail.body.html}")
    private String bodyHtml;

    private String prepareBody(String expr, Customer target) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(expr);

        EvaluationContext context = new StandardEvaluationContext(target);
        return (String) expression.getValue(context);
    }


    @Override
    public void sendMail(Customer target) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", this.smtpAuth);
        props.put("mail.smtp.host", this.smtpHost);
        props.put("mail.smtp.port", this.smtpPort);

        // create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(target.getEmailAddress()));
            message.setSubject(this.subject);

            // create text body part
            MimeBodyPart mimeBodyPartText = new MimeBodyPart();
            mimeBodyPartText.setText(this.prepareBody(this.bodyText, target));

            // create html body part
            MimeBodyPart mimeBodyPartHtml = new MimeBodyPart();
            mimeBodyPartHtml.setContent(this.prepareBody(this.bodyHtml, target), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPartText);
            multipart.addBodyPart(mimeBodyPartHtml);

            message.setContent(multipart);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
