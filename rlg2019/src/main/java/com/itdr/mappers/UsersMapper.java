package com.itdr.mappers;

import com.itdr.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUsernameAndPassword(String username, String password);

    int selectByusernameOrEmail(@Param("srt") String srt, @Param("type") String type);

    int updatePasswordById(@Param("id") Integer id, @Param("md5PasswordNew") String md5PasswordNew);

    String selectByUsername(@Param("username") String username);

    int selectByUsernameAndQusetionAndAnswer(
            @Param("username") String username, @Param("question") String question,
            @Param("answer") String answer);

    int updateByUsernameAndPassword(@Param("username") String username,
                                    @Param("md5PasswordNew") String md5PasswordNew);
}