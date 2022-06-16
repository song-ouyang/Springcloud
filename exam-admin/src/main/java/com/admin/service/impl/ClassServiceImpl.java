package com.admin.service.impl;

import com.admin.service.ClassImplService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.api.CommonResult;
import com.mbg.exam.entity.Class;
import com.mbg.exam.entity.Content;
import com.mbg.exam.mapper.ClassMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuo
 * @since 2022-03-17
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassImplService {


    @Autowired
    private ClassMapper classMapper;

    //查看所有学院和专业班级
    public CommonResult getschoolclass(){
     List list=classMapper.getschoolclass();
        if (list.isEmpty())
            return CommonResult.failed("没有任何学校班级");
        return CommonResult.success(list,"查看所有学院和专业班级成功");
    }

    //查看所有学校
    public CommonResult getuniversity(){
        List list=classMapper.getschool();
        if (list.isEmpty())
            return CommonResult.failed("查看所有学校失败");
        return CommonResult.success(list,"查看所有学校成功");
    }

    //根据学校查看专业班级
    public CommonResult getclass(String  school){
        List list=classMapper.getclass(school);
        if (list.isEmpty())
            return CommonResult.failed("根据学校查看专业班级失败");
        return CommonResult.success(list,"根据学校查看专业班级成功");
    }


}
