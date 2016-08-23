package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EnableMongoRepositories
@PropertySource(value = {"classpath:prop/env.properties"})
public class ApplicationConfig extends AbstractMongoConfiguration{


    private static final String MONGO_HOST = "mongo.host";
    private static final String MONGO_PORT = "mongo.port";
    private static final String MONGO_DB = "mongo.db";

/*    @Autowired
    private List<Converter<?, ?>> converters;*/

    @Autowired
    private Environment environment; //读取配置

    @Bean
    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }

    @Bean
    @Override
    protected String getDatabaseName() {
        return environment.getProperty(MONGO_DB);
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        MongoClient mongoClient = new MongoClient(environment.getProperty(MONGO_HOST), environment.getProperty(MONGO_PORT, Integer.class));
        return mongoClient;
    }

    /*@Override
    public CustomConversions customConversions() {
        return new CustomConversions(converters);
    }*/

}