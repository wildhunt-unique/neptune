package com.qtu404.neptune.util.email.strategy;

import org.springframework.mail.MailSender;

public interface Strategy {
    public String getSenderAddress();

    public MailSender getMailSender();
}
