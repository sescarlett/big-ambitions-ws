package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.ProductDisplay;
import com.sscarlett.big_ambitions_companion.model.ProductList;
import com.sscarlett.big_ambitions_companion.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping(value = "/list", produces = "application/json")
    public List<ProductList> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/new")
    public void postNewProduct(@RequestBody ProductDisplay product) {
        productService.postNewProduct(product);
    }

    @PatchMapping(value = "/update")
    public void patchProduct(@RequestBody ProductDisplay product) {
        productService.patchProduct(product);
    }
}
