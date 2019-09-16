package io.github.leejoker.demo.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.persistence.EntityManager
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "todoListEntityManagerFactory",
        transactionManagerRef = "todoListTransactionManager",
        basePackages = ["io.github.leejoker.demo.modules.todolist.dao"])
class TodoListJpaConfig {
    @Autowired
    private JpaProperties jpaProperties

    @Autowired
    @Qualifier("todoListDataSource")
    private DataSource dataSource

    @Bean(name = "todoListEntityManager")
    @Primary
    EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return todoListEntityManagerFactory(builder).getObject().createEntityManager()
    }

    /**
     * 指定需要扫描的实体包实现与数据库关联
     * @param builder
     * @return
     */
    @Bean(name = "todoListEntityManagerFactory")
    @Primary
    LocalContainerEntityManagerFactoryBean todoListEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).properties(jpaProperties.properties)
                .packages("io.github.leejoker.demo.modules.todolist.entity")
                .persistenceUnit("todolist")
                .build()
    }

    /**
     * 创建事务管理
     * @param builder
     * @return
     */
    @Bean(name = "todoListTransactionManager")
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(todoListEntityManagerFactory(builder).getObject())
    }
}
