package com.itheima.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.exception.MyException;
import com.itheima.springmvc.pojo.Item;
import com.itheima.springmvc.pojo.QueryVo;
import com.itheima.springmvc.service.ItemService;

@Controller
//@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value={"itemList", "itemList2"})
	public ModelAndView itemList() {
		ModelAndView mav = new ModelAndView();

		List<Item> itemList = itemService.getItemList();

		mav.addObject("itemList", itemList);
		// mav.setViewName("/WEB-INF/jsp/itemList.jsp");
		mav.setViewName("itemList");
		return mav;
	}
	
//	@RequestMapping("itemEdit")
//	public ModelAndView itemEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {   //传递request
//		
//		ModelAndView mav = new ModelAndView();
//		
//		String idString = request.getParameter("id");             //通过request对象获取id
//		System.out.println("response: "+ response);
//		System.out.println("session: "+ session);
//		//查询商品信息
//		Item item = itemService.getItemById(new Integer(idString));
//		mav.addObject("item",item);
//		mav.setViewName("itemEdit");
//		return mav;
//	} 

	/**
	 * 跟据ID查询商品信息，跳转修改商品页面
	 * 演示默认支持的参数传递
	 * Model/ModelMap返回数据模型
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	 
//	@RequestMapping("itemEdit")
//	public String itemEdit(Model model,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		String idStr = request.getParameter("id");
//		System.out.println("response:" + response);
//		System.out.println("session:" + session);
//		// 查询商品信息
//		Item item = itemService.getItemById(new Integer(idStr));
//		//model返回数据模型
//		model.addAttribute("item", item);
//		//mav.addObject("item", item);
//		return "itemEdit";
//	}
	
//	@RequestMapping("itemEdit")
//	public String itemEdit(Model model,@RequestParam(value="id",required=true,defaultValue="1") Integer ids) {
//		// 查询商品信息
//		Item item = itemService.getItemById(ids);
//		//model返回数据模型
//		model.addAttribute("item", item);
//		//mav.addObject("item", item);
//		return "itemEdit";
//	}
	
	@RequestMapping("itemEdit")
	public String itemEdit(Model model,@RequestParam(value="id", required=true, defaultValue="1")  Integer ids) {
		// 查询商品信息
		Item item = itemService.getItemById(ids);
		//model返回数据模型
		model.addAttribute("item", item);
		//mav.addObject("item", item);
		return "itemEdit";
	}
	
	/**
	 * 修改商品
	 * 演示pojo参数绑定
	 * @param item
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="updateItem", method={RequestMethod.POST, RequestMethod.GET})
	public String updateItem(Item item,MultipartFile pictureFile, Model model) throws Exception{
		
		//图片新名字
		String newName = UUID.randomUUID().toString();
		//图片原来的名字
		String oldName = pictureFile.getOriginalFilename();
		//后缀
		String sux = oldName.substring(oldName.lastIndexOf("."));
		//新建本地文件流
		File file = new File("C:\\WebWork\\"+ newName +sux);
		
		//写入本地磁盘
		pictureFile.transferTo(file);
		
		//保存图片到数据库
		item.setPic(newName+sux);
		
		itemService.updateItem(item);
		model.addAttribute("item", item);
		model.addAttribute("msg", "修改商品信息成功");
		return "itemEdit";
		//return "forward:itemList.action";
		//return "redirect:itemList.action";
	}
	
	@RequestMapping("queryItem")
	public String queryItem(QueryVo vo,Integer[] ids, Model model){
		if(vo.getItem() != null){
			System.out.println(vo.getItem());
		}
		System.out.println("ids======");
		if(ids != null && ids.length > 0){
			for (Integer id : ids) {
				System.out.println(id);
			}
		}
		
		if(vo.getItems() != null && vo.getItems().size() > 0){
			for (Item item : vo.getItems()) {
				System.out.println(item);
			}
		}
		
		//模拟搜索商品
		List<Item> itemList = itemService.getItemList();

		model.addAttribute("itemList", itemList);
		return "itemList";
	}
	
	@RequestMapping("queryVoid")
	public void queryVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//request 响应用户请求
		//request.setAttribute("msg", "这个是request响应的消息");
		//request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
		//response.sendRedirect("/04-springmvc-mybatis/itemList.action");
		
		if(true){
			throw new MyException("你查找的商品不存在, 请确认信息!");
		}
		
		int i = 1/0;
		
		//设置响应的字符编码
		response.setCharacterEncoding("gbk");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println("这个是response打印的消息");
	}
}
