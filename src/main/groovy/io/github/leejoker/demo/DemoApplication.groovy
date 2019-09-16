package io.github.leejoker.demo

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = [XADataSourceAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class])
class DemoApplication {
    static void main(String[] args) {
        SpringApplication.run(DemoApplication, args)
    }
}
