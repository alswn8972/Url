package com.alive.backend.common.utils;

import com.alive.backend.url.dtos.UrlForCheck;
import com.alive.backend.url.repository.UrlEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Assembler {
    public static List<UrlForCheck> urlEntityToUrlForChecker(List<UrlEntity> urls) {
        return urls.stream().map(url ->UrlForCheck.builder()
                .id(url.getId())
                .userName(url.getUserEntity().getUserName())
                .userEmail(url.getUserEntity().getUserEmail())
                .urlAddress(url.getUrlAddress())
                        .pending(url.isPending())
                .build())
                .collect(Collectors.toList());
    }
}
