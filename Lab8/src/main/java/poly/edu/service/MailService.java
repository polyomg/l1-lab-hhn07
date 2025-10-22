package poly.edu.service;

import poly.edu.model.Mail;

public interface MailService {
    void send(Mail mail);
    void push(Mail mail);
}
