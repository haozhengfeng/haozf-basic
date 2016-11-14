package net.zhxm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Web开发工具类集合
 * 
 * <pre>Title: WebUtils.java</pre> 
 * <pre>Description:IT部</pre> 
 * <pre>Copyright: Copyright (c) 2014</pre> 
 * <pre>Company:石家庄天远科技有限公司</pre> 
 * @author zhengxingmiao
 * @date Sep 24, 2014 2:15:32 PM
 * @version V1.0
 */
public class WebCommonUtils {
	
	/**
	 * main方法
	 * 
	 * zhengxingmiao Sep 24, 2014 2:12:12 PM
	 * @param args
	 */
	public static void main(String args[]) {
		
		System.out.println("==========1 禁用账户===========");
		WebCommonUtils.setDisableAccount("郑兴苗");
		System.out.println(getListDisableAccount());
		
		System.out.println("==========2 汉字转拼音===========");
		System.out.println(getFullSpell("汉字转化为拼音，中国"));
		
		System.out.println("==========3 根据IP解析地址===========");
		String ip = "222.223.231.137";
		System.out.println("==========淘宝IP解析服务===========");
		HashMap<String, String> tb_hm = getTaoBaoIpInfo(ip);
		System.out.println("country："+tb_hm.get("country"));
		System.out.println("area："+tb_hm.get("area"));
		System.out.println("province："+tb_hm.get("province"));
		System.out.println("city："+tb_hm.get("city"));
		System.out.println("county："+tb_hm.get("county"));
		
		System.out.println("==========新浪IP解析服务===========");
		HashMap<String, String> sina_hm = getSiNaIpInfo(ip);
		System.out.println("country："+sina_hm.get("country"));
		System.out.println("province："+sina_hm.get("province"));
		System.out.println("city："+sina_hm.get("city"));
		System.out.println("district："+sina_hm.get("district"));
		
		System.out.println("==========5 百度地图经纬度地址相互转换===========");
		String lnglat = GetLngLatByAddr("北京市海淀区中关村大街27号1101-08室人民大学,中关村,苏州街","");
		System.out.println("lnglat="+lnglat);
	}
	
	// 1、禁用账户部分

	private static ArrayList<String> disable_account_list = new ArrayList<String>();
	
	/**
	 * 获取禁止注册的帐户名列表
	 * zhengxingmiao Sep 24, 2014 2:09:49 PM
	 * @return
	 */
	public static ArrayList<String> getListDisableAccount(){
		disable_account_list.add("administrator");
		disable_account_list.add("admin");
		disable_account_list.add("adnim");
		disable_account_list.add("管理员");
		return disable_account_list;
	}
	
	/**
	 * 添加禁用账户
	 * zhengxingmiao Sep 24, 2014 2:09:49 PM
	 * @return 
	 * @return
	 */
	public static void setDisableAccount(String str){
		disable_account_list.add(str);
	}
	
	
	// 2、汉字转拼音部分
	
	private static LinkedHashMap<String, Integer> spellMap = null;

	static {
		if (spellMap == null) {
			spellMap = new LinkedHashMap<String, Integer>(400);
		}
		initialize();
	}

	private static void spellPut(String spell, int ascii) {
		spellMap.put(spell, new Integer(ascii));
	}

