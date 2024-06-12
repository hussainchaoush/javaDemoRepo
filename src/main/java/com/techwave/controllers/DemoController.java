package com.techwave.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techwave.Models.Pojo.Product;
import com.techwave.Models.ServiceImpl.ProductDao;
import com.techwave.Models.Services.IProduct;

@CrossOrigin
@RestController
public class DemoController {

	@Autowired
	IProduct productDao;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Product> list = productDao.getAll();
		if (list.isEmpty()) {
			map.clear();
			map.put("status", 0);
			map.put("message", "No Records found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		} else {
			map.clear();
			map.put("status", 1);
			map.put("data", list);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

	}

	@GetMapping("/getAll/{id}")
	public ResponseEntity<?> getAll(@PathVariable("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product p = productDao.getProduct(id);
			map.clear();
			map.put("status", 1);
			map.put("data", p);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception E) {
			map.clear();
			map.put("status", 0);
			map.put("message", "No Records found with id: " + id);
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product p) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			productDao.addProduct(p);
			map.put("status", 1);
			map.put("data", p);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (Exception e) {
			String s;
			if (e.getMessage().contains("SYSTEM.UK_LE5WC5DC4KJ8A604C6H2A3S3D")) {
				s = "Cannot have duplicate Product Description";
			} else {
				s = "could not insert data";
			}
			map.clear();
			map.put("status", 0);
			map.put("message", s);
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/modify/{id}")
	public ResponseEntity<?> modifyProduct(@RequestBody Product p, @PathVariable("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product P=productDao.updateProduct(p, id);
			map.clear();
			map.put("status", 1);
			map.put("data", P);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (NoSuchElementException E) {
			map.clear();
			map.put("status", 0);
			map.put("message", "No record found product with the id: " + id);
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

		} catch (DataIntegrityViolationException E) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Cannot have duplicate Product Description");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		
		}catch (Exception E) {
			map.clear();
			map.put("status", 0);
			map.put("message", E.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletProduct(@PathVariable("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product product=productDao.getProduct(id);
			productDao.deleteProduct(id);
			map.clear();
			map.put("status", 1);
			map.put("message", "1 Row deleted sucessfully....with id: "+ id);
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		catch(Exception E) {
			map.clear();
			map.put("status", 0);
			map.put("message", "No Records found with id: " + id);
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
		
	}

}
