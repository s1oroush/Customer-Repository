package com.gam.mci.customerRepository.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IndividualCustomerRequest {
    private String customerCode;
    private String nationalId;
    private String citizenshipType;
    private String identificationCode;
    private String firstName;
    private String lastName;
    private String statusReason;
    private String fatherName;
    private String transactionId;
}
