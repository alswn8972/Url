package com.alive.backend.common.utils;

import com.alive.backend.common.mail.service.AuthCodeService;
import com.alive.backend.common.mail.dtos.MailWrapper;
import com.alive.backend.url.dtos.UrlForCheck;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlHistoryEntity;
import com.alive.backend.url.service.UrlHistoryService;
import com.alive.backend.url.service.UrlService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import java.util.List;
import java.util.Optional;

@Component
public class UrlCheckerScheduler {
    private final UrlService urlService;
    private final UrlHistoryService urlHistoryService;
    private final AuthCodeService authCodeService;

    public UrlCheckerScheduler(final UrlService urlService, final UrlHistoryService urlHistoryService, final AuthCodeService authCodeService) {
        this.urlService = urlService;
        this.urlHistoryService = urlHistoryService;
        this.authCodeService = authCodeService;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void schedule() {
        List<UrlEntity> urlEntities = urlService.getUrls();
        List<UrlForCheck> urls = Assembler.urlEntityToUrlForChecker(urlEntities);

        urls.stream().filter(url -> !url.isPending()).forEach(filteredUrl -> {
            int statusCode = UrlConnector.getUrlStatusCode(filteredUrl.getUrlAddress());
            urlService.changeStatusCode(filteredUrl.getId(), statusCode);
            if(statusCode >= 300) {
                try {
                    authCodeService.sendMail(
                            authCodeService.makeMail(
                                    MailWrapper.builder()
                                            .receiver(filteredUrl.getUserEmail())
                                            .userName(filteredUrl.getUserName())
                                            .urlAddress(filteredUrl.getUrlAddress())
                                            .statusCode(statusCode)
                                            .build(),filteredUrl.getId()
                            )
                    );
                } catch (AddressException e) {
                    e.printStackTrace();
                }
                // 확인 누르면 false로 바꿔줘야함
                urlService.changePendingStateToTrue(filteredUrl.getId());

            }

            // 방어 코드 짜야함
            Optional<UrlEntity> urlEntity = urlService.findUrl(filteredUrl.getId());
            UrlHistoryEntity urlHistoryEntity = new UrlHistoryEntity();
            urlHistoryEntity.setUrlId(urlEntity.get().getId());
            urlHistoryEntity.setStatusCode(statusCode);
            urlHistoryService.save(urlHistoryEntity);
        });
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteData() {
        urlHistoryService.deleteData();
    }

}
