package com.gam.mci.customerRepository.Service;

import com.gam.mci.customerRepository.entities.CorporationCustomerEntity;
import com.gam.mci.customerRepository.mapper.CorporationCustomerMapper;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.repository.CorporationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.gam.mci.customerRepository.Service.CorporationModerObjectService.*;
import static com.gam.mci.customerRepository.Service.CorporationModerObjectService.anyCorporationCustomerModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CorporationCustomerServiceImpTest {

    @InjectMocks
    CorporationCustomerServiceImp serviceImp;

    @Mock
    CorporationRepository repository;

    @Mock
    CorporationCustomerMapper mapper;


    @Test
    void saveCorporationCustomer() {
        given(mapper.customerModelToCustomerEntity(any(CorporationCustomerModel.class)))
                .willReturn(anyCorporationCustomerEntity());
        given(repository.save(any(CorporationCustomerEntity.class)))
                .willReturn(anyCorporationCustomerEntityWithId());
        given(mapper.customerEntityToCustomerModel(any(CorporationCustomerEntity.class)))
                .willReturn(anyCorporationCustomerModelWithId());

        CorporationCustomerModel model = serviceImp.saveCorporationCustomer(anyCorporationCustomerModel());

        assertEquals(anyCorporationCustomerModel().getNationalId(), model.getNationalId());
    }

    @Test
    @DisplayName("given fetchCorporationCustomerByNationalId when repository has object then return model")
    void fetchCorporationCustomerByNationalId() {
        doReturn(Optional.of(anyCorporationCustomerEntity())).when(repository).findByNationalId(anyString());
        given(mapper.customerEntityToCustomerModel(any(CorporationCustomerEntity.class)))
                .willReturn(anyCorporationCustomerModel());

        CorporationCustomerModel model = serviceImp.fetchCorporationCustomerByNationalId(anyNationalId());

        assertEquals(anyCorporationCustomerModel().getNationalId(), model.getNationalId());
    }

    @Test
    @DisplayName("given fetchCorporationCustomerByNationalId when repository has no object then throw exception")
    void fetchCorporationCustomerByNationalId_exception() {
        doReturn(Optional.empty()).when(repository).findById(anyLong());

        assertThrows(ResponseStatusException.class,
                () -> serviceImp.fetchCorporationCustomerByNationalId(anyNationalId()));
    }

    @Test
    void updateCorporationCustomer() {
        given(repository.findByNationalId(anyNationalId())).willReturn(Optional.of(anyCorporationCustomerEntity()));
        given(mapper.customerEntityToCustomerModel(any(CorporationCustomerEntity.class))).willReturn(anyCorporationCustomerModel());

        CorporationCustomerEntity entity = mock(CorporationCustomerEntity.class);
        given(mapper.customerModelToCustomerEntity(any(CorporationCustomerModel.class))).willReturn(entity);

        serviceImp.updateCorporationCustomer(anyNationalId(), anyCorporationCustomerModel());

        verify(entity, atMostOnce()).setId(anyLong());
        verify(repository, atMostOnce()).save(eq(entity));

    }

    @Test
    void patchCorporationCustomer() {
        given(repository.findByNationalId(anyNationalId())).willReturn(Optional.of(anyCorporationCustomerEntity()));
        given(mapper.customerEntityToCustomerModel(any(CorporationCustomerEntity.class))).willReturn(anyCorporationCustomerModel());

        CorporationCustomerEntity entity = mock(CorporationCustomerEntity.class);
        given(mapper.customerModelToCustomerEntity(any(CorporationCustomerModel.class))).willReturn(entity);

        serviceImp.patchCorporationCustomer(anyNationalId() , anyCorporationCustomerModel());

        mapper.updateCustomer(anyCorporationCustomerModel(),entity);

        serviceImp.saveCorporationCustomer(anyCorporationCustomerModel());

    }
}


