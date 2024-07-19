package com.zero.JobPostApp.Controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.impl.CompanyServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CompanyController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CompanyControllerDiffblueTest {
    @Autowired
    private CompanyController companyController;

    @MockBean
    private CompanyServiceImpl companyServiceImpl;

    /**
     * Method under test: {@link CompanyController#getAllCompany()}
     */
    @Test
    void testGetAllCompany() throws Exception {
        // Arrange
        when(companyServiceImpl.getAllCompany()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyController#getCompanyById(Long)}
     */
    @Test
    void testGetCompanyById() throws Exception {
        // Arrange
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        when(companyServiceImpl.getCompanyById(Mockito.<Long>any())).thenReturn(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\",\"email\":\"jane.doe@example.org\",\"industryType\":\"Industry"
                                        + " Type\",\"jobs\":[]}"));
    }

    /**
     * Method under test: {@link CompanyController#addCompany(Company)}
     */
    @Test
    void testAddCompany() throws Exception {
        // Arrange
        when(companyServiceImpl.addCompany(Mockito.<Company>any())).thenReturn(true);

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CompanyController#updateCompany(Long, Company)}
     */
    @Test
    void testUpdateCompany() throws Exception {
        // Arrange
        when(companyServiceImpl.updateCompany(Mockito.<Company>any(), Mockito.<Long>any())).thenReturn(true);

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/company/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CompanyController#updateCompany(Long, Company)}
     */
    @Test
    void testUpdateCompany2() throws Exception {
        // Arrange
        when(companyServiceImpl.updateCompany(Mockito.<Company>any(), Mockito.<Long>any())).thenReturn(false);

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setIndustryType("Industry Type");
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/company/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CompanyController#deleteCompany(Long)}
     */
    @Test
    void testDeleteCompany() throws Exception {
        // Arrange
        when(companyServiceImpl.deleteCompany(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CompanyController#deleteCompany(Long)}
     */
    @Test
    void testDeleteCompany2() throws Exception {
        // Arrange
        when(companyServiceImpl.deleteCompany(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}
