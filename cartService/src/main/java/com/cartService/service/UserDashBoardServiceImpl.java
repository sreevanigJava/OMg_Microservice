package com.cartService.service;

import java.util.List;


import javax.transaction.Transactional;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartService.dao.MyCartDao;
import com.cartService.dao.MyCartListDao;
import com.cartService.entity.MyCart;
import com.cartService.entity.MyCartLists;
import com.cartService.entity.ProductsEntity;
import com.cartService.exception.CustomException;
import com.cartService.feign.ProductEntityFeign;

@Service
@Transactional
public class UserDashBoardServiceImpl implements UserDashBoardService {

	@Autowired
	MyCartDao cartDao;
	//@Autowired
	//ProductsDao productsDao;
	@Autowired
	MyCartListDao myCartListDao;
	@Autowired
	ProductEntityFeign feign;
	//private static Logger logger = Logger.getLogger(UserDashBoardServiceImpl.class);

	@Override
	public MyCart saveToCart(MyCart myCart, String userName) {

		//logger.info("Save to cart method start..");
		String prodIdToBeAdded = myCart.getEntity().getProdId();
		System.err.println(">>>>>>>>"+prodIdToBeAdded);
		//ProductsEntity productsEntity = productsDao.findByProdId(myCart.getEntity().getProdId());

		List<MyCart> myCarts = cartDao.findByUserName(userName);
		System.out.println(myCarts+">>>>");

		for (int i = 0; i < myCarts.size(); i++) {
			String addedProd = myCarts.get(i).getProdId();
			if (addedProd.equals(prodIdToBeAdded)) {
				throw new CustomException("Product is already exist in your cart");
			}

		}
		myCart.setUserName(userName);
		myCart.setProdId(prodIdToBeAdded);
		//logger.info("End of save to cart method..");
		return cartDao.save(myCart);
	}

	public MyCartLists myCartList(String userName, MyCartLists myCartList) {
		//logger.info("My cart list method start..");
		List<MyCart> cartList = cartDao.findByUserName(userName);
		System.err.println("<<<<<<<<<<<<<<<"+cartList);
		if (cartList.isEmpty()) {
			throw new CustomException("Your Cart is empty , Please add to cart");
		}
		long totalCartPrice = 0;
		System.err.println("my cart excuted>>>>>>>>>>>>>");
		for (MyCart myCart : cartList) {
		ProductsEntity entity=	feign.getProdById(myCart.getProdId());
			totalCartPrice = totalCartPrice +entity.getPrice() * myCart.getQty();
			System.err.println("inside for loop  excuted>>>>>>>>>>>>>");

		}

		if (myCartListDao.existsByUserName(userName)) {
			MyCartLists mycartList = myCartListDao.findByUserName(userName);
			String myCartListId = mycartList.getId();
			// myCartListDao.delete(mycartList);
			myCartList.setId(myCartListId);
		}

		myCartList.setUserName(userName);
		myCartList.setMyCartItems(cartList);
		myCartList.setTotalCost(totalCartPrice);
		//logger.info("My cart list method end..");
		return myCartListDao.save(myCartList);

	}

	public MyCartLists updateMyCart(String cartId, long qty, String userName, MyCartLists myCartList) {

	//	logger.info("Update my cart start..");
		if (!(cartDao.existsByCartId(cartId))) {
			throw new CustomException("Given product does not exist in your Cart");
		}
		MyCart myCart = cartDao.findByCartId(cartId);
		myCart.setQty(qty);
		cartDao.save(myCart);

	//	logger.info("Update my cart end..");
		return this.myCartList(userName, myCartList);
	}

//	public MyCartLists updateMyCart(String cartId, long qty,String userName,MyCartLists myCartList)
//	{
//		//Save Updated quantity to the myCart
//		MyCart myCart= cartDao.findByCartId(cartId);
//		myCart.setQty(qty);
//		cartDao.save(myCart);
//		 
//		//Reflect the changes in myCartList that are made in mycart
//		MyCartLists cartLists=myCartListDao.findByUserName(userName);
//		long totalCartPrice=cartLists.getTotalCost();
//		
//		for(MyCart mycart:cartList)
//		{
//			if(cartLists.getMyCartItems().contains(myCart))
//			
//			totalCartPrice=totalCartPrice+myCart.getEntity().getPrice()*myCart.getQty();
//		}
//		
//		return this.myCartList(userName, myCartList);
//	}
	public MyCartLists getMyCartList(String userName, MyCartLists myCartList) {
		this.myCartList(userName, myCartList);
	//	logger.info("Get my cart list execution..");
		return myCartListDao.findByUserName(userName);
	}

	@Override
	public void deleteCartItem(String itemId, String userName, MyCartLists myCartList) {

	//	logger.info("Delete cart item start..");
		if (!(cartDao.existsById(itemId))) {
			throw new CustomException("Could not delete as it does not exist");
		}
		MyCart myCart = cartDao.findByCartId(itemId);

		cartDao.delete(myCart);
		// this.getMyCartList(userName, myCartList);

	}

	@Override
	public String deleteAllCartItems(String userName, MyCartLists myCartList) {

	//	logger.info("Delete cart item start..");
		// MyCart myCart = cartDao.findByCartId(itemId);
		MyCartLists myCartLists = myCartListDao.findByUserName(userName);
		List<MyCart> myCarts = myCartLists.getMyCartItems();

		MyCart myCart = myCarts.get(0);
		MyCart cart = cartDao.findByCartId(myCart.getCartId());

		System.out.println(myCart);
		cartDao.delete(cart);
//		for(int i=0; i<myCarts.size();i++)
//		{
//			System.out.println(myCarts.get(i)+"<><><><><><><><><><<><><><><><<><>><<<<><>>><");
//		
//		}

		return "Deleted ";

	}
	public String deleteMyCartList(String userName) {

		MyCartLists myCartLists = myCartListDao.findByUserName(userName);
		List<MyCart> myCarts = myCartLists.getMyCartItems();

		for (int i = 0; i < myCarts.size(); i++) {
			System.out.println(myCarts.get(i) + "<><><><><><><><><><<><><><><><<><>><<<<><>>><");
			cartDao.delete(myCarts.get(i));
		}
		// myCartListDao.delete(myCartLists);
		return "My Cart List deleted..";
	}

}
