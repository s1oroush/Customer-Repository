package com.gam.mci.customerRepository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorporationCustomerModel {
    private Long id;
    private String nationalId;
    private String customerName;
    private Date registrationDate;
    private String registrationNumber;
    private String transaction_id;
    private String created_by;
    private String revision_comment;
}
