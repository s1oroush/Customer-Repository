package com.gam.mci.customerRepository.controller;

import com.gam.mci.customerRepository.Service.CorporationCustomerService;
import com.gam.mci.customerRepository.mapper.CorporationCustomerMapper;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.request.CorporationCustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.gam.mci.customerRepository.controller.CorporationModerObjectController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CorporationRESTController.class)
class CorporationRESTControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CorporationCustomerService service;

    @MockBean
    CorporationCustomerMapper mapper;


    @Test
    void handlePost() throws Exception {
        given(mapper.customerRequestToCustomerModel(any(CorporationCustomerRequest.class))).willReturn(anyCorporationCustomerModel());
        given(service.saveCorporationCustomer(any(CorporationCustomerModel.class))).willReturn(anyCorporationCustomerModelWithId());
        mockMvc.perform(post("/corporation")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(anyCorporationCustomerModel())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void getCorporationCustomer() throws Exception {
        mockMvc.perform(get("/corporation/" + anyCorporationCustomerRequestId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateCorporationCustomerByNationalId() throws Exception {
        given(mapper.customerRequestToCustomerModel(any(CorporationCustomerRequest.class))).willReturn(anyCorporationCustomerModel());
        mockMvc.perform(put("/corporation/" + anyCorporationCustomerModel().getNationalId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(anyCorporationCustomerModel())))
                .andExpect(status().isNoContent());

        verify(service, atLeastOnce()).updateCorporationCustomer(anyString(), any(CorporationCustomerModel.class));


    }

    @Test
    void updatePartialCorporationCustomerByNationalId() throws Exception {
        given(mapper.customerRequestToCustomerModel(any(CorporationCustomerRequest.class))).willReturn(anyCorporationCustomerModel());
        mockMvc.perform(patch("/corporation/" + "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(anyCorporationCustomerRequest())))
                .andExpect(status().isNoContent());

        verify(service, atLeastOnce()).patchCorporationCustomer(anyString(), any(CorporationCustomerModel.class));
    }
}