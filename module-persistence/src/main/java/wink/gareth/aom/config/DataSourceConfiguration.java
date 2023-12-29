package wink.gareth.aom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;;

@Configuration
public class DataSourceConfiguration {
//
//    @Value("${spring.data.mongodb.database}")
//    private String database;
//
//    @Bean
//    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
//        return new MongoTransactionManager(dbFactory);
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return database;
//    }
}
