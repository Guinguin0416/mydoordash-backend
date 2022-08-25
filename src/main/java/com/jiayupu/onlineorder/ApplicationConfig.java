package com.jiayupu.onlineorder;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class ApplicationConfig {

   @Bean(name = "sessionFactory")
   // @Bean不能define一个void type，必须是一个class
   public LocalSessionFactoryBean sessionFactory() {
      String PAKAGE_NAME = "com.jiayupu.onlineorder.entity";
      // 扫描entity，把class对应到onlineOrder DB里面
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(dataSource());
      sessionFactory.setPackagesToScan(PAKAGE_NAME);
      sessionFactory.setHibernateProperties(hibernateProperties());
      return sessionFactory;
   }
   // session通过SessionFactory得到，可以对DB中的某一行进行CRUD

   @Bean(name = "dataSource")
   // 连接数据库
   public DataSource dataSource() {
      String RDS_ENDPOINT = "laiproject-instance.cib2uchvpfk7.us-west-1.rds.amazonaws.com";
      String USERNAME = "admin";
      String PASSWORD = "L97j04j16USC1005";
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://" + RDS_ENDPOINT + ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
      dataSource.setUsername(USERNAME);
      dataSource.setPassword(PASSWORD);

      return dataSource;
   }

   private final Properties hibernateProperties() {
      Properties hibernateProperties = new Properties();
      hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
      hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
      hibernateProperties.setProperty("hibernate.show_sql", "true");
      return hibernateProperties;
   }
}

