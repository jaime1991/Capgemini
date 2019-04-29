package com.capgemini.mibank.transactional.api.rest;

import com.capgemini.mibank.transactional.api.dto.CustomerProductDto;
import com.capgemini.mibank.transactional.api.dto.CustomerProductRequest;
import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.entity.Product;
import com.capgemini.mibank.transactional.api.service.ProductService;
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
public class ProductController {

    @Autowired
    ProductService productServices;

    @GetMapping(value = "/product/{customerId}/{productNumber}/get")
    public CustomerProductDto getProductsByCustomerId(@PathVariable(value = "customerId" ) String customerId, @PathVariable(value = "productNumber" ) String productNumber){
        return productServices.findProductByCustomerAndProductNumber(customerId, productNumber);
    }
    
    @GetMapping(value = "/product/{customerId}/get")
    public Iterable<CustomerProductDto> getProductsByCustomerId(@PathVariable(value = "customerId" ) String customerId){
        return productServices.findProductByCustomerID(customerId);
    }  
    
    @PostMapping(value = "/product/createConstumerProduct")
    public ResponseEntity<ResponseOut> createCustomerProduct(@RequestBody CustomerProductRequest request){
        return productServices.createCustomerProduct(request);
    }
    
    @GetMapping(value = "/product/get")
    public Iterable<Product> getAllProducts() {
        return productServices.getAllProducts();
    }
}
