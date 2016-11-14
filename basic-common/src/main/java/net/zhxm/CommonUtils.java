package net.zhxm;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonUtils {
	
	/**
	 * 格式化省份名称
	 * @param ProvinceName 省份
	 * @return 格式化后的省份
	 */
	public static String formatProvinceName(String ProvinceName) {
		StringBuilder sb = new StringBuilder();
		//System.out.println(str);
		if("全国".equals(ProvinceName)){
			sb.append("china");
		}else if("北京市".equals(ProvinceName)){
			sb.append("北京");
		}else if("天津市".equals(ProvinceName)){
			sb.append("天津");
		}else if("上海市".equals(ProvinceName)){
			sb.append("上海");
		}else if("重庆市".equals(ProvinceName)){
			sb.append("重庆");
		}else if("内蒙古自治区".equals(ProvinceName)){
			sb.append("内蒙古");
		}else if("广西壮族自治区".equals(ProvinceName)){
			sb.append("广西");
		}else if("西藏自治区".equals(ProvinceName)){
			sb.append("西藏");
		}else if("宁夏回族自治区".equals(ProvinceName)){
			sb.append("宁夏");
		}else if("新疆维吾尔自治区".equals(ProvinceName)){
			sb.append("新疆");
		}else if("台湾省".equals(ProvinceName)){
			sb.append("台湾");
		}else if("香港特别行政区".equals(ProvinceName)){
			sb.append("香港");
		}else if("澳门特别行政区".equals(ProvinceName)){
			sb.append("澳门");
		}else{
			sb.append( ProvinceName.replace("省", "") );
		}
		return sb.toString();
	}
	
	/**
	 * 格式化省份
	 * zhengxingmiao Jun 23, 2015 10:33:57 AM
	 * @param list 省份列表
	 * @return 格式化后的省份
	 */
	public static ArrayList formatProvince(ArrayList list) {
		ArrayList list_new = new ArrayList();
		int size = list.size();
		if(size>0){
			HashMap hm = null;
			for(int i=0;i<size;i++){
				hm = (HashMap)list.get(i);
				
				if("内蒙古自治区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","内蒙古");
				}
				if("广西壮族自治区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","广西");
				}
				if("西藏自治区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","西藏");
				}
				if("宁夏回族自治区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","宁夏");
				}
				if("新疆维吾尔自治区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","新疆");
				}
				if("台湾省".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","台湾");
				}
				if("香港特别行政区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","香港");
				}
				if("澳门特别行政区".equals(hm.get("area_prov").toString())){
					hm.put("area_prov","澳门");
				}
				list_new.add(hm);
			}
		}
		return list_new;
	}
	
	/**
	 * 计算同比环比结果
	 * @param nowYearData 今年
	 * @param lastYearData 去年
	 * @return 返回同比、环比结果
	 */
	public static Object getYoYMoM(float nowYearData, float lastYearData) {
		//System.out.println("s1="+s1);
		//System.out.println("s2="+s2);
		if( nowYearData==0.0 || lastYearData==0.0){
			return 0;
		}
		return (int)((nowYearData/lastYearData)*100-100) ;
	}
	
}
