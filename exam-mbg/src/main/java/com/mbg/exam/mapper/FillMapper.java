package com.mbg.exam.mapper;

import com.mbg.exam.entity.Fill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface FillMapper extends BaseMapper<Fill> {


    List<Map<String, Object>> select2(int id);
}
