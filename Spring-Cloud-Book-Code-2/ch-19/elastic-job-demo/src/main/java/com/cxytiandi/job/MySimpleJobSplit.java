package com.cxytiandi.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySimpleJobSplit implements SimpleJob {
	@Override
	public void execute(ShardingContext context) {
		String shardParamter = context.getShardingParameter();
		System.out.println("分片参数：" + shardParamter);
		int value = Integer.parseInt(shardParamter);
		for (int i = 0; i < 5; i++) {
			//分两片,通过取模跟分片参数对比
			if (i % 2 == value) {
				String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
				System.out.println(time + ":开始执行简单任务" + i);
			}
		}
	}

}
