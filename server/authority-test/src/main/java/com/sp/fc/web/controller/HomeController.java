package com.sp.fc.web.controller;


import com.sp.fc.web.service.SecurityMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final SecurityMessageService securityMessageService;

    public HomeController(SecurityMessageService securityMessageService) {
        this.securityMessageService = securityMessageService;
    }
    @PreAuthorize("hasRole('USER')") // SecurityConfigdp에 @EnableGlobalMethodSecurity(prePostEnabled = true)선언하지 않으면 먹히지 않음, class에서 따로 설정 가능
    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        return "hello " + securityMessageService.message(name);
    }
}
