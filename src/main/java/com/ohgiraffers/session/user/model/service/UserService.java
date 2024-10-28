package com.ohgiraffers.session.user.model.service;

import com.ohgiraffers.session.auth.model.dto.UserDTO;
import com.ohgiraffers.session.user.model.dao.UserMapper;
import com.ohgiraffers.session.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {

    private PasswordEncoder encoder;
    private UserMapper userMapper;

    @Autowired
    public UserService(PasswordEncoder encoder, UserMapper userMapper) {
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Transactional
    public Integer regist(SignupDTO newUserInfo) {

        System.out.println("암호화 전 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("암호화 후 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = userMapper.regist(newUserInfo);
        } catch(DuplicateKeyException e) {          // 데이터 무결성 위반(중복 키) 발생 시 처리
            result = 0;
            e.printStackTrace();
        } catch(BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }
        System.out.println("회원 가입 처리 결과 => " + result);

        return result;
    }

    public UserDTO findByUsername(String username) {

        UserDTO foundUser = userMapper.findByUsername(username);

        if(!Objects.isNull(foundUser)) {
            return foundUser;
        } else {
            return null;
        }

    }
}
