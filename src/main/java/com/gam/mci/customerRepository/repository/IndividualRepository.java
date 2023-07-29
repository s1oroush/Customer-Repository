package com.gam.mci.customerRepository.repository;

import com.gam.mci.customerRepository.entities.IndividualCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualRepository extends JpaRepository<IndividualCustomerEntity, Long> {

    Optional<IndividualCustomerEntity> findByNationalId(String nationalId);
}
