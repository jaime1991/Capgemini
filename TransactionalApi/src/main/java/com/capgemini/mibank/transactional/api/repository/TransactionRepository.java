package com.capgemini.mibank.transactional.api.repository;

import com.capgemini.mibank.transactional.api.entity.Transaction;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Jaime Cadena
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {

    @Query(value = "select t from Transaction t "
            + "where t.customerProductId.customerId.customerId = :Id " 
            + " AND t.customerProductId.productNumber = :productNumber "
            + "order by t.date asc")
    List<Transaction> lastTransaction(@Param("Id") String id, @Param("productNumber") String productNumbe, Pageable pageable);

}
