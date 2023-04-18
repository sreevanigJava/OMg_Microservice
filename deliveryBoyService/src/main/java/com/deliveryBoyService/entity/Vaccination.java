package com.deliveryBoyService.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vaccination")
public class Vaccination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vacid;
	private String doseName;
	
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	 private LocalDate date;
	@Lob
	private byte[] vaccinationcertificate;
	
}
