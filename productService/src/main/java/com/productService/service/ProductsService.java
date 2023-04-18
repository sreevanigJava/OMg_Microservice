package com.productService.service;
import java.util.List;

import com.productService.dto.ProductsDto;
import com.productService.entity.ProductsEntity;
public interface ProductsService {

	ProductsEntity addProducts(ProductsDto productsDto);
	List <ProductsEntity> getAllProducts();
	List<ProductsEntity> getCategory(String catName);
	ProductsEntity findProdByName(String prodName);
	ProductsEntity findByProdId(String prodId);
	ProductsEntity updateProd(ProductsEntity productsEntity);
}