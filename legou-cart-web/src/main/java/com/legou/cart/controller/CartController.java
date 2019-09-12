package com.legou.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongToDoubleFunction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.cart.service.CartService;
import com.legou.common.utils.CookieUtils;
import com.legou.common.utils.JsonUtils;
import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbItem;
import com.legou.pojo.TbUser;
import com.legou.service.ItemService;


@Controller
public class CartController {	
	@Autowired
	ItemService itemService;
	@Autowired
	CartService cartService;
	//获得cookie中购物车的方法
	public List<TbItem> getCookieByname(HttpServletRequest request) {
		String jsonString=CookieUtils.getCookieValue(request, "LegouCart",true);
		//判断是否有值
		if(StringUtils.isBlank(jsonString)) {
			//就算没有值 return一个空的集合 就是[],这样后面转json 页不会报错
			return new ArrayList<TbItem>();
		}
		//有值得话就将数据转成list集合
		 List<TbItem> list =JsonUtils.jsonToList(jsonString, TbItem.class);
		return list;
	}		
	@RequestMapping("/cart/add/{timeid}")//添加购物车
	public String gotocart(@PathVariable long timeid,@RequestParam(defaultValue="1")Integer num,HttpServletRequest request,HttpServletResponse response) {
		//判断用户是否登录 如果登陆了 我们就将购物车保存到服务器的Redis中 不在保存cookie中
		TbUser tbUser=(TbUser) request.getAttribute("legouUser");
		if(tbUser != null) {
			//调用服务 保存到Redis
			cartService.addcart(timeid, num, tbUser);
			return "cartSuccess";
		}
		
		String tonkenString =(String) request.getAttribute("legouToken");
		//在用户没有登录的情况下 为了客流 也在cookie中创建购物车信息
		//判断cookie中有没有这个商品 如果有直接添加商品数量就可以了
		//调用上面自定义的获得cookie中购物车的方法
		List<TbItem>list=getCookieByname(request);
		//定义一个Boolean
		boolean fiag=false;
		//遍这个购物车 查看商品是否存在
		for (TbItem tbItem : list) {
			if(tbItem.getId()==timeid) {
				//说明需要添加的商品已经存在
				tbItem.setNum(tbItem.getNum()+num);
				fiag=true;
			}
		}	
		//如果cookie中没有这个商品需要从数据库中找到这个商品
		//调用查找商品的service  注意 dubbo订阅服务要打开
		if(!fiag) {
		TbItem tbItem=itemService.getItem(timeid);
		//找到需要添加到购物车的商品 添加多少个这个商品由传过来的数目为准
		tbItem.setNum(num);
		//还需要一张这个商品的图片,商品由可能多张图片 
		String imagesString =tbItem.getImage();
		//给图片地址的字符串分割成数组
		String[] allStrings=imagesString.split(",");
		tbItem.setImage(allStrings[0]);
		//将要添加到购物车的商品放入集合
		list.add(tbItem);
		}
		//在将他保存到cookie中,并取名LegouCart
		CookieUtils.setCookie(request, response, "LegouCart", JsonUtils.objectToJson(list), 30 * 60 * 60, true);
		//跳转添加购物车成功页面
		return "cartSuccess";
	}
	@RequestMapping("/cart/cart")//去购物车页面
	public String tocart(HttpServletResponse response, HttpServletRequest request,Model model) {//去购物车页面
		//获得cookie中所有购物车信息
		List<TbItem> list=getCookieByname(request);
		//判断用户是否登录
		TbUser tbUser=(TbUser) request.getAttribute("legouUser");
		
		if(tbUser != null) {
			//登录了 我们就需要把cookie中的购物车和Redis中的购物车合并
            cartService.moveCookieToRedis(tbUser,list);
			//合并完后要将cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "LegouCart");
			//合并成功后 再次查找Redis中的购物车 将他转换成List<tbitem>类型集合
			list=cartService.getList(tbUser);
			
		}
		
		model.addAttribute("cartList", list);
		return "cart";
	}
	//修改购物车商品信息
	@RequestMapping("/cart/update/num/{itemid}/{num}")
	@ResponseBody
	public LegouResult cartupdate(@PathVariable long itemid,@PathVariable int num,HttpServletRequest request,HttpServletResponse response) {
		//判断用户是否登录
		TbUser tbUser=(TbUser) request.getAttribute("legouUser");				
			if(tbUser != null) {
				cartService.updatenum(itemid,num,tbUser);
				return LegouResult.ok();
			}
		
		
		//通过传过来的id找到购物车里的值
		List<TbItem>list=getCookieByname(request);
		//遍历集合找到需要的值
		for (TbItem tbItem : list) {
			if(tbItem.getId()==itemid) {
				//修改数量就可以
				tbItem.setNum(num);
				break;
			}			
		}
		//修改完了重新存入cookie
		CookieUtils.setCookie(request, response, "LegouCart", JsonUtils.objectToJson(list), 30 * 60 * 60, true);
		return LegouResult.ok();
	}
	//删除购物车商品
	@RequestMapping("/cart/delete/{itemid}")
	public String cartdelete(@PathVariable long itemid,HttpServletRequest request,HttpServletResponse response)  {
		//判断用户是否登录
			TbUser tbUser=(TbUser) request.getAttribute("legouUser");				
				if(tbUser != null) {
					cartService.delectCart(itemid,tbUser);				
						return "redirect:/cart/cart.html";
					}		
		//通过传过来的id找到购物车里的值
				List<TbItem>list=getCookieByname(request);
				//遍历集合找到需要的值
				for (TbItem tbItem : list) {
					if(tbItem.getId()==itemid) {
						//删除这个商品
						list.remove(tbItem);
						break;
					}			
				}
				//删除成功后重新保存cookie
				CookieUtils.setCookie(request, response, "LegouCart", JsonUtils.objectToJson(list), 30 * 60 * 60, true);
				
				return "redirect:/cart/cart.html";			

	}
}
