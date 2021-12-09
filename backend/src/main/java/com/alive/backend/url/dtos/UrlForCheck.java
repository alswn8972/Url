package com.alive.backend.url.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlForCheck {
    private final Long id;
    private final String userName;
    private final String userEmail;
    private final String urlAddress;
    private final boolean pending;
}
