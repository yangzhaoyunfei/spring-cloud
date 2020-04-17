package com.cxytiandi.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySimpleJob implements SimpleJob {
	@Override
	public void execute(ShardingContext context) {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		System.out.println(time + ":开始执行简单任务");
	}

}
