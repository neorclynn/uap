package neo.uap.util.email;

import neo.uap.domain.system.EmailTemplate;
import neo.uap.mapper.EmailTemplateMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class BaseEmail {
    @Autowired
    protected EmailTemplateMapper emailTemplateMapper;

    @Autowired
    protected JavaMailSender mailSender;

    protected EmailTemplate emailTemplate;

    protected String name;

    @Getter
    @Setter
    @Value("${spring.mail.from}")
    protected String from;

    @Getter
    protected String[] to;

    @Getter
    @Setter
    protected String[] cc;

    protected Map<String, String> parameters = new HashMap<String, String>();

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void setTo(String to) {
        this.to = new String[]{to};
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void send() {
        emailTemplate = emailTemplateMapper.get(name);
        fillPlaceHolder();

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(emailTemplate.getSubject());
            helper.setText(emailTemplate.getContent(), true);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
        mailSender.send(message);
    }

    private void fillPlaceHolder() {
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            emailTemplate.setContent(emailTemplate.getContent().replace(entry.getKey(), entry.getValue()));
        }
    }
}
