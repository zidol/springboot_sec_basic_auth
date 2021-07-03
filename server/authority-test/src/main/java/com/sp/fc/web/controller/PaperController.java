package com.sp.fc.web.controller;


import com.sp.fc.web.config.CustomSecurityTag;
import com.sp.fc.web.service.Paper;
import com.sp.fc.web.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/paper")
@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

//    @PreAuthorize("isStudent()")
//    @PostFilter("filterObject.state != T(com.sp.fc.web.service.Paper.State).PREPARE") //CustomeMethodSercuretyExpressionRoot 에 공톻적으로 사용
//    @PostFilter("noPrepareState(filterObject)")
//    @PostFilter("noPrepareState(filterObject) && filterObject.studentIds.contains(#user.username)")
    @GetMapping("/mypapers")
    public List<Paper> myPapers(@AuthenticationPrincipal User user){
        return paperService.getMyPapers(user.getUsername());
    }

    @Secured({"ROLE_USER","RUN_AS_PRIMARY"})
    @GetMapping("/allpapers")
    public List<Paper> allpapers(@AuthenticationPrincipal User user){
        return paperService.getAllPapers();
    }

    @PreAuthorize("hasPermission(#paperId, 'paper', 'read')")
//    @PostAuthorize("returnObject.studentIds.contains(#user.username)")
    @GetMapping("/get/{paperId}")
    public Paper getPaper(@AuthenticationPrincipal User user, @PathVariable Long paperId){
        return paperService.getPaper(paperId);
    }

    @CustomSecurityTag("SCHOOL_PRIMARY")
//    @Secured({"SCHOOL_PRIMARY"})    //MethodSecurityConfiguration에서 securedEnabled = true 선언 해야 사용가능
    @GetMapping("/getPapersByPrimary")
    public List<Paper> getPapersByPrimary(){
        return paperService.getAllPapers();
    }


}
