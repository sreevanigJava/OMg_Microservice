package com.profileservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class D_AgentAdress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id ;
    private String userName;
    private String houseNumber ;
    private String streetName;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private long pincode;
    private boolean currentAddress;
}
