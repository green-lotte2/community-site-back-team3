package kr.co.orangenode.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    public void updateQuestionStatus(Integer questionDTO);
}
