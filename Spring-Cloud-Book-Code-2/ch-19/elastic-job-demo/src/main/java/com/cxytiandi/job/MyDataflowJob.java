package com.cxytiandi.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.Arrays;
import java.util.List;

public class MyDataflowJob implements DataflowJob<String> {

	@Override
	public List<String> fetchData(ShardingContext context) {
		return Arrays.asList("1", "2", "3");
	}

	@Override
	public void processData(ShardingContext context, List<String> data) {
		System.out.println("处理数据：" + data.toString());
	}

}
