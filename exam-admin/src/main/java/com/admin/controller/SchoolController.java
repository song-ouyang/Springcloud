package com.admin.controller;



import com.admin.service.ClassImplService;
import com.common.api.CommonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam/school")
@CrossOrigin(origins = "*")
public class SchoolController {


    @Autowired
    private ClassImplService classImplService;

    //查看所有的学校
    @RequestMapping("/getschool")
    public CommonResult getschoolclass() {
        return classImplService.getschoolclass();
    }



    //查看所有大学
    @RequestMapping("/getuniversity")
    public CommonResult getuniversity() {
        return classImplService.getuniversity();
    }



    //根据大学查看专业班级
    @RequestMapping("/getclass")
    public CommonResult getclass(@RequestParam String school) {
        return classImplService.getclass(school);
    }

}
