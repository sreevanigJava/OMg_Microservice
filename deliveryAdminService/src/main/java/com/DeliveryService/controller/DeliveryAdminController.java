package com.DeliveryService.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DeliveryService.dao.DeliveryBoyDao;
import com.DeliveryService.entity.DeliveryBoyEntity;
import com.DeliveryService.entity.DeliveryBoyOrders;
import com.DeliveryService.entity.Gmail;
import com.DeliveryService.service.DeliveryBoyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/dAdmin")
@CrossOrigin("*")

public class DeliveryAdminController {
@Autowired
DeliveryBoyService deliveryBoyServiceImpl;
@Autowired
DeliveryBoyDao deliveryBoyDao;

@PostMapping(value = "/save/Agent")
public DeliveryBoyEntity saveAgent(@RequestParam String addingAgentEntity,@RequestParam MultipartFile file, @RequestParam List<MultipartFile> file2 ,@RequestParam MultipartFile img) throws IOException {
	
      ObjectMapper objectMapper = new ObjectMapper();
      DeliveryBoyEntity addingEntity=objectMapper.readValue(addingAgentEntity, DeliveryBoyEntity.class);
    return deliveryBoyServiceImpl.saveAgent(addingEntity, file, file2,img);
}
@GetMapping("/getAgent")
public ResponseEntity<DeliveryBoyEntity> getAgents() {

    return new ResponseEntity(deliveryBoyServiceImpl.getAgent(), HttpStatus.OK);
}
@DeleteMapping("/deleteAgent")
public ResponseEntity<DeliveryBoyEntity> deleteAgent(@RequestParam Integer dAgentid,@RequestHeader String userName,DeliveryBoyEntity deliveryBoyEntity)
{
	//String userName=adminRegistrationServiceImpl.getMyToken(httpServletRequest);
	
  deliveryBoyServiceImpl.delAgent(dAgentid, userName, deliveryBoyEntity);
  return new ResponseEntity("Agent Deleted sucessfully",HttpStatus.OK);
}
@PostMapping("/assignorders")
public DeliveryBoyOrders assign(@RequestBody DeliveryBoyOrders deliveryBoyOrders)
{
	return deliveryBoyServiceImpl.assignorders(deliveryBoyOrders);
}

@PostMapping("/sendgmail")
 
public void send(@RequestBody Gmail gmail,String body,String subject) {

	deliveryBoyServiceImpl.sendmail(gmail, body, subject);
}
@PostMapping("/existByPhnNum")
public boolean checkPhn(@RequestParam String phnNum)
{
	return deliveryBoyDao.existsByPhoneNumber(phnNum);
	
}


}
