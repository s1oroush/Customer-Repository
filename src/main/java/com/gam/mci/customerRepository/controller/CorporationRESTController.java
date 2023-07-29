package com.gam.mci.customerRepository.controller;

import com.gam.mci.customerRepository.Service.CorporationCustomerService;
import com.gam.mci.customerRepository.mapper.CorporationCustomerMapper;
import com.gam.mci.customerRepository.model.CorporationCustomerModel;
import com.gam.mci.customerRepository.request.CorporationCustomerRequest;
import com.gam.mci.customerRepository.response.CorporationCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/corporation")
public class CorporationRESTController {
    private final CorporationCustomerService corporationCustomerService;
    private final CorporationCustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity handlePost(@RequestBody CorporationCustomerRequest request){
        CorporationCustomerModel model = customerMapper.customerRequestToCustomerModel(request);
        CorporationCustomerModel customer =corporationCustomerService.saveCorporationCustomer(model);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/individual/" + customer.getNationalId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{nationalId}")
    public CorporationCustomerResponse getIndividualCustomer(@PathVariable ("nationalId") String nationalId)
            throws ResponseStatusException {
        CorporationCustomerModel model = corporationCustomerService.fetchCorporationCustomerByNationalId(nationalId);
        return customerMapper.customerModelToCustomerResponse(model);
    }

    @PutMapping("/{nationalId}")
    public ResponseEntity<CorporationCustomerResponse> updateCorporationCustomerByNationalId(@PathVariable ("nationalId") String  nationalId
            , @RequestBody CorporationCustomerRequest corporationCustomerRequest) throws ResponseStatusException {
        CorporationCustomerModel model = customerMapper.customerRequestToCustomerModel(corporationCustomerRequest);
        corporationCustomerService.updateCorporationCustomer(nationalId,model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{nationalId}")
    public ResponseEntity<CorporationCustomerResponse> updatePartialCorporationCustomerByNationalId(@PathVariable("nationalId") String nationalId
            , @RequestBody CorporationCustomerRequest corporationCustomerRequest){
        CorporationCustomerModel model = customerMapper.customerRequestToCustomerModel(corporationCustomerRequest);
        corporationCustomerService.patchCorporationCustomer(nationalId,model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
