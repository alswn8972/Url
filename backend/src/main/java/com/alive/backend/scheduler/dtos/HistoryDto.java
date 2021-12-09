package com.alive.backend.scheduler.dtos;

import com.alive.backend.url.dtos.UrlDto;
import lombok.Data;

@Data
public class HistoryDto {
    private UrlDto urlDto;

    int stateCode;
}
