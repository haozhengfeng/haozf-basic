package net.zhxm.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 文件操作工具类, 继承org.apache.commons.io.FileUtils类
 * FileUtils
 * @author zhengxingmiao
 * @date Aug 10, 2013
 */
public class FileUtils extends org.apache.commons.io.FileUtils{

	/**
	 * 创建目录
	 * @param descDirName 目录名,包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static void createDirectory(String descDirName) {
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (!descDir.exists()) {
			// 创建目录
			descDir.mkdirs();
		}
	}
	
	/**
	 * 生成文件名
	 * 返回日期文件名+5位随机数：20140306125115105+88888
	 * </br>zhengxingmiao  Mar 6, 2014
	 * </br>修改者名字 修改日期
	 * </br>修改内容
	 * @return
	 */
	public static String getDateFileName() {
		// System.out.println("时间戳="+System.currentTimeMillis());
		String fileName = "nofilename";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		fileName = formatter.format(date);
		//System.out.println("fileName=" + fileName);
		// new Random().nextInt(9000)
		String random = RandomStringUtils.randomNumeric(5);
		//System.out.println("random=" + random);
		return new StringBuilder().append(fileName).append(random).toString();
	}
	
	
	
	
	
	
}
