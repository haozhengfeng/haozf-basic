package org.haozf.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;

public class Alipay {
    
    private AlipayClient alipayClient = null;
    public Alipay() {
        if(alipayClient == null){
            alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayContext.APP_ID,AlipayContext.APP_PRIVATE_KEY,"json",AlipayContext.CHARSET,AlipayContext.ALIPAY_PUBLIC_KEY);    
        }
    }
    
    public void pay(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setBizContent("{" +
            "    \"out_trade_no\":\"20150320010101002\"," +
            "    \"total_amount\":88.88," +
            "    \"subject\":\"Iphone6 16G\"," +
            "    \"seller_id\":\"2088123456789012\"," +
            "    \"product_code\":\"QUICK_WAP_PAY\"" +
            "  }");//填充业务参数
        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        httpResponse.setContentType("text/html;charset=" + AlipayServiceEnvConstants.CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
    }

}
