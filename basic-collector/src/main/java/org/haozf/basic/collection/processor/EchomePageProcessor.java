package org.haozf.basic.collection.processor;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class EchomePageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1);

	@Override
	public void process(Page page) {
		List<String> list = page.getHtml().links().regex("(http://product\\.cehome\\.com/.*)").all();
		if (page.getRequest().getUrl().lastIndexOf("/view/") > 0) {
			System.out.println("产品详情页面");
		}
//		for (String s : list) {
//			System.out.println(s);
//		}

		// 查询分类链接 http://product.cehome.com
		page.addTargetRequests(list);

		// page.putField("author",
		// page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		// page.putField("name",
		// page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		// page.putField("readme",
		// page.getHtml().xpath("//div[@id='readme']/tidyText()"));

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new EchomePageProcessor()).addUrl("http://www.cehome.com/").thread(10).run();
	}

}
