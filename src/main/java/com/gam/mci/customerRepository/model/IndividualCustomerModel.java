package com.gam.mci.customerRepository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class IndividualCustomerModel {
    private Long id;
    private String nationalId;
    private String citizenshipType;
    private String identificationCode;
    private String firstName;
    private String lastName;
    private String statusReason;
    private String fatherName;
    private String transactionId;
}
