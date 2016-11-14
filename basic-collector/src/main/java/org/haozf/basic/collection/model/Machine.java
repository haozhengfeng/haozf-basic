package org.haozf.basic.collection.model;

import java.util.HashMap;
import java.util.Map;

public class Machine {
	
	public static Map<String,Machine> machines = new HashMap<String,Machine>();
	
	// 设备类型：挖掘机
	// 设备品牌：玉柴
	// 设备型号：YC210LC-8
	// 设备序列号：00818
	// 工作小时：6小时
	// 出厂日期：2011年
	// 合格证：有
	// 发票：无
	// 是否打过锤：无
	// 发布时间：2016-04-22

	private String no;
	private String classes;
	private String brand;
	private String model;
	private String worktime;
	private String date;
	

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "Machine [no=" + no + ", classes=" + classes + ", brand=" + brand + ", model=" + model + ", worktime=" + worktime + ", date=" + date + "]";
	}

}
