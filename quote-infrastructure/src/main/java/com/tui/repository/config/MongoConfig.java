package com.tui.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.tui.repository")
public class MongoConfig {
    // MongoDB configuration
}
