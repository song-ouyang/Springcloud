package com.mbg.exam.mapper;

import com.mbg.exam.entity.Yue;
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
public interface YueMapper extends BaseMapper<Yue> {


    List<Map<String, Object>> select3(int id);
}
