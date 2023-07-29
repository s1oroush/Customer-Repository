package com.gam.mci.customerRepository.Service;

import com.gam.mci.customerRepository.entities.IndividualCustomerEntity;
import com.gam.mci.customerRepository.mapper.IndividualCustomerMapper;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.repository.IndividualRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.gam.mci.customerRepository.Service.IndividualModerObjectService.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class IndividualCustomerServiceImpTest {

    @InjectMocks
    IndividualCustomerServiceImp serviceImp;

    @Mock
    IndividualRepository repository;

    @Mock
    IndividualCustomerMapper mapper;

    @Test
    void saveIndividualCustomer() {
        given(mapper.customerModelToCustomerEntity(any(IndividualCustomerModel.class)))
                .willReturn(anyIndividualCustomerEntity());
        given(repository.save(any(IndividualCustomerEntity.class)))
                .willReturn(anyIndividualCustomerEntityWithId());
        given(mapper.customerEntityToCustomerModel(any(IndividualCustomerEntity.class)))
                .willReturn(anyIndividualCustomerModelWithId());
        IndividualCustomerModel model = serviceImp.saveIndividualCustomer(anyIndividualCustomerModel());

        assertEquals(anyIndividualCustomerModel().getNationalId(), model.getNationalId());
    }

    @Test
    @DisplayName("given fetchIndividualCustomerByNationalId when repository has object then return model")
    void fetchIndividualCustomerByNationalId() {
        doReturn(Optional.of(anyIndividualCustomerEntity())).when(repository).findByNationalId(anyString());
        given(mapper.customerEntityToCustomerModel(any(IndividualCustomerEntity.class)))
                .willReturn(anyIndividualCustomerModel());

        IndividualCustomerModel model = serviceImp.fetchIndividualCustomerByNationalId(anyNationalId());

        assertEquals(anyIndividualCustomerModel().getNationalId(), model.getNationalId());
    }

    @Test
    @DisplayName("given fetchIndividualCustomerByNationalId when repository has no object then throw exception")
    void fetchIndividualCustomerByNationalId_exception() {
        doReturn(Optional.empty()).when(repository).findById(anyLong());

        assertThrows(ResponseStatusException.class,
                () -> serviceImp.fetchIndividualCustomerByNationalId(anyNationalId()));
    }

    @Test
    void updateIndividualCustomer() {
        given(repository.findByNationalId(anyNationalId())).willReturn(Optional.of(anyIndividualCustomerEntity()));
        given(mapper.customerEntityToCustomerModel(any(IndividualCustomerEntity.class))).willReturn(anyIndividualCustomerModel());

        IndividualCustomerEntity entity = mock(IndividualCustomerEntity.class);
        given(mapper.customerModelToCustomerEntity(any(IndividualCustomerModel.class))).willReturn(entity);

        serviceImp.updateIndividualCustomer(anyNationalId(), anyIndividualCustomerModel());

        verify(entity, atMostOnce()).setId(anyLong());
        verify(repository, atMostOnce()).save(eq(entity));
    }

    @Test
    void patchIndividualCustomer() {
        given(repository.findByNationalId(anyNationalId())).willReturn(Optional.of(anyIndividualCustomerEntity()));
        given(mapper.customerEntityToCustomerModel(any(IndividualCustomerEntity.class))).willReturn(anyIndividualCustomerModel());

        IndividualCustomerEntity entity = mock(IndividualCustomerEntity.class);
        given(mapper.customerModelToCustomerEntity(any(IndividualCustomerModel.class))).willReturn(entity);

        serviceImp.patchIndividualCustomer(anyNationalId() , anyIndividualCustomerModel());

        mapper.updateCustomer(anyIndividualCustomerModel(),entity);

        serviceImp.saveIndividualCustomer(anyIndividualCustomerModel());
    }
}