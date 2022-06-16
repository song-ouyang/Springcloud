package com.mbg.exam.mapper;

import com.mbg.exam.entity.FullInput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zuo
 * @since 2022-04-13
 */
public interface FullInputMapper extends BaseMapper<FullInput> {
    List<Map<String, Object>> select6(int id);

}
