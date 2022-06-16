package com.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.api.CommonResult;
import com.mbg.exam.entity.Class;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zuo
 * @since 2022-03-17
 */
public interface ClassImplService extends IService<Class> {

    CommonResult getschoolclass();

    CommonResult getuniversity();

    CommonResult getclass(String  schol);

}
