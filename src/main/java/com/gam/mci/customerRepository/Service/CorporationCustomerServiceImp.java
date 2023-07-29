package com.gam.mci.customerRepository.Service;

import com.gam.mci.customerRepository.entities.CorporationCustomerEntity;
import com.gam.mci.customerRepository.mapper.CorporationCustomerMapper;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.repository.CorporationRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Data
@Service
public class CorporationCustomerServiceImp implements CorporationCustomerService {

    private final CorporationRepository corporationRepository;
    private final CorporationCustomerMapper customerMapper;

    public CorporationCustomerServiceImp(CorporationRepository corporationRepository, CorporationCustomerMapper customerMapper) {
        this.corporationRepository = corporationRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    @Override
    public CorporationCustomerModel saveCorporationCustomer(CorporationCustomerModel model) {
        CorporationCustomerEntity entity = customerMapper.customerModelToCustomerEntity(model);
        CorporationCustomerEntity savedEntity = corporationRepository.save(entity);
        return customerMapper.customerEntityToCustomerModel(savedEntity);
    }

    @Override
    public CorporationCustomerModel fetchCorporationCustomerByNationalId(String nationalId) throws ResponseStatusException {
        Optional<CorporationCustomerEntity> entity = corporationRepository.findByNationalId(nationalId);
        if (entity.isPresent()){
            return customerMapper.customerEntityToCustomerModel(entity.get());
        }
        else{
            throw new ResponseStatusException (HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public void updateCorporationCustomer(String nationalId, CorporationCustomerModel model) throws ResponseStatusException {
        CorporationCustomerEntity entity = customerMapper.customerModelToCustomerEntity(model);
        CorporationCustomerModel fetchedModel =fetchCorporationCustomerByNationalId(nationalId);
        entity.setId(fetchedModel.getId());
        corporationRepository.save(entity);
    }

    @Transactional
    @Override
    public void patchCorporationCustomer(String nationalId, CorporationCustomerModel model) {

        CorporationCustomerEntity entity = customerMapper.customerModelToCustomerEntity(model);
        CorporationCustomerModel fetchedModel = fetchCorporationCustomerByNationalId(nationalId);
        customerMapper.updateCustomer(fetchedModel, entity);
        saveCorporationCustomer(fetchedModel);
    }
}
