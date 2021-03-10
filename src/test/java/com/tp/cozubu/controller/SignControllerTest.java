package com.tp.cozubu.controller;

import com.tp.cozubu.model.service.common.user.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@RestClientTest // 단위테스트 restApi 테스트
@Transactional
@RequiredArgsConstructor
class SignControllerTest {
    private final UserService userService;

    @Test
    public void signUp() {

    }
}