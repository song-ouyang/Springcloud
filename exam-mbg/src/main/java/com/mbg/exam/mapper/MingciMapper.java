package com.mbg.exam.mapper;

import com.mbg.exam.entity.Mingci;
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
public interface MingciMapper extends BaseMapper<Mingci> {

    List<Map<String, Object>> select5(int id);
}
