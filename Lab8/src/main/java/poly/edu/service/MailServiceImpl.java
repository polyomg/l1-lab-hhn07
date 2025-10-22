package poly.edu.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import poly.edu.model.Mail;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    List<Mail> queue = new ArrayList<>();

    @Override
    public void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo().split("[,; ]+"));

            if (!isNullOrEmpty(mail.getCc())) helper.setCc(mail.getCc().split("[,; ]+"));
            if (!isNullOrEmpty(mail.getBcc())) helper.setBcc(mail.getBcc().split("[,; ]+"));

            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            if (!isNullOrEmpty(mail.getFilenames())) {
                for (String filename : mail.getFilenames().split("[,;]+")) {
                    File file = new File(filename.trim());
                    if (file.exists()) {
                        helper.addAttachment(file.getName(), file);
                    }
                }
            }

            mailSender.send(message);
            System.out.println("✅ Đã gửi mail đến: " + mail.getTo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void push(Mail mail) {
        queue.add(mail);
        System.out.println("📩 Đã thêm mail vào hàng đợi: " + mail.getTo());
    }

    @Scheduled(fixedDelay = 5000)
    public void run() {
        while (!queue.isEmpty()) {
            Mail mail = queue.remove(0);
            this.send(mail);
        }
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }
}
