package org.haozf.basic.alipay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.haozf.basic.alipay.AlipayContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;


@RequestMapping("/alipay")
@Controller
public class AlipayController {
    
	@RequestMapping("/pay")
	public void pay(HttpServletResponse httpResponse) {
	    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",AlipayContext.APP_ID,AlipayContext.APP_PRIVATE_KEY,"json",AlipayContext.CHARSET,AlipayContext.ALIPAY_PUBLIC_KEY);    
	    
	    AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl("https://www.baidu.com");
        alipayRequest.setBizContent("{" +
            "    \"out_trade_no\":\"20150320010101007\"," +
            "    \"total_amount\":88.88," +
            "    \"subject\":\"Iphone6 16G\"," +
            "    \"seller_id\":\"2088102174814141\"," +
            "    \"product_code\":\"QUICK_WAP_PAY\"" +
            "  }");
        try {
            String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            httpResponse.setContentType("text/html;charset=" + AlipayContext.CHARSET);
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	//https://www.baidu.com/?total_amount=88.88&timestamp=2016-10-13+21%3A11%3A25&sign=g4Yj2BMyeIWI8S5r28zKcsuxSAlwCg6H7p498BLpcSKyWtCIWBZLBvLoyQNmJM4AxYorJrxKNtF%2Bm%2BWK970LC6i%2F08AIfi88LZ7xkO8l9URDl2gpJ%2B4P1kELG68EGKIfJ%2F4hF0Q3ZVpK6oUisXYsjk7OFOSQ3oXBI8Pix4fEnK8%3D&trade_no=2016101321001004430200314827&sign_type=RSA&auth_app_id=2016090900470631&charset=utf-8&seller_id=2088102174814141&method=alipay.trade.wap.pay.return&app_id=2016090900470631&out_trade_no=20150320010101005&version=1.0
	   
	@RequestMapping("/Userinfo")
	public void Userinfo(){
	    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",AlipayContext.APP_ID,AlipayContext.APP_PRIVATE_KEY,"json",AlipayContext.CHARSET,AlipayContext.ALIPAY_PUBLIC_KEY);    
	    AlipayUserUserinfoShareRequest request = new AlipayUserUserinfoShareRequest();
	    try {
            AlipayUserUserinfoShareResponse response = alipayClient.execute(request);
            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
	}
}
