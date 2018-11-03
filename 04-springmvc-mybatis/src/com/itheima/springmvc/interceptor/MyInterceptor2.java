package com.itheima.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * @author changqiangqiang
 *
 */
public class MyInterceptor2 implements HandlerInterceptor {
	
	//方法执行之后被执行
	//处理异常, 清理资源, 记录日志等
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("MyInterceptor2.afterCompletion ....");

	}
	
	//方法执行之后, 返回ModelAndView之前被执行
	//设置页面的公用参数
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("MyInterceptor2.postHandle...");

	}
	
	//进入方法前被执行
	//登录拦截， 权限校验
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("MyInterceptor2.preHandle...");
		//true放行， false拦截
		return true;
	}

}
