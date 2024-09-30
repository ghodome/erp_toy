package com.erp.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 인증 관련 RestController
@RestController
@RequestMapping("/auth")
public class AuthRestController {

	@PostMapping("/")
    public void auth() {
        // 인증이 완료된 상태에서 요청이 들어오면,
        // 사용자의 정보를 반환하거나, 필요한 추가 작업을 수행할 수 있습니다.
    }
}
