package com.batch.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BatchPostConstruct {
    private final DataSource dataSource;

    @Autowired
    public BatchPostConstruct(@Qualifier("batchDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initDB() {
       // Resource resource = new ClassPathResource("org/springframework/batch/core/schema-h2.sql");
        Resource schemaResource = new ClassPathResource("config/batch-job-schema-h2.sql");
        Resource deleteResource = new ClassPathResource("config/batch-job-delete-h2.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(schemaResource, deleteResource);
        databasePopulator.execute(dataSource);
    }
}
