package com.clairedelune.batch5.springbatch5;

import com.clairedelune.batch5.springbatch5.jobs.TaskJobConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBatchTest
@SpringBootTest(classes = {BatchTestConfig.class, TaskJobConfig.class})
class SpringBatchTaskJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void stdJobTest(@Qualifier("taskJob") Job taskJob) throws Exception {
        jobLauncherTestUtils.setJob(taskJob);
        JobParameters jobParameters =
                jobLauncherTestUtils.getUniqueJobParametersBuilder()
                        .toJobParameters();
        // when
        final JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        Assertions.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }

}
