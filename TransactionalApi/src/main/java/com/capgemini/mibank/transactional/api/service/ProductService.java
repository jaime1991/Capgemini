package com.capgemini.mibank.transactional.api.service;

import com.capgemini.mibank.transactional.api.dto.CustomerProductDto;
import com.capgemini.mibank.transactional.api.dto.CustomerProductRequest;
import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.entity.Customer;
import com.capgemini.mibank.transactional.api.entity.CustomerProduct;
import com.capgemini.mibank.transactional.api.entity.Product;
import com.capgemini.mibank.transactional.api.helper.ConstantHelper;
import com.capgemini.mibank.transactional.api.helper.UtilHelper;
import com.capgemini.mibank.transactional.api.repository.CustomerProductRepository;
import com.capgemini.mibank.transactional.api.repository.CustomerRepository;
import com.capgemini.mibank.transactional.api.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Jaime Cadena
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerProductRepository customerProductRepository;

    public CustomerProductDto findProductByCustomerAndProductNumber(String customerId, String productNumber) {
        CustomerProduct item = null;
        CustomerProductDto dto = new CustomerProductDto();

        if (!"".equalsIgnoreCase(customerId) && !"".equalsIgnoreCase(productNumber)) {
            item = productRepository.findByCustomerIdAndProductNumber(customerId, productNumber);
        }

        if (item == null) {
            dto = new CustomerProductDto();
        } else {
            dto.setCustomerId(item.getCustomerId().getCustomerId());
            dto.setProductName(item.getProductId().getName());
            dto.setProductNumber(item.getProductNumber());
            dto.setBalance(item.getBalance());
            dto.setCreationDate(item.getCreationDate());
            dto.setTerminationDate(item.getTerminationDate());
            dto.setStatus(item.getStatus());
        }

        return dto;
    }

    public List<CustomerProductDto> findProductByCustomerID(@Param("customerId") String customerId) {
        List<CustomerProduct> cp = productRepository.findByCustomerId(customerId);
        List<CustomerProductDto> listDto = new ArrayList<>();

        cp.stream().map((item) -> {
            CustomerProductDto dto = new CustomerProductDto();
            dto.setCustomerId(item.getCustomerId().getCustomerId());
            dto.setProductName(item.getProductId().getName());
            dto.setProductNumber(item.getProductNumber());
            dto.setBalance(item.getBalance());
            dto.setCreationDate(item.getCreationDate());
            dto.setTerminationDate(item.getTerminationDate());
            dto.setStatus(item.getStatus());
            return dto;
        }).forEach(dto -> listDto.add(dto));

        return listDto;
    }

    public ResponseEntity<ResponseOut> createCustomerProduct(CustomerProductRequest request) {
        Customer customer = customerRepository.findByCustomerID(request.getCustomer_id());
        Optional<Product> product = productRepository.findById(request.getProduct_id());
        Integer id = null;

        if (customer != null && !customer.getCustomerId().equals("") && product.isPresent()) {
            CustomerProduct cp = new CustomerProduct();
            cp.setCustomerId(customer);
            cp.setProductId(product.get());
            cp.setStatus(ConstantHelper.STATE_ACTIVE);
            cp.setCreationDate(new Date());
            cp.setBalance(0);
            cp.setProductNumber("1");
            id = customerProductRepository.save(cp).getId();
            
            System.out.println("id: " + id);
            if (id != null && id > 0) {
                return ResponseEntity.ok(UtilHelper.mapperResponse(
                        id,
                        ConstantHelper.CODE_EXITO));
            } else {
                return ResponseEntity.ok(UtilHelper.mapperResponse(
                        ConstantHelper.CODE_ERROR_CREATE_CUSTOMER_PRODUCT2));
            }
        } else {
            return ResponseEntity.ok(UtilHelper.mapperResponse(
                    ConstantHelper.CODE_ERROR_CREATE_CUSTOMER_PRODUCT));
        }
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
