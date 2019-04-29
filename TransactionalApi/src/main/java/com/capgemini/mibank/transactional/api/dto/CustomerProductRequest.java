package com.capgemini.mibank.transactional.api.dto;

/**
 * @author Jaime Cadena
 */
public class CustomerProductRequest {
    private int product_id;
    private String customer_id;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    
}
