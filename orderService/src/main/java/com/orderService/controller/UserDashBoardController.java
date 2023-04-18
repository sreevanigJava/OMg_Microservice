package com.orderService.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.orderService.entity.OrdersEntity;
import com.orderService.service.UserDashBoardService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class UserDashBoardController {
	@Autowired
	UserDashBoardService UserDashBoardServiceImpl;
	
	@PostMapping("/user/payWithCod")
	public ResponseEntity<OrdersEntity> placeAOrder(@RequestHeader String userName, OrdersEntity entity)
	{
		//String userName=adminRegistrationServiceImpl.getMyToken(httpServletRequest);
		return new ResponseEntity<OrdersEntity>(UserDashBoardServiceImpl.placeAOrder(userName,entity), HttpStatus.OK);
	}
	@PostMapping("/user/generatePdf")
	public ResponseEntity<String> generatePdf( @RequestParam String ordersId, HttpServletResponse response) throws DocumentException, IOException
	{
		UserDashBoardServiceImpl.geratePdf(response, ordersId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@GetMapping("/getorderid")
	public OrdersEntity getorderid(@RequestParam String orderId) {
		return UserDashBoardServiceImpl.getorderid(orderId);
	}
}
