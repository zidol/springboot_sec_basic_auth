package com.sp.fc.web;


import org.springframework.security.access.AccessDeniedException;

public class YouCannotAccessUserPage extends AccessDeniedException {

    public YouCannotAccessUserPage() {
        super("유저 페이지 접근 거부");
    }
}
