package com.gam.mci.customerRepository.Service;

import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import org.springframework.web.server.ResponseStatusException;


public interface IndividualCustomerService {
    IndividualCustomerModel saveIndividualCustomer(IndividualCustomerModel model);

    IndividualCustomerModel fetchIndividualCustomerByNationalId(String nationalId) throws ResponseStatusException;

    void updateIndividualCustomer(String nationalId , IndividualCustomerModel model) throws ResponseStatusException;

    void patchIndividualCustomer(String nationalId, IndividualCustomerModel model);
}
