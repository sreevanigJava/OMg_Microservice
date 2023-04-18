package com.DeliveryService.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.DeliveryService.entity.DeliveryBoyEntity;
import com.DeliveryService.entity.DeliveryBoyOrders;
import com.DeliveryService.entity.Gmail;

public interface DeliveryBoyService {
	public DeliveryBoyEntity saveAgent(DeliveryBoyEntity deliveryBoyEntity, MultipartFile file, List<MultipartFile> file2,MultipartFile file3)  throws IOException;
   List<DeliveryBoyEntity>getAgent();
 void delAgent(Integer dAgentid,String userName, DeliveryBoyEntity deliveryBoyEntity);
 public DeliveryBoyOrders assignorders(DeliveryBoyOrders deliveryBoyOrders);
 public void sendmail(Gmail gmail,String body,String subject);
}
