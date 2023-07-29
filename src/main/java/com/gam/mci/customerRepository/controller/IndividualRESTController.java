package com.gam.mci.customerRepository.controller;

import com.gam.mci.customerRepository.Service.IndividualCustomerService;
import com.gam.mci.customerRepository.mapper.IndividualCustomerMapper;
import com.gam.mci.customerRepository.model.IndividualCustomerModel;
import com.gam.mci.customerRepository.request.IndividualCustomerRequest;
import com.gam.mci.customerRepository.response.IndividualCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/individual")
public class IndividualRESTController {

    private final IndividualCustomerService individualCustomerService;
    private final IndividualCustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity handlePost(@RequestBody IndividualCustomerRequest request){
        IndividualCustomerModel model = customerMapper.customerRequestToCustomerModel(request);
        IndividualCustomerModel customer =individualCustomerService.saveIndividualCustomer(model);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/individual/" + customer.getNationalId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{nationalId}")
    public IndividualCustomerResponse getIndividualCustomer(@PathVariable ("nationalId") String nationalId)
            throws ResponseStatusException {
                IndividualCustomerModel model = individualCustomerService.fetchIndividualCustomerByNationalId(nationalId);
                return customerMapper.customerModelToCustomerResponse(model);
    }

    @Transactional
    @PutMapping("/{nationalId}")
    public ResponseEntity<IndividualCustomerResponse> updateIndividualCustomerByNationalId(@PathVariable ("nationalId") String  nationalId
            , @RequestBody IndividualCustomerRequest individualCustomerRequest) throws ResponseStatusException {
        IndividualCustomerModel model = customerMapper.customerRequestToCustomerModel(individualCustomerRequest);
        individualCustomerService.updateIndividualCustomer(nationalId,model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{nationalId}")
    public ResponseEntity<IndividualCustomerResponse> updatePartialIndividualCustomerByNationalId(@PathVariable("nationalId") String nationalId
            , @RequestBody IndividualCustomerRequest individualCustomerRequest){
        IndividualCustomerModel model = customerMapper.customerRequestToCustomerModel(individualCustomerRequest);
        individualCustomerService.patchIndividualCustomer(nationalId,model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
