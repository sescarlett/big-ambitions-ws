package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.ProductDao;
import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.model.Product;
import com.sscarlett.big_ambitions_companion.model.ProductDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        Product newProduct = new Product();
        newProduct.setProductId(newId);
        newProduct.setName(product.getName());
        newProduct.setCost(product.getCost());
        newProduct.setQuantity(newProduct.getQuantity());
        productDao.insertNewProduct(newProduct);
        for (IdValue newValue : product.getDisplays()) {
            productDao.insertDisplayX(newId, newValue.getId(), newValue.getValue());
        }
        for (Integer i: product.getImporters()) {
            productDao.insertImportX(i, newId);
        }
    }

    /**
     * updates an existing product
     *
     * @param product info
     */
    @Override
    public void patchProduct(ProductDisplay product) {
        // Update product details
        Product newProduct = new Product();
        newProduct.setProductId(product.getProductId());
        newProduct.setName(product.getName());
        newProduct.setCost(product.getCost());
        newProduct.setValue(product.getValue());
        productDao.updateProduct(newProduct);

        // Get current DB info
        List<IdValue> currentDisplays = productDao.selectDisplayX(product.getProductId());
        List<Integer> currentImporters = productDao.selectImportX(product.getProductId());

        // Update or insert displays
        for (IdValue newValue : product.getDisplays()) {
            boolean exists = currentDisplays.stream().anyMatch(display -> Objects.equals(display.getId(), newValue.getId()));
            if (exists) {
                productDao.updateDisplayX(product.getProductId(), newValue.getId(), newValue.getValue());
            } else {
                productDao.insertDisplayX(product.getProductId(), newValue.getId(), newValue.getValue());
            }
        }

        // Remove displays not in the new list
        currentDisplays.stream()
                .filter(display -> product.getDisplays().stream().noneMatch(newValue -> Objects.equals(newValue.getId(), display.getId())))
                .forEach(display -> productDao.removeDisplayX(product.getProductId(), display.getId()));

        // Insert importers not currently existing
        for (Integer importer : product.getImporters()) {
            if (!currentImporters.contains(importer)) {
                productDao.insertImportX(importer, product.getProductId());
            }
        }

        // Remove importers not in the new list
        currentImporters.stream()
                .filter(importer -> !product.getImporters().contains(importer))
                .forEach(importer -> productDao.removeImportX(importer, product.getProductId()));
    }
}
