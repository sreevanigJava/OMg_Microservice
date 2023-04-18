package com.cartService.service;

import com.cartService.entity.MyCart;
import com.cartService.entity.MyCartLists;

public interface UserDashBoardService {
	
	MyCart saveToCart(MyCart myCart,String userId);

	MyCartLists myCartList(String userName, MyCartLists myCartList);
	MyCartLists updateMyCart(String cartId, long qty,String userName,MyCartLists myCartList);
	 MyCartLists getMyCartList(String userName,MyCartLists myCartList);
	 String deleteMyCartList(String userName) ;
	void deleteCartItem(String itemId, String userName,MyCartLists myCartList );
   String deleteAllCartItems( String userName, MyCartLists myCartList);
}
