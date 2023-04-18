package com.deliveryBoyService.service;

import java.text.ParseException;

import com.deliveryBoyService.entity.ReturnedOrders;

public interface DeliveryBoyLoginService {
public boolean  sendotp(String phoneNumber) throws ParseException; 
public boolean validateOtp(int userEnteredotp);
public boolean otp(String phoneNumber) throws ParseException;
public boolean validate(int userOtp);
public ReturnedOrders cancledorder(ReturnedOrders returnOrders);


}
