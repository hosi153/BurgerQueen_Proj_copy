package com.example.burgerqueen_proj.product.service;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.category.service.CategoryService;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public Product findProduct(long productId){
        return findVerifyProduct(productId);
    }

    public Product findVerifyProduct(long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));
        return product;
    }


    public List<Product> findAllProductByCategory(long categoryId) {

        Category category = categoryService.findCategoryById(1L);
        List<Product> products = productRepository.findAllByCategory(category);
        return products;

    }

    public List<Product> findAllProduct() {
        List<Product> products = productRepository.findAllByOrderByCategoryAsc();
        return products;
    }


    public Product createProduct(Product product) {
        Category findCategory = categoryService.findVerifiedCategoryByName(product.getCategory().getCategoryName());
        product.setCategory(findCategory);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product findProduct = findVerifyProduct(product.getProductId());
        Category findCategory = categoryService.findVerifiedCategoryByName(product.getCategory().getCategoryName());
        product.setCategory(findCategory);

        Optional.ofNullable(product.getProductName()).ifPresent(findProduct::setProductName);
        Optional.of(product.getProductPrice()).ifPresent(findProduct::setProductPrice);
        Optional.of(product.getProductCount()).ifPresent(findProduct::setProductCount);
        Optional.ofNullable(product.getCategory()).ifPresent(findProduct::setCategory);
        Optional.ofNullable(product.getProductImage()).ifPresent(findProduct::setProductImage);
        Optional.ofNullable(product.getProductStatus()).ifPresent(findProduct::setProductStatus);

        return productRepository.save(findProduct);
    }

    public void deleteProduct(long productId) {
        findVerifyProduct(productId);
        productRepository.deleteById(productId);
    }


}
