package net.zhxm.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;


public class CaptchaServlet extends HttpServlet {
	
	public final static String KEY_NAME = "validateCode"; 
	public static int WIDTH = 120;
	public static int HEIGHT = 35;
	public static int LENGTH = 5;
	public final static Random random = new Random();
	
    public void init(ServletConfig config) throws ServletException {
    	if(null!=config.getInitParameter("captcha_width")){
    		WIDTH = Integer.parseInt(config.getInitParameter("captcha_width"));
    	}
    	if(null!=config.getInitParameter("captcha_height")){
    		HEIGHT = Integer.parseInt(config.getInitParameter("captcha_height"));
    	}
    	if(null!=config.getInitParameter("captcha_length")){
    		LENGTH = Integer.parseInt(config.getInitParameter("captcha_length"));
    	}
        //System.out.println("WIDTH："+WIDTH);
        //System.out.println("HEIGHT："+HEIGHT);
        //System.out.println("LENGTH："+LENGTH);
    }
    
	//Get默认方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//关闭缓存
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		response.setCharacterEncoding("utf-8");
		
		String code = RandomStringUtils.randomAlphanumeric(LENGTH).toUpperCase();
		code = code.replace('0', 'W');
		code = code.replace('o', 'R');
		code = code.replace('I', 'E');
		code = code.replace('1', 'T');
		
		HttpSession session = request.getSession();
		session.setAttribute(KEY_NAME, code);
		
		_Render(code, response.getOutputStream(), WIDTH, HEIGHT);
		
	}
	
	//post默认方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
		
    //画随机码图
    private static void _Render(String text, OutputStream out, int width, int height) throws IOException {
	    BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);        
	    Graphics2D g = (Graphics2D)bi.getGraphics();
	    
	    g.setColor(Color.WHITE);
	    g.fillRect(0,0,width,height);
    	//g.setColor(Color.RED);
	    //g.drawRect(1,1,width-2,height-2);
	    for(int i=0;i<10;i++){
	    	g.setColor(_GetRandColor(150, 250));
	    	g.drawOval(random.nextInt(110), random.nextInt(24), 5+random.nextInt(10), 5+random.nextInt(10));
	    }
	    Font mFont = new Font("Arial", Font.ITALIC, 28);
	    g.setFont(mFont);
	    g.setColor(_GetRandColor(10,240));
	    g.drawString(text, 10, 30);
	    ImageIO.write(bi, "png", out);
    }
    
    //给定范围获得随机颜色
    private static Color _GetRandColor(int fc,int bc){
		if (fc > 255) fc = 255;
		if (bc > 255) bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}