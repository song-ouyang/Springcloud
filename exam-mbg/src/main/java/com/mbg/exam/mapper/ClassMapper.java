package com.mbg.exam.mapper;

import com.mbg.exam.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zuo
 * @since 2022-03-17
 */
public interface ClassMapper extends BaseMapper<Class> {

    List<Map<String, Object>> getschoolclass();

    List<Map<String, Object>> getschool();


    List<Map<String, Object>> getclass(String school);

}
