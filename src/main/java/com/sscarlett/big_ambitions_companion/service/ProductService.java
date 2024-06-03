package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Product;
import com.sscarlett.big_ambitions_companion.model.ProductDisplay;

import java.util.List;

public interface ProductService {

    /**
     * selects list of all products
     * @return list
     */
    List<Product> getAllProducts();

    /**
     * creates new product
     * @param product info
     */
    void postNewProduct(ProductDisplay product);
}
