package com.gam.mci.customerRepository.mapper;

import com.gam.mci.customerRepository.entities.CorporationCustomerEntity;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.request.CorporationCustomerRequest;
import com.gam.mci.customerRepository.response.CorporationCustomerResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CorporationCustomerMapper {

    CorporationCustomerEntity customerModelToCustomerEntity(CorporationCustomerModel customerModel);

    CorporationCustomerModel customerEntityToCustomerModel(CorporationCustomerEntity customerEntity);

    CorporationCustomerModel customerRequestToCustomerModel(CorporationCustomerRequest customerRequest);

    CorporationCustomerResponse customerModelToCustomerResponse(CorporationCustomerModel customerModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomer(@MappingTarget CorporationCustomerModel model, CorporationCustomerEntity entity);
}
