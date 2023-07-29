package com.gam.mci.customerRepository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;


@Entity
@Table(name = "tb_cst_corporation_customer")
@SequenceGenerator(name = "sq_cst_customer", sequenceName = "sq_cst_customer", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorporationCustomerEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sq_cst_customer")
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "transaction_id")
    private String transaction_id;

    @Column(name = "created_by")
    private String created_by;

    @CreationTimestamp
    @Column(name = "created_date")
    private String created_date;

    @Column(name = "last_modified_by")
    private String last_modified_by;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private String last_modified_date;

    @Column(name = "revision_comment")
    private String revision_comment;


}