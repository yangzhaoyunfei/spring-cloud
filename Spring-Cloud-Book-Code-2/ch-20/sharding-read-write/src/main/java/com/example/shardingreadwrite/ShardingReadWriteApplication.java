package com.example.shardingreadwrite;

import com.cxytiandi.jdbc.CxytiandiJdbcTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class ShardingReadWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingReadWriteApplication.class, args);
    }

    /**
     * 增强版JdbcTemplate
     *
     * @param dataSource
     * @return
     */
    @Bean
    public CxytiandiJdbcTemplate cxytiandiJdbcTemplate(DataSource dataSource) {
        CxytiandiJdbcTemplate bean = new CxytiandiJdbcTemplate("com.example.shardingreadwritesplit.po");
        bean.setDataSource(dataSource);
        return bean;
    }
}
