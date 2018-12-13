package com.hand.dao;

import com.hand.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {


    User findById(Integer id);

    List<User> findAll();
}
