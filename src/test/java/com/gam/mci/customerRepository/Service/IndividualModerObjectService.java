package com.gam.mci.customerRepository.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gam.mci.customerRepository.entities.IndividualCustomerEntity;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.request.IndividualCustomerRequest;
import lombok.Data;

@Data
public class IndividualModerObjectService {
    public static String DUMMY = "dummy";
    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static IndividualCustomerRequest anyIndividualCustomerRequest() {
        return IndividualCustomerRequest.builder()
                .nationalId(DUMMY)
                .fatherName(DUMMY)
                .firstName(DUMMY)
                .build();
    }

    public static IndividualCustomerEntity anyIndividualCustomerEntity() {
        return IndividualCustomerEntity.builder()
                .nationalId(DUMMY)
                .fatherName(DUMMY)
                .firstName(DUMMY)
                .build();
    }

    public static IndividualCustomerModel anyIndividualCustomerModel() {
        return IndividualCustomerModel.builder()
                .id(1L)
                .nationalId(DUMMY)
                .fatherName(DUMMY)
                .firstName(DUMMY)
                .build();
    }

    public static IndividualCustomerModel anyIndividualCustomerModelWithId(){
        return IndividualCustomerModel.builder()
                .nationalId(DUMMY)
                .fatherName(DUMMY)
                .firstName(DUMMY)
                .build();
    }

    public static IndividualCustomerEntity anyIndividualCustomerEntityWithId(){
        return IndividualCustomerEntity.builder()
                .nationalId(DUMMY)
                .fatherName(DUMMY)
                .firstName(DUMMY)
                .build();
    }

    public static String anyIndividualCustomerRequestId() { return "1" ;}

    public static String anyNationalId(){return DUMMY;}
}
