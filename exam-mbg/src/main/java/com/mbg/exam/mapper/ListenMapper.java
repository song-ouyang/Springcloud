package com.mbg.exam.mapper;

import com.mbg.exam.entity.Listen;
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
public interface ListenMapper extends BaseMapper<Listen> {
    List<Map<String, Object>> select7(int id);
}
