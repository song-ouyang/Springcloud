package com.admin.controller;

import com.admin.service.StudentImplService;
import com.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "*")
public class WriteController {

    @Autowired
    private StudentImplService studentImplService;




    //考生写试卷
    @RequestMapping("/writepaper")
    public CommonResult writePaper(@RequestBody Map map) {
        return studentImplService.writePaper(map);
    }


    //成绩详情 查看学生自己的成绩
    @RequestMapping("/studentpaper")
   public CommonResult studentPaper(@RequestParam int paperid, @RequestParam String stuid)
    {
        return studentImplService.studentPaper(paperid,stuid);
    }

    //显示试卷的题目和学生答案
    @RequestMapping("/showstudentpaper")
    public CommonResult showStudentPaper(@RequestParam int paperid, @RequestParam String stuid)
    {
        return studentImplService.showStudentPaper(paperid,stuid);
    }

    //进行打分
    @RequestMapping("/correctpaper")
    public CommonResult correctPaper(@RequestBody Map map)
    {
        return studentImplService.correctPaper(map);
    }

    //生生互评
   @RequestMapping("/studentstudent")
    public CommonResult studentStudent(@RequestParam int paperid) {
        return   studentImplService.studentStudent(paperid);
    }



}
