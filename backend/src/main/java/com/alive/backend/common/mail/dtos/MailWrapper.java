package com.alive.backend.common.mail.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MailWrapper {
    private final String receiver;
    private final String sender = "noticemonitoring@gmail.com";
    private final String subject = "[알림] SITE Monitor에서 알려드립니다.";
    private final String content;

    @Builder
    public MailWrapper(final String receiver, final String userName, final String urlAddress, final int statusCode) {
        this.receiver = receiver;
        this.content = String.format("<html> <body> %s님이 등록하신 <a href=%s target='_blank'>%s</a>의 상태 코드가 현재 %d 상태입니다. <br></br> " +
                "문제가 있을 경우 <a href=%s target='_blank'>SITE Monitor</a>에서 확인하시길 바랍니다.</body></html>",
                userName,
                urlAddress,
                urlAddress,
                statusCode,
                "https://bc.monitor.o-r.kr");
    }
}
