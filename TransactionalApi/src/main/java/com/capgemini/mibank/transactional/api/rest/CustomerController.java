package com.capgemini.mibank.transactional.api.rest;

import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.entity.Customer;
import com.capgemini.mibank.transactional.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jaime Cadena
 */
@RestController
@RequestMapping("/api/1.0")
public class CustomerController {
    
    @Autowired
    CustomerService customerServices;
    
    @PostMapping(value = "/customer/sign-up")
    public ResponseEntity<ResponseOut> signUp(@RequestBody Customer customer){
        return customerServices.createCustomer(customer);
    }
    
    @GetMapping(value = "/customer/{customerId}/get")
    public Customer getCustomerByCustomerId(@PathVariable(value = "customerId" ) String customerId){
        return customerServices.findByCustomerID(customerId);
    }     
    
}
