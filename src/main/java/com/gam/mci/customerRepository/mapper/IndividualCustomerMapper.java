package com.gam.mci.customerRepository.mapper;

import com.gam.mci.customerRepository.entities.IndividualCustomerEntity;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.request.IndividualCustomerRequest;
import com.gam.mci.customerRepository.response.IndividualCustomerResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IndividualCustomerMapper {

    IndividualCustomerEntity customerModelToCustomerEntity(IndividualCustomerModel customerModel);

    IndividualCustomerModel customerEntityToCustomerModel(IndividualCustomerEntity customerEntity);

    IndividualCustomerModel customerRequestToCustomerModel(IndividualCustomerRequest customerRequest);

    IndividualCustomerResponse customerModelToCustomerResponse(IndividualCustomerModel customerModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomer(@MappingTarget IndividualCustomerModel model, IndividualCustomerEntity entity);
}
