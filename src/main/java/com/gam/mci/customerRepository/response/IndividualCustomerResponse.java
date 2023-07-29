package com.gam.mci.customerRepository.response;

import lombok.Data;

@Data
public class IndividualCustomerResponse {
    private String nationalId;
    private String citizenshipType;
    private String identificationCode;
    private String firstName;
    private String lastName;
    private String statusReason;
    private String fatherName;
    private String transactionId;
}
