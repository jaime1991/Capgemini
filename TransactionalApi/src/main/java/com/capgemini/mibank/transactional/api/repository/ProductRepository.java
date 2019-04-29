package com.capgemini.mibank.transactional.api.repository;

import com.capgemini.mibank.transactional.api.entity.CustomerProduct;
import com.capgemini.mibank.transactional.api.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Jaime Cadena
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
    @Query(value ="from CustomerProduct cp where cp.customerId.customerId = :customerId AND cp.productNumber = :productNumber")
    CustomerProduct findByCustomerIdAndProductNumber(@Param("customerId") String customerId,
                                                     @Param("productNumber") String productNumber);
    
    @Query(value ="from CustomerProduct cp where cp.customerId.customerId = :customerId")
    List<CustomerProduct> findByCustomerId( @Param("customerId") String customerId);
}
