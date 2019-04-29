package com.capgemini.mibank.transactional.api.repository;
import com.capgemini.mibank.transactional.api.entity.CustomerProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jaime Cadena
 */
public interface CustomerProductRepository extends PagingAndSortingRepository<CustomerProduct, Integer>{
    
}
