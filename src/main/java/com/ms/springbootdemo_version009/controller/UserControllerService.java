package com.ms.springbootdemo_version009.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;
import com.ms.springbootdemo_version009.bean.RequestSaveUser;
import com.ms.springbootdemo_version009.bean.SexEnum;
import com.ms.springbootdemo_version009.bean.User;
import com.ms.springbootdemo_version009.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserControllerService {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     * 
     * @param name
     * @param email
     * @param sex
     * @param birthday
     * @param hometown
     * @return
     */
    public User save(RequestSaveUser saveUser) {
        User user = new User();
        BeanUtils.copyProperties(saveUser, user);
        return userService.save(user);
    }

    /**
     * 筛选用户
     * 
     * @param name
     * @param email
     * @param sex
     * @param startdate
     * @param endate
     * @param hometown
     * @return
     */
    public List<User> findAll(String name, String email, SexEnum sex, Date startdate, Date endate, String hometown) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(name)) {
            map.put("name", name);
        }
        if (!Strings.isNullOrEmpty(email)) {
            map.put("email", email);
        }
        if (null != sex) {
            map.put("sex", sex);
        }
        if (null != startdate) {
            map.put("startdate", startdate);
        }
        if (null != endate) {
            map.put("enddate", endate);
        }
        if (!Strings.isNullOrEmpty(hometown)) {
            String[] hts = hometown.split(",");
            List<String> list = Arrays.asList(hts);
            map.put("hometown", list);
        }
        List<User> userList = userService.findAll();
        log.info(JSONArray.toJSONString(userList));
        return userList;
    }

    /**
     * 分页获取数据
     * 
     * @param name
     * @param email
     * @param sex
     * @param startdate
     * @param endate
     * @param hometown
     * @return
     */
    public Page<User> findAllByPage(String name, String email, SexEnum sex, Date startdate, Date endate,
            String hometown, Integer pageindex, Integer pagesize) {
        Pageable pageable = new PageRequest(pageindex, pagesize);
        Map<String, Object> map = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(name)) {
            map.put("name", name);
        }
        if (!Strings.isNullOrEmpty(email)) {
            map.put("email", email);
        }
        if (null != sex) {
            map.put("sex", sex);
        }
        if (null != startdate) {
            map.put("startdate", startdate);
        }
        if (null != endate) {
            map.put("enddate", endate);
        }
        if (!Strings.isNullOrEmpty(hometown)) {
            String[] hts = hometown.split(",");
            List<String> list = Arrays.asList(hts);
            map.put("hometown", list);
        }
        Page<User> userPage = (Page<User>) userService.findAll();
        log.info(JSONArray.toJSONString(userPage));
        return userPage;
    }
}
