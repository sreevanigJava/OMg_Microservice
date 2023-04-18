package com.profileservice.service;

import java.text.ParseException;


//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profileservice.entity.SmsEntity;
import com.profileservice.exception.CustomException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	private final String ACCOUNT_SID = "AC562c011ab3a76d32b48b25da94506129";
	private final String AUTH_TOKEN = "de246d600e2eb3a44a381a8935c8bf63";
	private final String FROM_NUMBER = "+14344438017";

	@Autowired
	OtpGenerator otpGenerator;

	//private static Logger logger = Logger.getLogger(SmsService.class);

	public void send(SmsEntity sms) throws ParseException {

	//	logger.info("Start of send method..");
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			Integer Otp = otpGenerator.generateOTP(sms.getPhoneNumber());

			System.out.println(sms.getPhoneNumber()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			String msg = "Your OTP -" + Otp + " Please verify this otp in your application for reset password";

			MessageCreator creater = Message.creator(new PhoneNumber(sms.getPhoneNumber()),
					new PhoneNumber(FROM_NUMBER), msg);
			creater.create();
		} catch (Exception ex) {
			System.out.println("Unable to send Otp");
			throw new CustomException("Unable to send Otp ");
		}
	//	logger.info("End of send method..");

	}

}
