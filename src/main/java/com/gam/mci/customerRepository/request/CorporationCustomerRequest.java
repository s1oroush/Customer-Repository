package com.gam.mci.customerRepository.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorporationCustomerRequest {
    private String nationalId;
    private String customerName;
    private Date registrationDate;
    private String registrationNumber;
    private String transaction_id;
}
