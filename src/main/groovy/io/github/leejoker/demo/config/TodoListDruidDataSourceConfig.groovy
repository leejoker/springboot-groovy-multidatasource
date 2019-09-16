package io.github.leejoker.demo.config

import com.alibaba.druid.pool.DruidDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource

import javax.sql.DataSource
import java.sql.SQLException

@Configuration
@PropertySource(value = ["classpath:config/todolist.properties"])
@ConfigurationProperties(prefix = "todolist.spring.datasource")
class TodoListDruidDataSourceConfig {
    @Value('${todolist.spring.datasource.url}')
    private String url
    @Value('${todolist.spring.datasource.username}')
    private String username
    @Value('${todolist.spring.datasource.password}')
    private String password
    @Value('${todolist.spring.datasource.driver-class-name}')
    private String driverClassName
    @Value('${todolist.spring.datasource.initialSize}')
    private int initialSize
    @Value('${todolist.spring.datasource.minIdle}')
    private int minIdle
    @Value('${todolist.spring.datasource.maxActive}')
    private int maxActive
    @Value('${todolist.spring.datasource.maxWait}')
    private int maxWait
    @Value('${todolist.spring.datasource.timeBetweenEvictionRunsMillis}')
    private int timeBetweenEvictionRunsMillis
    @Value('${todolist.spring.datasource.minEvictableIdleTimeMillis}')
    private int minEvictableIdleTimeMillis
    @Value('${todolist.spring.datasource.validationQuery}')
    private String validationQuery
    @Value('${todolist.spring.datasource.testWhileIdle}')
    private boolean testWhileIdle
    @Value('${todolist.spring.datasource.testOnBorrow}')
    private boolean testOnBorrow
    @Value('${todolist.spring.datasource.testOnReturn}')
    private boolean testOnReturn
    @Value('${todolist.spring.datasource.poolPreparedStatements}')
    private boolean poolPreparedStatements
    @Value('${todolist.spring.datasource.maxPoolPreparedStatementPerConnectionSize}')
    private int maxPoolPreparedStatementPerConnectionSize
    @Value('${todolist.spring.datasource.filters}')
    private String filters
    @Value('${todolist.spring.datasource.connectionProperties}')
    private String connectionProperties

    @Primary
    @Qualifier("todoListDataSource")
    @Bean(name = "todoListDataSource")
    DataSource todoListDataSource() {
        DruidDataSource datasource = new DruidDataSource()
        datasource.url = url
        datasource.username = username
        datasource.password = password
        datasource.driverClassName = driverClassName

        // configuration
        datasource.initialSize = initialSize
        datasource.minIdle = minIdle
        datasource.maxActive = maxActive
        datasource.maxWait = maxWait
        datasource.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis
        datasource.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis
        datasource.validationQuery = validationQuery
        datasource.testWhileIdle = testWhileIdle
        datasource.testOnBorrow = testOnBorrow
        datasource.testOnReturn = testOnReturn
        datasource.poolPreparedStatements = poolPreparedStatements
        datasource.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize
        try {
            datasource.filters = filters
        } catch (SQLException e) {
            println "druid configuration initialization filter: " + e
        }
        datasource.connectionProperties = connectionProperties
        return datasource
    }
}