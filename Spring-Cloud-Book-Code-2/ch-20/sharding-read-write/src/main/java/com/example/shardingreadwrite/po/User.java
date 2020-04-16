package com.example.shardingreadwrite.po;

import com.cxytiandi.jdbc.annotation.AutoId;
import com.cxytiandi.jdbc.annotation.Field;
import com.cxytiandi.jdbc.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user", author = "yinjihuan", desc = "用户表")
public class User implements Serializable {

	private static final long serialVersionUID = -1205226416664488559L;
	
	@AutoId
	@Field(value="id", desc="ID")
	private Long id;

	@Field(value="city", desc="城市")
	private String city = "";
	
	@Field(value="name", desc="姓名")
	private String name = "";
}
