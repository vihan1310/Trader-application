package com.vihan.usecase.trader.ui.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Trader {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String email;
	private Long balance;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
 	private Timestamp createdAt;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp updatedAt;

	public Trader() {
	}

	public Trader(String name, String email, Long balance, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Trader(String name, String email, Long balance, Timestamp createdAt) {
		super();
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
