package com.alive.backend.common.mail.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MailCodeWrapper {
    private final String receiver;
    private final String sender = "noticemonitoring@gmail.com";
    private final String subject = "[인증] SITE Monitor 인증코드입니다.";
    private final String content;

    @Builder
    public MailCodeWrapper(final String receiver, final String userName, final String authCode) {
        this.receiver = receiver;
        this.content = String.format("<html> <body> %s님이 등록하신 %s의 인증 코드는 %s 입니다. <br></br></body></html>",
                userName,
                authCode);
    }
}
