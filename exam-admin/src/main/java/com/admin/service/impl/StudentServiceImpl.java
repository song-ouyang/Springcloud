package com.admin.service.impl;



import com.admin.service.StudentImplService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.api.CommonResult;

import com.mbg.exam.entity.*;

import com.mbg.exam.mapper.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuo
 * @since 2022-03-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentImplService {


    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ChooseMapper chooseMapper;

    @Autowired
    private FillMapper fillMapper;

    @Autowired
    private YueMapper yueMapper;

    @Autowired
    private  FenMapper fenMapper;


    @Autowired
    private  MingciMapper mingciMapper;

    @Autowired
    private  ListenMapper listenMapper;


    @Autowired
    private FullInputMapper fullInputMapper;


    @Autowired
    private  StudentMapper studentMapper;


    @Autowired
    private  GradeMapper gradeMapper;

    /**
     * 查询所有学生所有信息
     * @return
     */
    public CommonResult queryStudentList() {
        QueryWrapper<Student> wrapper = new QueryWrapper();
        wrapper.eq("state_de",0);
        List<Student> studentList=baseMapper.selectList(wrapper);
       // studentList.forEach(System.out::println);
        if (!studentList.isEmpty())
            return CommonResult.success(studentList,"查看所有学生成功");
        else
            return  CommonResult.failed("查询失败");
    }

    /**
     * 删除一个学生(软删除，修改状态)
     */
    public  CommonResult  deleteStudent(String username)
    {
        Student student=new Student();
        student.setStateDe(1);
        QueryWrapper<Student> wrapper= new QueryWrapper<>();
        wrapper.eq("username",username);
        int result=baseMapper.update(student,wrapper);
        if(result>0)
            return  CommonResult.success(result,"删除学生成功");
        else
            return  CommonResult.failed("删除学生失败");
    }

    /**
     * 查看单个学生详细信息成功
     * @param username
     * @return
     */
    public  CommonResult<Student>selectStudentByUsername(String username)
    {
        QueryWrapper<Student> wrapper= new QueryWrapper<>();
        wrapper.eq("username",username);
        Student student=baseMapper.selectOne(wrapper);
        System.out.println(student);
        if(student!=null)
        {
            return  CommonResult.success(student,"查询成功");
        }
        return  CommonResult.failed("查询失败");
    }


    /**
     * 添加学生
     * @param username
     * @return
     */
    public  CommonResult addStudent(String name,String username,String password,String school,String classes)
    {
        QueryWrapper<Student> wrapper= new QueryWrapper<>();
        wrapper.eq("username",username);
        List list=baseMapper.selectList(wrapper);
        if (!list.isEmpty())
            return  CommonResult.failed("添加失败，已有该学号的学生");
        Student student=new Student();
        student.setName(name);
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setSchool(school);
        student.setClasses(classes);
        int result=baseMapper.insert(student);
        if(result>=1)
        {
            return  CommonResult.success(student,"添加成功");
        }
        return  CommonResult.failed("添加失败，学号已经存在");
    }

    //todo  学生答题

    /**
     * 开始考试  插入answer表 连接答案和试卷
     * @param map
     * @return
     */
    @Override
    public CommonResult writePaper(Map map) {
        Answer answer=new Answer();
        answer.setPaperId((String) map.get("paper_id"));
        answer.setStuId((String) map.get("stu_id"));
        //answer.setIsTrue(Integer.parseInt((String) map.get("is_true")));
        List<String> list = (List) map.get("ks_answer");
        System.out.println(list.size());
        int vis=1;
        for (int i = 0; i < list.size(); i++) {
            Object lo = list.get(i);
            Map entry = (Map) lo;
           // System.out.println(lo);
          //  System.out.println(entry);
         //   System.out.println(entry.get("answer"));
            answer.setAnswer2(null);
            answer.setAnswer3(null);
            answer.setAnswer4(null);
            answer.setAnswer5(null);
            answer.setState(Integer.parseInt((String) entry.get("state")));
            answer.setAnswer((String) entry.get("answer"));
            answer.setAnswerOrder(vis);
            System.out.println(entry.size());
            if (entry.size()==2)
            {
               // System.out.println(entry.get("answer2"));
                answer.setAnswer2((String) entry.get("answer2"));
            }
            else if (entry.size()==3)
            {
                answer.setAnswer2((String) entry.get("answer2"));
                answer.setAnswer3((String) entry.get("answer3"));
             //   System.out.println(entry.get("answer2"));
            //    System.out.println(entry.get("answer3"));
            }
            else if (entry.size()==4)
            {
                answer.setAnswer2((String) entry.get("answer2"));
                answer.setAnswer3((String) entry.get("answer3"));
                answer.setAnswer4((String) entry.get("answer4"));
             //   System.out.println(entry.get("answer2"));
            //    System.out.println(entry.get("answer3"));
              //  System.out.println(entry.get("answer4"));
            }
            else if (entry.size()==5)
            {
                answer.setAnswer2((String) entry.get("answer2"));
                answer.setAnswer3((String) entry.get("answer3"));
                answer.setAnswer4((String) entry.get("answer4"));
                answer.setAnswer5((String) entry.get("answer5"));

             //   System.out.println(entry.get("answer2"));
            //    System.out.println(entry.get("answer3"));
             //   System.out.println(entry.get("answer4"));
             //   System.out.println(entry.get("answer5"));
            }
          //  answer.setAnswer((String) map.get("state"));
            System.out.println(answer);
            answerMapper.insert(answer);
            vis++;
        }
        return CommonResult.success("答题提交成功");
    }

    @Override
    public CommonResult studentPaper(int paperid, String stuid) {
        List list = answerMapper.getpaperandanswer1(paperid, stuid);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            Object lo = list.get(i);
            Map entry = (Map) lo;
            //System.out.println(entry.get("type"));
            //System.out.println(entry.get("ex_id"));
            int type = (int) entry.get("type");
            int id = (int) entry.get("ex_id");
            //  System.out.println(list.get(i));
            if (type == 1 || type == 2) {
                System.out.println("aaa");
                QueryWrapper<Choose> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", chooseMapper.selectList(queryWrapper1));
            }
            //填空
            else if (type == 3) {
                QueryWrapper<Fill> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", fillMapper.selectList(queryWrapper1));
            }
            //判断  1
            else if (type == 4) {
                QueryWrapper<Fill> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", fillMapper.selectList(queryWrapper1));
            }
            //阅读理解  1
            else if (type == 5) {
                QueryWrapper<Yue> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", yueMapper.selectList(queryWrapper1));
            }

            //分录资料  1
            else if (type == 6 || type == 7) {
                QueryWrapper<Fen> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", fenMapper.selectList(queryWrapper1));
            }

            //8.名词解析 9.论述  10.计算题  11.简答 12.口语  1
            else if (type == 8 || type == 9 || type == 10 || type == 11 || type == 12) {
                QueryWrapper<Mingci> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", mingciMapper.selectList(queryWrapper1));
            }

            // 13.完型  1
            else if (type == 13) {
                QueryWrapper<FullInput> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", fullInputMapper.selectList(queryWrapper1));
                continue;
            }
            //  14.听力  1
            else if (type == 14) {
                QueryWrapper<Listen> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id", id);
                entry.put("topic", listenMapper.selectList(queryWrapper1));

            }
        }
        return CommonResult.success(list, "查看成功");
    }

    @Override
    public CommonResult showStudentPaper(int paperid,String stuid) {
        List list=answerMapper.getpaperandanswer2(paperid,stuid);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            Object lo = list.get(i);
            Map entry = (Map) lo;
            //System.out.println(entry.get("type"));
            //System.out.println(entry.get("ex_id"));
            int type= (int) entry.get("type");
            int id= (int)entry.get("ex_id");
            //  System.out.println(list.get(i));
            if (type==1||type==2)
            {
                System.out.println("aaa");
                QueryWrapper<Choose> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",chooseMapper.selectList(queryWrapper1));
            }
            //填空
            else if(type==3)
            {
                QueryWrapper<Fill> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fillMapper.selectList(queryWrapper1));
            }
            //判断  1
            else if(type==4)
            {
                QueryWrapper<Fill> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fillMapper.selectList(queryWrapper1));
            }
            //阅读理解  1
            else if(type==5)
            {
                QueryWrapper<Yue> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",yueMapper.selectList(queryWrapper1));
            }

            //分录资料  1
            else if(type==6||type==7)
            {
                QueryWrapper<Fen> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fenMapper.selectList(queryWrapper1));
            }

            //8.名词解析 9.论述  10.计算题  11.简答 12.口语  1
            else if(type==8||type==9||type==10||type==11||type==12)
            {
                QueryWrapper<Mingci> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",mingciMapper.selectList(queryWrapper1));
            }

            // 13.完型  1
            else if(type==13)
            {
                QueryWrapper<FullInput> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fullInputMapper.selectList(queryWrapper1));
                continue;
            }
            //  14.听力  1
            else if(type==14)
            {
                QueryWrapper<Listen> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",listenMapper.selectList(queryWrapper1));

            }
        }
        return CommonResult.success(list,"查看成功");
    }



    @Override
    public CommonResult studentStudent(int paperid) {

        //在试卷相同的清空下 生成随机一个学生
        QueryWrapper<Answer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("paper_id",paperid);
        List list1 =answerMapper.selectList(queryWrapper);
     //   System.out.println(list1.size());
        Random r = new Random();
        int rand = r.nextInt(list1.size()-1);
        Answer lo1 = (Answer) list1.get(rand);
      //  System.out.println(lo.getStuId());

       List list=answerMapper.getpaperandanswer2(paperid,lo1.getStuId());
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            Object lo = list.get(i);
            Map entry = (Map) lo;
            //System.out.println(entry.get("type"));
            //System.out.println(entry.get("ex_id"));
            int type= (int) entry.get("type");
            int id= (int)entry.get("ex_id");
            //  System.out.println(list.get(i));
            if (type==1||type==2)
            {
                System.out.println("aaa");
                QueryWrapper<Choose> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",chooseMapper.selectList(queryWrapper1));
            }
            //填空
            else if(type==3)
            {
                QueryWrapper<Fill> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fillMapper.selectList(queryWrapper1));
            }
            //判断  1
            else if(type==4)
            {
                QueryWrapper<Fill> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fillMapper.selectList(queryWrapper1));
            }
            //阅读理解  1
            else if(type==5)
            {
                QueryWrapper<Yue> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",yueMapper.selectList(queryWrapper1));
            }

            //分录资料  1
            else if(type==6||type==7)
            {
                QueryWrapper<Fen> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fenMapper.selectList(queryWrapper1));
            }

            //8.名词解析 9.论述  10.计算题  11.简答 12.口语  1
            else if(type==8||type==9||type==10||type==11||type==12)
            {
                QueryWrapper<Mingci> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",mingciMapper.selectList(queryWrapper1));
            }

            // 13.完型  1
            else if(type==13)
            {
                QueryWrapper<FullInput> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",fullInputMapper.selectList(queryWrapper1));
                continue;
            }
            //  14.听力  1
            else if(type==14)
            {
                QueryWrapper<Listen> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("id",id);
                entry.put("topic",listenMapper.selectList(queryWrapper1));

            }
        }
        return CommonResult.success(list,"查看成功");
    }



    @Override
    public CommonResult correctPaper(Map map) {
        int paper_id=  Integer.parseInt((String) map.get("paper_id"));
      String  stu_id= (String) map.get("stu_id");
        List list = (List) map.get("arr_score");
        System.out.println(paper_id+"*********"+ stu_id);
        int sum=0;
        for (int i = 0; i < list.size(); i++) {
            Answer answer=new Answer();
            System.out.println(list.get(i));
            answer.setScore((int)list.get(i));
            QueryWrapper<Answer> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("paper_id",paper_id);
            queryWrapper1.eq("stu_id",stu_id);
            queryWrapper1.eq("answer_order",i+1);
            answerMapper.update(answer,queryWrapper1);
             sum=sum+(int)list.get(i);
        }
        //统计这张试卷这个人的分
        System.out.println(sum);
       Grade grade =new Grade();
       grade.setExamId(paper_id);
       grade.setStuNum(stu_id);
        grade.setGrade(sum);
        System.out.println(grade);
        gradeMapper.insert(grade);
        return CommonResult.success("批阅成功");
    }
}
