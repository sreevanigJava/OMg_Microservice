package com.cartService.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.cartService.idGenerator.CustomeIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="myCart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCart {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "user_sql")
    @GenericGenerator(name="user_sql", strategy="com.cartService.idGenerator.CustomeIdGenerator", parameters = {
            @Parameter(name=CustomeIdGenerator.INCREMENT_PARAM, value="1"),
            @Parameter(name=CustomeIdGenerator.VALUE_PREFIX_PARAMAETER, value="CART"),
            @Parameter(name=CustomeIdGenerator.NUMBER_FORMAT_PARAMETER, value="%03d")
    })
	private String cartId;
	private String userName;
	private String prodId;
	@Transient
	@ManyToOne
	@JoinColumn(name="prodId")
	private ProductsEntity entity;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date AddOnDate;
	private double price;
	private long qty;
}
