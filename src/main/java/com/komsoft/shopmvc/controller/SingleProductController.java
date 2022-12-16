package com.komsoft.shopmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.komsoft.shopmvc.model.Product;
import com.komsoft.shopmvc.repository.DBManager;

@Controller
@RequestMapping("/product")
public class SingleProductController {

	DBManager menager = new DBManager();;

	protected String doGet(ModelMap modelMap, @RequestParam String prid) {

		Product product = menager.getOneProduct(prid);

		modelMap.addAttribute("product", product);
		String url = "WEB-INF/views/product.jsp";
		return url;
	}

}
