package com.cartService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cartService.entity.AddToCart;
import com.cartService.entity.MyCart;
import com.cartService.entity.MyCartLists;
import com.cartService.entity.ProductsEntity;
import com.cartService.exception.CustomException;
import com.cartService.feign.ProductEntityFeign;
import com.cartService.service.UserDashBoardService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class UserDashBoardController {
	@Autowired
	ProductEntityFeign feign;
	@Autowired
	UserDashBoardService UserDashBoardServiceImpl;

	@PostMapping("/user/addToCart")
	public ResponseEntity<MyCart> saveToCart(@RequestBody AddToCart addToCart, MyCart cart,
			@RequestHeader String userName) {
		//userName="sri1";
		ProductsEntity entity = feign.getProdById(addToCart.getProdId());
		System.out.println(entity+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if (entity.getStatus().equals("not available")) {
			throw new CustomException("Product is not available");
		}
		cart.setEntity(entity);
		cart.setQty(addToCart.getQty());

		//String userName = adminRegistrationServiceImpl.getMyToken(httpServletRequest);
		return new ResponseEntity<MyCart>(UserDashBoardServiceImpl.saveToCart(cart, userName), HttpStatus.OK);
	}
	
	@GetMapping("/user/getMyCartList")
	public ResponseEntity<MyCartLists> getMyCartList(@RequestHeader String userName, MyCartLists cartList) {
		//String userName = adminRegistrationServiceImpl.getMyToken(httpServletRequest);
		
		return new ResponseEntity<MyCartLists>(UserDashBoardServiceImpl.myCartList(userName, cartList), HttpStatus.OK);
	}
	
	@PutMapping("/user/updateMyCart")
	public ResponseEntity<MyCartLists> upDateMyCart(@RequestParam String cartId, @RequestParam long qty,@RequestHeader String userName,MyCartLists myCartList)
	{
		//String userName=adminRegistrationServiceImpl.getMyToken(httpServletRequest);
		
		return new ResponseEntity<MyCartLists>(UserDashBoardServiceImpl.updateMyCart(cartId, qty, userName, myCartList),HttpStatus.OK);
		
	}
	@GetMapping("/user/getAllCartList")
	public ResponseEntity<MyCartLists> getMyCar(@RequestHeader String userName,MyCartLists myCartList)
	{
		//userName=adminRegistrationServiceImpl.getMyToken(httpServletRequest);
		return new ResponseEntity<MyCartLists>(UserDashBoardServiceImpl.getMyCartList(userName,myCartList),HttpStatus.OK);
	}
	@DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteCartItem(@RequestParam String cartId,@RequestHeader String userName,MyCartLists myCartList)  {
		
		//String userName=adminRegistrationServiceImpl.getMyToken(httpServletRequest);

		UserDashBoardServiceImpl.deleteCartItem(cartId,userName,myCartList);
        return new ResponseEntity<String>("s", HttpStatus.OK);
    }
	@DeleteMapping("/user/deleteMyCartList")
    public ResponseEntity<String> deleteMyCartIList(@RequestHeader String userName, MyCartLists cartLists)  {
		
		//String userName=adminRegistrationServiceImpl.getMyToken(httpServletRequest);

	String deleted=	UserDashBoardServiceImpl.deleteAllCartItems(userName,cartLists);
        return new ResponseEntity<String>(deleted, HttpStatus.OK);
    }
	
}
