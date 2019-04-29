package com.capgemini.mibank.transactional.api.repository;

import com.capgemini.mibank.transactional.api.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Jaime Cadena
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    @Query(value = "FROM Customer u WHERE u.customerId = :customerId")
    Customer findByCustomerID(@Param("customerId") String customerId);

}
