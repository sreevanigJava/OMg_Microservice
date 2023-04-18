package com.productService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productService.dto.CategoriesDto;
import com.productService.dto.ProductsDto;
import com.productService.entity.CategoriesEntity;
import com.productService.entity.ProductsEntity;
import com.productService.service.CategoriesService;
import com.productService.service.ProductsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/products")
public class AdminDashBoardController {

	@Autowired
	CategoriesService adminDashBoardServiceImpl;
	@Autowired
	ProductsService productsServiceImpl;

	@PostMapping("/addCategory")
	public ResponseEntity<Boolean> addCategory(@RequestBody CategoriesDto categoriesDto) {
		System.err.println("add category called");
		if (adminDashBoardServiceImpl.addCategory(categoriesDto)) {
			return new ResponseEntity(true, HttpStatus.CREATED);
		}

		return new ResponseEntity(false, HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping("/addProducts")
	public ResponseEntity<ProductsEntity> addCategory(@RequestBody @Valid ProductsDto productsDto) {
		System.err.println("add category called");
		return new ResponseEntity(productsServiceImpl.addProducts(productsDto), HttpStatus.CREATED);
	}

	@GetMapping("/getCategories")
	public ResponseEntity<CategoriesEntity> getCategories() {
		System.err.println("getcat called");
		return new ResponseEntity(adminDashBoardServiceImpl.getAllCategories(), HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<ProductsEntity> getProducts() {
		return new ResponseEntity(productsServiceImpl.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/getOneCategory")
	public ResponseEntity<ProductsEntity> getProducts(@RequestParam("catName") String catName) {

		return new ResponseEntity(productsServiceImpl.getCategory(catName), HttpStatus.OK);
	}
	@GetMapping("/getProdByProdName")
	public ResponseEntity<ProductsEntity> getProd(@RequestParam("prodName") String prodName) {
		return new ResponseEntity<ProductsEntity>(productsServiceImpl.findProdByName(prodName), HttpStatus.OK);
	}
	@GetMapping("/getProdByProdId/{prodId}")
	public ResponseEntity<ProductsEntity> getProdById(@PathVariable("prodId") String prodId)
	{
		return new ResponseEntity<ProductsEntity>(productsServiceImpl.findByProdId(prodId),HttpStatus.OK);
		
	}
	@PutMapping("/updateProd")
	public ResponseEntity<ProductsEntity> updateProd(@RequestBody ProductsEntity productsEntity) {
		return new ResponseEntity<ProductsEntity>(productsServiceImpl.updateProd(productsEntity), HttpStatus.OK);
	}


}

