package com.alive.backend.url.dtos;

import com.alive.backend.common.utils.BaseEntity;
import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.repository.UserEntity;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class UrlDto {
        private UserDto userDto;

        String urlName;
        String urlContent;
        String urlAddress;
        int isCheck;
}
