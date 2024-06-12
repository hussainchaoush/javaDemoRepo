package com.techwave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techwave.Models.Pojo.Product;
import com.techwave.Models.ServiceImpl.ProductDao;
import com.techwave.Models.Services.IProduct;

@Controller
public class SampleController {

	@Autowired
	IProduct productDao;
	
	@RequestMapping("/")
	public String Test(Model M) {
		//ProductDao productDao=new ProductDao();
		List<Product> list=productDao.getAll();
		M.addAttribute("list", list);
		return "Test";
	}
}
