package com.ms.springbootdemo_version009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ms.springbootdemo_version009.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    public User findByEmail(String email);

    public Integer deleteById(Long id);
}