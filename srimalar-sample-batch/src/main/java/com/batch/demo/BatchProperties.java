package com.batch.demo;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "job.datasource")
public class BatchProperties {
    private String url;
    private String username;

    private String password;

    private String driver;

    public BatchProperties() {
        this.url = "jdbc:h2:tcp://localhost/~/data/db-batch-job";
        this.username = "sa";
        this.password = "password";
        this.driver = "org.h2.Driver";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
