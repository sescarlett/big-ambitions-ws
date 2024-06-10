package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.ProductDisplay;
import com.sscarlett.big_ambitions_companion.model.ProductList;

import java.util.List;

public interface ProductService {

    /**
     * selects list of all products
     * @return list
     */
    List<ProductList> getAllProducts();

    /**
     * creates new product
     * @param product info
     */
    void postNewProduct(ProductDisplay product);

    /**
     * updates an existing product
     * @param product info
     */
    void patchProduct(ProductDisplay product);
}
