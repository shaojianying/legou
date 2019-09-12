package com.legou.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastdfsDemo {
public static void main(String[] args) throws FileNotFoundException, IOException, MyException {
	//读取fastdfs配置文件
	ClientGlobal.init("D:\\legouDemo\\legou\\legou-manager-web\\src\\main\\resources\\config\\fastdfs.properties");
	//创建TrackerClient用来连接Trackerserver
	TrackerClient trackerClient=new TrackerClient();
	TrackerServer trackerServer =trackerClient.getConnection();//秘书
	
	StorageServer storageServer=null;//存照片
	StorageClient storageClient=new StorageClient(trackerServer, storageServer);
	String[] strings=storageClient.upload_appender_file("D://timg.jpg", "jpg",null);
	for (String string : strings) {
		System.out.println(string);
	}
	
}
}
