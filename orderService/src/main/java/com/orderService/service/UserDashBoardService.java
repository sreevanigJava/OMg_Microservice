package com.orderService.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.orderService.entity.OrdersEntity;

public interface UserDashBoardService {

	 OrdersEntity placeAOrder(String userName, OrdersEntity entity);

	void geratePdf(HttpServletResponse response, String ordersId)throws DocumentException, IOException ;

	OrdersEntity getorderid(String orderId);

}
