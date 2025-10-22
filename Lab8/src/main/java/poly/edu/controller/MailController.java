package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.model.Mail;
import poly.edu.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("mail", new Mail());
        return "mail/form";
    }

    @PostMapping("/send")
    public String sendMail(@ModelAttribute("mail") Mail mail, Model model) {
        mailService.send(mail);
        model.addAttribute("message", "✅ Gửi mail trực tiếp thành công!");
        return "mail/form";
    }

    @PostMapping("/push")
    public String pushMail(@ModelAttribute("mail") Mail mail, Model model) {
        mailService.push(mail);
        model.addAttribute("message", "📩 Mail đã được xếp vào hàng đợi!");
        return "mail/form";
    }
}
