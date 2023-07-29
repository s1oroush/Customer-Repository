package com.gam.mci.customerRepository.repository;

import com.gam.mci.customerRepository.entities.CorporationCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorporationRepository extends JpaRepository<CorporationCustomerEntity, Long> {

    Optional<CorporationCustomerEntity> findByNationalId(String nationalId);
}
