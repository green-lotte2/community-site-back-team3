package kr.co.orangenode.mapper;

import kr.co.orangenode.dto.page.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PageMapper {

    public int updateTitle(@Param("pageDTO")PageDTO pageDTO);

}
