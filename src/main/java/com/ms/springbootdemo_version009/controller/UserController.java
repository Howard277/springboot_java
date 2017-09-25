package com.ms.springbootdemo_version009.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ms.springbootdemo_version009.annotation.SystemLogAnnotation;
import com.ms.springbootdemo_version009.annotation.SystemLogExcludeAnnotation;
import com.ms.springbootdemo_version009.bean.RequestSaveUser;
import com.ms.springbootdemo_version009.bean.SexEnum;
import com.ms.springbootdemo_version009.bean.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户操作controller<br/>
 * 需要对所有的方法添加日志
 * 
 * @author Administrator
 *
 */
@Slf4j
@SystemLogAnnotation
@Controller
@RequestMapping(path = "user")
public class UserController {

	@Autowired
	private UserControllerService userControllerService;

	/**
	 * 添加用户操作-打开界面<br/>
	 * 不需要日志
	 * 
	 * @return
	 */
	@SystemLogExcludeAnnotation
	@RequestMapping(path = "demo", method = RequestMethod.GET)
	public ModelAndView demo(ModelAndView mav) {
		log.info("call user demo");
		List<String> listUserName = new ArrayList<String>();
		listUserName.add("吴克涛");
		listUserName.add("小孩");
		listUserName.add("张三");
		listUserName.add("李四");
		mav.addObject("listUserName",listUserName);
		mav.addObject("myname", "吴克涛");
		
		User user = new User(null, "wuketao@houbank.cn", "吴克涛", SexEnum.MAN, new Date(), "北京");
		mav.addObject("user", user);
		
		
		Map<Integer, String> map = new TreeMap<Integer, String>();
		map.put(0, "初级");
		map.put(2, "高级");
		map.put(1, "中级");
		mav.addObject("map",map);
		
		List<User> listUser = userControllerService.findAll(null, null, null, null, null, null);
		mav.addObject("listUser",listUser);
		
		mav.setViewName("/user/demo");
		return mav;
	}

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
	@ResponseBody
	@RequestMapping(path = "save", method = RequestMethod.POST)
	public User save(@RequestBody RequestSaveUser saveUser) {
		return userControllerService.save(saveUser);
	}

	/**
	 * 查询界面
	 * 
	 * @return
	 */
	@RequestMapping(path = "query", method = RequestMethod.GET)
	public ModelAndView query(ModelAndView mav) {
		mav.setViewName("/user/query");
		return mav;
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
	@ResponseBody
	@RequestMapping(path = "findAll", method = RequestMethod.GET)
	public List<User> findAll(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "sex", required = false) SexEnum sex,
			@RequestParam(name = "startdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startdate,
			@RequestParam(name = "endate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endate,
			@RequestParam(name = "hometown", required = false) String hometown) {

		return userControllerService.findAll(name, email, sex, startdate, endate, hometown);
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
	@ResponseBody
	@RequestMapping(path = "findAllByPage", method = RequestMethod.GET)
	public Page<User> findAllByPage(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "sex", required = false) SexEnum sex,
			@RequestParam(name = "startdate", required = false) Date startdate,
			@RequestParam(name = "endate", required = false) Date endate,
			@RequestParam(name = "hometown", required = false) String hometown,
			@RequestParam(name = "pageindex", required = false, defaultValue = "0") Integer pageindex,
			@RequestParam(name = "pagesize", required = false, defaultValue = "10") Integer pagesize) {

		return userControllerService.findAllByPage(name, email, sex, startdate, endate, hometown, pageindex, pagesize);
	}
}
