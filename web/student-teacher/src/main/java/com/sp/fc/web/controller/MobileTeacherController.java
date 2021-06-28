package com.sp.fc.web.controller;

import com.sp.fc.web.student.Student;
import com.sp.fc.web.student.StudentManager;
import com.sp.fc.web.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/teacher")
public class MobileTeacherController {

    @Autowired
    StudentManager studentManager;

    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    @GetMapping("/main")
    public List<Student> main(@AuthenticationPrincipal Teacher teacher){
        return studentManager.myStudentList(teacher.getId());
    }


}