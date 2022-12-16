package com.komsoft.shopmvc.controller;

import com.komsoft.shopmvc.managers.ProductEntityManager;
import com.komsoft.shopmvc.model.Product;
import com.komsoft.shopmvc.repository.DBManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

	// DBManager menager = new DBManager();
	ProductEntityManager manager = new ProductEntityManager();

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap, @RequestParam(required = false) String category) {

		List<Product> products = manager.getAll(category);
		//List<Product> products = menager.getAllProduct(category);

		modelMap.addAttribute("products", products);
		String url = "products";

		return url;
	}

}
