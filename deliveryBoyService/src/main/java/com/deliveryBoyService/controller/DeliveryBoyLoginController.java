package com.deliveryBoyService.controller;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryBoyService.dao.RegistrationDao;
import com.deliveryBoyService.entity.DeliveryBoyLogin;
import com.deliveryBoyService.entity.DeliveryBoyOtp;
import com.deliveryBoyService.entity.OrdereduserOtp;
import com.deliveryBoyService.entity.RegistrationEntity;
import com.deliveryBoyService.entity.ReturnedOrders;
import com.deliveryBoyService.service.DeliveryBoyLoginServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/delivery")

public class DeliveryBoyLoginController {
	
	@Autowired
	 DeliveryBoyLoginServiceImpl deliveryBoyLoginServiceImpl;
	@Autowired
	RegistrationDao registrationDao;
	
	
@PostMapping("/getotp")
public boolean otp(@RequestBody DeliveryBoyLogin deliveryBoyLogin) throws ParseException
{
	return deliveryBoyLoginServiceImpl.sendotp(deliveryBoyLogin.getPhoneNumber());
	
}
@PostMapping("/deliveryBoyLogin")
public boolean login(@RequestBody DeliveryBoyOtp deliveryBoyOtp)throws ParseException
{
	//deliveryBoyLoginServiceImpl.setOtp();
	
	return deliveryBoyLoginServiceImpl.validateOtp(deliveryBoyOtp.getUserOtp());

}
@PostMapping("/getotpforusers")
public boolean userOtp(@RequestParam String userName) throws java.text.ParseException
{ 
	RegistrationEntity registrationEntity=registrationDao.findByUserName(userName);

	return deliveryBoyLoginServiceImpl.otp(registrationEntity.getPhoneNumber()) ;
	
}
@PostMapping("/verifyingotp")
public boolean verify(@RequestBody OrdereduserOtp ordereduserOtp )
{
	return deliveryBoyLoginServiceImpl.validate(ordereduserOtp.getOtp());
	
}
@PostMapping("/cancled")
public ReturnedOrders returnorder(@RequestBody ReturnedOrders returnOrders)
{
	return deliveryBoyLoginServiceImpl.cancledorder(returnOrders );
	
}


}