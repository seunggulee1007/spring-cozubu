package com.tp.cozubu.model.mapper;

import com.tp.cozubu.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Mapper
public interface UserMapper extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
