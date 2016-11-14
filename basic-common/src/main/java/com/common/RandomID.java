package com.common;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Created by zywang
 * User: wang
 * Date: 2007-11-15
 * Time: 17:23:32
 */
public class RandomID {
    public String GenTradeId()    {
        String tradeId = "";
        RandomStrg.setCharset("a-zA-Z0-9");
        RandomStrg.setLength("7");
        try {
            RandomStrg.generateRandomObject();
            tradeId=RandomStrg.getRandom();
        } catch (Exception e){
        }
        String imageInDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
        return imageInDate+tradeId;
    }

    public String GenWebId()    {
        String tradeId = "";
        RandomStrg.setCharset("0-9");
        RandomStrg.setLength("10");
        try {
            RandomStrg.generateRandomObject();
            tradeId=RandomStrg.getRandom();
        } catch (Exception e){
        }
        return tradeId;
    }
	public String GenOrderId()    {
			
			Calendar c = Calendar.getInstance(Locale.CHINA);
			Date d = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			return sdf.format(d) + (int)(Math.random() *10);

		}

}
