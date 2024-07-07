package com.batch.demo;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Configuration
public class BatchAppConfig {

    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        BatchProperties properties = new BatchProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriver());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean
    public ItemReader<Payment> itemReader() throws UnexpectedInputException, ParseException {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        return () -> {
            int index = atomicInteger.addAndGet(1);
            if(0 < index && 10 > index) {
                Payment payment = new Payment();
                payment.setUser("Ramesh");
                payment.setId(100);
                payment.setDate(new Date());
                payment.setAmount(2900.50);
                return payment;
            }
            return null;
        };
    }

    @Bean(name = "step1")
    protected Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Payment> reader,
                         ItemProcessor<Payment, Payment> processor, ItemWriter<Payment> writer) {
        return new StepBuilder("step1", jobRepository).<Payment, Payment> chunk(10, transactionManager)
                .reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    public ItemProcessor<Payment, Payment> itemProcessor() {
        return new PaymentItemProcessor();
    }


    @Bean
    public ItemWriter<Payment> itemWriter() throws MalformedURLException {
        return new ItemWriter<Payment>() {
            @Override
            public void write(Chunk<? extends Payment> chunk) throws Exception {
                chunk.getItems().forEach(new Consumer<Payment>() {
                    @Override
                    public void accept(Payment payment) {
                        System.out.println(payment);
                    }
                });
            }
        };
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    static class PaymentItemProcessor implements ItemProcessor<Payment, Payment> {

        public Payment process(Payment item) {
            return item;
        }
    }
}
