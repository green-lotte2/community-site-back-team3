package kr.co.orangenode.mapper;

import kr.co.orangenode.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public void updateUser(@Param("userDTO")UserDTO userDTO);
}
