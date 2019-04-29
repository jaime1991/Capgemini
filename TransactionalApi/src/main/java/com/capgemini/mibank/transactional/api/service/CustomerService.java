package com.capgemini.mibank.transactional.api.service;

import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.entity.Customer;
import com.capgemini.mibank.transactional.api.helper.ConstantHelper;
import com.capgemini.mibank.transactional.api.helper.UtilHelper;
import com.capgemini.mibank.transactional.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

/**
 * @author Jaime Cadena
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<ResponseOut> createCustomer(Customer customer) {
        if (customerRepository.save(customer).getId() > 0) {
            return ResponseEntity.ok(
                    UtilHelper.mapperResponse(ConstantHelper.CODE_EXITO));
        }

        return ResponseEntity.ok(
                UtilHelper.mapperResponse(ConstantHelper.CODE_ERROR_CREATE_CUSTOMER));
    }

    public Customer findByCustomerID(String customerId) {
        if (!"".equalsIgnoreCase(customerId)) {
            return customerRepository.findByCustomerID(customerId);
        } 
        
        return new Customer();
    }
}
