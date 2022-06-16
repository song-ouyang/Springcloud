package com.mbg.exam.mapper;

import com.mbg.exam.entity.Choose;
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
public interface ChooseMapper extends BaseMapper<Choose> {


   List<Map<String, Object>> select1(@Param("id")int id);



  //List<Map<String, Object>> select1(int id);

}
