package com.itheima.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户请求处理器
 * @author changqiangqiang
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("login")
	public String login(String username, String password, HttpSession session){
		if(username.equals("admin")){
			session.setAttribute("username", username);   //添加session属性
			return "redirect:/itemList.action";   //这里如果不加/ ，那么表示跳转的是当前user下面的url
		}
		return "login";
	}
}
