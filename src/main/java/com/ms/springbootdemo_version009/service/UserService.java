package com.ms.springbootdemo_version009.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ms.springbootdemo_version009.bean.User;
import com.ms.springbootdemo_version009.repository.UserRepository;

@Transactional
@Component("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> save(List<User> list) {
        return userRepository.save(list);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public Integer deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    /**
     * 查询所有数据
     * 
     * @param paramMap
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
	}
}
