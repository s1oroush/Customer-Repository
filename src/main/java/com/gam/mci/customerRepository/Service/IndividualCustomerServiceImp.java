package com.gam.mci.customerRepository.Service;

import com.gam.mci.customerRepository.entities.IndividualCustomerEntity;
import com.gam.mci.customerRepository.mapper.IndividualCustomerMapper;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.repository.IndividualRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Data
@Service
public class IndividualCustomerServiceImp implements IndividualCustomerService {

    private final IndividualRepository individualRepository;
    private final IndividualCustomerMapper customerMapper;

    public IndividualCustomerServiceImp(IndividualRepository individualRepository, IndividualCustomerMapper customerMapper) {
        this.individualRepository = individualRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    @Override
    public IndividualCustomerModel saveIndividualCustomer(IndividualCustomerModel model) {
        IndividualCustomerEntity entity = customerMapper.customerModelToCustomerEntity(model);
        IndividualCustomerEntity savedEntity = individualRepository.save(entity);
        return customerMapper.customerEntityToCustomerModel(savedEntity);

    }



    @Override
    public IndividualCustomerModel fetchIndividualCustomerByNationalId(String nationalId) throws ResponseStatusException {
        Optional<IndividualCustomerEntity> entity = individualRepository.findByNationalId(nationalId);
        if (entity.isPresent()){
            return customerMapper.customerEntityToCustomerModel(entity.get());
        }
        else{
            throw new ResponseStatusException (HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @Override
    public void updateIndividualCustomer(String nationalId, IndividualCustomerModel model) throws ResponseStatusException {

        IndividualCustomerEntity entity = customerMapper.customerModelToCustomerEntity(model);
        IndividualCustomerModel fetchedModel =fetchIndividualCustomerByNationalId(nationalId);
        entity.setId(fetchedModel.getId());
        individualRepository.save(entity);
    }

    @Transactional
    @Override
    public void patchIndividualCustomer(String nationalId, IndividualCustomerModel model) {

        IndividualCustomerEntity entity = customerMapper.customerModelToCustomerEntity(model);
        IndividualCustomerModel fetchedModel =fetchIndividualCustomerByNationalId(nationalId);
        customerMapper.updateCustomer(fetchedModel , entity);
        saveIndividualCustomer(fetchedModel);
    }

}

/*

 */