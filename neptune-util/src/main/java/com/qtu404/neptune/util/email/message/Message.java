package com.qtu404.neptune.util.email.message;

import com.qtu404.neptune.util.email.Parameter;
import org.springframework.mail.SimpleMailMessage;

public interface Message {
    public void setParameter(Parameter request);

    public SimpleMailMessage getSimpleMailMessage();
}
