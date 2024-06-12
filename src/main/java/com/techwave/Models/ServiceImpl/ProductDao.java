package com.techwave.Models.ServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techwave.Models.Pojo.Product;
import com.techwave.Models.Services.IProduct;
import com.techwave.Models.Services.ProductRepository;

@Service
public class ProductDao implements IProduct{
 
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}

	@Override
	public Product addProduct(Product p) {
		// TODO Auto-generated method stub
		return productRepository.save(p);
	}

	@Override
	public Product updateProduct(Product p, int id) {
		// TODO Auto-generated method stub
		Product old=getProduct(id);
		old.setPrice(p.getPrice());
		old.setProductDesc(p.getProductDesc());
		return productRepository.save(old);

	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product getByDesc(String desc) {
		
		return productRepository.findByProductDescEquals(desc);
	}

	@Override
	public Product getByDescIs(String desc) {
		
		return productRepository.findByProductDescIs(desc);
	}

}
