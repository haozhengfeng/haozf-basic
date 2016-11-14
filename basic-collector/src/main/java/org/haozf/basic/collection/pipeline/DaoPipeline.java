package org.haozf.basic.collection.pipeline;

import org.haozf.basic.collection.model.Machine;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class DaoPipeline implements Pipeline {

	private Machine machine;

	@Override
	public void process(ResultItems resultItems, Task task) {
		System.out.println(resultItems.getRequest().getUrl());
		Object ob = resultItems.getAll().get("machine");
		if (ob instanceof Machine){
			machine = (Machine) ob;
			System.out.println(machine);
			if(Machine.machines.get(machine.getNo())==null){
				Machine.machines.put(machine.getNo(), machine);
				System.out.println("保存设备信息");
				return;
			}
			System.out.println("设备信息已存在");
		}
	}

}
