package com.gam.mci.customerRepository.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.request.CorporationCustomerRequest;
import com.gam.mci.customerRepository.request.IndividualCustomerRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CorporationModerObjectController {

    public static String DUMMY = "dummy";

    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static Iterable<CorporationCustomerRequest> customerIterable(){
        List<CorporationCustomerRequest> course = new ArrayList<>();
        course.add(anyCorporationCustomerRequest());
        course.add(anyCorporationCustomerRequest());
        course.add(anyCorporationCustomerRequest());
        return course;
    }

    public static CorporationCustomerRequest anyCorporationCustomerRequest() {
        return CorporationCustomerRequest.builder()
                .nationalId(DUMMY)
                .customerName(DUMMY)
                .registrationNumber(DUMMY)
                .transaction_id(DUMMY)
                .build();
    }



    public static CorporationCustomerModel anyCorporationCustomerModel() {
        return CorporationCustomerModel.builder()
                .nationalId("00000000000")
                .customerName(DUMMY)
                .registrationNumber(DUMMY)
                .transaction_id(DUMMY)
                .build();
    }



    public static CorporationCustomerModel anyCorporationCustomerModelWithId(){
        return CorporationCustomerModel.builder()
                .nationalId("00000000000")
                .created_by(DUMMY)
                .customerName(DUMMY)
                .build();

    }


    public static CorporationCustomerRequest anyCorporationCustomerRequestWithId() {
        return CorporationCustomerRequest.builder()
                .customerName(DUMMY)
                .nationalId(DUMMY)
                .registrationNumber(DUMMY)
                .transaction_id(DUMMY)
                .build();
    }

    public static long anyId() {
        return 1L;
    }

    public static String anyCorporationCustomerRequestId() { return "1" ;}


}
