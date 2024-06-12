package com.techwave.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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
public class Demo2Controller {

	// @Autowired can use both autowired and constructor
	IProduct productDao;

	public Demo2Controller(IProduct productDao) {
		super();
		this.productDao = productDao;
	}

	@GetMapping("/getAll2")
	public ResponseEntity<List<Product>> getAll() {
		List<Product> list = productDao.getAll();
		return ResponseEntity.of(Optional.of(list));
	}

	@GetMapping("/getAll2/{id}")
	public ResponseEntity<Object> getAllById(@PathVariable("id") int id) {

		try {
			Product p = productDao.getProduct(id);
			return ResponseEntity.of(Optional.of(p));

		} catch (NoSuchElementException E) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Records found for id: " + id);
		}
	}

	@PostMapping("/addProduct2")
	public ResponseEntity<Object> addProduct(@RequestBody Product P) {
		Product pr = productDao.getByDesc(P.getProductDesc());
		if (pr == null) {
			Product n = productDao.addProduct(P);
			return ResponseEntity.of(Optional.of(n));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already exist");
		}
	}

	@PutMapping("/modify2/{id}")
	public ResponseEntity<Object> update(@RequestBody Product P, @PathVariable("id") int id) {
		Product pr = productDao.getByDesc(P.getProductDesc());
		if (pr == null || pr.getId() == P.getId()) {
			Product u = productDao.updateProduct(P, id);
			return ResponseEntity.of(Optional.of(u));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Cannot update as Product Description already exist");
		}
	}

	@DeleteMapping("/delete2/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		productDao.deleteProduct(id);
		return ResponseEntity.of(Optional.of("1 row deletd with id: " + id));
	}

}
