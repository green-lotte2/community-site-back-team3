package kr.co.orangenode.mapper;

import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.entity.project.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectMapper {

    public void updateProject(@Param("projectDTO")ProjectDTO projectDTO);
}
