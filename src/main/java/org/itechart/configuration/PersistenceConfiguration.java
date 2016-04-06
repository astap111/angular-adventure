package org.itechart.configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.itechart.repository")
public class PersistenceConfiguration {
    private static final Logger LOGGER = LogManager.getLogger(PersistenceConfiguration.class);

    @Bean
    public DataSource dataSource() {
        try {
            Object ds = new InitialContext().lookup("java:/comp/env/jdbc/warehouse");
            return (DataSource) ds;
        } catch (NamingException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return null;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setShowSql(true);
//        vendorAdapter.setGenerateDdl(true);

        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.itechart.entity");

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
}
