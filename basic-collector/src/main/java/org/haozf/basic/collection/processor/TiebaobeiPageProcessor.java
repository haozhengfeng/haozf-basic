package org.haozf.basic.collection.processor;

import java.util.List;

import org.haozf.basic.collection.model.Machine;
import org.haozf.basic.collection.pipeline.DaoPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class TiebaobeiPageProcessor implements PageProcessor {

	Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public void process(Page page) {

		Html html = page.getHtml();
		System.out.println(html);
		// System.out.println(html.xpath("//div[@class='proDes fr']/h2/text()"));
		// System.out.println(html.$(".proDes.fr").toString());
		// System.out.println(html.$(".testTable.tab-con.baseInfo tr td"));

		List<Selectable> list = html.xpath("//table[@class='testTable tab-con baseInfo']/tbody/tr/td").nodes();
		Machine machine = new Machine();

		//String no = html.$(".device_num").toString();
		String no = html.xpath("//span[@class='device_num']/text()").toString();
		// no = html.css(".proDes.fr h2 lable").toString();
		if (no != null) {
			// <label>（此设备编号：380550）</label>
			//no = no.substring(no.indexOf("<label>（此设备编号：") + "<label>（此设备编号：".length(), no.indexOf("）</label>"));
			no = no.replace("（此设备编号：", "").replace("）", "");
			machine.setNo(no);
		}

		for (Selectable selectable : list) {
			String msg = selectable.xpath("//td/text()").toString();

			// if (msg.startsWith("设备序列号：")) {
			// machine.setNo(msg.replace("设备序列号：", ""));
			// }
			if (msg.startsWith("设备类型：")) {
				machine.setClasses(msg.replace("设备类型：", ""));
			}
			if (msg.startsWith("设备品牌：")) {
				machine.setBrand(msg.replace("设备品牌：", ""));
			}
			if (msg.startsWith("设备型号：")) {
				machine.setModel(msg.replace("设备型号：", ""));
			}
			if (msg.startsWith("工作小时：")) {
				machine.setWorktime(msg.replace("工作小时：", ""));
			}
			if (msg.startsWith("出厂日期：")) {
				machine.setDate(msg.replace("出厂日期：", ""));
			}
		}

		if (!(machine.getBrand() == null && machine.getClasses() == null && machine.getDate() == null && machine.getModel() == null && machine.getNo() == null && machine.getWorktime() == null)) {
			page.putField("machine", machine);
		}

		page.addTargetRequests(page.getHtml().links() // ue/wajueji/yuchai_yc240lc-8_378295.html
				 .regex("(http://www\\.tiebaobei\\.com/ue/\\w+/\\w+_\\w+-*\\w+_\\w+.html)")
//				.regex("(http://www\\.tiebaobei\\.com/.*)")
				 .all());
		
		page.addTargetRequests(page.getHtml().links() // ue/wajueji/yuchai_yc240lc-8_378295.html
				 .regex("(http://www\\.tiebaobei\\.com/ue/\\w+/)")
				 .all());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new TiebaobeiPageProcessor())
			.addUrl("http://www.tiebaobei.com/ue/wajueji/liugong_clg908dn_390502.html")
			.addPipeline(new DaoPipeline() )
			//.addPipeline(new FilePipeline("D:\\webmagic\\"))
			// 开启5个线程抓取
			.thread(20)
			// 启动爬虫
			.run();
		System.out.println(Machine.machines.size());
	}

}
