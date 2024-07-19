package com.zero.JobPostApp.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Repository.CompanyRepository;

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

@ContextConfiguration(classes = {CompanyServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CompanyServiceImplDiffblueTest {
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    /**
     * Method under test: {@link CompanyServiceImpl#getAllCompany()}
     */
    @Test
    void testGetAllCompany() {
        // Arrange
        ArrayList<Company> companyList = new ArrayList<>();
        when(companyRepository.findAll()).thenReturn(companyList);

        // Act
        List<Company> actualAllCompany = companyServiceImpl.getAllCompany();

        // Assert
        verify(companyRepository).findAll();
        assertTrue(actualAllCompany.isEmpty());
        assertSame(companyList, actualAllCompany);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#getCompanyById(Long)}
     */
    @Test
    void testGetCompanyById() {
        // Arrange
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        Optional<Company> ofResult = Optional.of(company);
        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Company actualCompanyById = companyServiceImpl.getCompanyById(1L);

        // Assert
        verify(companyRepository).findById(eq(1L));
        assertSame(company, actualCompanyById);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#addCompany(Company)}
     */
    @Test
    void testAddCompany() {
        // Arrange
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company);

        Company company2 = new Company();
        company2.setAddress("42 Main St");
        company2.setEmail("jane.doe@example.org");
        company2.setId(1L);
        company2.setIndustryType("Industry Type");
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");

        // Act
        boolean actualAddCompanyResult = companyServiceImpl.addCompany(company2);

        // Assert
        verify(companyRepository).save(isA(Company.class));
        assertTrue(actualAddCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#updateCompany(Company, Long)}
     */
    @Test
    void testUpdateCompany() {
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

        Company company3 = new Company();
        company3.setAddress("42 Main St");
        company3.setEmail("jane.doe@example.org");
        company3.setId(1L);
        company3.setIndustryType("Industry Type");
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");

        // Act
        boolean actualUpdateCompanyResult = companyServiceImpl.updateCompany(company3, 1L);

        // Assert
        verify(companyRepository).findById(eq(1L));
        verify(companyRepository).save(isA(Company.class));
        assertTrue(actualUpdateCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#updateCompany(Company, Long)}
     */
    @Test
    void testUpdateCompany2() {
        // Arrange
        Optional<Company> emptyResult = Optional.empty();
        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");

        // Act
        boolean actualUpdateCompanyResult = companyServiceImpl.updateCompany(company, 1L);

        // Assert
        verify(companyRepository).findById(eq(1L));
        assertFalse(actualUpdateCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#deleteCompany(Long)}
     */
    @Test
    void testDeleteCompany() {
        // Arrange
        doNothing().when(companyRepository).deleteById(Mockito.<Long>any());

        // Act
        boolean actualDeleteCompanyResult = companyServiceImpl.deleteCompany(1L);

        // Assert
        verify(companyRepository).deleteById(eq(1L));
        assertTrue(actualDeleteCompanyResult);
    }

}
