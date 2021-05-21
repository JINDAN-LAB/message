package com.jindan.jdy.service.activiti.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成随机的字符串
 * @author Administrator
 *
 */

@Slf4j
public class RandomStrUtils {

	
	private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	
	private static Random random=new Random();
	
	/**
	 * 使用日期+时间+4位随机数生成文件名
	 */
	public static String createFileNameUseTime(String oldName){
		//得到后缀名
		String suffix=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		//生成时间的随机数
		String time=sdf1.format(new Date());
		//生成四位随机数
		int num=random.nextInt(9000)+1000;
		return time+"_"+num+suffix;
	}
	
	/**
	 * 使用UUID
	 * @param oldName
	 */
	public static String createFileNameUseUUID(String oldName){
		//得到后缀名
		String suffix=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		//生成UUID
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid+suffix;
	}
	
	public static void main(String[] args) {
		log.info("“‘RandomStrUtils类’主方法”开始执行");
		String oldName="eclipse..注释模板.xml";
		String newName=createFileNameUseUUID(oldName);
		log.info("newName的值为："+newName);

		log.info("UUID.randomUUID().toString().replace(\"-\", \"\")的值为："+UUID.randomUUID().toString().replace("-", ""));
	}

	/**
	 * 得到当前上期下的日期  去生成文件夹名
	 * @return
	 */
	public static String getCurrentDateToStr() {
		return sdf2.format(new Date());
	}
}
