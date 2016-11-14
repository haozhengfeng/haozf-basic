package net.zhxm.utils;

/**
 * @author guozhenhua 设备号工具类
 */
public class qrcodeUtil {
	/**
	 * 设备号从字串转int
	 * 
	 * @author guozhenhua
	 * @param qrcode
	 * @return
	 */
	public static int fromString(String qrcode) throws NumberFormatException {
		int index = 0;
		for (int i = 0; i < qrcode.length(); i++) {
			char value = qrcode.charAt(i);
			if (value == '0') {
				continue;
			} else {
				index = i;
				break;
			}
		}
		return Integer.parseInt(qrcode.substring(index));
	}

	/**
	 * 设备号从int转字串
	 * 
	 * @author guozhenhua
	 * @param qrcode
	 * @return
	 */
	public static String fromInt(int qrcode) throws Exception {
		if (qrcode < 10) {
			return "000000" + qrcode;
		} else if (qrcode >= 10 && qrcode < 100) {
			return "00000" + qrcode;
		} else if (qrcode >= 100 && qrcode < 1000) {
			return "0000" + qrcode;
		} else if (qrcode >= 1000 && qrcode < 10000) {
			return "000" + qrcode;
		} else if (qrcode >= 10000 && qrcode < 100000) {
			return "00" + qrcode;
		} else if (qrcode >= 100000 && qrcode < 1000000) {
			return "0" + qrcode;
		} else if (qrcode >= 1000000 && qrcode < 10000000) {
			return "" + qrcode;
		} else {
			throw new Exception("数字过大");
		}
	}
}
