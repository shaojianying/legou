package com.legou.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.legou.common.utils.FastDFSClient;
import com.legou.common.utils.JsonUtils;

//图片上传处理器
@Controller
public class PicUploadController {
	@RequestMapping("/pic/upload")
	@ResponseBody//返回json  因为Kindeditor 的这个图片上传插件需要的数据格式也会json
	public String uploadFile(MultipartFile uploadFile) {//图片上传  返回的是图片的地址  多张图片 中间用逗号隔开,所以页面传过来的数据是文件类型
		System.out.println(uploadFile);
		//调用上传图片的工具类
		try {
			FastDFSClient fastDFSClient=new FastDFSClient("classpath:config/fastdfs.properties");
			//获得文件名 并或的.的下标
			int i=uploadFile.getOriginalFilename().lastIndexOf(".")+1;
			//截取文件名 获得扩展名
			String extName=uploadFile.getOriginalFilename().substring(i);
			//获得图片上传后的地址         
			String url=fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//将IP地址与图片上传后的地址拼接
			String hostname="http://192.168.25.133/";
			url=hostname+url;
			//因为使用的是kindeditor的图片插件 他要求一map的格式
			HashMap map=new HashMap();
			map.put("error", 0);
			map.put("url", url);
			//将map通过json工具类转为json格式
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			HashMap map=new HashMap();
			map.put("error", 1);
			map.put("message", "上传异常");
			return JsonUtils.objectToJson(map);
		}
	
	}
}
