package com.techwave.Models.Pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblProduct")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 25,unique = true)
	private String productDesc;
	private double price;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate manufactureDate;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String productDesc, double price, LocalDate manufactureDate) {
		super();
		this.id = id;
		this.productDesc = productDesc;
		this.price = price;
		this.manufactureDate = manufactureDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(LocalDate manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

}
