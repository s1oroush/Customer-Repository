package com.gam.mci.customerRepository.controller;

import com.gam.mci.customerRepository.Service.IndividualCustomerService;
import com.gam.mci.customerRepository.mapper.IndividualCustomerMapper;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.request.IndividualCustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.gam.mci.customerRepository.controller.CorporationModerObjectController.anyCorporationCustomerModel;
import static com.gam.mci.customerRepository.controller.IndividualModerObjectController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndividualRESTController.class)
class IndividualRESTControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IndividualCustomerService service;

    @MockBean
    IndividualCustomerMapper mapper;

    @Test
    void handlePost() throws Exception {
        given(mapper.customerRequestToCustomerModel(any(IndividualCustomerRequest.class))).willReturn(anyIndividualCustomerModel());
        given(service.saveIndividualCustomer(any(IndividualCustomerModel.class))).willReturn(anyIndividualCustomerModelWithId());
        mockMvc.perform(post("/individual")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(anyIndividualCustomerModel())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void getIndividualCustomer() throws Exception {
        mockMvc.perform(get("/individual/" + anyIndividualCustomerRequestId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateIndividualCustomerByNationalId() throws Exception {
        given(mapper.customerRequestToCustomerModel(any(IndividualCustomerRequest.class))).willReturn(anyIndividualCustomerModel());
        mockMvc.perform(put("/individual/" + anyCorporationCustomerModel().getNationalId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(anyIndividualCustomerModel())))
                .andExpect(status().isNoContent());

        verify(service, atLeastOnce()).updateIndividualCustomer(anyString(), any(IndividualCustomerModel.class));
    }

    @Test
    void updatePartialIndividualCustomerByNationalId() throws Exception{
        given(mapper.customerRequestToCustomerModel(any(IndividualCustomerRequest.class))).willReturn(anyIndividualCustomerModel());
        mockMvc.perform(patch("/individual/" + "test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(anyIndividualCustomerRequest())))
                .andExpect(status().isNoContent());

        verify(service , atLeastOnce()).patchIndividualCustomer(anyString(),any(IndividualCustomerModel.class));

    }
}