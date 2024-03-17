package com.clairedelune.batch5.springbatch5.jobs;

import com.clairedelune.batch5.springbatch5.domain.entity.TbStudent;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StdJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job stdJob() {
        return new JobBuilder("stdJob", jobRepository)
                .start(stdStep())
                .build();
    }


    @Bean
    public Step stdStep() {

        return new StepBuilder("stdStep", jobRepository)
                .<TbStudent, TbStudent>chunk(10, platformTransactionManager)
                .reader(stdItemReader())
                .writer(stdItemWriter())
                .build();
    }

    private JpaPagingItemReader<TbStudent> stdItemReader() {
        return new JpaPagingItemReaderBuilder<TbStudent>()
                .name("StdItemReader")
                .queryString("SELECT s FROM TbStudent s")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .build();
    }

    private ItemWriter<TbStudent> stdItemWriter() {
        return list -> {
            for (TbStudent std : list) {
                log.info("student no={}, name={}", std.getId(), std.getName());
            }
        };
    }
}