	private static void initialize() {
		spellPut("a", -20319);
		spellPut("ai", -20317);
		spellPut("an", -20304);
		spellPut("ang", -20295);
		spellPut("ao", -20292);
		spellPut("ba", -20283);
		spellPut("bai", -20265);
		spellPut("ban", -20257);
		spellPut("bang", -20242);
		spellPut("bao", -20230);
		spellPut("bei", -20051);
		spellPut("ben", -20036);
		spellPut("beng", -20032);
		spellPut("bi", -20026);
		spellPut("bian", -20002);
		spellPut("biao", -19990);
		spellPut("bie", -19986);
		spellPut("bin", -19982);
		spellPut("bing", -19976);
		spellPut("bo", -19805);
		spellPut("bu", -19784);
		spellPut("ca", -19775);
		spellPut("cai", -19774);
		spellPut("can", -19763);
		spellPut("cang", -19756);
		spellPut("cao", -19751);
		spellPut("ce", -19746);
		spellPut("ceng", -19741);
		spellPut("cha", -19739);
		spellPut("chai", -19728);
		spellPut("chan", -19725);
		spellPut("chang", -19715);
		spellPut("chao", -19540);
		spellPut("che", -19531);
		spellPut("chen", -19525);
		spellPut("cheng", -19515);
		spellPut("chi", -19500);
		spellPut("chong", -19484);
		spellPut("chou", -19479);
		spellPut("chu", -19467);
		spellPut("chuai", -19289);
		spellPut("chuan", -19288);
		spellPut("chuang", -19281);
		spellPut("chui", -19275);
		spellPut("chun", -19270);
		spellPut("chuo", -19263);
		spellPut("ci", -19261);
		spellPut("cong", -19249);
		spellPut("cou", -19243);
		spellPut("cu", -19242);
		spellPut("cuan", -19238);
		spellPut("cui", -19235);
		spellPut("cun", -19227);
		spellPut("cuo", -19224);
		spellPut("da", -19218);
		spellPut("dai", -19212);
		spellPut("dan", -19038);
		spellPut("dang", -19023);
		spellPut("dao", -19018);
		spellPut("de", -19006);
		spellPut("deng", -19003);
		spellPut("di", -18996);
		spellPut("dian", -18977);
		spellPut("diao", -18961);
		spellPut("die", -18952);
		spellPut("ding", -18783);
		spellPut("diu", -18774);
		spellPut("dong", -18773);
		spellPut("dou", -18763);
		spellPut("du", -18756);
		spellPut("duan", -18741);
		spellPut("dui", -18735);
		spellPut("dun", -18731);
		spellPut("duo", -18722);
		spellPut("e", -18710);
		spellPut("en", -18697);
		spellPut("er", -18696);
		spellPut("fa", -18526);
		spellPut("fan", -18518);
		spellPut("fang", -18501);
		spellPut("fei", -18490);
		spellPut("fen", -18478);
		spellPut("feng", -18463);
		spellPut("fo", -18448);
		spellPut("fou", -18447);
		spellPut("fu", -18446);
		spellPut("ga", -18239);
		spellPut("gai", -18237);
		spellPut("gan", -18231);
		spellPut("gang", -18220);
		spellPut("gao", -18211);
		spellPut("ge", -18201);
		spellPut("gei", -18184);
		spellPut("gen", -18183);
		spellPut("geng", -18181);
		spellPut("gong", -18012);
		spellPut("gou", -17997);
		spellPut("gu", -17988);
		spellPut("gua", -17970);
		spellPut("guai", -17964);
		spellPut("guan", -17961);
		spellPut("guang", -17950);
		spellPut("gui", -17947);
		spellPut("gun", -17931);
		spellPut("guo", -17928);
		spellPut("ha", -17922);
		spellPut("hai", -17759);
		spellPut("han", -17752);
		spellPut("hang", -17733);
		spellPut("hao", -17730);
		spellPut("he", -17721);
		spellPut("hei", -17703);
		spellPut("hen", -17701);
		spellPut("heng", -17697);
		spellPut("hong", -17692);
		spellPut("hou", -17683);
		spellPut("hu", -17676);
		spellPut("hua", -17496);
		spellPut("huai", -17487);
		spellPut("huan", -17482);
		spellPut("huang", -17468);
		spellPut("hui", -17454);
		spellPut("hun", -17433);
		spellPut("huo", -17427);
		spellPut("ji", -17417);
		spellPut("jia", -17202);
		spellPut("jian", -17185);
		spellPut("jiang", -16983);
		spellPut("jiao", -16970);
		spellPut("jie", -16942);
		spellPut("jin", -16915);
		spellPut("jing", -16733);
		spellPut("jiong", -16708);
		spellPut("jiu", -16706);
		spellPut("ju", -16689);
		spellPut("juan", -16664);
		spellPut("jue", -16657);
		spellPut("jun", -16647);
		spellPut("ka", -16474);
		spellPut("kai", -16470);
		spellPut("kan", -16465);
		spellPut("kang", -16459);
		spellPut("kao", -16452);
		spellPut("ke", -16448);
		spellPut("ken", -16433);
		spellPut("keng", -16429);
		spellPut("kong", -16427);
		spellPut("kou", -16423);
		spellPut("ku", -16419);
		spellPut("kua", -16412);
		spellPut("kuai", -16407);
		spellPut("kuan", -16403);
		spellPut("kuang", -16401);
		spellPut("kui", -16393);
		spellPut("kun", -16220);
		spellPut("kuo", -16216);
		spellPut("la", -16212);
		spellPut("lai", -16205);
		spellPut("lan", -16202);
		spellPut("lang", -16187);
		spellPut("lao", -16180);
		spellPut("le", -16171);
		spellPut("lei", -16169);
		spellPut("leng", -16158);
		spellPut("li", -16155);
		spellPut("lia", -15959);
		spellPut("lian", -15958);
		spellPut("liang", -15944);
		spellPut("liao", -15933);
		spellPut("lie", -15920);
		spellPut("lin", -15915);
		spellPut("ling", -15903);
		spellPut("liu", -15889);
		spellPut("long", -15878);
		spellPut("lou", -15707);
		spellPut("lu", -15701);
		spellPut("lv", -15681);
		spellPut("luan", -15667);
		spellPut("lue", -15661);
		spellPut("lun", -15659);
		spellPut("luo", -15652);
		spellPut("ma", -15640);
		spellPut("mai", -15631);
		spellPut("man", -15625);
		spellPut("mang", -15454);
		spellPut("mao", -15448);
		spellPut("me", -15436);
		spellPut("mei", -15435);
		spellPut("men", -15419);
		spellPut("meng", -15416);
		spellPut("mi", -15408);
		spellPut("mian", -15394);
		spellPut("miao", -15385);
		spellPut("mie", -15377);
		spellPut("min", -15375);
		spellPut("ming", -15369);
		spellPut("miu", -15363);
		spellPut("mo", -15362);
		spellPut("mou", -15183);
		spellPut("mu", -15180);
		spellPut("na", -15165);
		spellPut("nai", -15158);
		spellPut("nan", -15153);
		spellPut("nang", -15150);
		spellPut("nao", -15149);
		spellPut("ne", -15144);
		spellPut("nei", -15143);
		spellPut("nen", -15141);
		spellPut("neng", -15140);
		spellPut("ni", -15139);
		spellPut("nian", -15128);
		spellPut("niang", -15121);
		spellPut("niao", -15119);
		spellPut("nie", -15117);
		spellPut("nin", -15110);
		spellPut("ning", -15109);
		spellPut("niu", -14941);
		spellPut("nong", -14937);
		spellPut("nu", -14933);
		spellPut("nv", -14930);
		spellPut("nuan", -14929);
		spellPut("nue", -14928);
		spellPut("nuo", -14926);
		spellPut("o", -14922);
		spellPut("ou", -14921);
		spellPut("pa", -14914);
		spellPut("pai", -14908);
		spellPut("pan", -14902);
		spellPut("pang", -14894);
		spellPut("pao", -14889);
		spellPut("pei", -14882);
		spellPut("pen", -14873);
		spellPut("peng", -14871);
		spellPut("pi", -14857);
		spellPut("pian", -14678);
		spellPut("piao", -14674);
		spellPut("pie", -14670);
		spellPut("pin", -14668);
		spellPut("ping", -14663);
		spellPut("po", -14654);
		spellPut("pu", -14645);
		spellPut("qi", -14630);
		spellPut("qia", -14594);
		spellPut("qian", -14429);
		spellPut("qiang", -14407);
		spellPut("qiao", -14399);
		spellPut("qie", -14384);
		spellPut("qin", -14379);
		spellPut("qing", -14368);
		spellPut("qiong", -14355);
		spellPut("qiu", -14353);
		spellPut("qu", -14345);
		spellPut("quan", -14170);
		spellPut("que", -14159);
		spellPut("qun", -14151);
		spellPut("ran", -14149);
		spellPut("rang", -14145);
		spellPut("rao", -14140);
		spellPut("re", -14137);
		spellPut("ren", -14135);
		spellPut("reng", -14125);
		spellPut("ri", -14123);
		spellPut("rong", -14122);
		spellPut("rou", -14112);
		spellPut("ru", -14109);
		spellPut("ruan", -14099);
		spellPut("rui", -14097);
		spellPut("run", -14094);
		spellPut("ruo", -14092);
		spellPut("sa", -14090);
		spellPut("sai", -14087);
		spellPut("san", -14083);
		spellPut("sang", -13917);
		spellPut("sao", -13914);
		spellPut("se", -13910);
		spellPut("sen", -13907);
		spellPut("seng", -13906);
		spellPut("sha", -13905);
		spellPut("shai", -13896);
		spellPut("shan", -13894);
		spellPut("shang", -13878);
		spellPut("shao", -13870);
		spellPut("she", -13859);
		spellPut("shen", -13847);
		spellPut("sheng", -13831);
		spellPut("shi", -13658);
		spellPut("shou", -13611);
		spellPut("shu", -13601);
		spellPut("shua", -13406);
		spellPut("shuai", -13404);
		spellPut("shuan", -13400);
		spellPut("shuang", -13398);
		spellPut("shui", -13395);
		spellPut("shun", -13391);
		spellPut("shuo", -13387);
		spellPut("si", -13383);
		spellPut("song", -13367);
		spellPut("sou", -13359);
		spellPut("su", -13356);
		spellPut("suan", -13343);
		spellPut("sui", -13340);
		spellPut("sun", -13329);
		spellPut("suo", -13326);
		spellPut("ta", -13318);
		spellPut("tai", -13147);
		spellPut("tan", -13138);
		spellPut("tang", -13120);
		spellPut("tao", -13107);
		spellPut("te", -13096);
		spellPut("teng", -13095);
		spellPut("ti", -13091);
		spellPut("tian", -13076);
		spellPut("tiao", -13068);
		spellPut("tie", -13063);
		spellPut("ting", -13060);
		spellPut("tong", -12888);
		spellPut("tou", -12875);
		spellPut("tu", -12871);
		spellPut("tuan", -12860);
		spellPut("tui", -12858);
		spellPut("tun", -12852);
		spellPut("tuo", -12849);
		spellPut("wa", -12838);
		spellPut("wai", -12831);
		spellPut("wan", -12829);
		spellPut("wang", -12812);
		spellPut("wei", -12802);
		spellPut("wen", -12607);
		spellPut("weng", -12597);
		spellPut("wo", -12594);
		spellPut("wu", -12585);
		spellPut("xi", -12556);
		spellPut("xia", -12359);
		spellPut("xian", -12346);
		spellPut("xiang", -12320);
		spellPut("xiao", -12300);
		spellPut("xie", -12120);
		spellPut("xin", -12099);
		spellPut("xing", -12089);
		spellPut("xiong", -12074);
		spellPut("xiu", -12067);
		spellPut("xu", -12058);
		spellPut("xuan", -12039);
		spellPut("xue", -11867);
		spellPut("xun", -11861);
		spellPut("ya", -11847);
		spellPut("yan", -11831);
		spellPut("yang", -11798);
		spellPut("yao", -11781);
		spellPut("ye", -11604);
		spellPut("yi", -11589);
		spellPut("yin", -11536);
		spellPut("ying", -11358);
		spellPut("yo", -11340);
		spellPut("yong", -11339);
		spellPut("you", -11324);
		spellPut("yu", -11303);
		spellPut("yuan", -11097);
		spellPut("yue", -11077);
		spellPut("yun", -11067);
		spellPut("za", -11055);
		spellPut("zai", -11052);
		spellPut("zan", -11045);
		spellPut("zang", -11041);
		spellPut("zao", -11038);
		spellPut("ze", -11024);
		spellPut("zei", -11020);
		spellPut("zen", -11019);
		spellPut("zeng", -11018);
		spellPut("zha", -11014);
		spellPut("zhai", -10838);
		spellPut("zhan", -10832);
		spellPut("zhang", -10815);
		spellPut("zhao", -10800);
		spellPut("zhe", -10790);
		spellPut("zhen", -10780);
		spellPut("zheng", -10764);
		spellPut("zhi", -10587);
		spellPut("zhong", -10544);
		spellPut("zhou", -10533);
		spellPut("zhu", -10519);
		spellPut("zhua", -10331);
		spellPut("zhuai", -10329);
		spellPut("zhuan", -10328);
		spellPut("zhuang", -10322);
		spellPut("zhui", -10315);
		spellPut("zhun", -10309);
		spellPut("zhuo", -10307);
		spellPut("zi", -10296);
		spellPut("zong", -10281);
		spellPut("zou", -10274);
		spellPut("zu", -10270);
		spellPut("zuan", -10262);
		spellPut("zui", -10260);
		spellPut("zun", -10256);
		spellPut("zuo", -10254);
	}

