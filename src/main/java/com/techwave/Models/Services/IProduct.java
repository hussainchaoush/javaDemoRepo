package com.techwave.Models.Services;

import java.util.List;

import com.techwave.Models.Pojo.Product;

public interface IProduct {
	public List<Product> getAll();

	public Product getProduct(Integer id);

	public Product addProduct(Product p);

	public Product updateProduct(Product p, int id);

	public void deleteProduct(int id);

	public Product getByDesc(String desc);
	
	public Product getByDescIs(String desc);

}
