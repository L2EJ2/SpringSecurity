package com.ohgiraffers.session.user.model.dao;

import com.ohgiraffers.session.auth.model.dto.UserDTO;
import com.ohgiraffers.session.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int regist(SignupDTO newUserInfo);

    UserDTO findByUsername(String username);
}
