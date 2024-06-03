package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.ProductDao;
import com.sscarlett.big_ambitions_companion.model.Product;
import com.sscarlett.big_ambitions_companion.model.ProductDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * selects list of all products
     *
     * @return list
     */
    @Override
    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    /**
     * creates new product
     *
     * @param product info
     */
    @Override
    public void postNewProduct(ProductDisplay product) {
        Integer newId = productDao.selectMaxId();
        productDao.insertNewProduct(newId, product.getName());
        productDao.insertDisplayX(newId, product.getDisplays());
    }
}
