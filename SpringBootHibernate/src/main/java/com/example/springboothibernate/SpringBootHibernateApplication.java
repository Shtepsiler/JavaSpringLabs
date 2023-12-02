package com.example.springboothibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class SpringBootHibernateApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHibernateApplication.class, args);
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: application.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        System.out.println("## getDataSource: " + dataSource);
        return dataSource;
    }

    @Autowired
    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        // Package contains entity classes
        factoryBean.setPackagesToScan(new String[] { "com.example.springboothibernate" }); // Change to your package
        factoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        // See: application.properties
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("current_session_context_class", //
                env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        factoryBean.setHibernateProperties(properties);

        return factoryBean;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
