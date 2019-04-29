package com.capgemini.mibank.transactional.api.service;

import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.dto.TransactionDto;
import com.capgemini.mibank.transactional.api.entity.Customer;
import com.capgemini.mibank.transactional.api.entity.CustomerProduct;
import com.capgemini.mibank.transactional.api.entity.Transaction;
import com.capgemini.mibank.transactional.api.helper.ConstantHelper;
import com.capgemini.mibank.transactional.api.helper.UtilHelper;
import com.capgemini.mibank.transactional.api.repository.CustomerProductRepository;
import com.capgemini.mibank.transactional.api.repository.CustomerRepository;
import com.capgemini.mibank.transactional.api.repository.ProductRepository;
import com.capgemini.mibank.transactional.api.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Jaime Cadena
 */
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerProductRepository customerProductRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<ResponseOut> saveTransaction(Transaction transaction) {
        if (!customerProductRepository.existsById(transaction.getCustomerProductId().getId())) {
            return ResponseEntity.ok(UtilHelper.mapperResponse(
                    ConstantHelper.CODE_NOT_FOUND_CUSTOMER_PRODUCT));
        }

        Transaction newTransaction = transactionRepository.save(transaction);
        if (newTransaction.getId() == null || newTransaction.getId() <= 0) {
            return ResponseEntity.ok(UtilHelper.mapperResponse(
                    ConstantHelper.CODE_ERROR_CREATE_TRANSACTION));
        }

        TransactionDto dto = new TransactionDto();
        CustomerProduct cp = customerProductRepository.findById(newTransaction.getCustomerProductId().getId()).get();
        Customer c = customerRepository.findById(cp.getCustomerId().getId()).get();

        Transaction item = (Transaction) newTransaction;
        dto.setAmount(item.getAmount());
        dto.setChannelId(String.valueOf(item.getChannelId().getId()));
        dto.setCustomerId(c.getCustomerId());
        dto.setDate(item.getDate());
        dto.setProductNumber(cp.getProductNumber());
        dto.setStatus(item.getStatus());
        dto.setTransactionNumber(item.getTransactionNumber());
        dto.setType(String.valueOf(item.getType().getId()));

        return ResponseEntity.ok(UtilHelper.mapperResponse(
                dto,
                ConstantHelper.CODE_EXITO));
    }

    public List<TransactionDto> getLastTransactions(Integer limit, String customerId, String productNumber) {
        List<Transaction> result;
        List<TransactionDto> listResult = new ArrayList<>();

        Pageable p = PageRequest.of(0, limit);
        result = transactionRepository.lastTransaction(customerId, productNumber, p);

        result.stream().map((itemFound) -> {
            TransactionDto dto = new TransactionDto();
            Transaction item = (Transaction) itemFound;
            dto.setAmount(item.getAmount());
            dto.setChannelId(String.valueOf(item.getChannelId().getId()));
            dto.setCustomerId(customerId);
            dto.setDate(item.getDate());
            dto.setProductNumber(productNumber);
            dto.setStatus(item.getStatus());
            dto.setTransactionNumber(item.getTransactionNumber());
            dto.setType(String.valueOf(item.getType().getId()));
            return dto;
        }).forEach((dto) -> {

            listResult.add(dto);

        });
        return listResult;
    }
}
