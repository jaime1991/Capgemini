package com.capgemini.mibank.transactional.api.rest;

import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.dto.TransactionDto;
import com.capgemini.mibank.transactional.api.entity.Transaction;
import com.capgemini.mibank.transactional.api.service.TransactionService;
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
public class TransactionController {

    @Autowired
    TransactionService transactionServices;

    @PostMapping(value = "/transaction/save")
    public ResponseEntity<ResponseOut> saveTransaction(@RequestBody Transaction request){
        return transactionServices.saveTransaction(request);
    }
    
    @GetMapping(value = "/transaction/getLast/{limit}/{customerId}/{productNumber}")
    public Iterable<TransactionDto> getProductsByCustomerId(@PathVariable(value = "limit") Integer limit, @PathVariable(value = "customerId") String customerId, @PathVariable(value = "productNumber") String productNumber) {
        return transactionServices.getLastTransactions(limit, customerId, productNumber);
    }
}
