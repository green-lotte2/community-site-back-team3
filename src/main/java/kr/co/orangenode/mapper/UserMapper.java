package kr.co.orangenode.mapper;

import kr.co.orangenode.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public int updateUser(@Param("userDTO")UserDTO userDTO);

    public int updateUserWithoutPass(@Param("userDTO")UserDTO userDTO);

    public int updatePass(@Param("userDTO")UserDTO userDTO);
}
