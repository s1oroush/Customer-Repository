package com.gam.mci.customerRepository.response;

import lombok.Data;

import java.util.Date;

@Data
public class CorporationCustomerResponse {
    private String nationalId;
    private String customerName;
    private Date registrationDate;
    private String registrationNumber;
    private String transaction_id;
}
