package com.gam.mci.customerRepository.Service;

import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import org.springframework.web.server.ResponseStatusException;

public interface CorporationCustomerService {
    CorporationCustomerModel saveCorporationCustomer(CorporationCustomerModel model);

    CorporationCustomerModel fetchCorporationCustomerByNationalId(String nationalId) throws ResponseStatusException;

    void updateCorporationCustomer(String nationalId, CorporationCustomerModel model) throws ResponseStatusException;

    void patchCorporationCustomer(String nationalId, CorporationCustomerModel model);
}
