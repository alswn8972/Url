package com.alive.backend.scheduler;

import com.alive.backend.common.utils.Assembler;
import com.alive.backend.common.mail.MailService;
import com.alive.backend.common.utils.UrlConnector;
import com.alive.backend.common.mail.MailWrapper;
import com.alive.backend.url.dtos.UrlForCheck;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlHistoryEntity;
import com.alive.backend.url.service.UrlHistoryService;
import com.alive.backend.url.service.UrlService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UrlCheckerScheduler {
    private final UrlService urlService;
    private final UrlHistoryService urlHistoryService;
    private final MailService mailService;

    public UrlCheckerScheduler(final UrlService urlService, final UrlHistoryService urlHistoryService, final MailService mailService) {
        this.urlService = urlService;
        this.urlHistoryService = urlHistoryService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void schedule() {
        List<UrlEntity> urlEntities = urlService.getUrls();
        List<UrlForCheck> urls = Assembler.urlEntityToUrlForChecker(urlEntities);

        urls.stream().filter(url -> !url.isPending()).forEach(filteredUrl -> {
            int statusCode = UrlConnector.getUrlStatusCode(filteredUrl.getUrlAddress());
            urlService.changeStatusCode(filteredUrl.getId(), statusCode);
            if(statusCode >= 300) {
                mailService.sendMail(
                        mailService.makeMail(
                                MailWrapper.builder()
                                        .receiver(filteredUrl.getUserEmail())
                                        .userName(filteredUrl.getUserName())
                                        .urlAddress(filteredUrl.getUrlAddress())
                                        .statusCode(statusCode)
                                        .build()
                        )
                );
                // 확인 누르면 false로 바꿔줘야함
                urlService.changePendingStateToTrue(filteredUrl.getId());

            }

            // 방어 코드 짜야함
            UrlEntity urlEntity = urlService.findUrl(filteredUrl.getId());
            UrlHistoryEntity urlHistoryEntity = new UrlHistoryEntity();
            urlHistoryEntity.setUrlId(urlEntity.getId());
            urlHistoryEntity.setStatusCode(statusCode);
            urlHistoryService.save(urlHistoryEntity);
        });
    }

}
