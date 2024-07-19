package com.zero.JobPostApp.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Entity.Job;
import com.zero.JobPostApp.Repository.CompanyRepository;
import com.zero.JobPostApp.Repository.JobRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JobServiceImplDiffblueTest {
    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private JobRepository jobRepository;

    @Autowired
    private JobServiceImpl jobServiceImpl;

    /**
     * Method under test: {@link JobServiceImpl#getAllJobs()}
     */
    @Test
    void testGetAllJobs() {
        // Arrange
        ArrayList<Job> jobList = new ArrayList<>();
        when(jobRepository.findAll()).thenReturn(jobList);

        // Act
        List<Job> actualAllJobs = jobServiceImpl.getAllJobs();

        // Assert
        verify(jobRepository).findAll();
        assertTrue(actualAllJobs.isEmpty());
        assertSame(jobList, actualAllJobs);
    }

    /**
     * Method under test: {@link JobServiceImpl#getJobById(Long)}
     */
    @Test
    void testGetJobById() {
        // Arrange
        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setDescription("The characteristics of someone or something");
        job.setId(1L);
        job.setLocation("Location");
        job.setMaxSalary("Max Salary");
        job.setMinSalary("Min Salary");
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);
        when(jobRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Job actualJobById = jobServiceImpl.getJobById(1L);

        // Assert
        verify(jobRepository).findById(eq(1L));
        assertSame(job, actualJobById);
    }

    /**
     * Method under test: {@link JobServiceImpl#deleteJobById(Long)}
     */
    @Test
    void testDeleteJobById() {
        // Arrange
        doNothing().when(jobRepository).deleteById(Mockito.<Long>any());

        // Act
        boolean actualDeleteJobByIdResult = jobServiceImpl.deleteJobById(1L);

        // Assert
        verify(jobRepository).deleteById(eq(1L));
        assertTrue(actualDeleteJobByIdResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#updateJob(Job, Long)}
     */
    @Test
    void testUpdateJob() {
        // Arrange
        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setDescription("The characteristics of someone or something");
        job.setId(1L);
        job.setLocation("Location");
        job.setMaxSalary("Max Salary");
        job.setMinSalary("Min Salary");
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);

        Job job2 = new Job();
        job2.setCompanyName("Company Name");
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1L);
        job2.setLocation("Location");
        job2.setMaxSalary("Max Salary");
        job2.setMinSalary("Min Salary");
        job2.setTitle("Dr");
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job2);
        when(jobRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Job job3 = new Job();
        job3.setCompanyName("Company Name");
        job3.setDescription("The characteristics of someone or something");
        job3.setId(1L);
        job3.setLocation("Location");
        job3.setMaxSalary("Max Salary");
        job3.setMinSalary("Min Salary");
        job3.setTitle("Dr");

        // Act
        boolean actualUpdateJobResult = jobServiceImpl.updateJob(job3, 1L);

        // Assert
        verify(jobRepository).findById(eq(1L));
        verify(jobRepository).save(isA(Job.class));
        assertTrue(actualUpdateJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#updateJob(Job, Long)}
     */
    @Test
    void testUpdateJob2() {
        // Arrange
        Optional<Job> emptyResult = Optional.empty();
        when(jobRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setDescription("The characteristics of someone or something");
        job.setId(1L);
        job.setLocation("Location");
        job.setMaxSalary("Max Salary");
        job.setMinSalary("Min Salary");
        job.setTitle("Dr");

        // Act
        boolean actualUpdateJobResult = jobServiceImpl.updateJob(job, 1L);

        // Assert
        verify(jobRepository).findById(eq(1L));
        assertFalse(actualUpdateJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#createJob(Job, Long)}
     */
    @Test
    void testCreateJob() {
        // Arrange
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        Optional<Company> ofResult = Optional.of(company);

        Company company2 = new Company();
        company2.setAddress("42 Main St");
        company2.setEmail("jane.doe@example.org");
        company2.setId(1L);
        company2.setIndustryType("Industry Type");
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company2);
        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setDescription("The characteristics of someone or something");
        job.setId(1L);
        job.setLocation("Location");
        job.setMaxSalary("Max Salary");
        job.setMinSalary("Min Salary");
        job.setTitle("Dr");
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        Job job2 = new Job();
        job2.setCompanyName("Company Name");
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1L);
        job2.setLocation("Location");
        job2.setMaxSalary("Max Salary");
        job2.setMinSalary("Min Salary");
        job2.setTitle("Dr");

        // Act
        boolean actualCreateJobResult = jobServiceImpl.createJob(job2, 1L);

        // Assert
        verify(companyRepository).findById(eq(1L));
        verify(companyRepository).save(isA(Company.class));
        verify(jobRepository).save(isA(Job.class));
        assertEquals("Name", job2.getCompanyName());
        assertTrue(actualCreateJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#createJob(Job, Long)}
     */
    @Test
    void testCreateJob2() {
        // Arrange
        Optional<Company> emptyResult = Optional.empty();
        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setDescription("The characteristics of someone or something");
        job.setId(1L);
        job.setLocation("Location");
        job.setMaxSalary("Max Salary");
        job.setMinSalary("Min Salary");
        job.setTitle("Dr");

        // Act
        boolean actualCreateJobResult = jobServiceImpl.createJob(job, 1L);

        // Assert
        verify(companyRepository).findById(eq(1L));
        assertFalse(actualCreateJobResult);
    }
}
