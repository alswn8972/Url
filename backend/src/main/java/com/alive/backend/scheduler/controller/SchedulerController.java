package com.alive.backend.scheduler.controller;

import com.alive.backend.scheduler.service.SchedulerService;
import com.alive.backend.url.repository.HistoryEntity;
import com.alive.backend.url.repository.UrlEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.List;

@Component
public class SchedulerController {
    SchedulerService schedulerService;
    public SchedulerController(SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }
    @Scheduled(cron = "0 */1 * * * *")
    public void getAllUrl(){
        System.out.println("현재 시간은 과연 몇시 "+new Date());
        List<UrlEntity> urlEntityList = schedulerService.getAllUrl();


        //isCheck
        //등록된 url 0
        //정상인 url 1
        //비정상 url 2 => 메일 보내기
        //메일 보낸 url 3 => 다시 검사하기, 근데 상태는 변경하지 않기

        for(int i = 0; i < urlEntityList.size(); i++){
            if(urlEntityList.get(i).getIsCheck() == 0 || urlEntityList.get(i).getIsCheck() == 1){
                checkAllUrl(urlEntityList.get(i).getId(), urlEntityList.get(i).getUrlAddress());
            }else if(urlEntityList.get(i).getIsCheck() == 2){
                sendMail(urlEntityList.get(i).getId());
            }else if(urlEntityList.get(i).getIsCheck() == 3){
                continue;
            }
        }
    }
    public void sendMail(Long id){
        schedulerService.sendMail(id);
    }

    public void checkAllUrl(Long id, String urlAddress){
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            if(urlConnection instanceof HttpURLConnection){
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();

                int stateCode = httpURLConnection.getResponseCode();
                int timeout = httpURLConnection.getConnectTimeout();
                int contentLength = httpURLConnection.getContentLength();
                int readTimeout = httpURLConnection.getReadTimeout();
                String location = httpURLConnection.getHeaderField("Location");
                System.out.println(stateCode + " "+ timeout + " " + contentLength + " " + readTimeout + " " + location);

                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setUrlEntity(schedulerService.findUrl(id));
                historyEntity.setStateCode(stateCode);
                historyEntity.setTimeout(timeout);
                historyEntity.setReadTimeout(readTimeout);
                historyEntity.setContentLength(contentLength);

                if(stateCode >= 300){
                    schedulerService.changeUrlState(id);
                }else {
                    schedulerService.saveUrlHistory(historyEntity);
                }

            }
        } catch (UnknownHostException e) {

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
