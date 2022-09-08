package com.mbg.exam.mapper;

import com.mbg.exam.entity.Answer;
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
public interface AnswerMapper extends BaseMapper<Answer> {


    //   List<Map<String, Object>> getpaperandanswer1(@Param("paperid")int paperid,@Param("stuid")String stuid);
    List<Map<String, Object>> getpaperandanswer1(int paperid, String stuid);

    List<Map<String, Object>> getpaperandanswer2(int paperid,String stuid);

    List<Map<String, Object>> getrandow(int paperid);

}
