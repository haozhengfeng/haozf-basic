package org.haozf.basic.springjdbc;

import org.joda.time.DateTime;

public class TestJodaTime {

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args    参数
	 * @return void    返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateTime dt = new DateTime();
		System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(dt.toString("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"));
	}

}
