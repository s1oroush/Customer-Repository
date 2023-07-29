package com.gam.mci.customerRepository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "tb_cst_individual_customer")
@SequenceGenerator(name = "sq_cst_customer", sequenceName = "sq_cst_customer", allocationSize = 1)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sq_cst_customer")
    @Column(name = "CUSTOMER_ID")
    private Long id;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "citizenship_type")
    private String citizenshipType;

    @Column(name = "identification_code")
    private String identificationCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status_reason")
    private String statusReason;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "transaction_id")
    private String transactionId;

    @CreationTimestamp
    @Column(name = "created_date")
    private String createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private String lastModifiedDate;

    @Column(name = "revision_comment")
    private String revisionComment;

    @Column(name = "revision_number")
    private String revisionNumber;

    @Override
    public String toString() {
        return "IndividualCustomerEntity{" +
                "id=" + id +
                ", citizenshipType=" + citizenshipType +
                ", identificationCode='" + identificationCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}


