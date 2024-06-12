package com.techwave.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techwave.Models.Pojo.Product;
import com.techwave.Models.Services.IProduct;

@RestController
@CrossOrigin
public class PractiseDemoController3 {

	@Autowired
	IProduct productDao;

	@GetMapping("/getAllp")
	public ResponseEntity<List<Product>> getallp() {
		List<Product> plist = productDao.getAll();
		return ResponseEntity.of(Optional.of(plist));
	}

	@GetMapping("/getAllp/{id}")
	public ResponseEntity<Object> getallbyid(@PathVariable("id") int id) {
		try {
			Product p = productDao.getProduct(id);
			return ResponseEntity.of(Optional.of(p));
		}

		catch (NoSuchElementException E) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Product found for id: " + id);
		}
	}

	@PostMapping("/addProductp")
	public ResponseEntity<Object> addproductp(@RequestBody Product P) {
		Product pcheck = productDao.getByDescIs(P.getProductDesc());
		if (pcheck == null) {
			Product p = productDao.addProduct(P);
			return ResponseEntity.of(Optional.of(p));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Product Description already exsit");
		}

	}

	@PutMapping("/modifyp/{id}")
	public ResponseEntity<Object> modifyp(@RequestBody Product PInput, @PathVariable("id") int id) {
		Product pexist = productDao.getByDescIs(PInput.getProductDesc());
		if (pexist == null || pexist.getId() == PInput.getId()) {
			Product pupdated = productDao.updateProduct(PInput, id);
			return ResponseEntity.of(Optional.of(pupdated));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot update as ProductDesc already exist");
		}
	}
	
	@DeleteMapping("/deletep/{id}")
	public ResponseEntity<Object> deletep(@PathVariable("id")int id){
		productDao.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body("1 Row deleted with id: "+id);
	}

}