	/**
	 * 获得单个汉字的Ascii
	 * @author zhengxingmiao
	 * @param cn
	 * @return
	 */
	public static int getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
			return 0;
		}
		if (bytes.length == 1) { // 英文字符
			return bytes[0];
		}
		if (bytes.length == 2) { // 中文字符
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte) - 256 * 256;
			// System.out.println("ASCII=" + ascii);
			return ascii;
		}
		return 0; // 错误
	}

	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param ascii int 字符对应的ASCII
	 * @return String 拼音,首先判断ASCII是否>0&<160,如果是返回对应的字符, <BR>
	 *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	public static String getSpellByAscii(int ascii) {
		if (ascii > 0 && ascii < 160) { // 单字符
			return String.valueOf((char) ascii);
		}
		if (ascii < -20319 || ascii > -10247) { // 不知道的字符
			return null;
		}
		Set keySet = spellMap.keySet();
		Iterator it = keySet.iterator();
		String spell0 = null;
		String spell = null;
		int asciiRang0 = -20319;
		int asciiRang;
		while (it.hasNext()) {
			spell = (String) it.next();
			Object valObj = spellMap.get(spell);
			if (valObj instanceof Integer) {
				asciiRang = ((Integer) valObj).intValue();
				if (ascii >= asciiRang0 && ascii < asciiRang) { // 区间找到
					return (spell0 == null) ? spell : spell0;
				} else {
					spell0 = spell;
					asciiRang0 = asciiRang;
				}
			}
		}
		return null;
	}

	/**
	 * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
	 * 
	 * @param 汉字字符串cnStr
	 * @return String 转换成全拼后的字符串
	 */
	public static String getFullSpell(String cnStr) {
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}
		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			int ascii = getCnAscii(chars[i]);
			if (ascii == 0) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				String spell = getSpellByAscii(ascii);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell + "");// 以空格分开
				}
			}
		}
		return retuBuf.toString();
	}	
	
	
	// 3、根据IP地址解析
	
	/**
	 * 淘宝IP地址服务
	 * @author zhengxingmiao
	 * @param ip
	 * @return
	 */
	public static HashMap<String, String> getTaoBaoIpInfo(String ip) {
		HashMap<String, String> defaultMap = new HashMap<String, String>();
		defaultMap.put("country_id", "0");
		defaultMap.put("country", "");
		defaultMap.put("area_id", "0");
		defaultMap.put("area", "");
		defaultMap.put("province_id", "0");
		defaultMap.put("province", "");
		defaultMap.put("city_id", "0");
		defaultMap.put("city", "");
		defaultMap.put("county_id", "0");
		defaultMap.put("county", "");
		
		if (ip.equals("127.0.0.1")) {
			return defaultMap;
		}

		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip + "");
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			System.setProperty("sun.net.client.defaultConnectTimeout", "150000");
			System.setProperty("sun.net.client.defaultReadTimeout", "150000");
			httpConn.setRequestMethod("GET");//设置请求为POST方法
			connection.setDoOutput(true);//可以输出
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				temp = temp.trim();
				if (temp != null && temp.length() > 0) {
					//temp=temp.replaceAll("&lt;","<");
					//temp=temp.replaceAll("&gt;",">");
					sb.append(temp);
				}
			}
			br.close();
			isr.close();
			System.out.println(sb);
			//String remote_ip_info = sb.substring(sb.indexOf("{"), sb.lastIndexOf("}")+1);
			//System.out.println(sb);
			JSONObject jsonObj = JSON.parseObject(sb.toString());
			if ("0".equals(jsonObj.get("code").toString())) {
				//System.out.println("成功");
				JSONObject dataJSON = JSON.parseObject(jsonObj.get("data").toString());
				/*
				System.out.println("country_id:"+dataJSON.get("country_id"));
				System.out.println("country:"+dataJSON.get("country"));
				System.out.println("area_id:"+dataJSON.get("area_id"));
				System.out.println("area:"+dataJSON.get("area"));
				System.out.println("region_id:"+dataJSON.get("region_id"));
				System.out.println("region:"+dataJSON.get("region"));
				System.out.println("city_id:"+dataJSON.get("city_id"));
				System.out.println("city:"+dataJSON.get("city"));
				System.out.println("county_id:"+dataJSON.get("county_id"));
				System.out.println("county:"+dataJSON.get("county"));
				System.out.println("isp_id:"+dataJSON.get("isp_id"));
				System.out.println("isp:"+dataJSON.get("isp"));
				 */
				defaultMap.put("country_id", dataJSON.getString("country_id"));
				defaultMap.put("country", dataJSON.getString("country"));
				defaultMap.put("area_id", dataJSON.getString("area_id"));
				defaultMap.put("area", dataJSON.getString("area"));
				defaultMap.put("province_id", dataJSON.getString("region_id"));
				defaultMap.put("province", dataJSON.getString("region"));
				defaultMap.put("city_id", dataJSON.getString("city_id"));
				defaultMap.put("city", dataJSON.getString("city"));
				defaultMap.put("county_id", dataJSON.getString("county_id"));
				defaultMap.put("county", dataJSON.getString("county"));
			} else {
				return defaultMap;
			}
		} catch (Exception e) {
			System.out.println("Error：" + e.getMessage());
		}
		return defaultMap;
	}

	/**
	 * 新浪IP地址服务
	 * @author zhengxingmiao
	 * @param ip
	 * @return
	 */
	public static HashMap<String, String> getSiNaIpInfo(String ip) {
		
		HashMap<String, String> hm = new HashMap<String, String>();
		if(ip.equals("127.0.0.1")){
			hm.put("country", "");
			hm.put("province", "");
			hm.put("city", "");
			hm.put("district", "");
			return hm;
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip+"");
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			System.setProperty("sun.net.client.defaultConnectTimeout","150000");
			System.setProperty("sun.net.client.defaultReadTimeout", "150000");
			httpConn.setRequestMethod("GET");//设置请求为POST方法
			connection.setDoOutput(true);//可以输出
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				temp = temp.trim();
				if (temp != null && temp.length() > 0) {
					//temp=temp.replaceAll("&lt;","<");
					//temp=temp.replaceAll("&gt;",">");
					sb.append(temp);
				}
			}
			br.close();
			isr.close();
			//System.out.println(sb);
			String remote_ip_info = sb.substring(sb.indexOf("{"), sb.lastIndexOf("}")+1);
			//System.out.println(remote_ip_info);
			JSONObject jsonObj = JSON.parseObject(remote_ip_info);
			hm.put("country", jsonObj.getString("country"));
			hm.put("province", jsonObj.getString("province"));
			hm.put("city", jsonObj.getString("city"));
			hm.put("district", jsonObj.getString("district"));
		} catch (Exception e) {
			System.out.println("Error：" + e.getMessage());
		}
		return hm;
	}
	
	
	// 4、Request和Cookie部分
	
	/**
	 * 判断是否为搜索引擎
	 * @author：zhengxingmiao
	 * @method: isRobot
	 * @param req
	 * @return
	 * @time: Aug 24, 2011 2:21:44 PM
	 */
	public static boolean isRobot(HttpServletRequest req) {
		String ua = req.getHeader("user-agent");
		if (StringUtils.isBlank(ua))
			return false;
		return (ua != null && (ua.indexOf("Baiduspider") != -1
				|| ua.indexOf("Googlebot") != -1 || ua.indexOf("sogou") != -1
				|| ua.indexOf("sina") != -1 || ua.indexOf("iaskspider") != -1
				|| ua.indexOf("ia_archiver") != -1
				|| ua.indexOf("Sosospider") != -1
				|| ua.indexOf("YoudaoBot") != -1 || ua.indexOf("yahoo") != -1
				|| ua.indexOf("yodao") != -1 || ua.indexOf("MSNBot") != -1
				|| ua.indexOf("spider") != -1 || ua.indexOf("Twiceler") != -1
				|| ua.indexOf("Sosoimagespider") != -1
				|| ua.indexOf("naver.com/robots") != -1
				|| ua.indexOf("Nutch") != -1 || ua.indexOf("spider") != -1));
	}

	/**
	 * 获取客户端IP地址，此方法用在proxy环境中
	 * @author：zhengxingmiao
	 * @method: getRemoteAddr
	 * @param req
	 * @return
	 * @time: Aug 24, 2011 2:21:06 PM
	 */
	public static String getRemoteAddr(HttpServletRequest req) {
		String ip = req.getHeader("X-Forwarded-For");
		if (StringUtils.isNotBlank(ip)) {
			String[] ips = StringUtils.split(ip, ',');
			if (ips != null) {
				for (String tmpip : ips) {
					if (StringUtils.isBlank(tmpip))
						continue;
					tmpip = tmpip.trim();
					if (isIPAddr(tmpip) && !tmpip.startsWith("10.")
							&& !tmpip.startsWith("192.168.")
							&& !"127.0.0.1".equals(tmpip)) {
						return tmpip.trim();
					}
				}
			}
		}
		ip = req.getHeader("x-real-ip");
		if (isIPAddr(ip))
			return ip;
		ip = req.getRemoteAddr();
		if (ip.indexOf('.') == -1)
			ip = "127.0.0.1";
		return ip;
	}
	
	/**
	 * 判断字符串是否是一个IP地址
	 * @author：zhengxingmiao
	 * @method: isIPAddr
	 * @param addr
	 * @return
	 * @time: Aug 24, 2011 2:23:46 PM
	 */
	public static boolean isIPAddr(String addr) {
		if (StringUtils.isEmpty(addr))
			return false;
		String[] ips = StringUtils.split(addr, '.');
		if (ips.length != 4)
			return false;
		try {
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0
					&& ipc <= 255 && ipd >= 0 && ipd <= 255;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 获取HTTP端口
	 * @author：zhengxingmiao
	 * @method: getHttpPort
	 * @param req
	 * @return
	 * @time: Aug 24, 2011 2:23:59 PM
	 */
	public static int getHttpPort(HttpServletRequest req) {
		try {
			return new URL(req.getRequestURL().toString()).getPort();
		} catch (MalformedURLException excp) {
			return 80;
		}
	}

	/**
	 * 获取浏览器提交的整形参数
	 * @author：zhengxingmiao
	 * @method: getParam
	 * @param req
	 * @param param
	 * @param defaultValue
	 * @return
	 * @time: Aug 24, 2011 2:24:05 PM
	 */
	public static int getParam(HttpServletRequest req, String param,
			int defaultValue) {
		return NumberUtils.toInt(req.getParameter(param), defaultValue);
	}
	
	/**
	 * 获取浏览器提交的整形参数
	 * @author：zhengxingmiao
	 * @method: getParam
	 * @param req
	 * @param param
	 * @param defaultValue
	 * @return
	 * @time: Aug 24, 2011 2:24:39 PM
	 */
	public static long getParam(HttpServletRequest req, String param,
			long defaultValue) {
		return NumberUtils.toLong(req.getParameter(param), defaultValue);
	}

	/**
	 * 获取浏览器提交的参数数组
	 * @author：zhengxingmiao
	 * @method: getParamValues
	 * @param req
	 * @param name
	 * @return
	 * @time: Aug 24, 2011 2:24:44 PM
	 */
	public static long[] getParamValues(HttpServletRequest req, String name) {
		String[] values = req.getParameterValues(name);
		if (values == null)
			return null;
		return (long[]) ConvertUtils.convert(values, long.class);
	}

	/**
	 * 获取浏览器提交的字符串参数
	 * @author：zhengxingmiao
	 * @method: getParam
	 * @param req
	 * @param param
	 * @param defaultValue
	 * @return
	 * @time: Aug 24, 2011 2:25:08 PM
	 */
	public static String getParam(HttpServletRequest req, String param,
			String defaultValue) {
		String value = req.getParameter(param);
		return (StringUtils.isEmpty(value)) ? defaultValue : value;
	}
	
	/**
	 * 获取用户访问URL中的根域名 例如: www.dlog.cn -> dlog.cn
	 * @author：zhengxingmiao
	 * @method: getDomainOfServerName
	 * @param host
	 * @return
	 * @time: Aug 24, 2011 2:23:39 PM
	 */
	public static String getDomainOfServerName(String host) {
		if (isIPAddr(host))
			return null;
		String[] names = StringUtils.split(host, '.');
		int len = names.length;
		if (len == 1)
			return null;
		if (len == 3) {
			return makeup(names[len - 2], names[len - 1]);
		}
		if (len > 3) {
			String dp = names[len - 2];
			if (dp.equalsIgnoreCase("com") || dp.equalsIgnoreCase("gov")
					|| dp.equalsIgnoreCase("net") || dp.equalsIgnoreCase("edu")
					|| dp.equalsIgnoreCase("org"))
				return makeup(names[len - 3], names[len - 2], names[len - 1]);
			else
				return makeup(names[len - 2], names[len - 1]);
		}
		return host;
	}
	
	private static String makeup(String... ps) {
		StringBuilder s = new StringBuilder();
		for (int idx = 0; idx < ps.length; idx++) {
			if (idx > 0)
				s.append('.');
			s.append(ps[idx]);
		}
		return s.toString();
	}
	
	/**
	 * 获取COOKIE
	 * @author：zhengxingmiao
	 * @method: getCookie
	 * @param request
	 * @param name
	 * @return
	 * @time: Aug 24, 2011 2:21:55 PM
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie ck : cookies) {
			if (StringUtils.equalsIgnoreCase(name, ck.getName()))
				return ck;
		}
		return null;
	}
	
	/**
	 * 获取COOKIE
	 * @author：zhengxingmiao
	 * @method: getCookieValue
	 * @param request
	 * @param name
	 * @return
	 * @time: Aug 24, 2011 2:22:07 PM
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie ck : cookies) {
			if (StringUtils.equalsIgnoreCase(name, ck.getName()))
				return ck.getValue();
		}
		return null;
	}
	
	/**
	 * 设置COOKIE
	 * @author：zhengxingmiao
	 * @method: setCookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @time: Aug 24, 2011 2:22:13 PM
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		setCookie(request, response, name, value, maxAge, true);
	}
	
	/**
	 * 设置COOKIE
	 * @author：zhengxingmiao
	 * @method: setCookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param all_sub_domain
	 * @time: Aug 24, 2011 2:22:22 PM
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			int maxAge, boolean all_sub_domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		if (all_sub_domain) {
			String serverName = request.getServerName();
			String domain = getDomainOfServerName(serverName);
			if (domain != null && domain.indexOf('.') != -1) {
				cookie.setDomain('.' + domain);
			}
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 删除 COOKIE
	 * @author：zhengxingmiao
	 * @method: deleteCookie
	 * @param request
	 * @param response
	 * @param name
	 * @param all_sub_domain
	 * @time: Aug 24, 2011 2:22:36 PM
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String name, boolean all_sub_domain) {
		setCookie(request, response, name, "", 0, all_sub_domain);
	}
	

	// 5、百度地图经纬度地址相互转换
	
	/**
	 * 百度地图根据经纬度查询地址信息
	 * @author zhengxingmiao
	 * @param Longitude
	 * @param Latitude
	 * @return
	 */
	public static String GetAddrByLngLat(String longitude,String latitude) {
		String addr = "无法获取地址";
		//String url = "http://maps.googleapis.com/maps/api/geocode/xml?latlng="+longitude+","+latitude+"&sensor=false&language=zh-CN";
		StringBuilder url = new StringBuilder("");
		url.append("http://api.map.baidu.com/geocoder/v2/?ak=");
		// 百度密钥key
		url.append("99ca2880b33516d259d2579c669eda91");
		url.append("&callback=renderReverse");
		url.append("&location="+latitude+","+longitude+"");
		url.append("&output=json&pois=0");
		url.append("");
		//System.out.println(url);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url.toString());
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			return addr;
		}
		
		StringBuilder str = new StringBuilder("");
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String temp;
				if ((temp = br.readLine()) != null) {
					temp = temp.trim();
					if (temp != null && temp.length() > 0) {
						//temp=temp.replaceAll("&lt;","<");
						//temp=temp.replaceAll("&gt;",">");
						str.append(temp);
					}
				}
				br.close();
				insr.close();
				//System.out.println(str);
				String jsonstr = str.substring(str.indexOf("{"),str.lastIndexOf("}")+1);
				//System.out.println(jsonstr);
				JSONObject jsonObj = JSON.parseObject(jsonstr);
				//System.out.println(jsonObj.get("status").toString());
				if("0".equals(jsonObj.get("status").toString())){
					//System.out.println("成功");
					JSONObject resultJSON = JSON.parseObject(jsonObj.get("result").toString());
					addr = resultJSON.getString("formatted_address");
					addr += resultJSON.getString("business");
					//System.out.println("addr="+addr);
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return addr;
		}
		return addr;
	}
	
	/**
	 * 百度地图根据地址查询经纬度
	 * @author zhengxingmiao
	 * @param addr
	 * @param city
	 * @return
	 */
	public static String GetLngLatByAddr(String addr,String city) {
		// http://api.map.baidu.com/geocoder/v2/?ak=您的密钥&callback=renderOption&output=json&address=百度大厦&city=北京市
		StringBuilder url = new StringBuilder("");
		url.append("http://api.map.baidu.com/geocoder/v2/?ak=");
		// 百度密钥key
		url.append("99ca2880b33516d259d2579c669eda91");
		url.append("&callback=renderOption");
		url.append("&output=json");
		url.append("&address="+addr+"&city="+city+"");
		url.append("");
		//System.out.println(url);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url.toString());
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			return addr;
		}
		String lnglat = "";
		StringBuilder str = new StringBuilder("");
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String temp;
				if ((temp = br.readLine()) != null) {
					temp = temp.trim();
					if (temp != null && temp.length() > 0) {
						//temp=temp.replaceAll("&lt;","<");
						//temp=temp.replaceAll("&gt;",">");
						str.append(temp);
					}
				}
				br.close();
				insr.close();
				//System.out.println(str);
				String jsonstr = str.substring(str.indexOf("{"),str.lastIndexOf("}")+1);
				//System.out.println(jsonstr);
				JSONObject jsonObj = JSON.parseObject(jsonstr);
				//System.out.println(jsonObj.get("status").toString());
				if("0".equals(jsonObj.get("status").toString())){
					//System.out.println("成功");
					JSONObject resultJSON = JSON.parseObject(jsonObj.get("result").toString());
					//System.out.println(resultJSON);
					//System.out.println(resultJSON.getString("location"));
					JSONObject locationJSON = JSON.parseObject(resultJSON.get("location").toString());
					lnglat += locationJSON.getString("lng");
					lnglat += ",";
					lnglat += locationJSON.getString("lat");
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return lnglat;
		}
		return lnglat;
	}
	
	
	/**
	 * 取得客户端的MAC
	 * 
	 * zhengxingmiao Jul 4, 2015 8:44:04 AM
	 * @param ip
	 * @return
	 */
	public static String getMACAddress(String ip){
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++){
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1){
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}
	
	

}