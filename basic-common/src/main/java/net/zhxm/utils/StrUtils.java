package net.zhxm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils extends org.apache.commons.lang3.StringUtils {

	//默认的空值
	public static final String EMPTY = "";
	
	/**
	 * 检查字符串是否为空
	 * @Title: isEmpty 
	 * @param str
	 * @return boolean
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 1:27:34 PM
	 */
	public static boolean isEmpty(Object str) {
		if (str == null) {
			return true;
		} else if (str.toString().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 如果数据null或者""则替换为new_str
	 * @Title: getNotNullStringValue 
	 * @param str
	 * @param new_str
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 1:40:53 PM
	 */
	public static String getNotNullStringValue(Object str, String new_str) {
		if (isEmpty(str)) {
			return new_str;
		}
		return str.toString();
	}
	
	/**
	 * 判断整形数据null替换new_str
	 * @Title: getNotNullIntValue 
	 * @param str
	 * @param new_str
	 * @return int
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 1:42:05 PM
	 */
	public static int getNotNullIntValue(Object str, int new_str) {
		if (isEmpty(str)) {
			return new_str;
		}
		int n_str = 0;
		try {
			n_str = Integer.parseInt(str.toString());
		} catch (NumberFormatException e) {
			n_str = new_str;
		}
		return n_str;
	}
	
	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (isEmpty(str)) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 截取字符串 并补充后缀
	 * @Title: getLimitLengthString 
	 * @param str
	 * @param len
	 * @param houzhui
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 1:35:38 PM
	 */
	public static String abbr(String str, int len, String suffix) {
		if (isEmpty(str)) {
			return "";
		}
		try{
			int counterOfDoubleByte = 0;
			byte[] b = str.getBytes("GBK"); 
			if(b.length <= len){
				return str; 
			}
			for(int i = 0; i < len; i++){
				if(b[i] < 0){
					counterOfDoubleByte++; 
				}
			}
			if(counterOfDoubleByte % 2 == 0){
				return new String(b, 0, len, "GBK") + suffix; 
			}else{
				return new String(b, 0, len - 1, "GBK") + suffix; 
			}
		}catch(Exception e){
			return ""; 
		} 
	}
	
	/**
	 * 替换掉HTML标签方法
	 * @author zhengxingmiao
	 * @param html
	 * @return
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	/**
	 * 中文转换成unicode码
	 * @author zhengxingmiao
	 * @param cnStr
	 * @return
	 */
	public static String encodeUnicode(String cnStr) {
		String s1 = "";
		for (int i = 0; i < cnStr.length(); i++) {
			String hexB = Integer.toHexString(cnStr.charAt(i) & 0xffff);
			if (hexB.length() <= 2) {      
                hexB = "00" + hexB;      
            } 
			s1 += "\\u" + hexB;
		}
		return s1;
	}

	/**
	 * unicode码 转换成 中文
	 * @author zhengxingmiao
	 * @param unicodeStr
	 * @return
	 */
	public static String decodeUnicode(String unicodeStr) {
		int n = unicodeStr.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = unicodeStr.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * URL编码转换
	 * @author zhengxingmiao
	 * @param str
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String URIEncoding(String str,String encoding) throws UnsupportedEncodingException {
		return URLDecoder.decode(str, encoding);
	}
	
	/**
	 * 获取字符串css信息
	 * @Title: getStr_CssStyle 
	 * @param str
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 1:44:34 PM
	 */
	public static String getCssStyle(String str) {
		String return_str = "";
		if (!StrUtils.isEmpty(str)) {
			String pattern = "style=('(.*?)')";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			if (m.find()) {
				return_str = m.group(2);
			}
		}
		return return_str;
	}

	
	/**
	 * HTML字符串转换
	 * 
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public static String encodeHTML(String str) {
		if (isEmpty(str)) {
			return "";
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 还原HTML字符串数据
	 * 
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public static String decodeHTML(String str) {
		if (isEmpty(str)) {
			return "";
		}
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
		return str;
	}
	
	/**
	 * Html转换为TextArea文本:编辑时拿来做转换
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public static String Html2Text(String str) {
		if (str == null) {
			return "";
		}else if (str.length() == 0) {
			return "";
		}
		str = str.replaceAll("<br />", "\n");
		str = str.replaceAll("<br />", "\r");
		return str;
	}
	
	/**
	 * TextArea文本转换为Html:写入数据库时使用
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public static String Text2Html(String str) {
		if (str == null) {
			return "";
		}else if (str.length() == 0) {
			return "";
		}
		str = str.replaceAll("\n", "<br />");
		//str = str.replaceAll("\r", "<br />");
		return str;
	}
	
	/**
	 * 字符串非法字符检测
	 * 
	 * @Title: checkStringRule
	 * @param str
	 * @return boolean
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 1:45:18 PM
	 */
	public static boolean checkStringRule(String str) {
		if (isEmpty(str)) {
			return true;
		}
		boolean b = true;
		if ((str.indexOf("`") > -1) || (str.indexOf("~") > -1)
				|| (str.indexOf("!") > -1) || (str.indexOf("#") > -1)
				|| (str.indexOf("$") > -1) || (str.indexOf("%") > -1)
				|| (str.indexOf("^") > -1) || (str.indexOf("&") > -1)
				|| (str.indexOf("*") > -1) || (str.indexOf("(") > -1)
				|| (str.indexOf(")") > -1) || (str.indexOf("=") > -1)
				|| (str.indexOf("+") > -1) || (str.indexOf("|") > -1)
				|| (str.indexOf("\\") > -1) || (str.indexOf(";") > -1)
				|| (str.indexOf(":") > -1) || (str.indexOf("'") > -1)
				|| (str.indexOf("\"") > -1) || (str.indexOf("<") > -1)
				|| (str.indexOf(",") > -1) || (str.indexOf(">") > -1)
				|| (str.indexOf("?") > -1) || (str.indexOf("　") > -1)) {
			b = false;
		}
		return b;
	}
	
	/**
	 * 去除字符串前后的逗号
	 * 
	 * zhengxingmiao Oct 13, 2014 10:26:41 AM
	 * @param string
	 * @return
	 */
	public static String deleteComma(String str) {
		str = str.replaceAll("^,*|,*$", "");
		return str;
	}

	/**
	 * Object 转 int
	 * @author lizhao
	 * @param str
	 * @return
	 */
	public static int toInt(Object str) {
		int temp = 0;
		try {
			if (str != null && !str.equals("")) {
				temp = Integer.parseInt(str.toString());
			} else {
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("String装换int报错!" + ex.getMessage());
			return 0;
		}
		return temp;
	}

	
	private final static int[] li_SecPosValue ={
		1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472,
		3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590
	};
	
	private final static String[] lc_FirstLetter ={
		"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "O", "P",
		"Q", "R", "S", "T", "W", "X", "Y", "Z"
	};
	
	/**
	 * 获取首字母
	 * @Title: getFirstLetter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param chinese
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String getFirstLetter(String chinese) {
		if (chinese == null || chinese.trim().length() == 0) {
			return "";
		}
		chinese = conversionStr(chinese, "GB2312", "ISO8859-1");
		// 判断是不是汉字
		if (chinese.length() > 1) {
			int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
			int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
			li_SectorCode = li_SectorCode - 160;
			li_PositionCode = li_PositionCode - 160;
			int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
			if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
				for (int i = 0; i < 23; i++) {
					if (li_SecPosCode >= li_SecPosValue[i] && li_SecPosCode < li_SecPosValue[i + 1]) {
						chinese = lc_FirstLetter[i];
						break;
					}
				}
			}else{
				// 非汉字字符,如图形符号或ASCII码
				chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
				chinese = chinese.substring(0, 1);
			}
		}
		return chinese;
	}
	
	private static String conversionStr(String str, String charsetName,String toCharsetName){
		try{
			str = new String(str.getBytes(charsetName), toCharsetName);
		}catch (UnsupportedEncodingException e){
			System.out.println("字符串编码转换异常：" + e.getMessage());
		}
		return str;
	}
	
}